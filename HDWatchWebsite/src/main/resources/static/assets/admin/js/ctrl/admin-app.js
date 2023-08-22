// Khai báo ứng dụng Angular với tên "admin-app" và các module "ngRoute"
const app = angular.module("admin-app", ["ngRoute"]);
// Cấu hình routing cho ứng dụng
app.config(function($routeProvider) {
	$routeProvider
		.when("/category", {
			templateUrl: "/assets/admin/layout/category.html",
			controller: "category-ctrl"
		})
		.when("/brand", {
			templateUrl: "/assets/admin/layout/brand.html",
			controller: "brand-ctrl"
		})
		.when("/product", {
			templateUrl: "/assets/admin/layout/product.html",
			controller: "product-ctrl"
		})
		.when("/user", {
			templateUrl: "/assets/admin/layout/user.html",
			controller: "user-ctrl"
		})
		.when("/authenticate", {
			templateUrl: "/assets/admin/layout/authenticate.html",
			controller: "authenticate-ctrl"
		})
		.when("/report", {
			templateUrl: "/assets/admin/layout/report.html",
			controller: "report-ctrl"
		})
		.otherwise({
			templateUrl: "/assets/admin/layout/dashboard.html",
			controller: "dashboard-ctrl"
		})
});