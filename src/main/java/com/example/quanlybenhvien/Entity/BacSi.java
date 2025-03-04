package com.example.quanlybenhvien.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BACSI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BacSi {
@Id
    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "cccd")
    private String cccd;

    @Column(name = "email")
    private String email;

    @Column(name = "hinh")
    private String hinh;

    @ManyToOne
    @JoinColumn(name = "vai_tro", referencedColumnName = "ma_vai_tro")
    private Vaitro vaiTro;

    @ManyToOne
    @JoinColumn(name = "chuyen_khoa", referencedColumnName = "ma_chuyen_khoa")
    private ChuyenKhoa chuyenKhoa;
}
