// Biến lưu đường dẫn API của tài khoản
let urlAccount = "/rest/accounts";
app.controller("user-ctrl",function($scope, $http){
    $scope.accounts = [];
    $scope.account = {};
    $scope.chon = false;
    $scope.editing = false;
	

    // Lấy danh sách tài khoản từ API
    $http.get(urlAccount).then(resp => {
        $scope.accounts = resp.data;
    });
    
    // Kích hoạt chế độ ẩn sửa tài khoản, mật khẩu khi cập nhật tài khoản
	$scope.enableEditing = function() {
	    $scope.editing = true;
	};
	
    // Lấy thông tin của một tài khoản
    $scope.edit = function(username){
        var url = `${urlAccount}/${username}`;
        $http.get(url).then(resp => {
            $scope.account = resp.data;
            $scope.chon = true;
            $scope.editing = true;
        }).catch(error => {
            if(error.status == 404){
                alert("Không tồn tại tài khoản "+username);
            }
        });
    };
    //upload img
    
    // Cập nhật thông tin tài khoản
    $scope.update = function(username){
        var url = `${urlAccount}/${username}`;
        var data = angular.copy($scope.account);
        var index = $scope.accounts.findIndex(a => a.username == username);
        $http.put(url, data).then(resp => {
            $scope.accounts[index] = resp.data;
            alert("Cập nhật tài khoản thành công!");
        }).catch(error => {
            if(error.status == 404){
                alert("Không có tài khoản "+username);
            }
            alert("Cập nhật tài khoản thất bại!")
        });
    };
    
    // Tạo mới tài khoản
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
    
    // Reset thông tin tài khoản
    $scope.reset = function(){
        $scope.account = {
            activated: true
        };
        $scope.chon = false;
        $scope.editing = false;
        
    }
    
	//Tìm kiếm tài khoản
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