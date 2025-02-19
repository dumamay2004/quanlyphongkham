package com.example.quanlybenhvien.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "QUANLY")
public class QuanLy {
    @Id
    private String ma_quan_ly;
    private String ho_ten;
    private String mat_khau;
    private String email;
    @ManyToOne
    @JoinColumn(name = "vai_tro", nullable = false)
    private Vaitro vai_tro;
    private String hinh;
}
