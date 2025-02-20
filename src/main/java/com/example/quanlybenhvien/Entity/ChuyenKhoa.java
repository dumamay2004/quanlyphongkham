package com.example.quanlybenhvien.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHUYENKHOA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenKhoa {
@Id
    @Column(name = "ma_chuyen_khoa")
    private String maChuyenKhoa;

    @Column(name = "ten_chuyen_khoa")
    private String tenChuyenKhoa;

    @OneToMany(mappedBy = "chuyenKhoa")
    private List<NhanVien> nhanvien;
}
