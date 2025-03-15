package com.example.quanlybenhvien.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quanlybenhvien.Dao.BacSiDao;
import com.example.quanlybenhvien.Dao.QuanLyDao;
import com.example.quanlybenhvien.Entity.BacSi;
import com.example.quanlybenhvien.Entity.QuanLy;


@Service
public class UserService implements UserDetailsService{
    @Autowired 
    QuanLyDao quanLyDao;

    @Autowired
    BacSiDao bacSiDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tìm trong bảng QUANLY
        Optional<QuanLy> optionalQuanLy = quanLyDao.findByEmail(email);
        if (optionalQuanLy.isPresent()) {
            QuanLy admin = optionalQuanLy.get();
            return User.builder()
                    .username(admin.getEmail())
                    .password(admin.getMatKhau())
                    .roles("VT00") // ADMIN
                    .build();
        }

        // Nếu không tìm thấy trong QUANLY, tìm trong BACSI
        Optional<BacSi> optionalBacSi = bacSiDao.findByEmail(email);
        if (optionalBacSi.isPresent()) {
            BacSi bacSi = optionalBacSi.get();
            return User.builder()
                    .username(bacSi.getEmail())
                    .password(bacSi.getMatKhau())
                    .roles("VT01") // BÁC SĨ
                    .build();
        }

        throw new UsernameNotFoundException("Không tìm thấy tài khoản: " + email);
    }

    public void registerUser(QuanLy user) {
        user.setMatKhau(passwordEncoder.encode(user.getMatKhau()));
        quanLyDao.save(user);
    }

    public void registerBacSi(BacSi bacSi) {
        bacSi.setMatKhau(passwordEncoder.encode(bacSi.getMatKhau()));
        bacSiDao.save(bacSi);
    }
}
