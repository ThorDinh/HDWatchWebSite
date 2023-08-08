// Định dạng số tiền theo định dạng tiền Việt Nam
const numberFormat = new Intl.NumberFormat('vi-VN', {
	style: 'currency',
	currency: 'VND',
});
app.controller("dashboard-ctrl", function($scope, $http) {
	// Khởi tạo các biến và mảng dữ liệu
	$scope.total = [];
	$scope.costInMonth = [];
	$scope.costDate = [];
	$scope.costData = [];
	$scope.orderData = [];

	$scope.productInMonth = [];
	$scope.productName = [];
	$scope.productCount = [];
	
	// Lấy dữ liệu tổng quan (total) từ API
	$http.get('/admin/rest/report/total').then(resp => {
		$scope.total = resp.data;
	}).catch(error => {
		alert("Load total data fail");
		console.log(error);
	});
	
	// Lấy dữ liệu chi phí trong tháng (reportcost) từ API
	$http.get('/admin/rest/report/reportcost').then(resp => {
		$scope.costInMonth = resp.data;
		$scope.costInMonth.forEach(c => c.date = new Date(c.date));
		for (var i = 0; i < $scope.costInMonth.length; i++) {
			$scope.costDate.push($scope.costInMonth[i].date.getDate());
			$scope.costData.push($scope.costInMonth[i].cost);
			$scope.orderData.push($scope.costInMonth[i].sumOrder);
			if ($scope.costInMonth[i].date.getDate() == (new Date()).getDate()) {
				$scope.costToday = $scope.costInMonth[i].cost;
				$scope.orderToday = $scope.costInMonth[i].sumOrder;
			} else {
				$scope.costToday = 0;
				$scope.orderToday = 0;
			}
		}
		console.log((new Date()).getDate())
	}).catch(error => {
		alert("Load cost data fail");
		console.log(error);
	});
	
	// Lấy dữ liệu sản phẩm bán chạy trong tháng (bestSellerInMonth) từ API
	$http.get('/admin/rest/report/bestSellerInMonth').then(resp => {
		$scope.productInMonth = resp.data;
		for (var i = 0; i < 5; i++) {
			$scope.productName.push($scope.productInMonth[i].name);
			$scope.productCount.push($scope.productInMonth[i].count);
		}
	});
	
	// Hàm reportCost để vẽ biểu đồ chi phí trong tháng
	$scope.reportCost = function() {
		let date = (new Date()).toLocaleString('default', { month: 'short' });

		const data = {
			labels: $scope.costDate,
			datasets: [
				{
					label: 'Cost in ' + date,
					data: $scope.costData,
					fill: false,
					borderColor: 'rgb(145, 15, 6)',
					tension: 0.1
				}
			]
		};

		const config = {
			type: 'line',
			data: data,
		};
		const myChart = new Chart(
			document.getElementById('costChart'),
			config
		);
	}
	
	// Gọi hàm vẽ biểu đồ chi phí trong tháng
	$scope.reportCost();
});

