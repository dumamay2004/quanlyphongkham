-- Tạo database
CREATE DATABASE QuanLyDuAn;

USE QuanLyDuAn;
go
-- drop database QuanLyDuAn

-- unique là không cho các giá trị trong cột trùng lặp
-- decimal là giới hạn số trước dấu phẩy và sau dấu phẩy ví dụ decimal(10,2) thì nhập số 1000,2345 vô nó sẽ thành 1000,23

-- Tạo bảng vai trò
CREATE TABLE VAITRO (
    ma_vai_tro VARCHAR(20) NOT NULL PRIMARY KEY,
    ten_vai_tro NVARCHAR(255) NOT NULL
);

CREATE TABLE QUANLY
(
	ma_quan_ly varchar(5) primary key,
	ho_ten nvarchar(225) not null,
	mat_khau varchar(255) not null,
	email nvarchar(225) not null,
	vai_tro varchar(20) not null,
	hinh nvarchar(100),
	foreign key (vai_tro) REFERENCES VAITRO(ma_vai_tro)
)

-- Tạo bảng chuyên khoa
CREATE TABLE CHUYENKHOA (
    ma_chuyen_khoa VARCHAR(20) NOT NULL PRIMARY KEY,
    ten_chuyen_khoa NVARCHAR(255) NOT NULL
);

-- Tạo bảng nhân viên
CREATE TABLE NHANVIEN (
    ma_nhan_vien VARCHAR(20) NOT NULL PRIMARY KEY,
    ho_ten NVARCHAR(255) NOT NULL,
    gioi_tinh VARCHAR(10) NOT NULL,
    dia_chi NVARCHAR(255) NOT NULL,
    SDT VARCHAR(15) NOT NULL UNIQUE,
    cccd VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    hinh NVARCHAR(255) NOT NULL,
    vai_tro VARCHAR(20) NOT NULL,
    chuyen_khoa VARCHAR(20) NOT NULL,
    FOREIGN KEY (vai_tro) REFERENCES VAITRO(ma_vai_tro),
    FOREIGN KEY (chuyen_khoa) REFERENCES CHUYENKHOA(ma_chuyen_khoa)
);

-- Tạo bảng bệnh nhân
CREATE TABLE BENHNHAN (
    ma_benh_nhan int identity(1,1) PRIMARY KEY,
    ho_ten NVARCHAR(255) NOT NULL,
    nam_sinh DATE ,
    gioi_tinh VARCHAR(10) ,
    sdt VARCHAR(15) UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    mat_khau NVARCHAR(255) ,
    hinh NVARCHAR(255) ,
    bao_hiem NVARCHAR(50),
    tinh_tp NVARCHAR(100) ,
    quan_huyen NVARCHAR(100),
    duong NVARCHAR(255)
);

-- Tạo bảng bệnh án
CREATE TABLE BENHAN (
    ma_benh_an int identity(1,1) PRIMARY KEY,
    ten_benh_an NVARCHAR(255) NOT NULL,
    ma_benh_nhan int NOT NULL,
    ma_bac_si VARCHAR(20) NOT NULL,
    ngay_kham DATE NOT NULL,
    trieu_chung NVARCHAR(255) NOT NULL,
    dieu_tri NVARCHAR(255) NOT NULL,
    ghi_chu NVARCHAR(255) NOT NULL,
    FOREIGN KEY (ma_benh_nhan) REFERENCES BENHNHAN(ma_benh_nhan),
    FOREIGN KEY (ma_bac_si) REFERENCES NHANVIEN(ma_nhan_vien)
);

-- Tạo bảng dịch vụ
CREATE TABLE DICHVU (
    ma_dich_vu VARCHAR(20) NOT NULL PRIMARY KEY,
    ten_dich_vu NVARCHAR(255) NOT NULL,
    mo_ta NVARCHAR(255) NOT NULL,
    gia DECIMAL(10,2) NOT NULL
);

