let urlProduct = "/admin/rest/products";
app.controller("product-ctrl", function ($scope, $http) {
    $scope.products = [];
    $scope.brands = [];
    $scope.categories = [];
    $scope.product = {};
    $scope.chon = false;
    $scope.pageSize = 10;
    
    //Phân trang
     $scope.next = function(){
        if($scope.start < $scope.products.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.products.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }
    
    //Lấy toàn bộ sản phẩm
    $http.get(urlProduct).then(resp => {
        $scope.products = resp.data;
    });
    
    //Lấy danh sách danh mục
    $http.get(urlCategory).then(resp => {
        $scope.categories = resp.data;
    });
    
    //Lấy danh sách thương hiệu
    $http.get(urlBrand).then(resp => {
        $scope.brands = resp.data;
    });
    
    //Lấy 1 sản phẩm
    $scope.edit = function(id){
        var url = `${urlProduct}/${id}`;
        $http.get(url).then(resp => {
            $scope.product = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại sản phẩm "+$scope.cate.id);
            }
        });
        $scope.start = 0;
    };
    
	//Lấy hình ảnh
	$scope.getImageName = function(productImages) {
		var images = productImages.split(',');
		var imageName = images[0].replace(/"/g, '').replace('[', '').replace(']', '');
		return imageName;
	};
    
    //Cập nhật sản phẩm
    $scope.update = function(id){
        var url = `${urlProduct}/${id}`;
        var data = angular.copy($scope.cate);
        var index = $scope.products.findIndex(c => c.id == id);
        $http.put(url, data).then(resp => {
            $scope.products[index] = resp.data;
            alert("Cập nhật sản phẩm thành công!");
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại sản phẩm "+$scope.product.id);
            }
            alert("Cập nhật sản phẩm thất bại!");
        });
    };
    
    //Tạo sản phẩm
    $scope.create = function(){
        var data = angular.copy($scope.product);
        $http.post(urlProduct, data).then(resp => {
            $scope.products.push(resp.data);
            $scope.reset();
            alert("Thêm mới sản phẩm thành công!");
        }).catch(error => {   
            if(error.status == 400){
                alert("Đã tồn tại sản phẩm "+$scope.product.id);
            }
            alert("Cập nhật sản phẩm thất bại!");
            console.log("error ",error);
        });
    };
    
    //Làm mới sản phẩm
    $scope.reset = function(){
        $scope.product = {};
        $scope.chon = false;
    }
    
    //Xóa sản phẩm
	$scope.showDeleteModal = function (productId) {
	    $scope.productToDeleteId = productId;
	    $('#confirmDeleteModal').modal('show');
	};
    $scope.delete = function(id){
        var url = `${urlProduct}/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.products.findIndex(c => c.id == id);
            $scope.products.splice(index, 1);
            $scope.reset();
            $('#confirmDeleteModal').modal('hide');
            alert("Xóa sản phẩm thành công!");
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại sản phẩm "+$scope.product.id);
            } else if(error.status == 500){
				alert("Không thể xóa sản phẩm này");
			}
			alert("Xóa sản phẩm thất bại!");
        });
    };
    
    //Tìm kiếm
    $scope.search = function(kw){
        if(kw != null){
            var url = `${urlProduct}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.products = resp.data;
            });
        }
    };
    
    $scope.reset();
});