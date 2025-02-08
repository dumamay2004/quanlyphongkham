package com.example.quanlybenhvien.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BENHNHAN")
public class BenhNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer maBN ;
    Integer soBH ;
    String gioitinh ;
    String hoten ;
    String sodienthoai ;
    String matkhau ;
    @Column(unique = true)
    String email ;
    String quanhuyen ;
    String tinh_tp ;
    String hinh;
    @Transient
    private String nhapLaiMatKhau;
}