-- Tạo bảng lịch khám
CREATE TABLE LICHKHAM (
    ma_lich_kham int identity(1,1) PRIMARY KEY,
    ma_benh_nhan int NOT NULL,
    ma_nhan_vien VARCHAR(20) NOT NULL,
    ma_dich_vu VARCHAR(20) NOT NULL,
    ma_chuyen_khoa VARCHAR(20) NOT NULL,
    ngay_kham DATE NOT NULL,
    gio_kham DATE NOT NULL,
    trang_thai NVARCHAR(50) NOT NULL,
    ghi_chu NVARCHAR(255) NOT NULL,
    FOREIGN KEY (ma_benh_nhan) REFERENCES BENHNHAN(ma_benh_nhan),
    FOREIGN KEY (ma_nhan_vien) REFERENCES NHANVIEN(ma_nhan_vien),
    FOREIGN KEY (ma_dich_vu) REFERENCES DICHVU(ma_dich_vu),
    FOREIGN KEY (ma_chuyen_khoa) REFERENCES CHUYENKHOA(ma_chuyen_khoa)
);

-- Tạo bảng chi tiết dịch vụ
CREATE TABLE CHITIETDICHVU (
    ma_chi_tiet_dich_vu int identity(1,1) PRIMARY KEY,
    ma_lich_kham int NOT NULL,
    ma_dich_vu VARCHAR(20) NOT NULL,
    soluong INT NOT NULL,
    tong_tien DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (ma_lich_kham) REFERENCES LICHKHAM(ma_lich_kham),
    FOREIGN KEY (ma_dich_vu) REFERENCES DICHVU(ma_dich_vu)
);

-- Tạo bảng đơn thuốc
CREATE TABLE DONTHUOC (
    ma_don_thuoc int identity(1,1) PRIMARY KEY,
    ma_benh_an int NOT NULL,
    nhan_vien VARCHAR(20) NOT NULL,
    ngay_lap DATE NOT NULL,
    FOREIGN KEY (ma_benh_an) REFERENCES BENHAN(ma_benh_an),
    FOREIGN KEY (nhan_vien) REFERENCES NHANVIEN(ma_nhan_vien)
);

-- Tạo bảng thuốc
CREATE TABLE THUOC (
    ma_thuoc VARCHAR(20) NOT NULL PRIMARY KEY,
    ten_thuoc NVARCHAR(255) NOT NULL,
    mo_ta NVARCHAR(255) NOT NULL,
    gia_thuoc DECIMAL(10,2) NOT NULL,
    don_vi NVARCHAR(50) NOT NULL,
    han_su_dung DATE NOT NULL,
    nhan_vien VARCHAR(20) NOT NULL,
	so_luong_hien_co INT NOT NULL,
    FOREIGN KEY (nhan_vien) REFERENCES NHANVIEN(ma_nhan_vien)
);

-- Tạo bảng chi tiết đơn thuốc
CREATE TABLE CHITIETDONTHUOC (
	ma_chi_tiet_dt int identity(1,1) primary key,
    ma_don_thuoc int NOT NULL,
    ma_thuoc VARCHAR(20) NOT NULL,
    soluong INT NOT NULL,
    lieu_luong NVARCHAR(100) NOT NULL,
    FOREIGN KEY (ma_don_thuoc) REFERENCES DONTHUOC(ma_don_thuoc),
    FOREIGN KEY (ma_thuoc) REFERENCES THUOC(ma_thuoc)
);

-- Tạo bảng thanh toán
CREATE TABLE THANHTOAN (
    ma_thanh_toan int identity(1,1) PRIMARY KEY,
    ma_lich_kham int NOT NULL,
    ngay_thanh_toan DATE NOT NULL,
    bao_hiem_ho_tro DECIMAL(10,2) NOT NULL,
    so_tien_phai_tra DECIMAL(10,2) NOT NULL,
    tong_tien DECIMAL(10,2) NOT NULL,
    hinh_thuc NVARCHAR(100) NOT NULL,
    trang_thai NVARCHAR(50) NOT NULL,
    FOREIGN KEY (ma_lich_kham) REFERENCES LICHKHAM(ma_lich_kham)
);

