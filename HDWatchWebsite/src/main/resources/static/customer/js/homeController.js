let host = "http://localhost:8080/rest";
app.controller("homeController", function($scope,$http){
    $scope.form = {}
    $scope.items = {}
    $scope.reset = function(){
        $scope.form = {available:false}
    }
    
    //Đổ dữ liệu (Lấy tất cả product)
    $scope.load_all = function(){
        var url = `${host}/products`
        $http.get(url).then(resp =>{
            $scope.items = resp.data;
            console.log("Success",resp)
        }).catch(error =>{
            console.log("Error",error)
        })
    }
    
    //Đổ product lên form edit(lấy 1 product)
    $scope.edit = function(id){
        var url = `${host}/products/${id}`
        $http.get(url).then(resp =>{
            $scope.form = resp.data;
            console.log("Success",resp)
        }).catch(error =>{
            console.log("Error",error)
        })
    }
    
    //Thêm product
    $scope.create = function(){
        var item = angular.copy($scope.form)
        var url = `${host}/products`
        $http.post(url, item).then(resp =>{
            $scope.items.push(item);
            $scope.reset();
            console.log("Success",resp)
        }).catch(error =>{
            console.log("Error",error)
        })
    }
    
    //Sửa product
    $scope.update = function(){
        var item = angular.copy($scope.form)
        var url = `${host}/products/${$scope.form.id}`
        $http.put(url, item).then(resp =>{
            var index = $scope.items.findIndex(item => item == $scope.form.id)
            $scope.items[index] = resp.data
            $scope.reset();
            $scope.load_all();
            console.log("Success",resp)
        }).catch(error =>{
            console.log("Error",error)
        })
    }
    
    //Xóa product
    $scope.delete = function(id){
        var url = `${host}/products/${id}`
        $http.delete(url).then(resp =>{
            delete $scope.items[id];
            $scope.reset();
            $scope.load_all();
            console.log("Success",resp)
        }).catch(error =>{
            console.log("Error",error)
        })
    }

    //Thực hiện tải toàn bộ products
    $scope.load_all();
    $scope.reset();
})