create database QLBENHVIEN
use QLBENHVIEN
CREATE TABLE VAITRO
(
	maVT varchar(10) primary key,
	tenVT nvarchar(30) not null
)
CREATE TABLE QUANLY
(
	maQL varchar(5) primary key,
	hoten nvarchar(225) not null,
	matkhau varchar(20) not null,
	email nvarchar(225) not null,
	vaitro varchar(10) not null,
	hinh nvarchar(100),
	foreign key (vaitro) REFERENCES VAITRO(maVT)
)
select * from benhnhan
CREATE TABLE BENHNHAN
(
	maBN int identity(1,1),
	soBH int ,
	gioitinh varchar(10),
	hoten varchar(225),
	sodienthoai varchar(10),
	matkhau varchar(225) ,
	email nvarchar(225) ,
	quanhuyen varchar (50),
	tinh_tp varchar (100),
	hinh nvarchar(100),
	primary key(mabn)
)
ALTER TABLE BENHNHAN
ALTER COLUMN soBH int null;
 CREATE TABLE CHUYENKHOA
 (
	maCK nvarchar(10) primary key,
	tenCK nvarchar(50) NOT NULL,
	soluong int
)
 CREATE TABLE BACSI
 ( 
maBS VARCHAR(5),
hotenBS varchar(225) NOT NULL,
diachi varchar(100) NOT NULL,
SDT Varchar(20) NOT NULL,
email nvarchar(225) NOT NULL,
chuyenkhoa nvarchar(10) NOT NULL,
chucvu varchar(50) NOT NULL,
vaitro varchar(10) not null,
hinh nvarchar(100),
primary key(maBS),
FOREIGN KEY(chuyenkhoa) REFERENCES CHUYENKHOA(maCK),
foreign key (vaitro) REFERENCES VAITRO(maVT)
 )

 CREATE TABLE BENHAN
 (
	maBA varchar(5),
    tenBa varchar(100) NOT NULL,
    ngayBD DATE NOT NULL,
    ngayKT DATE NOT NULL,
    chuandoan varchar(100) NOT NULL,
    MABN int NOT NULL,
    MABS VARCHAR(5) NOT NULL,
    primary key(maBA),
    FOREIGN KEY (MABN)REFERENCES BENHNHAN(MABN),
    FOREIGN KEY (MABS)REFERENCES BACSI(MABS)
 )
 CREATE TABLE LIENHE
 (
	id int identity(1,1) primary key,
	email nvarchar(225) not null,
	SDT varchar(10) not null,
	noidung nvarchar(300) not null,
	ngayLH datetime default getdate(),
	maBN int
	foreign key(maBN) references BENHNHAN(maBN)
 )
 CREATE TABLE HOADON
 (
	mahd int identity(1,1),
    phiPS decimal,
    tamung decimal,
	tong decimal,
    ngaylap date,
    maBA varchar(5),
    primary key(maHD),
    foreign key (maba)references benhan(maba)
 )
 CREATE TABLE DONTHUOC
 (
	madt varchar(5),
    tendt varchar(100)not null ,
    ngaylap datetime default getdate(),
    mahd int,
    primary key(madt),
    foreign key(mahd)references hoadon(mahd)
 )
 CREATE TABLE THUOC
 (
	Mathuoc varchar(5),
    tenthuoc varchar(100),
    giathuoc decimal,
     ghichu varchar(100),
    primary key(mathuoc)
 )
 CREATE TABLE CHITIETTHUOC
 (
	Madt varchar(5),
    mathuoc varchar(5),
    soluong int,
    primary key(madt,mathuoc),
    foreign key(madt)references donthuoc(madt),
    foreign key(mathuoc) references thuoc(mathuoc)
 )
 CREATE TABLE DICHVU
 (
	madv varchar(5),
    tenDV varchar(100),
    loaidv varchar(100),
    primary key(madv)
 )
 CREATE TABLE CHITIETDV
 (
	maDV varchar(5),
    mahd int,
    primary key(madv,mahd),
    gia decimal,
    soluong int,
    foreign key(madv)references dichvu(madv),
    foreign key(mahd)references hoadon(mahd)
 )
 CREATE TABLE LICHKHAM
