package com.example.quanlybenhvien.Entity;

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
@Table(name = "CHITIETDONTHUOC")
public class ChiTietDonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maChiTietDT;

    private Integer soLuong;
    private String lieuLuong;

    @ManyToOne
    @JoinColumn(name = "ma_don_thuoc")
    private DonThuoc donThuoc;

    @ManyToOne
    @JoinColumn(name = "ma_thuoc")
    private Thuoc thuoc;
}


