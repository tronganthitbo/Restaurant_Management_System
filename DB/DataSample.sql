USE RestaurantManagement;
GO

-- Bật cho phép chèn dữ liệu Unicode
SET NOCOUNT ON;
GO

------------------------------------------------------------
-- 1️⃣ Bảng type (nguyên liệu)
------------------------------------------------------------
INSERT INTO type (type_name, description)
VALUES 
(N'Thịt', N'Các loại thịt như bò, gà, heo'),
(N'Hải sản', N'Các loại tôm, cua, cá, mực'),
(N'Rau củ', N'Các loại rau và củ quả tươi'),
(N'Gia vị', N'Các loại gia vị, nước sốt, dầu ăn');

------------------------------------------------------------
-- 2️⃣ Bảng category (danh mục món)
------------------------------------------------------------
INSERT INTO category (category_name, description)
VALUES 
(N'Món khai vị', N'Các món ăn nhẹ trước bữa chính'),
(N'Món chính', N'Món chính trong thực đơn'),
(N'Tráng miệng', N'Món ngọt, trái cây sau bữa ăn'),
(N'Nước uống', N'Cà phê, nước ngọt, sinh tố');

------------------------------------------------------------
-- 3️⃣ Bảng role (vai trò nhân viên)
------------------------------------------------------------
INSERT INTO role (role_name, description)
VALUES
(N'Admin', N'Quản trị toàn hệ thống'),
(N'Quản lý', N'Quản lý nhà hàng'),
(N'Nhân viên phục vụ', N'Phục vụ khách'),
(N'Nhân viên bếp', N'Chuẩn bị món ăn'),
(N'Thu ngân', N'Thu tiền và xử lý thanh toán');

------------------------------------------------------------
-- 4️⃣ Bảng customer (khách hàng)
------------------------------------------------------------
INSERT INTO customer (customer_name, phone_number, email, address, date_of_birth)
VALUES
(N'Trần Văn A', '0905123456', 'a@gmail.com', N'123 Nguyễn Trãi, Hà Nội', '1990-05-10'),
(N'Nguyễn Thị B', '0912345678', 'b@yahoo.com', N'456 Lê Lợi, TP.HCM', '1995-03-15'),
(N'Lê C', '0933334444', 'c@gmail.com', N'789 Huỳnh Thúc Kháng, Đà Nẵng', '2000-09-25');

------------------------------------------------------------
-- 5️⃣ Bảng table (bàn ăn)
------------------------------------------------------------
INSERT INTO [table] (table_number, table_capacity)
VALUES 
('T01', 4),
('T02', 4),
('T03', 6),
('T04', 8);

------------------------------------------------------------
-- 6️⃣ Bảng ingredient (nguyên liệu)
------------------------------------------------------------
INSERT INTO ingredient (ingredient_name, type_id, price)
VALUES
(N'Thịt bò', 1, 200000),
(N'Tôm sú', 2, 180000),
(N'Rau xà lách', 3, 20000),
(N'Dầu ăn', 4, 30000);

------------------------------------------------------------
-- 7️⃣ Bảng employee (nhân viên) - password mã hóa MD5
------------------------------------------------------------
INSERT INTO employee (emp_account, password, emp_name, gender, dob, phone_number, email, address, role_id)
VALUES
('admin', CONVERT(NVARCHAR(255), HASHBYTES('MD5', N'admin123'), 2), N'Nguyễn Quản Trị', N'Nam', '1988-01-01', '0901112222', 'admin@rms.com', N'Hà Nội', 1),
('manager1', CONVERT(NVARCHAR(255), HASHBYTES('MD5', N'manager123'), 2), N'Lê Quản Lý', N'Nữ', '1990-02-20', '0903334444', 'manager@rms.com', N'TP.HCM', 2),
('waiter1', CONVERT(NVARCHAR(255), HASHBYTES('MD5', N'waiter123'), 2), N'Trần Phục Vụ', N'Nam', '1995-07-12', '0905556666', 'waiter@rms.com', N'Hải Phòng', 3),
('chef1', CONVERT(NVARCHAR(255), HASHBYTES('MD5', N'chef123'), 2), N'Phạm Đầu Bếp', N'Nam', '1985-05-25', '0907778888', 'chef@rms.com', N'Đà Nẵng', 4),
('cashier1', CONVERT(NVARCHAR(255), HASHBYTES('MD5', N'cashier123'), 2), N'Ngô Thu Ngân', N'Nữ', '1992-09-15', '0909990000', 'cashier@rms.com', N'Cần Thơ', 5);

