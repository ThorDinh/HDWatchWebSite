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

	//Quản lí yêu thích
	$scope.favorite = {
		items: [],
		//Thêm sản phẩm vào yêu thích
		add(id) {
			// Kiểm tra xem người dùng đã đăng nhập chưa
			AuthService.checkAuthentication()
				.then(response => {
					const loggedIn = response.data.authenticated;
					if (!loggedIn) {
						// Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
						window.location.href = '/login/form';
					} else {
						var item = this.items.find(item => item.id == id);
						// Nếu sản phẩm đã có trong danh sách yêu thích
						if (item) {
							item.favorite = !item.favorite; // Toggle trạng thái yêu thích
							var index = this.items.findIndex(item => item.id == id);
							this.items.splice(index, 1);
							this.saveToLocalStorage();
						} else {
							// Nếu sản phẩm chưa có trong danh sách yêu thích
							$http.get(`/rest/products/${id}`).then(resp => {
								resp.data.favorite = true;
								this.items.push(resp.data);
								this.saveToLocalStorage();
							});
						}
					}
				})
				.catch(error => {
					console.error('Lỗi khi kiểm tra xác thực: ', error);
				});
		},
		// Xóa sản phẩm khỏi danh sách yêu thích
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		// Tính tổng số mặt hàng trong danh sách yêu thích
		get count() {
			return this.items
				.map(item => item.favorite)
				.reduce((total, favorite) => total += favorite, 0);
		},
		// Lưu danh sách yêu thích vào Local Storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("favorite", json);
		},
		// Đọc danh sách yêu thích từ Local Storage
		loadFromLocalStorage() {
			var json = localStorage.getItem("favorite");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$scope.favorite.loadFromLocalStorage();
}]);