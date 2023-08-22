const app = angular.module("my-app", []);

app.controller("shopping-cart-ctrl", ['$scope', '$http', 'AuthService', function($scope, $http, AuthService) {
	$scope.loggedIn = false; // Trạng thái đăng nhập mặc định

	// Kiểm tra trạng thái xác thực khi trang được tải hoặc khi cần thiết
	AuthService.checkAuthentication()
		.then(function(response) {
			$scope.loggedIn = response.data.authenticated;
		})
		.catch(function(error) {
			console.error('Lỗi khi kiểm tra xác thực: ', error);
		});

	//Quản lí giỏ hàng
	$scope.cart = {
		items: [],
		//Thêm sản phẩm vào giỏ hàng
		add(id) {
			var item = this.items.find(item => item.id == id);
			//nếu giỏ hàng có sản phẩm
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			} else {
				// Nếu chưa có sản phẩm trong giỏ hàng
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		// Xóa sản phẩm khỏi giỏ hàng
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		// Xóa toàn bộ sản phẩm trong giỏ hàng
		clear() {
			this.items = [];
			this.saveToLocalStorage();
		},
		//Tính thành tiền của 1 sản phẩm
		amt_of(item) { },
		//Tính tổng các mặt hàng trong giỏ hàng
		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		// Tổng thành tiền các mặt hàng trong giỏ hàng
		get amount() {
			return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
		},
		//Lưu giỏ hàng vào Local Storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		//Đọc giỏ hàng từ Local Storage
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$scope.cart.loadFromLocalStorage();

	// Thanh toán và tạo đơn hàng
	$scope.order = {
		accounts: { username: $("#username").text() },
		createDate: new Date(),
		address: "",
		status: "Đang xác nhận",
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					products: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			})
		},
		purchase() {
			var order = angular.copy(this);
			//Thực hiện đặt hàng
			if (!order.paymentMethod) {
				alert("Vui lòng chọn phương thức thanh toán!");
				return;
			}
			//Thanh toán bằng tiền mặt
			$http.post("/rest/orders", order).then(resp => {
				if (resp.data.paymentMethod === "cod") {
					$scope.cart.clear();
					alert("Đặt hàng thành công! Vui lòng thanh toán khi nhận hàng.");
					location.href = "/order/detail/" + resp.data.id;
					// Thanh toán online bằng thẻ tín dụng
				} else if (resp.data.paymentMethod === "online") {
					var prices = $scope.getTotalPrice()
					location.href = "/submitOrder?amount=" + prices + "&id=" + resp.data.id;
					$scope.cart.clear();
				}
			}).catch(error => {
				alert("Đặt hàng thất bại!");
				console.log(error)

			})
		}
	}

	//Thành tiền
	$scope.getTotalPrice = function() {
		var total = 0;
		angular.forEach($scope.cart.items, function(item) {
			total += item.qty * item.price;
		});
		return total;
	};

	//Lấy hình ảnh ở giỏ hàng
	$scope.getImageName = function(productImages) {
		var images = productImages.split(',');
		var imageName = images[0].replace(/"/g, '').replace('[', '').replace(']', '');
		return imageName;
	};

	// Yêu thích
	$scope.favorite = {
		favoriteId: '',
		items: [],
		add(id) {

			AuthService.checkAuthentication()
				.then(response => {
					const loggedIn = response.data;
					if (loggedIn.authenticated == false) {
						window.location.href = '/login/form';
					} else {
						var item = this.items.find(item => item.productId == id);
						if (item) {
							const favoritedetailId = item.id; // Assuming the ID of the Favoritedetails
							this.remove(favoritedetailId);
						} else {
							const favoritedetails = {
								favoriteId: this.favoriteId, // Assuming you have favoriteId set
								productId: id
							};
							$http.post('/rest/favorites', favoritedetails).then(response => {
								response.data.isLiked = true;
								this.items.push(response.data);
							});
						}
					}

				})
				.catch(error => {
					console.error('Error checking authentication: ', error);
				});
			console.log(this.items)

		},
		get count() {
			return this.items.length;
		}
	};

	//Tìm sản phẩm đã yêu thích theo productId
	$scope.isProductLiked = function(productId) {
		const item = $scope.favorite.items.find(item => item.productId === productId);
		return item ? item.isLiked : false;
	};

	$scope.favorite.remove = function(id) {
		$http.delete(`/rest/favorites/${id}`).then(() => {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
		});
	}

	// Load favorites from the server
	$scope.favorite.loadFromDatabase = function() { // Changed the method name
		AuthService.checkAuthentication()
			.then(response => {
				const loggedIn = response.data;
				if (loggedIn) {
					const accountId = loggedIn.username;

					$http.get(`/rest/favorites/user/${accountId}`).then(resp => {
						if (resp.data.length > 0) {
							this.favoriteId = resp.data[0].id;
							$http.get(`/rest/favorites/details/${this.favoriteId}`).then(resp => {
								const itemsWithIsLiked = resp.data.map(item => ({
									...item,
									isLiked: true // Set isLiked to true for each product
								}));
								this.items = itemsWithIsLiked;
							});
						}
					});
				}
			})
			.catch(error => {
				console.error('Error checking authentication: ', error);
			});
	};

	// Load favorites from local storage
	$scope.favorite.loadFromDatabase(); // Calling the correct method

}]);