(
    maLK int identity(1,1) primary key, -- Mã lịch khám tự động tăng
    ngayKham date not null,             -- Ngày khám
    gioKham time not null,              -- Giờ khám
    maBN int not null,                  -- Mã bệnh nhân
    maBS varchar(5) not null,           -- Mã bác sĩ
    maCK nvarchar(10) not null,         -- Mã chuyên khoa
    trangthai nvarchar(50) default N'Chờ xác nhận', -- Trạng thái lịch hẹn
    ghiChu nvarchar(255),              -- Ghi chú nếu cần
    maDV varchar(5),                   -- Mã dịch vụ
    FOREIGN KEY (maBN) REFERENCES BENHNHAN(maBN),
    FOREIGN KEY (maBS) REFERENCES BACSI(maBS),
    FOREIGN KEY (maCK) REFERENCES CHUYENKHOA(maCK),
    FOREIGN KEY (maDV) REFERENCES DICHVU(maDV)
);

CREATE TABLE THANHTOAN
(
    maTT int identity(1,1) primary key,   -- Mã thanh toán (tự động tăng)
    maLK int not null,                   -- Mã lịch khám (liên kết với lịch khám)
	mahd int not null,
    ngayTT datetime default getdate(),   -- Ngày thanh toán
    hinhThuc nvarchar(50) not null,      -- Hình thức thanh toán (tiền mặt, chuyển khoản, thẻ)
    soTien decimal(10,2) not null,       -- Số tiền thanh toán
    trangThai nvarchar(50) default N'Chưa thanh toán', -- Trạng thái (Chưa thanh toán, Đã thanh toán)
    FOREIGN KEY (maLK) REFERENCES LICHKHAM(maLK),
	FOREIGN KEY (mahd) REFERENCES HOADON(mahd)
)

 INSERT INTO BENHNHAN(MABN,SOBH,GIOITINH,HOTENLOTBN,TENBN,QUANHUYEN,TINH_TP)
 VALUES
