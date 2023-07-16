CREATE DATABASE HDWatch;

USE HDWatch;

CREATE TABLE accounts(
	username varchar(50) primary key not null,
	activated bit not null,
	email varchar(50) not null,
	fullname varchar(50) not null,
	password varchar(50) not null,
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
	detail nvarchar(MAX),
	product_images nvarchar(MAX) 
);

CREATE TABLE categories(
	id int identity primary key not null,
	name nvarchar(50) not null unique
);

CREATE TABLE brands(
	id int identity primary key not null,
	name nvarchar(50) not null unique,
	images nvarchar(50) not null
);

CREATE TABLE orders(
	id int identity primary key not null,
	account_id varchar(50) not null,
	address nvarchar(500) not null,
	create_date date not null,
	status nvarchar(50) null
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
	product_id int not null,
	available bit
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
INSERT INTO accounts VALUES ('hienbt',1,'hienbt@gmail.com','Bui The Hien','123456','',''),
('dinhnk',1,'dinhnk@gmail.com','Nguyen Kha Dinh','123456','',''),
('haonx',1,'haonx@gmail.com','Ngo Xuan Hao','123456','',''),
('duyntd',1,'duyntd@gmail.com','Nguyen Thanh Duc Duy','123456','',''),
('quynt',1,'quynt@gmail.com','Ngo Thanh Quy','123456','','');

-- roles
INSERT INTO roles VALUES ('CUS','Customer'),
('DIR','Director'),
('STA','Staff');

-- roledetails
INSERT INTO roledetails VALUES ('hienbt','DIR'),
('dinhnk','STA'),
('haonx','CUS'),
('duyntd','STA'),
('quynt','CUS');

-- categories
INSERT INTO categories VALUES (N'Cho nam'),
(N'Cho nữ'),
('Unisex');

-- brands
INSERT INTO brands VALUES ('TISSOT','tissotBrand.png'),
('SEIKO','seikoBrand.png'),
('ROLEX','rolexBrand.png'),
('OMEGA','omegaBrand.png'),
('CALVIN KLEIN','calvinKleinBrand.png');

-- products
-- tissot 1
INSERT INTO products(name,price,old_price,available,create_date,brand_id,category_id,stock,product_images)
VALUES('Le Locle Automatic Black Dial Men Watch',241,261,1,'2023-05-26',1,1,100,N'["tissot1_1.jpg","tissot1_2.jpg","tissot1_3.jpg"]'),
('Couturier Powermatic 80 Ladies Watch',239.00,269,1,'2023-05-26',1,2,100,N'["tissot2_1.jpg","tissot2_2.jpg","tissot2_3.jpg"]'),
('Heritage Visodate Silver Opalin Dial Unisex Watch',139.00,159,1,'2023-05-26',1,3,100,N'["tissot3_1.jpg","tissot3_2.jpg","tissot3_3.jpg"]'),
('Heritage Hand Wind White Dial Unisex Watch',434.99,454,1,'2023-05-26',1,3,100,N'["tissot4_1.jpg","tissot4_2.jpg","tissot4_3.jpg"]'),
('PR 100 Sport Chic Chronograph Quartz Silver Dial Watch',245.00,265,1,'2023-05-26',1,3,100,N'["tissot5_1.jpg","tissot5_2.jpg","tissot5_3.jpg"]'),
-- seiko 2
('5 Automatic Black Dial Men Watch',179.00,199,1,'2023-05-26',2,1,100,N'["seiko1_1.jpg","seiko1_2.jpg","seiko1_3.jpg"]'),
('Quartz White Dial Black Leather Ladies Watch',99.99,119.99,1,'2023-05-26',2,2,100,N'["seiko2_1.jpg","seiko2_2.jpg","seiko2_3.jpg"]'),
('5 Black Dial Unisex Watch',318.00,335.00,1,'2023-05-26',2,3,100,N'["seiko3_1.jpg","seiko3_2.jpg","seiko3_3.jpg"]'),
('Quartz White Dial Watch',89.00,250.00,1,'2023-05-26',2,3,100,N'["seiko4_1.jpg","seiko4_2.jpg","seiko4_3.jpg"]'),
('Series 5 Automatic Gold Dial Watch',149.99,240.00,1,'2023-05-26',2,3,100,N'["seiko5_1.jpg","seiko5_2.jpg","seiko5_3.jpg"]'),
-- rolex 3
('Submariner Automatic Chronometer Black Dial Men Watch BKSO',13015.00,13215,1,'2023-05-26',3,1,100,N'["rolex1_1.jpg","rolex1_2.jpg","rolex1_3.jpg"]'),
('Lady Datejust Champagne Roman Dial Diamond Bezel Automatic Watch',15500.00,16300.00,1,'2023-05-26',3,2,100,N'["rolex2_1.jpg","rolex2_2.jpg","rolex2_3.jpg"]'),
('Oyster Perpetual 36 Automatic Chronometer Turquoise Blue Dial Watch',16250.00,16450,1,'2023-05-26',3,3,100,N'["rolex3_1.jpg","rolex3_2.jpg","rolex3_3.jpg"]'),
('Day-Date 36 Automatic Blue Diamond Dial 18kt White Gold President Watch',37500.00,40950.00,1,'2023-05-26',3,3,100,N'["rolex4_1.jpg","rolex4_2.jpg","rolex4_3.jpg"]'),
('Day-Date 36 Blue Diamond Dial 18kt White Gold President Watch',49000.00,55750.00,1,'2023-05-26',3,3,100,N'["rolex5_1.jpg","rolex5_2.jpg","rolex5_3.jpg"]'),
-- omega 4
('Speedmaster Racing Automatic Chronograph Men Watch',3349.00,4800.00,1,'2023-05-26',4,1,100,N'["omega1_1.jpg","omega1_2.jpg","omega1_3.jpg"]'),
('De Ville Prestige Black Dial Ladies Watch',1675.00,3250.00,1,'2023-05-26',4,2,100,N'["omega2_1.jpg","omega2_2.jpg","omega2_3.jpg"]'),
('Planet Ocean Co-Axial Blue Dial Mid-size Titanium Watch',4450.00,7800.00,1,'2023-05-26',4,3,100,N'["omega3_1.jpg","omega3_2.jpg","omega3_3.jpg"]'),
('Constellation Co-Axial Automatic Blue Dial Watch',2725.00,4400.00,1,'2023-05-26',4,3,100,N'["omega4_1.jpg","omega4_2.jpg","omega4_3.jpg"]'),
('De Ville Prestige Co-Axial Automatic Unisex Watch',2500.00,3850.00,1,'2023-05-26',4,3,100,N'["omega5_1.jpg","omega5_2.jpg","omega5_3.jpg"]'),
-- calvin klein 5
('Quartz Silver Dial Men Watch',44.99,64.99,1,'2023-05-26',5,1,100,N'["calvinklein1_1.jpg","calvinklein1_2.jpg","calvinklein1_3.jpg"]'),
('Minimal Quartz Black Dial Ladies Watch',49.99,69.99,1,'2023-05-26',5,2,100,N'["calvinklein2_1.jpg","calvinklein2_2.jpg","calvinklein2_3.jpg"]'),
('Minimal Quartz Silver Dial Unisex Watch',74.99,94.99,1,'2023-05-26',5,3,100,N'["calvinklein3_1.jpg","calvinklein3_2.jpg","calvinklein3_3.jpg"]'),
('Contrast Quartz Ladies Watch',44.99,64.99,1,'2023-05-26',5,2,100,N'["calvinklein4_1.jpg","calvinklein4_2.jpg","calvinklein4_3.jpg"]'),
('Completion Silver Dial Men Watch',39.99,59.99,1,'2023-05-26',5,1,100,N'["calvinklein5_1.jpg","calvinklein5_2.jpg","calvinklein5_3.jpg"]');

--orders
-- Insert data for Account 1
INSERT INTO orders (account_id, address, create_date, status)
VALUES
    ('hienbt', N'Số 10, Đường Trần Hưng Đạo, Quận Hoàn Kiếm, Thành phố Hà Nội', '2023-06-01', N'Đã hoàn thành'),
    ('hienbt', N'Số 20, Đường Nguyễn Huệ, Quận 1, Thành phố Hồ Chí Minh', '2023-06-02', N'Đã hoàn thành'),
    ('hienbt', N'Số 30, Đường Bạch Đằng, Quận Hải Châu, Thành phố Đà Nẵng', '2023-06-03', N'Đã hoàn thành'),
    ('hienbt', N'Số 40, Đường Lạch Tray, Quận Ngô Quyền, Thành phố Hải Phòng', '2023-06-04', N'Đã hoàn thành'),
    ('hienbt', N'Số 50, Đường Hưng Phú, Quận Ninh Kiều, Thành phố Cần Thơ', '2023-06-05', N'Đã hoàn thành');

-- Insert data for Account 2
INSERT INTO orders (account_id, address, create_date, status)
VALUES
    ('dinhnk', N'Số 60, Đường Bình Gia, Quận Thủ Dầu Một, Tỉnh Bình Dương', '2023-06-01', N'Đã hoàn thành'),
    ('dinhnk', N'Số 70, Đường Hùng Vương, Quận Văn Lâm, Tỉnh Hưng Yên', '2023-06-02', N'Đã hoàn thành'),
    ('dinhnk', N'Số 80, Đường Hàm Nghi, Quận TP. Vinh, Tỉnh Nghệ An', '2023-06-03', N'Đã hoàn thành'),
    ('dinhnk', N'Số 90, Đường Đồng Khởi, Quận Thành phố Vũng Tàu, Tỉnh Bà Rịa - Vũng Tàu', '2023-06-04', N'Đã hoàn thành'),
    ('dinhnk', N'Số 100, Đường Nguyễn Văn Cừ, Quận TP. Hòa Bình, Tỉnh Hòa Bình', '2023-06-05', N'Đã hoàn thành');
    
-- Insert data for Account 3
INSERT INTO orders (account_id, address, create_date, status)
VALUES
    ('haonx', N'Số 110, Đường Hạ Long, Quận Hồng Gai, Tỉnh Quảng Ninh', '2023-06-01', N'Đã hoàn thành'),
    ('haonx', N'Số 120, Đường Lý Thường Kiệt, Quận TP. Tân An, Tỉnh Long An', '2023-06-02', N'Đã hoàn thành'),
    ('haonx', N'Số 130, Đường Y Ngông, Quận TP. Buôn Ma Thuột, Tỉnh Đắk Lắk', '2023-06-03', N'Đã hoàn thành'),
    ('haonx', N'Số 140, Đường Trần Đại Nghĩa, Quận TP. Mỹ Tho, Tỉnh Tiền Giang', '2023-06-04', N'Đã hoàn thành'),
    ('haonx', N'Số 150, Đường Nguyễn Khuyến, Quận TP. Phủ Lý, Tỉnh Hà Nam', '2023-06-05', N'Đã hoàn thành');
    
-- Insert data for Account 4
INSERT INTO orders (account_id, address, create_date, status)
VALUES
    ('duyntd', N'Số 160, Đường Đường Thúy An, Quận TP. Vĩnh Yên, Tỉnh Vĩnh Phúc', '2023-06-01', N'Đã hoàn thành'),
    ('duyntd', N'Số 170, Đường Hội Đồng, Quận TP. Đồng Xoài, Tỉnh Bình Phước', '2023-06-02', N'Đã hoàn thành'),
    ('duyntd', N'Số 180, Đường Bế Văn Đàn, Quận TP. Bắc Ninh, Tỉnh Bắc Ninh', '2023-06-03', N'Đã hoàn thành'),
    ('duyntd', N'Số 190, Đường Nguyễn Lương Bằng, Quận TP. Chí Linh, Tỉnh Hải Dương', '2023-06-04', N'Đã hoàn thành'),
    ('duyntd', N'Số 200, Đường Hùng Vương, Quận TP. Tuy Hòa, Tỉnh Phú Yên', '2023-06-05', N'Đã hoàn thành');

-- Insert data for Account 5
INSERT INTO orders (account_id, address, create_date, status)
VALUES
    ('quynt', N'Số 210, Đường Nguyễn Đình Chiểu, Quận TP. Quy Nhơn, Tỉnh Bình Định', '2023-06-01', N'Đã hoàn thành'),
    ('quynt', N'Số 220, Đường Nguyễn Huệ, Quận TP. Kon Tum, Tỉnh Kon Tum', '2023-06-02', N'Đã hoàn thành'),
    ('quynt', N'Số 230, Đường Võ Thị Sáu, Quận TP. Bạc Liêu, Tỉnh Bạc Liêu', '2023-06-03', N'Đã hoàn thành'),
    ('quynt', N'Số 240, Đường Trần Hưng Đạo, Quận TP. Yên Bái, Tỉnh Yên Bái', '2023-06-04', N'Đã hoàn thành'),
    ('quynt', N'Số 250, Đường Nguyễn Huệ, Quận TP. Lạng Sơn, Tỉnh Lạng Sơn', '2023-06-05', N'Đã hoàn thành');

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

