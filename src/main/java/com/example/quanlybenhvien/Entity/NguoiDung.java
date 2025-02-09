package com.example.quanlybenhvien.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "NGUOIDUNG")
public class NguoiDung {
    @Id
    private String maND;
    private String hoten;
    private String matkhau;
    private String email;
    @ManyToOne
    @JoinColumn(name = "vaitro", nullable = false)
    private Vaitro vaiTro;
    private String hinh;
}