('BN01',001,'NỮ','TRÂN THI','HOA','PHƯỜNG LONG TRƯỜNG QUẬN THỦ ĐƯC','TP.HCM'),
('BN02',002,'NAM','TRÂN LÊ','TRUNG','PHƯỜNG LONG PHƯỚC QUẬN 1 ','TP.HCM'),
('BN03',003,'NỮ','NGUYỄN TRANG','NHƯ','PHƯỜNG NGUYẾN HUỆ QUẬN 7','TP.HCM'),
('BN04',004,'NAM','LÊ BÁ','THẠCH','PHƯỜNG LONG HÒA QUẬN 3','TP.HCM'),
('BN05',005,'NỮ','TRÂN THI','HẰNG','PHƯỜNG LONG TRƯỜNG QUẬN THỦ ĐƯC','TP.HCM'),
('BN06',006,'NAM','HUỲNH PHÚC','KHÁNG','PHƯỜNG HAI BÀ TRƯNG QUẬN 8','TP.HCM'),
('BN07',007,'NỮ','HỒ TUYẾT','LÊ','PHƯỜNG HOÀN NGHI QUẬN 4','TP.HCM'),
('BN08',008,'NAM','TRÂN TRUNG ','HẬU','PHƯỜNG 1 QUẬN 8','TP.HCM'),
('BN09',009,'NỮ','TRÂN GIA','KHÁNH','PHƯỜNG LONG TRƯỜNG QUẬN THỦ ĐƯC','TP.HCM'),
('BN010',0010,'NỮ','CHÂU NGỌC','HOÀ','PHƯỜNG TÂN PHONG QUẬN PHÚ NHUẬN','TP.HCM'),
('BN011',0011,'NAM','TRÂN TRUNG ','DŨNG','PHƯỜNG 8 QUẬN TÂN THUẬN ĐÔNG','TP.HCM'),
('BN012',0012,'NAM','LÊ BẢO','NAM','PHƯỜNG 9 QUẬN NHÀ BÈ','TP.HCM'),
('BN013',0013,'NAM','TRÂN CHÍ','BẢO','PHƯỜNG 3 QUẬN 7','TP.HCM'),
('BN014',0014,'NỮ','NGUYỄN THI','HỒNG','PHƯỜNG LONG TRƯỜNG QUẬN THỦ ĐƯC','TP.HCM'),
('BN015',0015,'NAM','ĐÀM VĨNH','HƯNG','PHƯỜNG 3 QUẬN 1','TP.HCM'),
('BN016',0016,'NỮ','TRÂN NGUYẾN ','HÀ','PHƯỜNG LONG HÀ THỦ ĐƯC','TP.HCM'),
('BN017',0017,'NỮ','TRÂN THI','HUỆ','PHƯỜNG LONG BÌNH QUẬN THỦ ĐƯC','TP.HCM')
INSERT INTO BACSI(MABS, HOTENBS,DICCHI,SDT, KHOA,CHUCVU)VALUES
('BS01','CHÂU PHONG','TP.HCM','0987656467','KHOA KHÁM BỆNH','NV'),
('BS02','TRẦN ĐỨC LINH','HÀ NỘI','0987656498','KHOA HỒI SỨC CC','TP'),
('BS03','TUỆ HIÊN','TP.HCM','0987656579','KHOA NỘI TỔNG HỢP','NV'),
('BS04','TRẦN LIÊM KHÁNH','HUẾ','0987656967','KHOA NỘI TIM MẠCH','NV'),
('BS05','LÝ KHÁNH','TP.HCM','0987656411','KHOA TIÊU HÓA','TP'),
('BS06','NGUYỄN TỔ LIÊN','TP.HCM','0987654440','KHOA THẦN KINH','PTP'),
('BS07','CHÂU ANH KIỆT','HÀ NỘI','0987656670','KHOA NỘI TIẾT','TP'),
('BS08','TRƯƠNG TIỂU HÙNG','TP.HCM','0987655098','KHOA NHI','NV'),
('BS09','NGUYỄN HÀ','HUẾ','0987656401','CHẤN THƯƠNG CHỈNH HÌNH','TP'),
('BS010','TRẦN CÔNG MẪN','TP.HCM','0987656412','PHỤ SẢN','NV'),
('BS011','TRẦN NGỌC MAI','TP.HCM','0987656222','TAI MŨI HỌNG','PTP')
INSERT INTO BENHAN(MABA,TENBA,NGAYBD,NGAYKT,CHUANDOAN,MABN,MABS)VALUES
('BA01','đau dạ dày','2023-06-12','2023-08-12','Cần theo dõi','BN01','BS01'),
('BA02','đau khớp','2023-06-12','2023-07-12','Uống thuốc,hẹn tái khám','BN02','BS01'),
('BA03','đau đầu','2023-06-12','2023-09-12','Cần theo dõi','BN03','BS01'),
('BA04','đau họng','2023-07-12','2023-08-12','Không cần điều trị','BN04','BS02'),
('BA05','đau mắt','2023-07-12','2023-08-12','Không cần điều trị','BN05','BS02'),
('BA06','gan nhiễm mỡ','2023-08-12','2023-09-12','Uống thuốc, hẹn tái khám','BN06','BS02'),
('BA07','nhiễm khuẩn tiết liệu','2023-08-13','2023-09-12','Uống thuốc, hẹn tái khám','BN07','BS02'),
('BA08','rối loạn nội tiết','2023-06-13','2023-10-12','Cần theo dõi','BN08','BS09'),
('BA09','rối loạn tiền đình','2023-09-13','2023-10-12','Cần theo dõi','BN09','BS09'),
('BA10','khản tiếng','2023-06-13','2023-07-12','Không cần điều trị','BN010','BS09'),
('BA11','ngộ độc thực phẩm','2023-09-14','2023-10-12','Uống thuốc, hẹn tái khám','BN011','BS09'),
('BA12','nhồi máu não','2023-06-14','2023-10-12','Cần theo dõi','BN012','BS03'),
('BA13','phát ban','2023-09-14','2023-10-12','Uống thuốc, hẹn tái khám','BN013','BS05'),
('BA14','sốt','2023-06-14','2023-07-12','Cần theo dõi','BN014','BS05'),
('BA15','lẹo mắt','2023-09-15','2023-10-01','Không cần điều trị','BN015','BS02'),
('BA16','mề đay','2023-07-15','2023-07-30','Không cần điều trị','BN016','BS010'),
('BA17','nóng gan','2023-07-15','2023-08-12','Uống thuốc, hẹn tái khám','BN017','BS010'),
('BA18','quai bì','2023-08-15','2023-09-12','Uống thuốc, hẹn tái khám','BN08','BS07'),
('BA19','sỏi mật','2023-08-15','2023-11-12','Cần theo dõi','BN09','BS07'),
('BA20','sỏi thận','2023-08-15','2023-10-12','Cần theo dõi','BN010','BS02'),
('BA21','sốt xuất huyết','2023-07-16','2023-08-12','Cần theo dõi','BN010','BS05'),
('BA22','táo bón','2023-07-16','2023-07-30','Không cần điều trị','BN010','BS08'),
('BA23','thiếu máu não','2023-10-16','2023-07-12','Uống thuốc, hẹn tái khám','BN013','BS07'),
('BA24','tiêu chảy','2023-06-16','2023-07-12','Cần theo dõi','BN014','BS07'),
('BA25','thiếu máu','2023-06-17','2023-07-12','hẹn tái khám','BN015','BS06'),
('BA26','u tuyến giáp','2023-06-17','2023-09-12','Cần theo dõi','BN016','BS02'),
('BA27','u xơ tử cung','2023-06-12','2023-08-12','cần điều trị','BN017','BS06'),
('BA28','viêm dạ dày','2023-06-16','2023-09-16','Cần theo dõi','BN012','BS09'),
('BA29','viêm tai giữa','2023-10-17','2023-11-17','cần theo dõi','BN012','BS010'),
('BA30','viêm phổi','2023-06-17','2023-08-12','Uống thuốc, hẹn tái khám','BN013','BS010'),
('BA31','viêm xoang','2023-06-12','2023-10-12','Uống thuốc, hẹn tái khám','BN013','BS011'),
('BA32','hiếm muộn','2023-05-12','2023-12-12','Cần theo dõi','BN014','BS011'),
('BA33','viêm mũi dị ứng','2023-06-12','2023-08-12','Uống thuốc, hẹn tái khám','BN015','BS010')

