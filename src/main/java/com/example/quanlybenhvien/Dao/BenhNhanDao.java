package com.example.quanlybenhvien.Dao;

import com.example.quanlybenhvien.Entity.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface BenhNhanDao extends JpaRepository<BenhNhan, Integer> {
    Optional<BenhNhan> findByEmail(String email);
    Optional<BenhNhan> findById(Integer maBenhNhan);
    boolean existsByEmail(String email);
    Optional<BenhNhan> findBySdt(String sdt);
}
