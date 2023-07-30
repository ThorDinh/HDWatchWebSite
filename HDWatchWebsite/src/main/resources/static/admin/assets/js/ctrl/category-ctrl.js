let urlCategory = '/admin/rest/categories';
app.controller("category-ctrl",function($scope, $http){
    $scope.cates = [];
    $scope.cate = {};
    $scope.chon = false;
    
    //Lấy toàn bộ danh mục
    $http.get(urlCategory).then(resp => {
        $scope.cates = resp.data;
    });
    
    //Lấy 1 danh mục
    $scope.edit = function(id){
        var url = `${urlCategory}/${id}`;
        $http.get(url).then(resp => {
            $scope.cate = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại category "+$scope.cate.id);
            }
        });
    };
    
    //Cập nhật danh mục
    $scope.update = function(id){
        var url = `${urlCategory}/${id}`;
        var data = angular.copy($scope.cate);
        var index = $scope.cates.findIndex(c => c.id == id);
        $http.put(url, data).then(resp => {
            $scope.cates[index] = resp.data;
            alert("Cập nhật danh mục thành công!");
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại category "+$scope.cate.id);
            }
        });
    };
    
    //Tạo danh mục
    $scope.create = function(){
        var data = angular.copy($scope.cate);
        $http.post(urlCategory, data).then(resp => {
            $scope.cates.push(resp.data);
            $scope.reset();
            alert("Thêm mới danh mục thành công!");
        }).catch(error => {   
            if(error.status == 400){
                alert("Đã tồn tại category "+$scope.cate.id);
            }
            console.log("error ",error);
        });
    };
    
    //Làm mới danh mục
    $scope.reset = function(){
        $scope.cate = {};
        $scope.chon = false;
    }
    
    //Xóa danh mục
	$scope.showDeleteModal = function (categoryId) {
	    $scope.categoryToDeleteId = categoryId;
	    $('#confirmDeleteModal').modal('show');
	};
    $scope.delete = function(id){
        var url = `${urlCategory}/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.cates.findIndex(c => c.id == id);
            $scope.cates.splice(index, 1);
            $scope.reset();
            $('#confirmDeleteModal').modal('hide');
            alert("Xóa danh mục thành công!");
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại danh mục "+$scope.cate.id);
            } else if(error.status == 500){
				alert("Không thể xóa danh mục này");
			}
        });
    };
    
    //Tìm kiếm
    $scope.search = function(kw){
        if(kw != null){
            var url = `${urlCategory}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.cates = resp.data;
            });
        }
    };
    
    $scope.reset();
});