-- Tạo bảng thanh toán đơn thuốc
CREATE TABLE THANHTOAN_DONTHUOC (
    ma_thanh_toan_dt int identity(1,1) NOT NULL PRIMARY KEY,
    ma_don_thuoc int NOT NULL,
    ngay_thanh_toan DATE NOT NULL,
    bao_hiem_ho_tro DECIMAL(10,2) NOT NULL,
    so_tien_phai_tra DECIMAL(10,2) NOT NULL,
    tong_tien DECIMAL(10,2) NOT NULL,
    hinh_thuc NVARCHAR(100) NOT NULL,
    trang_thai NVARCHAR(50) NOT NULL,
    FOREIGN KEY (ma_don_thuoc) REFERENCES DONTHUOC(ma_don_thuoc)
);

---------------------------------------------------------------insert vô bảng -------------------------------------------------------------------------
-- insert từng cái 1 đừng kéo 1 lần insert hết là lỗi
-- Bảng VAITRO
INSERT INTO VAITRO (ma_vai_tro, ten_vai_tro) VALUES
('VT01', N'Bác sĩ'),
('VT02', N'Y tá'),
('VT03', N'Nhân viên tiếp tân');
INSERT INTO VAITRO (ma_vai_tro, ten_vai_tro) VALUES
('VT00', N'Quản lý');

-- Bảng QUANLY
INSERT INTO QUANLY (ma_quan_ly, ho_ten, mat_khau, email, vai_tro, hinh)
VALUES ('QL01', N'Nguyễn Văn A', '123', N'a@gmail.com', 'VT00', 'avatar.jpg');
UPDATE QUANLY SET mat_khau = '$2a$10$RsgeqGNABvDJH6c1cCYHqetCYyat6y.cK3eMTPQBjRlLRj/NTJhRO' WHERE email = 'a@gmail.com';
select * from QUANLY
-- Bảng CHUYENKHOA
INSERT INTO CHUYENKHOA (ma_chuyen_khoa, ten_chuyen_khoa) VALUES
('CK01', N'Nội khoa'),
('CK02', N'Ngoại khoa'),
('CK03', N'Nhi khoa');

-- Bảng DICHVU
INSERT INTO DICHVU (ma_dich_vu, ten_dich_vu, mo_ta, gia) VALUES
('DV01', N'Khám tổng quát', N'Kiểm tra sức khỏe tổng quát', 500000),
('DV02', N'Xét nghiệm máu', N'Xét nghiệm máu tổng quát', 200000),
('DV03', N'Chụp X-quang', N'Chụp X-quang phổi', 300000);


-- Bảng NHANVIEN (tham chiếu VAITRO và CHUYENKHOA)
INSERT INTO NHANVIEN (ma_nhan_vien, ho_ten, gioi_tinh, dia_chi, SDT, cccd, email, hinh, vai_tro, chuyen_khoa) VALUES
('NV01', N'Nguyễn Văn A', 'Nam', N'Hà Nội', '0123456789', '123456789012', 'vana@example.com', 'a.jpg', 'VT01', 'CK01'),
('NV02', N'Trần Thị B', 'Nữ', N'Hồ Chí Minh', '0987654321', '987654321098', 'thib@example.com', 'b.jpg', 'VT02', 'CK02'),
('NV03', N'Lê Văn C', 'Nam', N'Đà Nẵng', '0912345678', '192837465012', 'vanc@example.com', 'c.jpg', 'VT03', 'CK03');

-- Bảng BENHNHAN
INSERT INTO BENHNHAN ( ho_ten, nam_sinh, gioi_tinh, sdt, email, mat_khau, hinh, bao_hiem, tinh_tp, quan_huyen, duong) VALUES
( N'Phạm Thị X', '1990-05-15', 'Nữ', '0901122334', 'phamx@example.com', '123456', 'x.jpg', 'BHYT123', 'Hà Nội', 'Cầu Giấy', 'Trần Duy Hưng'),
( N'Hoàng Văn Y', '1985-10-20', 'Nam', '0978123456', 'hoangy@example.com', 'abcdef', 'y.jpg', 'BHYT456', 'Hồ Chí Minh', 'Quận 1', 'Lê Lợi'),
( N'Đinh Thị Z', '2000-08-25', 'Nữ', '0965566778', 'dinhtz@example.com', 'qwerty', 'z.jpg', 'BHYT789', 'Đà Nẵng', 'Hải Châu', 'Nguyễn Văn Linh');


