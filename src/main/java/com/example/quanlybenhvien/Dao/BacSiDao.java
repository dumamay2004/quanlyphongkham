package com.example.quanlybenhvien.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quanlybenhvien.Entity.BacSi;



@Repository
public interface BacSiDao extends JpaRepository<BacSi,String>{
    Optional<BacSi> findByhoTen(String hoTen); // Tìm bác sĩ theo họ tên

    boolean existsById(@SuppressWarnings("null") String id); // Kiểm tra mã bác sĩ có tồn tại không

    boolean existsByEmail(String email);

    boolean existsByhoTen(String hoTen); // Kiểm tra tên bác sĩ có tồn tại không

    List<BacSi> findByhoTenContainingIgnoreCase(String hoTen); // Tìm bác sĩ theo tên chứa từ khóa
}
