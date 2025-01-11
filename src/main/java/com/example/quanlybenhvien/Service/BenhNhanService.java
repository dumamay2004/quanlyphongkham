package com.example.quanlybenhvien.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quanlybenhvien.Dao.BenhNhanDao;
import com.example.quanlybenhvien.Entity.BenhNhan;

@Service
public class BenhNhanService {
    
    private final PasswordEncoder passwordEncoder;

    @Autowired
    BenhNhanDao benhNhanDao;

    // Constructor đúng tên lớp và tiêm PasswordEncoder
  
    public BenhNhanService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String dangKyNguoiDung(BenhNhan benhNhan) {
        // Kiểm tra nếu email bị null hoặc rỗng
        if (benhNhan.getEmail() == null || benhNhan.getEmail().isEmpty()) {
            return "Email không được để trống!";
        }

        // Kiểm tra email đã tồn tại
        if (benhNhanDao.findBenhNhanByEmail(benhNhan.getEmail()).isPresent()) {
            return "Email đã tồn tại!";
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (!benhNhan.getMatkhau().equals(benhNhan.getNhapLaiMatKhau())) {
            return "Mật khẩu và xác nhận mật khẩu không trùng khớp!";
        }

        // Mã hóa mật khẩu
        benhNhan.setMatkhau(passwordEncoder.encode(benhNhan.getMatkhau()));

        // Lưu người dùng vào cơ sở dữ liệu
        try {
            benhNhanDao.save(benhNhan);
        } catch (Exception e) {
            return "Đã xảy ra lỗi khi lưu người dùng!";
        }

        return "Đăng ký thành công!";
    }
}
