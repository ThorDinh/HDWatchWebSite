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
	
	$scope.reports = [];
	$scope.report = {};

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
		//console.log((new Date()).getDate())
	}).catch(error => {
		alert("Load cost data fail");
		console.log(error);
	});
	
		// Lấy danh sách thống kê từ API
	$http.get(urlReport).then(resp => {
		const dataArray = resp.data; // Giả sử phản hồi từ API là một mảng các mảng con
		
		// Ánh xạ dữ liệu từ phản hồi API vào mảng reports
		$scope.reports = dataArray.map(innerArray => {
			const month = innerArray[0];
			const year = innerArray[1];
			const totalCost = innerArray[2];
			
			// Tạo đối tượng biểu diễn thông tin báo cáo
			return {
				month: month,
				year: year,
				totalCost: totalCost
			};
		});

		// Tạo biểu đồ cột
		const ctx = document.getElementById('columnChart').getContext('2d');
		const months = $scope.reports.map(report => `Tháng ${report.month} , ${report.year}`);
		const totalCosts = $scope.reports.map(report => report.totalCost);

		const columnChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: months,
				datasets: [{
					label: 'Tổng doanh thu theo tháng',
					data: totalCosts,
					backgroundColor: 'rgba(75, 192, 192, 0.6)', // Tùy chỉnh màu sắc
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					y: {
						beginAtZero: true,
						ticks: {
							callback: function(value) {
								return value.toLocaleString('vi-VN');
							}
						}
					}
				}
			}
		});

		console.log($scope.reports);
	});
});

