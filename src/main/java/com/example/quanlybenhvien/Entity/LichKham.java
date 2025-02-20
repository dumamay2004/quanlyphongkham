package com.example.quanlybenhvien.Entity;

import java.sql.Date;

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
@Table(name = "LICHKHAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_lich_kham")
    private Integer maLichKham;

    @ManyToOne
    @JoinColumn(name = "ma_benh_nhan")
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_dich_vu")
    private DichVu dichVu;

    @ManyToOne
    @JoinColumn(name = "ma_chuyen_khoa")
    private ChuyenKhoa chuyenKhoa;

    @Column(name = "ngay_kham")
    private Date ngayKham;

    @Column(name = "gio_kham")
    private Date gioKham;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "ghi_chu")
    private String ghiChu;
}
