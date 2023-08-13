CREATE DATABASE HDWatch;

USE HDWatch;

CREATE TABLE accounts(
	username varchar(50) primary key not null,
	activated bit not null,
	email varchar(50) not null,
	fullname nvarchar(50) not null,
	password varchar(max) not null,
	google varchar(max) null,
	facebook varchar(max) null
);

CREATE TABLE carts(
	id int identity primary key not null,
	account_id varchar(50) not null,
);

CREATE TABLE cartdetails(
	id int identity primary key not null,
	cart_id int not null,
	product_id int not null,
	quantity int not null,
	price float not null
)

CREATE TABLE roles(
	id varchar(3) primary key not null,
	role nvarchar(50) not null
);

CREATE TABLE roledetails(
	id int identity primary key not null,
	account_id varchar(50) not null,
	role_id varchar(3) not null
);

CREATE TABLE products(
	id int identity primary key not null,
	name nvarchar(500) not null unique,
	price float not null,
	old_price float not null,
	available bit not null,
	create_date date not null,
	brand_id int not null,
	category_id int not null,
	stock int not null,
	description nvarchar(MAX),
	product_images nvarchar(MAX) 
);

CREATE TABLE categories(
	id int identity primary key not null,
	name nvarchar(50) not null unique
);

CREATE TABLE brands(
	id int identity primary key not null,
	name nvarchar(50) not null unique
);

CREATE TABLE orders(
	id int identity primary key not null,
	account_id varchar(50) not null,
	address nvarchar(500) not null,
	create_date date not null,
	status nvarchar(50) null,
	payment_method varchar(255) not null,
	phone_number varchar(11) not null,
	email varchar(50) null
);

CREATE TABLE orderdetails(
	id int identity primary key not null,
	order_id int not null,
	product_id int not null,
	price float not null,
	quantity int not null
);

CREATE TABLE favorites(
	id int identity primary key not null,
	account_id varchar(50) not null
);

CREATE TABLE favoritedetails(
	id int identity primary key not null,
	favorite_id int not null,
	product_id int not null
);

CREATE TABLE sale_events (
  id INT identity PRIMARY KEY,
  name NVARCHAR(255) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  price_sale int NOT NULL
);


-- roledetails
ALTER TABLE roledetails
ADD CONSTRAINT FK_roledetails_accounts
FOREIGN KEY (account_id) REFERENCES accounts(username);

ALTER TABLE roledetails
ADD CONSTRAINT FK_roledetails_roles
FOREIGN KEY (role_id) REFERENCES roles(id);

-- products
ALTER TABLE products
ADD CONSTRAINT FK_products_brands
FOREIGN KEY (brand_id) REFERENCES brands(id);

ALTER TABLE products
ADD CONSTRAINT FK_products_categories
FOREIGN KEY (category_id) REFERENCES categories(id);

-- orders
ALTER TABLE orders
ADD CONSTRAINT FK_orders_accounts
FOREIGN KEY (account_id) REFERENCES accounts(username);

-- orderdetails
ALTER TABLE orderdetails
ADD CONSTRAINT FK_orderdetails_orders
FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE orderdetails
ADD CONSTRAINT FK_orderdetails_products
FOREIGN KEY (product_id) REFERENCES products(id);

-- carts
ALTER TABLE carts
ADD CONSTRAINT FK_carts_accounts
FOREIGN KEY (account_id) REFERENCES accounts(username);

-- cartdetails
ALTER TABLE cartdetails
ADD CONSTRAINT FK_cartdetails_carts
FOREIGN KEY (cart_id) REFERENCES carts(id);

ALTER TABLE cartdetails
ADD CONSTRAINT FK_cartdetails_products
FOREIGN KEY (product_id) REFERENCES products(id);

-- favorites
ALTER TABLE favorites
ADD CONSTRAINT FK_favorites_accounts
FOREIGN KEY (account_id) REFERENCES accounts(username);

-- favoritedetails
ALTER TABLE favoritedetails
ADD CONSTRAINT FK_favoritedetails_favorites
FOREIGN KEY (favorite_id) REFERENCES favorites(id);

ALTER TABLE favoritedetails
ADD CONSTRAINT FK_favoritedetails_products
FOREIGN KEY (product_id) REFERENCES products(id);

-- Data
-- accounts
INSERT INTO accounts VALUES ('hienbt',1,'hienbt@gmail.com','Bui The Hien','$2a$10$TeydjHxMrF8Oaucet.QEB.9lcQRHqOQVh37hnftWflFIJ1vGV7qpC','',''),
('dinhnk',1,'dinhnk@gmail.com','Nguyen Kha Dinh','$2a$10$w9MBN9t8gGe.UKoOKrKbnukxdt4ja3klDAWH4zplbf13Br.SjSom2','',''),
('haonx',1,'haonx@gmail.com','Ngo Xuan Hao','$2a$10$rGvC8zlnI/Nukpmb2IorF.KsMos2wiShJhBEZl9pnzUNPpM/uf1.e','',''),
('duyntd',1,'duyntd@gmail.com','Nguyen Thanh Duc Duy','$2a$10$mP2yvgaEBVGSntQRQ0Qoxe8T4sWRddC8m6qtBcy9Irk95wCnetYlq','',''),
('quynt',1,'quynt@gmail.com','Ngo Thanh Quy','$2a$10$/I.QXSN9C43TpzjukdofhO/pMZhjzkaZWWzFyfnqz2iLjjCSo0HAu','','');

