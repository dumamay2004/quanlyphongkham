package com.example.quanlybenhvien.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.quanlybenhvien.Dao.ChiTietDichVuDao;
import com.example.quanlybenhvien.Dao.LichKhamDao;
import com.example.quanlybenhvien.Entity.BacSi;
import com.example.quanlybenhvien.Entity.BenhNhan;
import com.example.quanlybenhvien.Entity.ChiTietDichVu;
import com.example.quanlybenhvien.Entity.LichKham;
import com.example.quanlybenhvien.Entity.NhanVien;

@Service
public class LichKhamService {

    private final ChiTietDichVuDao ChiTietDichVuDao;
    @Autowired
    private LichKhamDao lichKhamDao;

    LichKhamService(ChiTietDichVuDao chiTietDichVuDao) {
        this.ChiTietDichVuDao = chiTietDichVuDao;
    }

    public void save(LichKham lichKham) {
        lichKhamDao.save(lichKham);
    }

    // Lấy danh sách lịch khám theo trạng thái
    public List<LichKham> layLichKhamTheoTrangThai(String trangThai) {
        return lichKhamDao.findByTrangThai(trangThai);
    }

    public List<LichKham> findByBenhNhan(BenhNhan benhNhan) {
        return lichKhamDao.findByBenhNhan(benhNhan);
    }
     // Lấy danh sách lịch khám chờ xác nhận (trạng thái "Chờ xác nhận")
    public List<LichKham> getLichKhamChoXacNhan() {
        return lichKhamDao.findByTrangThai("Chờ xác nhận");
    }

    // Phương thức xác nhận lịch khám, gán thông tin nhân viên vào lịch khám
    public void xacNhanLichKham(int maLichKham, String trangThai, String ghiChu, NhanVien nhanVien) {
        Optional<LichKham> optional = lichKhamDao.findById(maLichKham);
        if (optional.isPresent()) {
            LichKham lichKham = optional.get();
            lichKham.setTrangThai("chờ bác sĩ xác nhận");
            lichKham.setGhiChu(ghiChu);
            // Gán đối tượng nhân viên xác nhận vào lịch khám
            lichKham.setNhanVien(nhanVien);
            lichKhamDao.save(lichKham);
        } else {
            throw new RuntimeException("Không tìm thấy lịch khám với mã: " + maLichKham);
        }
    }

    public List<LichKham> getLichKhamChoXacNhanTheoBacSi(BacSi bacSi) {
        // Giả sử bạn đã định nghĩa phương thức trong Dao: findByBacSiAndTrangThai(bacSi, "Chờ bác sĩ xác nhận")
        return lichKhamDao.findByBacSiAndTrangThai(bacSi, "Chờ bác sĩ xác nhận");
    }

    // Cập nhật lịch khám khi bác sĩ xác nhận hoặc hủy
    public void xacNhanLichKhamTheoBacSi(int maLichKham, String trangThai, String ghiChu, BacSi bacSi) {
        Optional<LichKham> optional = lichKhamDao.findById(maLichKham);
        if (optional.isPresent()) {
            LichKham lichKham = optional.get();
            lichKham.setTrangThai(trangThai);
            lichKham.setGhiChu(ghiChu);
            // Gán bác sĩ vào lịch khám (nếu chưa được gán hoặc cập nhật lại)
            lichKham.setBacSi(bacSi);
            lichKhamDao.save(lichKham);
        } else {
            throw new RuntimeException("Không tìm thấy lịch khám với mã: " + maLichKham);
        }
    }
    public List<LichKham> getLichKhamDaXacNhanTheoBacSi(BacSi bacSi) {
        return lichKhamDao.findByBacSiAndTrangThai(bacSi, "Đã xác nhận bởi bác sĩ");
    }
    public LichKham getLichKhamById(int maLichKham) {
        return lichKhamDao.findById(maLichKham).orElse(null);  // Sử dụng JPA repository
    }
    
    public LichKham addLichKhamWithDichVu(LichKham lichKham, List<ChiTietDichVu> chiTietDichVus) {
        // Bước 1: Lưu đối tượng LichKham trước
        LichKham savedLichKham = lichKhamDao.save(lichKham);
        
        // Bước 2: Lưu danh sách ChiTietDichVu và liên kết chúng với LichKham
        for (ChiTietDichVu chiTiet : chiTietDichVus) {
            chiTiet.setLichKham(savedLichKham);  // Gán LichKham cho ChiTietDichVu
            ChiTietDichVuDao.save(chiTiet); // Lưu ChiTietDichVu vào cơ sở dữ liệu
        }
        
        // Bước 3: Thêm các ChiTietDichVu vào danh sách chiTietDichVus của LichKham
        savedLichKham.getChiTietDichVus().addAll(chiTietDichVus);

        // Bước 4: Lưu lại LichKham với các ChiTietDichVu đã được thêm vào danh sách
        return lichKhamDao.save(savedLichKham);
    }
}
