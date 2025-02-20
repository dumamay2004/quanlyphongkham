package com.example.quanlybenhvien.Entity;

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
@Table(name = "DONTHUOC")
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDonThuoc;

    private Date ngayLap;

    @ManyToOne
    @JoinColumn(name = "ma_benh_an")
    private BenhAn benhAn;

    @ManyToOne
    @JoinColumn(name = "nhan_vien")
    private NhanVien nhanVien;
}
