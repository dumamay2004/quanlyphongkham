package com.example.quanlybenhvien.Entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "THANHTOAN_DONTHUOC")
public class ThanhToanDonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_thanh_toan_dt")
    private Integer maThanhToanDt;

    @Column(name = "ma_don_thuoc", nullable = false)
    private Integer maDonThuoc;

    @Column(name = "ngay_thanh_toan", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngayThanhToan;

    @Column(name = "bao_hiem_ho_tro", nullable = false)
    private Double baoHiemHoTro;  // Đổi BigDecimal -> Double và bỏ precision/scale

    @Column(name = "so_tien_phai_tra", nullable = false)
    private Double soTienPhaiTra;

    @Column(name = "tong_tien", nullable = false)
    private Double tongTien;

    @Column(name = "hinh_thuc", nullable = false)
    private String hinhThuc;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai;
}