INSERT INTO HOADON(MAHD,PHIPS,TAMUNG,NGAYLAP,MABA)VALUES
('HD1',500000,2000000,'2023/07/01','BA01'),
('HD2',1000000,2000000,'2023/07/10','BA01'),
('HD3',700000,2000000,'2023/10/10','BA01'),
('HD4',500000,5000000,'2023/09/18','BA02'),
('HD5',1200000,3000000,'2023/09/16','BA02'),
('HD6',800000,4000000,'2023/10/17','BA05'),
('HD7',300000,6000000,'2023/11/17','BA05'),
('HD8',500000,4000000,'2023/11/19','BA07'),
('HD9',600000,3000000,'2023/10/19','BA07'),
('HD10',200000,6000000,'2023/12/1','BA01'),
('HD11',200000,10000000,'2023/11/1','BA02'),
('HD12',700000,3000000,'2023/07/1','BA01'),
('HD13',1000000,1000000,'2023/06/11','BA02'),
('HD14',700000,8000000,'2023/06/1','BA06'),
('HD15',400000,3000000,'2023/06/1','BA06'),
('HD16',400000,2000000,'2023/07/15','BA07'),
('HD17',300000,2000000,'2023/07/15','BA07'),
('HD18',300000,3000000,'2023/08/14','BA08'),
('HD19',300000,3000000,'2023/08/14','BA08'),
('HD20',200000,1000000,'2023/09/14','BA08'),
('HD21',550000,1000000,'2023/09/12','BA09'),
('HD22',800000,1000000,'2023/10/12','BA09'),
('HD23',900000,2000000,'2023/10/12','BA10'),
('HD24',100000,2000000,'2023/06/12','BA10'),
('HD25',100000,3000000,'2023/06/10','BA10'),
('HD26',200000,3000000,'2023/07/10','BA11'),
('HD27',400000,3000000,'2023/07/10','BA11'),
('HD28',400000,2000000,'2023/07/10','BA11'),
('HD29',500000,4000000,'2023/08/16','BA12'),
('HD30',400000,4000000,'2023/08/16','BA12'),
('HD31',1000000,2000000,'2023/10/16','BA12'),
('HD32',6500000,2000000,'2023/10/1','BA13'),
('HD33',500000,2000000,'2023/10/1','BA13'),
('HD34',500000,4000000,'2023/10/6','BA16'),
('HD35',300000,4000000,'2023/10/9','BA16'),
('HD36',300000,4000000,'2023/07/09','BA17'),
('HD37',900000,5000000,'2023/06/1','BA18'),
('HD38',700000,5000000,'2023/09/1','BA19'),
('HD39',500000,5000000,'2023/09/16','BA20'),
('HD40',100000,5000000,'2023/08/19','BA21'),
('HD41',300000,1000000,'2023/08/11','BA25'),
('HD42',250000,1000000,'2023/08/19','BA26'),
('HD43',100000,1000000,'2023/07/14','BA27'),
('HD44',2500000,2000000,'2023/07/11','BA28'),
('HD45',200000,2000000,'2023/06/14','BA28'),
('HD46',100000,2000000,'2023/06/14','BA22'),
('HD47',500000,4000000,'2023/07/20','BA30'),
('HD48',200000,4000000,'2023/06/20','BA30'),
('HD49',200000,3000000,'2023/06/20','BA31'),
('HD50',200000,1000000,'2023/06/10','BA32')

