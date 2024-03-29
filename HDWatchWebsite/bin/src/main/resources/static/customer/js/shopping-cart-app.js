const app = angular.module("my-app",[]);
app.controller("shopping-cart-ctrl",['$scope', '$http', 'AuthService', function($scope,$http, AuthService) {
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
		items:[],
		//Thêm sản phẩm vào giỏ hàng
		add(id){
			var item = this.items.find(item => item.id == id);
			//nếu giỏ hàng có sản phẩm
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			//nếu không có sản phẩm
			} else {
				$http.get(`/rest/products/${id}`).then(resp =>{
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		//Xóa sản phẩm ra giỏ hàng
		remove(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index,1);
			this.saveToLocalStorage();
		},
		//Xóa toàn bộ sản phẩm ra giỏ hàng
		clear(){
			this.items = [];
			this.saveToLocalStorage();
		},
		//Tính thành tiền của 1 sản phẩm
		amt_of(item){},
		//Tính tổng các mặt hàng trong giỏ hàng
		get count(){
			return this.items
			.map(item=>item.qty)
			.reduce((total,qty)=> total +=qty,0);
		},
		//Tổng thành tiền các mặt hàng trong giỏ
		get amount(){
			return this.items
			.map(item => item.qty * item.price)
			.reduce((total,qty)=> total += qty,0);
		},
		//Lưu giỏ hàng vào Local Storage
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
		},
		//Đọc giỏ hàng từ Local Storage
		loadFromLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}
	
	$scope.cart.loadFromLocalStorage();
	
	$scope.order = {
		createDate : new Date(),
		address:"",
		account: {username: $("#username").text()},
		get orderDetail(){
			return $scope.cart.items.map(item => {
				return {
					product:{id: item.id},
					price: item.price,
					quantity: item.qty 
				}
			})
		},
		purchase(){
			var order = angular.copy(this);
			//Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp =>{
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi!")
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
  		var imageName = images[0].replace(/"/g, '').replace('[','').replace(']','');
  		return imageName;
	};

}]);