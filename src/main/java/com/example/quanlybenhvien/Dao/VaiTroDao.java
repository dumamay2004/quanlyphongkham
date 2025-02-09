package com.example.quanlybenhvien.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quanlybenhvien.Entity.Vaitro;

public interface VaiTroDao extends JpaRepository<Vaitro,String>{
    Optional<Vaitro> findByMaVT(String maVT);
}