------------------------------------------------------------
-- 8️⃣ Bảng menu_item (món ăn)
------------------------------------------------------------
INSERT INTO menu_item (category_id, item_name, ingredients, price, description)
VALUES
(1, N'Gỏi cuốn tôm thịt', N'Tôm, thịt, rau sống, bánh tráng', 45000, N'Món khai vị truyền thống Việt Nam'),
(2, N'Bò lúc lắc', N'Thịt bò, hành tây, ớt chuông', 120000, N'Món chính đặc trưng miền Nam'),
(2, N'Tôm rang me', N'Tôm sú, nước me, tỏi, ớt', 130000, N'Món tôm đậm vị chua ngọt'),
(3, N'Chè khúc bạch', N'Sữa, gelatin, hạnh nhân', 40000, N'Món tráng miệng mát lạnh'),
(4, N'Cà phê sữa đá', N'Cà phê, sữa đặc', 30000, N'Thức uống truyền thống');

------------------------------------------------------------
-- 9️⃣ Bảng reservation (đặt bàn)
------------------------------------------------------------
INSERT INTO reservation (customer_id, table_id, reservation_date, reservation_time, party_size)
VALUES
(1, 1, '2025-10-11', '18:00', 4),
(2, 3, '2025-10-12', '19:00', 6);

------------------------------------------------------------
-- 🔟 Bảng voucher (mã giảm giá)
------------------------------------------------------------
INSERT INTO voucher (voucher_code, voucher_name, discount_type, discount_value, quantity, start_date, end_date)
VALUES
('DISCOUNT10', N'Giảm 10%', N'Percent', 10, 100, '2025-01-01', '2025-12-31'),
('SALE50K', N'Giảm 50K', N'Amount', 50000, 50, '2025-05-01', '2025-12-31');

------------------------------------------------------------
-- 1️⃣1️⃣ Bảng order (đơn hàng)
------------------------------------------------------------
INSERT INTO [order] (reservation_id, table_id, customer_id, emp_id, voucher_id, order_status, subtotal, discount, total_amount, payment_method)
VALUES
(1, 1, 1, 5, 1, N'Paid', 300000, 30000, 270000, N'Cash'),
(NULL, 3, 2, 5, NULL, N'Pending', 150000, 0, 150000, N'Card');

------------------------------------------------------------
-- 1️⃣2️⃣ Bảng order_item (chi tiết đơn hàng)
------------------------------------------------------------
INSERT INTO order_item (order_id, menu_item_id, unit_price, discount_amount, total_amount, description)
VALUES
(1, 2, 120000, 12000, 108000, N'Bò lúc lắc - giảm 10%'),
(1, 3, 130000, 13000, 117000, N'Tôm rang me - giảm 10%'),
(2, 5, 30000, 0, 30000, N'Cà phê sữa đá');

------------------------------------------------------------
-- 1️⃣3️⃣ Bảng recipe (công thức món)
------------------------------------------------------------
INSERT INTO recipe (menu_item_id)
VALUES (2), (3);

------------------------------------------------------------
-- 1️⃣4️⃣ Bảng recipe_item (thành phần trong công thức)
------------------------------------------------------------
INSERT INTO recipe_item (recipe_id, ingredient_id, quantity, unit, note)
VALUES
(1, 1, 0.5, N'kg', N'Dùng thịt bò tươi'),
(1, 3, 0.2, N'kg', N'Thêm rau xào'),
(2, 2, 0.4, N'kg', N'Tôm rang vừa chín');

------------------------------------------------------------
-- 1️⃣5️⃣ Bảng supplier (nhà cung cấp)
------------------------------------------------------------
INSERT INTO supplier (supplier_name, phone_number, email, address, contact_person, status)
VALUES
(N'Công ty TNHH Thực Phẩm Sạch', '0281112222', 'contact@thucphamsach.vn', N'12 Nguyễn Văn Linh, Q7, TP.HCM', N'Nguyễn Văn Bình', N'Active'),
(N'Công ty Hải Sản Biển Đông', '0236667777', 'info@biendong.vn', N'99 Lê Duẩn, Đà Nẵng', N'Lê Thị Hồng', N'Active');

------------------------------------------------------------
-- 1️⃣6️⃣ Bảng import (phiếu nhập hàng)
------------------------------------------------------------
INSERT INTO import (supplier_id, emp_id, status)
VALUES
(1, 4, N'Completed'),
(2, 4, N'Pending');

------------------------------------------------------------
-- 1️⃣7️⃣ Bảng import_detail (chi tiết nhập hàng)
------------------------------------------------------------
INSERT INTO import_detail (import_id, ingredient_id, quantity, unit_price)
VALUES
(1, 1, 10, 180000),
(1, 3, 20, 15000),
(2, 2, 15, 160000);

GO
PRINT N'✅ Dữ liệu mẫu đã được chèn thành công!';
GO
