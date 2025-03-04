package com.example.quanlybenhvien.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DONTHUOC")
public class DonThuoc {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_thuoc")
    private Integer maDonThuoc;

    @Column(name = "ma_benh_an", nullable = false)
    private Integer maBenhAn;

    @Column(name = "nhan_vien", length = 20, nullable = false)
    private String nhanVien;

    @Column(name = "ngay_lap", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngayLap;
}
