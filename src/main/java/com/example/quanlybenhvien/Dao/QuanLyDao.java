package com.example.quanlybenhvien.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quanlybenhvien.Entity.QuanLy;
import java.util.List;



public interface QuanLyDao extends JpaRepository<QuanLy, String> {
    Optional<QuanLy> findByEmail(String email);
}
