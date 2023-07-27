app.controller ("authenticate-ctrl", function($scope, $http){
    $http.get(`/rest/accounts/authorities`).then(resp =>{
    	$scope.db = resp.data;
    })
    $scope.index_of = function(username, role){
    	return $scope.db.authorities.
    	findIndex(a => a.accounts.username == username && a.roles.role == role);
    }
    $scope.update = function(username, role){
    	var index = $scope.index_of(username.username, role.role);
    	console.log(index);
    	if(index >= 0){
    		var id = $scope.db.authorities[index].id;
    		$http.delete(`/rest/accounts/authorities/${id}`).then(resp => {
    			$scope.db.authorities.splice(index, 1);
    		})
    	}else{
    		var authority = {
    			accounts:username,
    			roles:role
    		}
    		$http.post(`/rest/accounts/authorities`,authority).then(resp => {
    			$scope.db.authorities.push(resp.data);
    		})
    	}
    }
    $scope.search = function(kw){
        if(kw != null){
            var url = `/rest/accounts/authorities/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.db.accounts = resp.data;
            });
        }
    };
})