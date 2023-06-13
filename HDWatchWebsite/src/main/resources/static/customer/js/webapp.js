var app = angular.module("myapp", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "customer/product/home.html"
        })
        .when("/about", {
            templateUrl: "customer/about.html"
        })
        .when("/contact", {
            templateUrl: "customer/contact.html"
        })
        .when("/category", {
            templateUrl: "customer/product/category.html"
        })
        .when("/brand", {
            templateUrl: "customer/product/brand.html"
        })
        .when("/cart", {
            templateUrl: "customer/cart/cart.html"
        })
        .otherwise({
            redirectTo: "/"
        })
});
app.run(function ($rootScope) {
    $rootScope.$on('$routeChangeStart', function () {
        $rootScope.loading = true;
    });
    $rootScope.$on('$routeChangeSuccess', function () {
        $rootScope.loading = false;
    });
    $rootScope.$on('$routeChangeError', function () {
        $rootScope.loading = false;
        alert("Lỗi, không tải được Template");
    });
});