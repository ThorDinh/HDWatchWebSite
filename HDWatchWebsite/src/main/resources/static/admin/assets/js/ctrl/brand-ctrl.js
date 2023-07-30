let urlBrand = "/admin/rest/brands";
app.controller("brand-ctrl",function($scope, $http){
    $scope.brands = [];
    $scope.brand = {};
    $scope.chon = false;
    //Lấy danh sách thương hiệu
    $http.get(urlBrand).then(resp => {
        $scope.brands = resp.data;
    });
    //Lấy 1 thương hiệu
    $scope.edit = function(id){
        var url = `${urlBrand}/${id}`;
        $http.get(url).then(resp => {
            $scope.brand = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại brand "+$scope.brand.id);
            }
        });
    };
    //Cập nhật thương hiệu
    $scope.update = function(id){
        var url = `${urlBrand}/${id}`;
        var data = angular.copy($scope.brand);
        var index = $scope.brands.findIndex(b => b.id == id);
        $http.put(url, data).then(resp => {
            $scope.brands[index] = resp.data;
            $scope.reset();
            alert("Cập nhật thương hiệu "+ data.name+" thành công")
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại brand "+$scope.brand.id);
            }
			alert("Cập nhật thương hiệu "+ data.name+" thất bại")
        });
    };
    //Thêm mới thương hiệu
    $scope.create = function(){
        var data = angular.copy($scope.brand);
        $http.post(urlBrand, data).then(resp => {
            $scope.brands.push(resp.data);
            $scope.reset();
            alert("Thêm mới thương hiệu "+ data.name+" thành công");
        }).catch(error => {   
            if(error.status == 400){
                alert("Đã tồn tại brand "+$scope.brand.id);
            }
            console.log("error ",error);
            alert("Thêm mới thương hiệu "+ data.name+" thất bại!")
        });
    };
    //Làm mới thương hiệu
    $scope.reset = function(){
        $scope.brand = {};
        $scope.chon = false;
    }
    //Xóa thương hiệu
    $scope.showDeleteModal = function (brandId) {
	    $scope.brandToDeleteId = brandId;
	    $('#confirmDeleteModal').modal('show');
	};
    $scope.delete = function(id){
        var url = `${urlBrand}/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.brands.findIndex(b => b.id == id);
            $scope.brands.splice(index, 1);
            $scope.reset();
            $('#confirmDeleteModal').modal('hide');
            alert("Xóa thương hiệu thành công")
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại brand "+$scope.brand.id);
            }
            alert("Xóa thương hiệu thất bại")
        });
    };
    //Tìm kiếm thương hiệu
    $scope.search = function(kw){
        if(kw != null){
            var url = `${urlBrand}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.brands = resp.data;
            });
        }
    };
    
    $scope.reset();
});