-- Bảng BENHAN (phụ thuộc NHANVIEN và BENHNHAN)
INSERT INTO BENHAN ( ten_benh_an, ma_benh_nhan, ma_bac_si, ngay_kham, trieu_chung, dieu_tri, ghi_chu) VALUES
(N'Cảm cúm', 1, 'NV01', '2024-02-01', N'Sốt, ho, mệt mỏi', N'Uống thuốc hạ sốt', N'Tái khám sau 1 tuần'),
(N'Viêm phổi', 2, 'NV01', '2024-02-02', N'Khó thở, ho có đờm', N'Dùng kháng sinh, nghỉ ngơi', N'Tái khám sau 2 tuần'),
(N'Đau dạ dày', 3, 'NV01', '2024-02-03', N'Đau bụng, buồn nôn', N'Ăn uống khoa học, thuốc dạ dày', N'Không uống rượu bia');

-- Bảng LICHKHAM (phụ thuộc BENHNHAN, NHANVIEN, DICHVU, CHUYENKHOA)
INSERT INTO LICHKHAM ( ma_benh_nhan, ma_nhan_vien, ma_dich_vu, ma_chuyen_khoa, ngay_kham, gio_kham, trang_thai, ghi_chu) VALUES
(1, 'NV01', 'DV01', 'CK01', '2024-02-10', '08:30:00', N'Chờ khám', N''),
(2, 'NV02', 'DV02', 'CK02', '2024-02-11', '09:00:00', N'Đã khám', N''),
(3, 'NV03', 'DV03', 'CK03', '2024-02-12', '10:00:00', N'Chờ khám', N'');
select * from donthuoc
-- Bảng DONTHUOC (phụ thuộc BENHAN và NHANVIEN)
INSERT INTO DONTHUOC ( ma_benh_an, nhan_vien, ngay_lap) VALUES
( 1, 'NV01', '2024-02-10'),
( 2, 'NV01', '2024-02-11'),
( 3, 'NV01', '2024-02-12');

-- Bảng THUOC
INSERT INTO THUOC (ma_thuoc, ten_thuoc, mo_ta, gia_thuoc, don_vi, han_su_dung, nhan_vien, so_luong_hien_co) VALUES
('T01', N'Paracetamol', N'Giảm đau hạ sốt', 5000, N'Viên', '2025-12-31', 'NV01', 1000),
('T02', N'Amoxicillin', N'Kháng sinh', 10000, N'Viên', '2025-11-30', 'NV01', 1000),
('T03', N'Omeprazol', N'Điều trị dạ dày', 15000, N'Viên', '2025-10-31', 'NV01', 1000);

-- Bảng CHITIETDONTHUOC
INSERT INTO CHITIETDONTHUOC (ma_don_thuoc, ma_thuoc, soluong, lieu_luong) VALUES
(1, 'T01', 10, N'1 viên/lần, 3 lần/ngày'),
(2, 'T02', 14, N'1 viên/lần, 2 lần/ngày'),
(3, 'T03', 20, N'1 viên/lần, 1 lần/ngày');

-- Bảng THANHTOAN
INSERT INTO THANHTOAN ( ma_lich_kham, ngay_thanh_toan, bao_hiem_ho_tro, so_tien_phai_tra, tong_tien, hinh_thuc, trang_thai) VALUES
(1 ,'2024-02-10', 100000, 400000, 500000, N'Tiền mặt', N'Đã thanh toán'),
(2, '2024-02-11', 50000, 150000, 200000, N'Thanh toán online', N'Đã thanh toán'),
(3 ,'2024-02-12', 100000, 200000, 300000, N'Tiền mặt', N'Chưa thanh toán');
select * from donthuoc
-- Bảng CHITIETDICHVU
INSERT INTO CHITIETDICHVU ( ma_lich_kham, ma_dich_vu, soluong, tong_tien) VALUES
(1, 'DV01', 1, 500000),
( 2, 'DV02', 1, 200000),
(3, 'DV03', 1, 300000);