INSERT INTO DONTHUOC(MADT,TENDT,NGAYLAP,MAHD)VALUES
('DT1','KB000002','2023-06-12','HD1'),
('DT2','KB000006','2023-06-12','HD1'),
('DT3','KB000007','2023-06-13','HD2'),
('DT4','KB000011','2023-06-14','HD2'),
('DT5','KB000013','2023-06-14','HD3'),
('DT6','KB000017','2023-06-15','HD4'),
('DT7','KB000018','2023-06-15','HD4'),
('DT8','KB000023','2023-06-16','HD5'),
('DT9','KB000028','2023-06-12','HD5'),
('DT10','KB000036','2023-06-13','HD6'),
('DT11','KB000047','2023-06-12','HD7'),
('DT12','KB000048','2023-06-12','HD8'),
('DT13','KB000057','2023-06-15','HD9'),
('DT14','KB000058','2023-06-15','HD19'),
('DT15','KB000075','2023-06-16','HD9'),
('DT16','KB000076','2023-06-16','HD10'),
('DT17','KB000080','2023-06-17','HD18'),
('DT18','KB000081','2023-06-12','HD10'),
('DT19','KB000087','2023-06-15','HD11'),
('DT20','KB000088','2023-06-15','HD17'),
('DT21','KB000092','2023-06-17','HD11'),
('DT22','KB000100','2023-06-14','HD23'),
('DT23','KB000101','2023-06-15','HD30')

