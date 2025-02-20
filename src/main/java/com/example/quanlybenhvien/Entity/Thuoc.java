package com.example.quanlybenhvien.Entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
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
@Table(name = "THUOC")
public class Thuoc {
    @Id
    private String maThuoc;

    private String tenThuoc;
    private String moTa;
    private BigDecimal giaThuoc;
    private String donVi;
    private Date hanSuDung;
    private Integer soLuongHienCo;

    @ManyToOne
    @JoinColumn(name = "nhan_vien")
    private NhanVien nhanVien;
}