-- roles
INSERT INTO roles VALUES ('CUS',N'Khách hàng'),
('DIR',N'Quản lý'),
('STA',N'Nhân viên');

-- roledetails
INSERT INTO roledetails VALUES ('hienbt','DIR'),
('dinhnk','STA'),
('haonx','CUS'),
('duyntd','STA'),
('quynt','CUS');

-- categories
INSERT INTO categories VALUES (N'Nam'),
(N'Nữ'),
('Unisex');

-- brands
INSERT INTO brands VALUES ('TISSOT'),
('SEIKO'),
('ROLEX'),
('OMEGA'),
('CALVIN KLEIN');

-- products
-- tissot 1
INSERT INTO products(name,price,old_price,available,create_date,brand_id,category_id,stock,product_images,description)
VALUES('Le Locle Automatic Black Dial Men Watch',5694830,6167430,1,'2023-05-26',1,1,100,N'["tissot1_1.jpg","tissot1_2.jpg","tissot1_3.jpg"]',
N'Vỏ thép không gỉ PVD màu vàng hồng với dây đeo bằng da màu đen. Viền PVD vàng hồng cố định. Mặt số màu đen với các kim có tông màu vàng hồng và các vạch chỉ giờ bằng chữ số Ả Rập. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Quay số phụ giây nhỏ. Bộ chuyển động tự động ETA calibre 2825-2, dựa trên ETA 2824-2, chứa 25 Jewels, tốc độ 28800 vph và có khả năng dự trữ năng lượng khoảng 42 giờ. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng trong suốt. Hình dạng hộp tròn. Kích thước vỏ: 39,3 mm. Độ dày vỏ: 11,6 mm. Chiều rộng dải: 19 mm. Gấp qua móc cài. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: ngày, giờ, phút, giây nhỏ. Dòng Le Locle. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ nam Tissot Le Locle Automatic Black Dial T006.428.36.052.00.'),
('Couturier Powermatic 80 Ladies Watch',5647570,6356470,1,'2023-05-26',1,2,100,N'["tissot2_1.jpg","tissot2_2.jpg","tissot2_3.jpg"]',
N'Vỏ thép không gỉ với vòng đeo tay bằng thép không gỉ hai tông màu (tông màu bạc và vàng PVD). Viền PVD vàng vàng cố định. Mặt số màu bạc với các kim kiểu dauphine có tông màu vàng vàng và vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Tissot calibre Powermatic 80 (C07.111), dựa trên ETA 2824-2, chứa 23 Jewels, dao động ở tốc độ 21600 vph và có khả năng dự trữ năng lượng khoảng 80 giờ. Tinh thể sapphire chống trầy xước. Ốp lưng trong suốt. Hình dạng hộp tròn. Kích thước vỏ: 32 mm. Độ dày vỏ: 10 mm. Chiều rộng dải: 18 mm. Kẹp bướm. Chống nước ở 100 mét / 330 feet. Chức năng: ngày, giờ, phút, giây. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Đồng hồ nữ Tissot Couturier Powermatic 80 T035.207.22.031.00.'),
('Heritage Visodate Silver Opalin Dial Unisex Watch',3284570,3757170,1,'2023-05-26',1,3,100,N'["tissot3_1.jpg","tissot3_2.jpg","tissot3_3.jpg"]',
N'Vỏ thép không gỉ PVD màu vàng hồng với dây đeo bằng da màu nâu. Viền thép không gỉ PVD vàng hồng cố định. Mặt số bằng đá opal bằng bạc với kim kiểu dauphine tông vàng hồng và vạch chỉ giờ. Các chữ số Ả Rập đánh dấu các vị trí 3, 6, 9 và 12 giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Bộ máy thạch anh ETA Calibre F06.111. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng vỏ tròn, kích thước vỏ: 40 mm, độ dày vỏ: 10,66 mm. Chiều rộng dải: 20 mm. Tang móc. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: ngày, giờ, phút, giây. Dòng Di sản Visodate. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ Tissot Heritage Visodate Mặt số bạc Opalin T118.410.36.277.01.'),
('Heritage Hand Wind White Dial Unisex Watch',10278813,10728020,1,'2023-05-26',1,3,100,N'["tissot4_1.jpg","tissot4_2.jpg","tissot4_3.jpg"]',
N'Vỏ thép không gỉ PVD 316L màu vàng hồng với dây đeo bằng da màu nâu. Viền PVD vàng hồng cố định. Mặt số màu trắng với kim màu đen và vạch số giờ Ả Rập. Điểm đánh dấu phút xung quanh một vòng trong. Loại quay số: Analog. Hiển thị một mặt số phụ: giây nhỏ. Bộ chuyển động gió bằng tay ETA calibre 7001 với khả năng dự trữ năng lượng trong 42 giờ. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng vỏ Tonneau. Kích thước vỏ: 31,1 mm x 42,4 mm. Độ dày vỏ: 10,5 mm. Chiều rộng dải: 16 mm. Chốt triển khai với một nút ấn. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: giờ, phút, giây nhỏ. Dòng di sản. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ Unisex Tissot Heritage Hand Wind White Dial T128.505.36.012.00.'),
('PR 100 Sport Chic Chronograph Quartz Silver Dial Watch',5789350,6261950,1,'2023-05-26',1,3,100,N'["tissot5_1.jpg","tissot5_2.jpg","tissot5_3.jpg"]',
N'Vỏ thép không gỉ với vòng thép không gỉ. Cố định bezel thép không gỉ. Mặt số màu bạc với các kim có tông màu bạc sáng và vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 4 giờ. Đồng hồ bấm giờ - ba mặt số phụ hiển thị: 60 giây, 30 phút và 12 giờ. Bộ máy thạch anh AA cỡ nòng G15.212 của ETA. Tinh thể sapphire chống trầy xước. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 38 mm. Độ dày vỏ: 10 mm. Chiều rộng dải: 19 mm. Gấp qua móc cài. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: bấm giờ, ngày, giờ, phút, giây. Dòng Pr 100 Sport Sang trọng. Phong cách đồng hồ thể thao. Nhãn đồng hồ: Swiss Made. Đồng hồ Tissot PR 100 Sport Chic Chronograph Quartz Silver Dial T101.917.11.031.00.'),
-- seiko 2
('5 Automatic Black Dial Men Watch',4229770,4702370,1,'2023-05-26',2,1,100,N'["seiko1_1.jpg","seiko1_2.jpg","seiko1_3.jpg"]',
N'Vỏ và vòng đeo tay bằng thép không gỉ tông vàng vàng. Viền thép không gỉ tông vàng vàng cố định. Mặt số màu đen với các kim có tông màu vàng vàng và vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Thứ trong tuần và ngày hiển thị ở vị trí 3 giờ. Chuyển động tự động. Tinh thể Hardlex chống trầy xước. Kéo/đẩy vương miện. Ốp lưng trong suốt. Hình dạng vỏ tròn, kích thước vỏ: 37 mm, độ dày vỏ: 12 mm. Chiều rộng dải: 18 mm. Gấp qua móc cài bằng chốt an toàn. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: ngày, thứ, giờ, phút, giây. Dòng Seiko5. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Japan Movt. Đồng hồ nam Seiko 5 Automatic mặt đen SNKG50J1.'),
('Quartz White Dial Black Leather Ladies Watch',2362763,2835363,1,'2023-05-26',2,2,100,N'["seiko2_1.jpg","seiko2_2.jpg","seiko2_3.jpg"]',
N'Vỏ thép không gỉ với dây đeo bằng da màu đen (dập nổi vân cá sấu). Cố định bezel thép không gỉ. Mặt số màu trắng với kim màu đen và vạch số giờ La Mã. Điểm đánh dấu phút xung quanh một vòng trong. Loại quay số: Analog. Bộ máy thạch anh Seiko Calibre 4N30. Tinh thể Hardlex chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Vỏ hộp hình chữ nhật. Kích thước vỏ: 18,9 mm. Tang móc. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: giờ, phút. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Japan Movt. Đồng hồ nữ dây da đen mặt trắng Seiko Quartz SWR053.'),
('5 Black Dial Unisex Watch',7514340,7916050,1,'2023-05-26',2,3,100,N'["seiko3_1.jpg","seiko3_2.jpg","seiko3_3.jpg"]',
N'SEIKO 5 “YUTO HORIGOME” PHIÊN BẢN GIỚI HẠN SKX STREET STYLE 42.5MM VỎ ĐEN MẶT SỐ ĐEN DÂY CAO SU, Mẫu cực chất phiên bản giới hạn được collab với VĐV skateboard nổi tiếng người Nhật Yuto Horigome.'),
('Quartz White Dial Watch',2103070,5907500,1,'2023-05-26',2,3,100,N'["seiko4_1.jpg","seiko4_2.jpg","seiko4_3.jpg"]',
N'Vỏ thép không gỉ tông màu bạc với dây đeo bằng da màu đen. Viền tông màu bạc. Mặt số màu trắng với kim màu đen và vạch số giờ Ả Rập. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Chuyển động thạch anh. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng chắc chắn. Hình dạng vỏ tròn, kích thước vỏ: 29 mm, độ dày vỏ: 8 mm. Khóa gài. Chống nước ở độ sâu 50 mét / 165 feet. Chức năng: ngày, giờ, phút, giây. Nhãn đồng hồ: Japan Movt. Đồng hồ Seiko Quartz White Dial SUR639P1.'),
('Series 5 Automatic Gold Dial Watch',3544263,5671200,1,'2023-05-26',2,3,100,N'["seiko5_1.jpg","seiko5_2.jpg","seiko5_3.jpg"]',
N'Vỏ và vòng đeo tay bằng thép không gỉ tông vàng vàng. Viền thép không gỉ tông vàng vàng cố định. Mặt số bằng vàng với các kim có tông màu vàng và vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Thứ trong tuần và ngày hiển thị ở vị trí 3 giờ. Chuyển động tự động. Tinh thể Hardlex chống trầy xước. Kéo/đẩy vương miện. Ốp lưng trong suốt. Hình dạng vỏ tròn, kích thước vỏ: 36 mm, độ dày vỏ: 12 mm. Chiều rộng dải: 21 mm. Triển khai với khóa nhả nút nhấn. Chống nước ở độ sâu 50 mét / 165 feet. Chức năng: ngày, thứ, giờ, phút, giây. Sê-ri 5 Sê-ri. Phong cách đồng hồ giản dị. Đồng hồ Seiko mặt số vàng tự động sê-ri 5 SNKE06K1S.'),
-- rolex 3
('Submariner Automatic Chronometer Black Dial Men Watch BKSO',307544450,312270450,1,'2023-05-26',3,1,100,N'["rolex1_1.jpg","rolex1_2.jpg","rolex1_3.jpg"]',
N'Đồng hồ nam Rolex Submariner Automatic Chronometer Mặt đen 124060BKSO - mẫu 2022 có hộp Rolex Vỏ thép không gỉ với vòng đeo tay bằng thép không gỉ. Gờ thép không gỉ xoay một chiều với vòng gốm đen. Mặt số màu đen với các kim có tông màu bạc và các vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Bộ máy tự động Rolex calibre 3230, chứa 31 Jewels, tốc độ 28800 vph và có khả năng dự trữ năng lượng khoảng 70 giờ. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 41 mm. Độ dày vỏ: 12,5 mm. Khóa Oysterlock. Chống nước ở 300 mét / 1000 feet. Chức năng: giờ, phút, giây, đồng hồ bấm giờ. Dòng tàu ngầm. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Biến thể vật phẩm: M124060-0001, 124060ln. Đồng hồ nam Rolex Submariner Automatic Chronometer Mặt đen 124060BKSO.'),
('Lady Datejust Champagne Roman Dial Diamond Bezel Automatic Watch',366265000,385169000,1,'2023-05-26',3,2,100,N'["rolex2_1.jpg","rolex2_2.jpg","rolex2_3.jpg"]',
N'Vỏ thép không gỉ với vòng đeo tay Rolex jubilee bằng thép không gỉ với các liên kết trung tâm bằng vàng hồng 18k. Viền vàng vàng 18k cố định được đính kim cương. Mặt số sâm panh với các kim có tông màu vàng vàng và vạch số giờ La Mã. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Rolex calibre 2236 với khả năng dự trữ năng lượng trong 55 giờ. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 28 mm. Che giấu vương miện kẹp. Chống nước ở 100 mét / 330 feet. Chức năng: ngày, giờ, phút, giây, đồng hồ bấm giờ. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Đồng hồ tự động Rolex Lady Datejust Champagne Dial Roman Diamond Bezel 279383CRJ.'),
('Oyster Perpetual 36 Automatic Chronometer Turquoise Blue Dial Watch',383987500,388713500,1,'2023-05-26',3,3,100,N'["rolex3_1.jpg","rolex3_2.jpg","rolex3_3.jpg"]',
N'Đồng hồ đeo tay bằng thép không gỉ. Cố định khung thép không gỉ màu xanh ngọc lam. Đánh dấu giờ chỉ số. Loại quay số: Analog. Tay phát quang và đánh dấu. Bộ máy tự động Rolex Calibre 3230, chứa 31 viên ngọc, dao động ở tốc độ 28800 vph và có khả năng dự trữ năng lượng khoảng 70 giờ. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng chắc chắn. Hình dạng vỏ tròn, kích thước vỏ: 36 mm. Khóa Oysterlock. Chống nước ở 100 mét / 330 feet. Chức năng: giờ, phút, giây, đồng hồ bấm giờ. Dòng Oyster Perpetual 36. Phong cách đồng hồ thể thao. Nhãn đồng hồ: Swiss Made. Biến thể vật phẩm: M126000-0006. Đồng hồ Rolex Oyster Perpetual 36 Automatic Chronometer Mặt xanh ngọc lam 126000TQBLSO.'),
('Day-Date 36 Automatic Blue Diamond Dial 18kt White Gold President Watch',886125000,967648500,1,'2023-05-26',3,3,100,N'["rolex4_1.jpg","rolex4_2.jpg","rolex4_3.jpg"]',
N'Vỏ bằng vàng trắng 18k với dây đeo rolex President bằng vàng trắng 18k. Vòng bezel bằng vàng trắng 18k có rãnh cố định. Mặt số màu xanh với vạch chỉ giờ kim cương. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Rolex Calibre 3255, dựa trên Rolex 3035, chứa 31 viên ngọc, dao động ở tốc độ 28800 vph và có khả năng dự trữ năng lượng khoảng 70 giờ. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 36 mm. Chống nước ở 100 mét / 330 feet. Chức năng: ngày, thứ, giờ, phút, giây. Thông tin bổ sung: kim cương dát vàng 18 ct và kim cương cắt baguette ở vị trí 6 và 9 giờ. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Đồng hồ đeo tay President bằng vàng trắng 18 kt Rolex Day-Date 36 Automatic Blue Diamond Dial 128239BLDP.'),
('Day-Date 36 Blue Diamond Dial 18kt White Gold President Watch',1157870000,1317372500,1,'2023-05-26',3,3,100,N'["rolex5_1.jpg","rolex5_2.jpg","rolex5_3.jpg"]',
N'Vỏ bằng vàng trắng 18k với dây đeo rolex President bằng vàng trắng 18k. Cố định bezel đặt kim cương. Mặt số màu xanh lam với các kim có tông màu bạc và vạch chỉ giờ bằng kim cương. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Rolex Calibre 3255, dựa trên Rolex 3035, chứa 31 viên ngọc, dao động ở tốc độ 28800 vph và có khả năng dự trữ năng lượng khoảng 70 giờ. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 36 mm. Khóa gập. Chống nước ở 100 mét / 330 feet. Chức năng: ngày, thứ, giờ, phút, giây. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Rolex Day-Date 36 Blue Diamond Dial 18kt Vàng trắng President Watch 128349BLDP.'),
-- omega 4
('Speedmaster Racing Automatic Chronograph Men Watch',79136870,113424000,1,'2023-05-26',4,1,100,N'["omega1_1.jpg","omega1_2.jpg","omega1_3.jpg"]',
N'Vỏ thép không gỉ với vòng thép không gỉ. Viền thép không gỉ cố định với vòng trên cùng bằng nhôm màu đen hiển thị các vạch đo tốc độ. Mặt số màu đen với các kim có tông màu bạc sáng và vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 6 giờ. Đồng hồ bấm giờ - ba mặt số phụ hiển thị: 60 giây, 30 phút và 12 giờ. Bộ chuyển động tự động Omega calibre 3330 với khả năng dự trữ năng lượng trong 52 giờ. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Kích thước vỏ: 40 mm. Độ dày vỏ: 15 mm. Hình dạng hộp tròn. Chiều rộng dải: 19 mm. Chiều dài dải: 8,5 inch. Chốt triển khai với một nút ấn. Chống nước ở 100 mét / 330 feet. Chức năng: đồng hồ bấm giờ, ngày, giờ, phút, giây, đồng hồ bấm giờ, máy đo tốc độ. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Các biến thể của mặt hàng: 326 30 40 50 01 001, 326-30-40-50-01-001, 326/30/40/50/01/001, 32630405001001. Đồng hồ nam Omega Speedmaster Racing Automatic Chronograph 326.30.40.50.01.001.'),
('De Ville Prestige Black Dial Ladies Watch',39580250,76797500,1,'2023-05-26',4,2,100,N'["omega2_1.jpg","omega2_2.jpg","omega2_3.jpg"]',
N'Vỏ thép không gỉ tông màu bạc với dây đeo bằng da màu đen có hoa văn. Cố định khung thép không gỉ tông màu bạc. Mặt số màu đen với các kim có tông màu bạc và vạch chỉ giờ bằng kim cương. Các chữ số La Mã đánh dấu các vị trí 3, 6, 9 và 12 giờ. Loại quay số: Analog. Bộ máy thạch anh Omega Calibre 4061. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng vỏ tròn, kích thước vỏ: 27,4 mm. Chiều rộng dải: 12 mm. Tang móc. Chống nước ở 100 mét / 330 feet. Chức năng: giờ, phút. Dòng De Ville Prestige. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Đồng hồ nữ Omega De Ville Prestige Black Dial 424.13.27.60.51.001.'),
('Planet Ocean Co-Axial Blue Dial Mid-size Titanium Watch',105153500,184314000,1,'2023-05-26',4,3,100,N'["omega3_1.jpg","omega3_2.jpg","omega3_3.jpg"]',
N'Vỏ titan màu xám với dây đeo cao su màu xanh. Titan màu xám xoay một hướng với gờ trên cùng bằng gốm màu xanh khảm. Mặt số màu xanh lam với các kim có tông màu bạc sáng và vạch chỉ giờ. Các chữ số Ả Rập đánh dấu vị trí 6, 9 và 12 giờ. điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Omega calibre 8520 với khả năng dự trữ năng lượng trong 50 giờ. Tinh thể sapphire chống trầy xước. Vít xuống vương miện. Ốp lưng trong suốt. Hình dạng hộp tròn. Kích thước vỏ: 37,5 mm. Chiều rộng dải: 18 mm. Chốt triển khai. Chống nước ở độ sâu 600 mét / 2000 feet. Chức năng: ngày, giờ, phút, giây, đồng hồ bấm giờ. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Các biến thể vật phẩm: 232 92 38 20 03 001, 232-92-38-20-03-001, 232/92/38/20/03/001, 23292382003001. Đồng hồ titan cỡ trung Omega Planet Ocean Co-Axial Blue Dial 232.92 .38.20.03.001.'),
('Constellation Co-Axial Automatic Blue Dial Watch',64391750,103972000,1,'2023-05-26',4,3,100,N'["omega4_1.jpg","omega4_2.jpg","omega4_3.jpg"]',
N'Vỏ thép không gỉ với vòng thép không gỉ. Cố định bezel thép không gỉ. Mặt số màu xanh lam (mẫu hình thoi) với kim đồng hồ có tông màu bạc sáng và vạch chỉ giờ. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Omega calibre 2500, dựa trên ETA 2892-A2, chứa 27 viên ngọc, dao động ở tốc độ 25200 vph và có khả năng dự trữ năng lượng khoảng 48 giờ. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng trong suốt. Hình dạng hộp tròn. Kích thước vỏ: 35 mm. Độ dày vỏ: 10,2 mm. Chiều rộng dải: 22 mm. Gấp qua móc cài bằng cách nhả nút ấn. Chống nước ở 100 mét / 330 feet. Chức năng: ngày, giờ, phút, giây, bộ thoát đồng trục, đồng hồ bấm giờ. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Các biến thể của mặt hàng: 123 10 35 20 03 002, 123-10-35-20-03-002, 123/10/35/20/03/002, 12310352003002. Đồng hồ Omega Constellation Co-Axial Automatic Blue Dial 12310352003002.'),
('De Ville Prestige Co-Axial Automatic Unisex Watch',59075000,90975500,1,'2023-05-26',4,3,100,N'["omega5_1.jpg","omega5_2.jpg","omega5_3.jpg"]',
N'Vỏ thép không gỉ với vòng thép không gỉ. Cố định bezel thép không gỉ. Mặt số màu đen với kim kiểu dauphine tông màu bạc và vạch chỉ giờ. Các chữ số La Mã đánh dấu các vị trí 3, 6, 9 và 12 giờ. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Bộ máy tự động Omega calibre 2500, dựa trên ETA 2892-A2, chứa 27 viên ngọc, dao động ở tốc độ 25200 vph và có khả năng dự trữ năng lượng khoảng 48 giờ. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 36,8 mm. Độ dày vỏ: 10 mm. Chiều rộng dải: 20 mm, chiều dài dải: 8 inch. Gấp qua móc cài. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: ngày, giờ, phút, giây, bộ thoát đồng trục, đồng hồ bấm giờ. Dòng De Ville Prestige. Phong cách đồng hồ sang trọng. Nhãn đồng hồ: Swiss Made. Các biến thể của mặt hàng: 424 10 37 20 01 001, 424-10-37-20-01-001, 424/10/37/20/01/001, 42410372001001. Đồng hồ Unisex Omega De Ville Prestige Co-Axial Automatic Automatic 424.10.37.20. 01.001.'),
-- calvin klein 5
('Quartz Silver Dial Men Watch',1063113,1535713,1,'2023-05-26',5,1,100,N'["calvinklein1_1.jpg","calvinklein1_2.jpg","calvinklein1_3.jpg"]',
N'Vỏ thép không gỉ với vòng thép không gỉ. Cố định bezel thép không gỉ. Mặt số màu bạc với các kim có tông màu bạc và các vạch chỉ giờ/số Ả Rập. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 3 giờ. Chuyển động thạch anh. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 43 mm. Độ dày vỏ: 7,5 mm. Chiều rộng dải: 20 mm. Gấp qua móc cài bằng cách nhả nút ấn. Chống nước ở độ sâu 50 mét / 165 feet. Chức năng: ngày, giờ, phút, giây. Phong cách đồng hồ đeo tay. Nhãn đồng hồ: Swiss Made. Đồng hồ nam mặt đá thạch anh Calvin Klein KAM21146.'),
('Minimal Quartz Black Dial Ladies Watch',1181263,1653863,1,'2023-05-26',5,2,100,N'["calvinklein2_1.jpg","calvinklein2_2.jpg","calvinklein2_3.jpg"]',
N'Vỏ thép không gỉ PVD màu đen với vòng đeo tay bằng thép không gỉ PVD màu đen. Cố định viền PVD màu đen. Mặt số màu đen với tay tông màu bạc. Không có điểm đánh dấu. Loại quay số: Analog. Chuyển động thạch anh. Tinh thể khoáng chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 35 mm. Độ dày vỏ: 7 mm. Chiều rộng dải: 18 mm. Gấp qua trượt qua móc cài. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: giờ, phút. Dòng tối thiểu. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ nữ Calvin Klein Minimal Quartz Black Dial K3M5245X.'),
('Minimal Quartz Silver Dial Unisex Watch',1772013,2244613,1,'2023-05-26',5,3,100,N'["calvinklein3_1.jpg","calvinklein3_2.jpg","calvinklein3_3.jpg"]',
N'Vỏ thép không gỉ PVD vàng hồng với vòng đeo tay bằng thép không gỉ PVD vàng hồng. Viền PVD vàng hồng cố định. Mặt số màu bạc với kim đồng hồ tông vàng hồng. Không có điểm đánh dấu. Loại quay số: Analog. Bộ máy thạch anh ETA 901.001. Tinh thể khoáng chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 35 mm. Độ dày vỏ: 7 mm. Chiều rộng dải: 18 mm. Gấp qua trượt qua móc cài. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: giờ, phút. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ Unisex Calvin Klein Minimal Quartz Mặt số bạc K3M22U26.'),
('Contrast Quartz Ladies Watch',1063113,1535713,1,'2023-05-26',5,2,100,N'["calvinklein4_1.jpg","calvinklein4_2.jpg","calvinklein4_3.jpg"]',
N'Vỏ thép không gỉ với vòng đeo tay bằng thép không gỉ có trung tâm bằng silicon màu xanh. Cố định bezel thép không gỉ. Mặt số màu bạc (sọc xanh) với các kim màu bạc và vạch chỉ giờ. Loại quay số: Analog. Hiển thị ngày ở vị trí 3 giờ. Bộ máy thạch anh Calibre ETA F05.111. Tinh thể khoáng chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 34 mm. Độ dày vỏ: 7 mm. Chốt triển khai. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: ngày, giờ, phút, giây. Dòng tương phản. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ nữ Calvin Klein Contrast Quartz K9E231VX.'),
('Completion Silver Dial Men Watch',944963,1417563,1,'2023-05-26',5,1,100,N'["calvinklein5_1.jpg","calvinklein5_2.jpg","calvinklein5_3.jpg"]',
N'Vỏ thép không gỉ với dây đeo bằng da màu đen. Cố định bezel thép không gỉ. Mặt số màu bạc với các kim có tông màu bạc sáng và các vạch chỉ giờ/số Ả Rập. Điểm đánh dấu phút xung quanh vành ngoài. Loại quay số: Analog. Tay phát quang và đánh dấu. Hiển thị ngày ở vị trí 3 giờ. Chuyển động thạch anh. Tinh thể sapphire chống trầy xước. Kéo/đẩy vương miện. Ốp lưng chắc chắn. Hình dạng hộp tròn. Kích thước vỏ: 43 mm. Độ dày vỏ: 7,5 mm. Chiều rộng dải: 20 mm. Tang móc. Chống nước ở độ sâu 30 mét / 100 feet. Chức năng: ngày, giờ, phút, giây. Phong cách đồng hồ giản dị. Nhãn đồng hồ: Swiss Made. Đồng hồ nam mặt bạc hoàn thiện Calvin Klein KAM211C6.');

