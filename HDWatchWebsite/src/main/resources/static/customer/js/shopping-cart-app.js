const app = angular.module("my-app", []);

app.controller("shopping-cart-ctrl", ['$scope', '$http', 'AuthService', function($scope, $http, AuthService) {
	$scope.loggedIn = false; // Default state

	// Check authentication status on page load or when needed
	AuthService.checkAuthentication()
		.then(function(response) {
			$scope.loggedIn = response.data.authenticated;
		})
		.catch(function(error) {
			console.error('Error checking authentication:', error);
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
				//nếu không có sản phẩm
			} else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		//Xóa sản phẩm ra giỏ hàng
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		//Xóa toàn bộ sản phẩm ra giỏ hàng
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
		//Tổng thành tiền các mặt hàng trong giỏ
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

	//Thanh toán và xuất order
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
			$http.post("/rest/orders", order).then(resp => {
				 if (resp.data.paymentMethod === "cod") {
                    $scope.cart.clear();
                    alert("Đặt hàng thành công! Vui lòng thanh toán khi nhận hàng.");
                    location.href = "/order/detail/" + resp.data.id;
              }else
               if(resp.data.paymentMethod === "online"){
				  var prices = $scope.getTotalPrice()
				  $scope.cart.clear();
				  location.href = "/submitOrder?amount=" + prices + "&id=" + resp.data.id;
				  
			  }
			}).catch(error => {
				alert(order);
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
			// Check if the user is logged in
			AuthService.checkAuthentication()
				.then(response => {
					const loggedIn = response.data.authenticated;
					if (!loggedIn) {
						// Redirect to the login form if not logged in
						// Assuming your login page URL is '/login'
						window.location.href = '/login/form';
					} else {
						var item = this.items.find(item => item.id == id);
						// If the item is already in the list
						if (item) {
							item.favorite = !item.favorite; // Toggle the favorite status
							var index = this.items.findIndex(item => item.id == id);
							this.items.splice(index, 1);
							this.saveToLocalStorage();
						} else {
							// If the item is not in the list
							$http.get(`/rest/products/${id}`).then(resp => {
								resp.data.favorite = true;
								this.items.push(resp.data);
								this.saveToLocalStorage();
							});
						}
					}
				})
				.catch(error => {
					console.error('Error checking authentication:', error);
				});
		},
		//Xóa sản phẩm ra danh sách yêu thích
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		//Tính tổng các mặt hàng trong giỏ hàng
		get count() {
			return this.items
				.map(item => item.favorite)
				.reduce((total, favorite) => total += favorite, 0);
		},
		//Xóa toàn bộ sản phẩm ra giỏ hàng
		clear() {
			this.items = [];
			this.saveToLocalStorage();
		},
		//Lưu giỏ hàng vào Local Storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("favorite", json);
		},
		//Đọc giỏ hàng từ Local Storage
		loadFromLocalStorage() {
			var json = localStorage.getItem("favorite");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$scope.favorite.loadFromLocalStorage();
}]);