INSERT INTO THUOC(MATHUOC,TENTHUOC,GIATHUOC,GHICHU)VALUES
('T01','Ciprofloxacin (hydroclorid)',120000,'UỐNG'),
('T02','Fentanyl (citrat)',1000000,'UỐNG'),
('T03','Halotha',950000,'TIÊM'),
('T04','Ketamin (hydroclorid)',1200000,'TIÊM'),
('T05','Oxygen dược dụng',880000,'UỐNG'),
('T06','Thiopental (natri)',500000,'UỐNG'),
('T07','Bupivacain hydroclorid',1000000,'UỐNG'),
('T08','Lidocain hydroclorid',770000,'UỐNG'),
('T09','Procain hydroclorid',15000000,'TIÊM'),
('T10','Lidocain hydroclorid + Adrenalin',800000,'UỐNG'),
('T11','Ephedrin hydroclorid',2000000,'UỐNG'),
('T12','Dexamethason',3500000,'UỐNG'),
('T13','Dexamethason phosphat (natri)',2000000,'TIÊM'),
('T14','Docusat natri (*)',1200000,'TIÊM'),
('T15','Erythromycin',900000,'UỐNG'),
('T16','Fluoxetin (hydroclorid)',900000,'UỐNG'),
('T17','Hyoscin butylbromid',450000,'UỐNG'),
('T18','Hyoscin hydrobromid',1000000,'UỐNG'),
('T19','Lactulose (*)',2000000,'TIÊM'),
('T20','Midazolam',750000,'UỐNG'),
('T21','Ondansetron (hydroclorid)',900000,'UỐNG'),
('T22','Dexamethason,Nhập khẩu',1200000,'UỐNG'),
('T23','Dexamethason phosphat (natri)',450000,'UỐNG'),
('T24','Adrenalin ',780000,'UỐNG'),
('T25','Hydrocortison acetat',850000,'UỐNG')

INSERT INTO CHITIETTHUOC(MADT,MATHUOC,SOLUONG)VALUES
('DT1','T01',20),
('DT1','T02',5),
('DT1','T03',30),
('DT1','T12',10),
('DT2','T13',15),
('DT2','T14',20),
('DT3','T07',5),
('DT3','T08',30),
('DT4','T09',10),
('DT4','T10',15),
('DT5','T11',30),
('DT5','T12',10),
('DT5','T13',15),
('DT5','T01',20),
('DT6','T02',5),
('DT6','T16',30),
('DT7','T17',10),
('DT7','T18',15),
('DT7','T19',20),
('DT7','T20',15),
('DT8','T21',20),
('DT8','T22',5),
('DT9','T23',30),
('DT9','T24',10),
('DT9','T25',15),
('DT9','T16',30),
('DT10','T17',10),
('DT10','T18',15),
('DT11','T01',20),
('DT11','T02',5),
('DT11','T22',30),
('DT11','T23',20),
('DT12','T23',30),
('DT12','T24',15),
('DT13','T22',20),
('DT13','T23',5),
('DT14','T24',30),
('DT14','T08',10),
('DT14','T25',15),
('DT14','T23',30),
('DT15','T22',10),
('DT15','T23',15),
('DT15','T01',20),
('DT5','T05',5),
('DT16','T05',30),
('DT16','T25',15),
('DT16','T07',20),
('DT16','T08',5),
('DT16','T19',30),
('DT17','T10',10),
('DT21','T11',15),
('DT17','T12',30),
('DT17','T11',10),
('DT21','T12',15),
('DT17','T04',20),
('DT18','T05',5),
('DT18','T06',15),
('DT18','T04',20),
('DT19','T05',5),
('DT18','T16',30),
('DT20','T07',10),
('DT18','T08',15),
('DT20','T09',30),
('DT22','T10',10),
('DT19','T11',15),
('DT22','T12',20),
('DT23','T13',15)

INSERT INTO DICHVU(MADV,TENDV,LOAIDV)VALUES
('DV1','MÁU','XÉT NGHIỆM')

