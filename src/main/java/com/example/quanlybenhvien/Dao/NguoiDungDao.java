package com.example.quanlybenhvien.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quanlybenhvien.Entity.NguoiDung;
import java.util.List;



public interface NguoiDungDao extends JpaRepository<NguoiDung, String> {
    Optional<NguoiDung> findByEmail(String email);
}
