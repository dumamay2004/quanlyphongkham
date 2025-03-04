package com.example.quanlybenhvien.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NHAPTHUOC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhapThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhap_thuoc")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_thuoc", nullable = false)
    private Thuoc thuoc;

    @Column(name = "so_luong_nhap", nullable = false)
    private Integer soLuongNhap;

    @Column(name = "ngay_nhap", nullable = false)
    private LocalDate ngayNhap = LocalDate.now();

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;
}
