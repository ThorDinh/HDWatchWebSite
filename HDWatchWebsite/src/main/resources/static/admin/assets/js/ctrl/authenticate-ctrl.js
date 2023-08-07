app.controller ("authenticate-ctrl", function($scope, $http){
	// Lấy dữ liệu từ endpoint "/rest/accounts/authorities" và gán vào biến $scope.db
    $http.get(`/rest/accounts/authorities`).then(resp =>{
    	$scope.db = resp.data;
    })
    
    // Hàm index_of tìm vị trí của một cặp username và role trong mảng $scope.db.authorities
    $scope.index_of = function(username, role){
    	return $scope.db.authorities.
    	findIndex(a => a.accounts.username == username && a.roles.role == role);
    }
    
    // Hàm update thêm hoặc xóa một authority (quyền) của username và role trong $scope.db.authorities
    $scope.update = function(username, role){
    	var index = $scope.index_of(username.username, role.role);
    	console.log(index);
    	if(index >= 0){
			// Nếu đã có quyền tồn tại, thực hiện xóa authority tương ứng
    		var id = $scope.db.authorities[index].id;
    		$http.delete(`/rest/accounts/authorities/${id}`).then(resp => {
    			$scope.db.authorities.splice(index, 1); // Xóa authority khỏi mảng
    		})
    	}else{
			// Nếu quyền chưa tồn tại, thực hiện thêm mới authority
    		var authority = {
    			accounts:username,
    			roles:role
    		}
    		$http.post(`/rest/accounts/authorities`,authority).then(resp => {
    			$scope.db.authorities.push(resp.data); // Thêm authority mới vào mảng
    		})
    	}
    }
    
    // Hàm search tìm kiếm tài khoản dựa trên từ khóa (kw) trong endpoint "/admin/rest/accounts/authorities/search"
    $scope.search = function(kw){
        if(kw != null){
            var url = `/admin/rest/accounts/authorities/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.db.accounts = resp.data;
            });
        }
    };
})