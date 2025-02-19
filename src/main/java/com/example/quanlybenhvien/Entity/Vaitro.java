package com.example.quanlybenhvien.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VAITRO")
public class Vaitro {
    @Id
    @Column(name = "ma_vai_tro")
    private String maVaiTro;

    @Column(name = "ten_vai_tro")
    private String tenVaiTro;
}
