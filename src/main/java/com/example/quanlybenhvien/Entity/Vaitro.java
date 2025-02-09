package com.example.quanlybenhvien.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VAITRO")
public class Vaitro {
    @Id
    String maVT;
    String tenVT;
}
