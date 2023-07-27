app.service('AuthService', ['$http', function($http) {
    this.checkAuthentication = function() {
        return $http.get('/api/auth/check');
    };
}]);