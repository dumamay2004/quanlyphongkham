package com.example.quanlybenhvien.Entity;

import java.math.BigDecimal;
import java.sql.Date;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "THANHTOAN")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maThanhToan;

    private Date ngayThanhToan;
    private BigDecimal baoHiemHoTro;
    private BigDecimal soTienPhaiTra;
    private BigDecimal tongTien;
    private String hinhThuc;
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "ma_lich_kham")
    private LichKham lichKham;
}
