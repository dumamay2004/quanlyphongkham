package com.example.quanlybenhvien.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quanlybenhvien.Dao.NguoiDungDao;
import com.example.quanlybenhvien.Entity.NguoiDung;
import com.example.quanlybenhvien.Entity.Vaitro;

import jakarta.transaction.Transactional;

@Service
public class NguoiDungService implements UserDetailsService{
    @Autowired
    NguoiDungDao nguoiDungDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungDao.findByEmail(email);
        if (optionalNguoiDung.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        }

        NguoiDung admin = optionalNguoiDung.get();
        if (!"QUANLY".equals(admin.getVaiTro().getMaVT())) {
            throw new UsernameNotFoundException("Bạn không có quyền đăng nhập vào hệ thống ADMIN");
        }

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getMatkhau())
                .roles("QUANLY") // Chỉ admin
                .build();
    }
    public void registerUser(NguoiDung user) {
        user.setMatkhau(passwordEncoder.encode(user.getMatkhau())); // Mã hóa mật khẩu
        nguoiDungDao.save(user);
    }

    @Transactional
    public void updatePassword(String email, String newPassword) {
        NguoiDung user = nguoiDungDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        
        // Mã hóa mật khẩu trước khi cập nhật
        user.setMatkhau(passwordEncoder.encode(newPassword));
        nguoiDungDao.save(user);
    }
}