--orders
-- Insert data for Account 1
INSERT INTO orders (account_id, address, create_date, status, payment_method, phone_number, email)
VALUES
    ('hienbt', N'Số 10, Đường Trần Hưng Đạo, Quận Hoàn Kiếm, Thành phố Hà Nội', '2023-06-01', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('hienbt', N'Số 20, Đường Nguyễn Huệ, Quận 1, Thành phố Hồ Chí Minh', '2023-06-02', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('hienbt', N'Số 30, Đường Bạch Đằng, Quận Hải Châu, Thành phố Đà Nẵng', '2023-06-03', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('hienbt', N'Số 40, Đường Lạch Tray, Quận Ngô Quyền, Thành phố Hải Phòng', '2023-06-04', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('hienbt', N'Số 50, Đường Hưng Phú, Quận Ninh Kiều, Thành phố Cần Thơ', '2023-06-05', N'Đã hoàn thành', 'cod', '01234567894', ''),

    ('dinhnk', N'Số 60, Đường Bình Gia, Quận Thủ Dầu Một, Tỉnh Bình Dương', '2023-06-01', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('dinhnk', N'Số 70, Đường Hùng Vương, Quận Văn Lâm, Tỉnh Hưng Yên', '2023-06-02', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('dinhnk', N'Số 80, Đường Hàm Nghi, Quận TP. Vinh, Tỉnh Nghệ An', '2023-06-03', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('dinhnk', N'Số 90, Đường Đồng Khởi, Quận Thành phố Vũng Tàu, Tỉnh Bà Rịa - Vũng Tàu', '2023-06-04', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('dinhnk', N'Số 100, Đường Nguyễn Văn Cừ, Quận TP. Hòa Bình, Tỉnh Hòa Bình', '2023-06-05', N'Đã hoàn thành', 'cod', '01234567894', ''),

    ('haonx', N'Số 110, Đường Hạ Long, Quận Hồng Gai, Tỉnh Quảng Ninh', '2023-06-01', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('haonx', N'Số 120, Đường Lý Thường Kiệt, Quận TP. Tân An, Tỉnh Long An', '2023-06-02', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('haonx', N'Số 130, Đường Y Ngông, Quận TP. Buôn Ma Thuột, Tỉnh Đắk Lắk', '2023-06-03', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('haonx', N'Số 140, Đường Trần Đại Nghĩa, Quận TP. Mỹ Tho, Tỉnh Tiền Giang', '2023-06-04', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('haonx', N'Số 150, Đường Nguyễn Khuyến, Quận TP. Phủ Lý, Tỉnh Hà Nam', '2023-06-05', N'Đã hoàn thành', 'cod', '01234567894', ''),

    ('duyntd', N'Số 160, Đường Đường Thúy An, Quận TP. Vĩnh Yên, Tỉnh Vĩnh Phúc', '2023-06-01', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('duyntd', N'Số 170, Đường Hội Đồng, Quận TP. Đồng Xoài, Tỉnh Bình Phước', '2023-06-02', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('duyntd', N'Số 180, Đường Bế Văn Đàn, Quận TP. Bắc Ninh, Tỉnh Bắc Ninh', '2023-06-03', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('duyntd', N'Số 190, Đường Nguyễn Lương Bằng, Quận TP. Chí Linh, Tỉnh Hải Dương', '2023-06-04', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('duyntd', N'Số 200, Đường Hùng Vương, Quận TP. Tuy Hòa, Tỉnh Phú Yên', '2023-06-05', N'Đã hoàn thành', 'cod', '01234567894', ''),

    ('quynt', N'Số 210, Đường Nguyễn Đình Chiểu, Quận TP. Quy Nhơn, Tỉnh Bình Định', '2023-06-01', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('quynt', N'Số 220, Đường Nguyễn Huệ, Quận TP. Kon Tum, Tỉnh Kon Tum', '2023-06-02', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('quynt', N'Số 230, Đường Võ Thị Sáu, Quận TP. Bạc Liêu, Tỉnh Bạc Liêu', '2023-06-03', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('quynt', N'Số 240, Đường Trần Hưng Đạo, Quận TP. Yên Bái, Tỉnh Yên Bái', '2023-06-04', N'Đã hoàn thành', 'cod', '01234567894', ''),
    ('quynt', N'Số 250, Đường Nguyễn Huệ, Quận TP. Lạng Sơn, Tỉnh Lạng Sơn', '2023-06-05', N'Đã hoàn thành', 'cod', '01234567894', '');

-- orderdetails
INSERT INTO orderdetails (order_id, product_id, price, quantity)
SELECT
    o.id AS order_id,
    p.id AS product_id,
    p.price AS price,
    FLOOR(RAND() * 10) + 1 AS quantity
FROM
    orders o
CROSS JOIN
    (
    SELECT
        id,
        price,
        ROW_NUMBER() OVER (PARTITION BY category_id ORDER BY RAND()) AS row_num
    FROM
        products
    ) p
WHERE
    p.row_num <= 3;

-- favorite
INSERT INTO favorites (account_id)
VALUES ('hienbt'),
('dinhnk'),
('haonx'),
('duyntd'),
('quynt');

-- favoritedetails
-- For favorite with account_id 'hienbt'
DECLARE @hienbtFavoriteId INT;
SELECT @hienbtFavoriteId = id FROM favorites WHERE account_id = 'hienbt';

INSERT INTO favoritedetails (favorite_id, product_id)
VALUES
    (@hienbtFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@hienbtFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@hienbtFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID()));

-- For favorite with account_id 'dinhnk'
DECLARE @dinhnkFavoriteId INT;
SELECT @dinhnkFavoriteId = id FROM favorites WHERE account_id = 'dinhnk';

INSERT INTO favoritedetails (favorite_id, product_id)
VALUES
    (@dinhnkFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@dinhnkFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@dinhnkFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID()));

-- For favorite with account_id 'haonx'
DECLARE @haonxFavoriteId INT;
SELECT @haonxFavoriteId = id FROM favorites WHERE account_id = 'haonx';

INSERT INTO favoritedetails (favorite_id, product_id)
VALUES
    (@haonxFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@haonxFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@haonxFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID()));

-- For favorite with account_id 'duyntd'
DECLARE @duyntdFavoriteId INT;
SELECT @duyntdFavoriteId = id FROM favorites WHERE account_id = 'duyntd';

INSERT INTO favoritedetails (favorite_id, product_id)
VALUES
    (@duyntdFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@duyntdFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@duyntdFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID()));

-- For favorite with account_id 'quynt'
DECLARE @quyntFavoriteId INT;
SELECT @quyntFavoriteId = id FROM favorites WHERE account_id = 'quynt';

INSERT INTO favoritedetails (favorite_id, product_id)
VALUES
    (@quyntFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@quyntFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID())),
    (@quyntFavoriteId, (SELECT TOP 1 id FROM products ORDER BY NEWID()));