INSERT INTO CHITIETDV(MADV,MAHD,GIA,SOLUONG)VALUES
('DV1','HD6','500000',2)
go


 --1 Liệt kê thông tin bệnh nhân có nơi sinh quận thủ đức 

 SELECT * FROM BENHNHAN WHERE quanhuyen LIKE '% QUẬN THỦ ĐƯC'

 --2 Hiển thị họ và tên bệnh nhân có so bh 0017 và địa chỉ quận thủ đức

 select CONCAT( hotenlotbn, tenbn) as 'HỌ và tên ' from BENHNHAN where soBH = 0017 and quanhuyen LIKE '% QUẬN THỦ ĐƯC'

 --3 thống kê số bệnh nhân theo giới tính sắp xếp tăng 

 select gioitinh, COUNT(*) as sobenhnhan from BENHNHAN
 group by gioitinh
 order by sobenhnhan asc;

 --4 Hiển thị thông tin bệnh nhân có giới tính nam sắp xếp theo mã bảo hiểm giảm đần

 select * from BENHNHAN where gioitinh like 'Nam'
 order by soBH desc

 --5 hiển thị thông tin bệnh án kể từ ngày 13/7/2023 trở đi và theo chuẩn đoán cần theo dõi

 select * from BENHAN where ngayBD >='2023/07/23' and chuandoan like 'Cần theo dõi'
 order by ngayBD 

 --6 Hiển thị thông tin bệnh án của bệnh nhân bn013

 select * from BENHAN where MABN = 'BN010'

 --7 Hiển thị tt bác sĩ khoa thần kinh có địa chỉ tp HCM

 select * from BACSI where khoa like'KHOA THẦN KINH' and dicchi like 'tp.HCM'

 --8 Hiển thị thông tin của bác sĩ chuẩn đoán bệnh nhân bn013
 select bs.mabs, bs.hotenBS, bs.dicchi, bs.SDT, bs.khoa, bs.chucvu, ba.MaBN from BACSI bs 
 join BENHAN ba on ba.MABS = bs.maBS
 where ba.MaBN = 'BN013'

 --9 Thống kê số bác sĩ theo chức vụ 
 select chucvu, COUNT(*) as'số bác sĩ 'from BACSI
 group by chucvu

 --10 Cho biết thông tin của hóa đơn của bệnh án có mã BA20

 select * from HOADON where maBA ='BA20'

 --11 Liệt kê MABA, Ho va ten bệnh nhân, giới tính, MaBA, tên bệnh án , MaBS, tên bác sĩ quản lý bệnh án đó

 select bn.MaBN , concat(bn.hotenlotbn,bn.tenbn)as 'họ và tên bệnh nhân', bn.gioitinh, MaBA , tenBA, bs.MaBS, bs.hotenbs from BENHAN ba
 join BENHNHAN bn on bn.maBN = ba.MABN
 join BACSI bs on bs.maBS = ba.MABS;

 --13 Cho biếc các dịch vụ sử dụng của hóa đơn HD6

 select * from CHITIETDV where mahd = 'HD6'

 --14 Liệt kê chi tiết thuốc của đơn thuốc có mã DT15 gồm: mathuoc , ten thuoc, soluong, gia
 select ct.Madt ,ct.mathuoc, t.tenthuoc, ct.soluong,t.giathuoc from CHITIETTHUOC ct
 join THUOC t on t.Mathuoc=ct.mathuoc
 where Madt = 'DT15'

 --15 Cho biết các đơn thuốc của bệnh nhân 'BN01' thông tin gồm: mabn, tenbn, mahd, ngaylap 

 select dt.MaDT, bn.mabn, bn.tenbn , hd.MaHD , hd.ngaylap from HOADON hd
 join BENHAN ba on ba.maBA = hd.maBA
 join BENHNHAN bn on bn.maBN = ba.MABN
 join DONTHUOC dt on dt.mahd = hd.mahd
 where bn.maBN = 'BN01'
 -- 16 cho biec số lượng thuốc đã bán của từng loại gồm mã thuốc , tên thuốc , tổng số lượng
select t.mathuoc, t.tenthuoc, sum(ct.soluong) as 'Tổng số lượng' from THUOC t
join CHITIETTHUOC ct on ct.mathuoc = t.Mathuoc
group by t.Mathuoc, t.tenthuoc

-- 17 cho biết thông tin bệnh nhân và số lượng bệnh của bệnh nhân gồm: mabn,  ten bn, sodt, soluongbenhan
select bn.Mabn , bn.Tenbn, bn.soBH , count(ba.maBA) as soluongbenan from BENHAN ba
join BENHNHAN bn on bn.maBN= ba.MABN
group by bn.Mabn , bn.Tenbn, bn.soBH