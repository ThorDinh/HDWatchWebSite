// Biến lưu đường dẫn API của sản phẩm
let urlProduct = "/rest/products";
app.controller("product-ctrl", function($scope, $http) {
	$scope.products = [];
	$scope.brands = [];
	$scope.categories = [];
	$scope.product = {
		productimages: ["cloud-upload.jpg"]
	};
	
	$scope.chon = false;
	$scope.pageSize = 10;
	$scope.start = 0;

	//Phân trang
	$scope.next = function() {
		if ($scope.start < $scope.products.length - $scope.pageSize) {
			$scope.start += $scope.pageSize;
		}
	}
	$scope.prev = function() {
		if ($scope.start > 0) {
			$scope.start -= $scope.pageSize;
		}
	}
	$scope.last = function() {
		var sotrang = Math.ceil($scope.products.length / $scope.pageSize);
		$scope.start = (sotrang - 1) * $scope.pageSize;
	}
	$scope.first = function() {
		$scope.start = 0;
	}

	// Lấy toàn bộ sản phẩm từ API
	$http.get(urlProduct).then(resp => {
		$scope.products = resp.data;
	});

	// Lấy danh sách danh mục từ API
	$http.get(urlCategory).then(resp => {
		$scope.categories = resp.data;
	});

	// Lấy danh sách thương hiệu từ API
	$http.get(urlBrand).then(resp => {
		$scope.brands = resp.data;
	});

	// Lấy thông tin của một sản phẩm
	$scope.edit = function(id) {
		var url = `${urlProduct}/${id}`;
		$http.get(url).then(resp => {
			$scope.product = resp.data;
			$scope.product.productimages = JSON.parse($scope.product.productimages);
			$scope.selectedImage = $scope.product.productimages[0];
			$scope.chon = true;
		}).catch(error => {
			if (error.status == 404) {
				alert("Không tồn tại sản phẩm " + $scope.cate.id);
			}
		});
		$scope.start = 0;
	};
	
	//Đổi hình ảnh
	$scope.selectedImage = $scope.product.productimages[0];
	$scope.changeImage = function(image) {
        $scope.selectedImage = image;
    };
    
    //Sửa hình ảnh
	$scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
        }).then(resp => {
			$scope.selectedImage = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	// Cập nhật thông tin sản phẩm
	$scope.update = function(id) {
		var url = `${urlProduct}/${id}`;
		var data = angular.copy($scope.product);
		var index = $scope.products.findIndex(c => c.id == id);
		$http.put(url, data).then(resp => {
			$scope.products[index] = resp.data;
			alert("Cập nhật sản phẩm thành công!");
		}).catch(error => {
			if (error.status == 404) {
				alert("Không tồn tại sản phẩm " + $scope.product.id);
			}
			alert("Cập nhật sản phẩm thất bại!");
		});
	};

	// Thêm mới sản phẩm
	$scope.create = function() {
		var data = angular.copy($scope.product);
		$http.post(urlProduct, data).then(resp => {
			$scope.products.push(resp.data);
			$scope.reset();
			alert("Thêm mới sản phẩm thành công!");
		}).catch(error => {
			if (error.status == 400) {
				alert("Đã tồn tại sản phẩm " + $scope.product.id);
			}
			alert("Cập nhật sản phẩm thất bại!");
			console.log("error ", error);
		});
	};

	// Làm mới thông tin sản phẩm
	$scope.reset = function() {
		$scope.product = {};
		$scope.product.productimages = ["cloud-upload.jpg"];
		$scope.chon = false;
	}

	//Xóa sản phẩm
	$scope.showDeleteModal = function(productId) {
		$scope.productToDeleteId = productId;
		$('#confirmDeleteModal').modal('show');
	};
	$scope.delete = function(id) {
		var url = `${urlProduct}/${id}`;
		$http.delete(url).then(resp => {
			var index = $scope.products.findIndex(c => c.id == id);
			$scope.products.splice(index, 1);
			$scope.reset();
			$('#confirmDeleteModal').modal('hide');
			alert("Xóa sản phẩm thành công!");
		}).catch(error => {
			if (error.status == 404) {
				alert("Không tồn tại sản phẩm " + $scope.product.id);
			} else if (error.status == 500) {
				alert("Không thể xóa sản phẩm này");
			}
			alert("Xóa sản phẩm thất bại!");
		});
	};

	// Tìm kiếm sản phẩm
	$scope.search = function(kw) {
		if (kw != null) {
			var url = `${urlProduct}/search?kw=${kw}`;
			$http.get(url).then(resp => {
				$scope.products = resp.data;
			});
		}
	};
	
	

	$scope.reset();
});