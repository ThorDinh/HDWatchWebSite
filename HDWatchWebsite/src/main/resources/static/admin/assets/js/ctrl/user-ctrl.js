let urlAccount = "/rest/accounts";
app.controller("user-ctrl",function($scope, $http){
    $scope.accounts = [];
    $scope.account = {};
    $scope.chon = false;
    //get danh sach accounts
    $http.get(urlAccount).then(resp => {
        $scope.accounts = resp.data;
    });
    //get 1 Account
    $scope.edit = function(username){
        var url = `${urlAccount}/${username}`;
        $http.get(url).then(resp => {
            $scope.account = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại tài khoản "+username);
            }
        });
    };
    //upload img
    
    //update account
    $scope.update = function(username){
        var url = `${urlAccount}/${username}`;
        var data = angular.copy($scope.account);
        var index = $scope.accounts.findIndex(a => a.username == username);
        $http.put(url, data).then(resp => {
            $scope.accounts[index] = resp.data;
            alert("Cập nhật tài khoản thành công!")
        }).catch(error => {
            if(error.status == 404){
                alert("Không có tài khoản "+username);
            }
            alert("Cập nhật tài khoản thất bại!")
        });
    };
    //tao account
    $scope.create = function(){
        var data = angular.copy($scope.account);
        $http.post(urlAccount, data).then(resp => {
            $scope.accounts.push(resp.data);
            alert("Tạo tài khoản thành công!")
            $scope.reset();
        }).catch(error => {   
            if(error.status == 400){
                alert("Tài khoản đã tồn tại "+data.username);
            }
            console.log("error ",error);
            alert("Tạo tài khoản thất bại!")
        });
    };
    //Rest khi load, khi tao moi thanh cong, khi delete, khi click btn reset
    $scope.reset = function(){
        $scope.account = {
            activated: true
        };
        $scope.chon = false;
    }
    //Xoa account
    $scope.delete = function(username){
        var url = `${urlAccount}/${username}`;
        $http.delete(url).then(resp => {
            var index = $scope.accounts.findIndex(a => a.username == username);
            $scope.accounts.splice(index, 1);
            alert("Xóa tài khoản thành công");
            $scope.reset();
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại tài khoản "+username);
            }
            alert("Xóa tài khoản thất bại");
        });
    };
    $scope.search = function(kw){
        if(kw != null){
            var url = `${urlAccount}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.accounts = resp.data;
            });
        }
    };
    $scope.reset();
});