-- Bảng THANHTOANDONTHUOC
INSERT INTO THANHTOAN_DONTHUOC ( ma_don_thuoc, ngay_thanh_toan, bao_hiem_ho_tro, so_tien_phai_tra, tong_tien, hinh_thuc, trang_thai) VALUES
(1, '2025-02-18', 50000, 450000, 500000, 'Tiền mặt', 'Đã thanh toán'),
(2, '2025-02-18', 30000, 170000, 200000, 'Chuyển khoản', 'Đã thanh toán'),
(3,'2025-02-18', 40000, 260000, 300000, 'Tiền mặt', 'Đã thanh toán');


-- trigger 

CREATE TRIGGER trg_update_thuoc
ON CHITIETDONTHUOC
AFTER INSERT
AS
BEGIN
    -- Trừ số lượng thuốc hiện có khi thêm chi tiết đơn thuốc mới
    UPDATE THUOC
    SET so_luong_hien_co = so_luong_hien_co - i.soluong
    FROM THUOC t
    INNER JOIN inserted i ON t.ma_thuoc = i.ma_thuoc;
END;
GO


-- quy trình thêm 1 bệnh nhân cho tới lúc thanh toán thêm từng cái 1 đừng kéo 1 lần hết sẽ lỗi
-- thêm bệnh nhân
INSERT INTO BENHNHAN (ma_benh_nhan, ho_ten, nam_sinh, gioi_tinh, sdt, email, mat_khau, hinh, bao_hiem, tinh_tp, quan_huyen, duong) VALUES
('BN04', N'Nguyễn Văn D', '1995-07-15', 'Nam', '0911223344', 'nguyenvd@example.com', '123456', 'd.jpg', 'BHYT999', 'Hà Nội', 'Hoàn Kiếm', 'Phố Huế');
-- tạo bệnh án
INSERT INTO BENHAN (ma_benh_an, ten_benh_an, ma_benh_nhan, ma_bac_si, ngay_kham, trieu_chung, dieu_tri, ghi_chu) VALUES
('BA04', N'Đau đầu', 'BN04', 'NV01', '2025-02-18', N'Nhức đầu, chóng mặt', N'Uống thuốc giảm đau, nghỉ ngơi', N'Tái khám nếu không đỡ');
-- tạo lịch khám
INSERT INTO LICHKHAM (ma_lich_kham, ma_benh_nhan, ma_nhan_vien, ma_dich_vu, ma_chuyen_khoa, ngay_kham, gio_kham, trang_thai, ghi_chu) VALUES
('LK04', 'BN04', 'NV01', 'DV01', 'CK01', '2025-02-18', '08:00:00', N'Chờ khám', N'');
-- tạo đơn thuốc khi khám xong
INSERT INTO DONTHUOC (ma_don_thuoc, ma_benh_an, nhan_vien, ngay_lap) VALUES
('DT04', 'BA04', 'NV01', '2025-02-18');
-- tạo thông tin cho đơn thuốc
INSERT INTO CHITIETDONTHUOC (ma_don_thuoc, ma_thuoc, soluong, lieu_luong) VALUES
('DT04', 'T01', 10, N'1 viên/lần, 3 lần/ngày');
-- thanh toán tiền khám 
INSERT INTO THANHTOAN (ma_thanh_toan, ma_lich_kham, ngay_thanh_toan, bao_hiem_ho_tro, so_tien_phai_tra, tong_tien, hinh_thuc, trang_thai) VALUES
('TT04', 'LK04', '2025-02-18', 100000, 400000, 500000, N'Tiền mặt', N'Đã thanh toán');
-- thanh toán tiền thuốc
INSERT INTO THANHTOAN_DONTHUOC (ma_thanh_toan_dt, ma_don_thuoc, ngay_thanh_toan, bao_hiem_ho_tro, so_tien_phai_tra, tong_tien, hinh_thuc, trang_thai) VALUES
('TTDT04', 'DT04', '2025-02-18', 50000, 450000, 500000, 'Tiền mặt', 'Đã thanh toán');


select * from THUOC;
