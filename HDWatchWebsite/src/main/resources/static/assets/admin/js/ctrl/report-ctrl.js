// Biến lưu đường dẫn API của thống kê
let urlReport = "/admin/rest/report/reportcosteachmonth";

app.controller("report-ctrl", function($scope, $http) {
    // Khởi tạo các biến và mảng dữ liệu
    $scope.reports = []; // Mảng để lưu dữ liệu báo cáo
    $scope.report = {}; // Báo cáo hiện tại đã chọn (nếu cần)
    $scope.orderByField = '';    // Trường sắp xếp ban đầu
    $scope.reverseSort = false;  // Thứ tự sắp xếp ban đầu

    // Lấy danh sách thương hiệu từ API
    $http.get(urlReport).then(resp => {
        const dataArray = resp.data; // Giả sử phản hồi từ API là một mảng các mảng con

        // Group data by year and month
        const groupedData = {};
        dataArray.forEach(innerArray => {
            const month = innerArray[0]; // Trích xuất tháng từ mảng con
            const year = innerArray[1]; // Trích xuất năm từ mảng con
            const totalCost = innerArray[2]; // Trích xuất tổng doanh thu từ mảng con
				
            if (!groupedData[year]) {
                groupedData[year] = [];
            }
            groupedData[year][month - 1] = totalCost;
            
            
        });

        // Tạo biểu đồ cột
        const ctx = document.getElementById('columnChart').getContext('2d');
        const years = Object.keys(groupedData); // Lấy danh sách các năm
        const labels = years.map(year => `Năm ${year}`); // Tạo nhãn từ danh sách các năm
        const datasets = years.map(year => {
            const totalCostsForYear = groupedData[year];
            return {
                label: `Tổng doanh thu năm ${year}`,
                data: totalCostsForYear, // Dữ liệu là tổng doanh thu của từng tháng trong năm
                backgroundColor: 'brown', // Tùy chỉnh màu sắc
                borderWidth: 1
            };
        });

        const columnChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: Array(12).fill().map((_, index) => `Tháng ${index + 1}`), // Nhãn cho từng tháng
                datasets: datasets // Đặt dữ liệu cho biểu đồ
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            callback: function(value) {
                                return value.toLocaleString('vi-VN'); // Định dạng nhãn trục Y theo kiểu tiếng Việt
                            }
                        }
                    }
                }
            }
        });

        console.log($scope.reports); // Xuất dữ liệu báo cáo đã xử lý
    });
});
