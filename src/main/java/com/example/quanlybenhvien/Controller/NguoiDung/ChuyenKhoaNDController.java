package com.example.quanlybenhvien.Controller.NguoiDung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.quanlybenhvien.Entity.ChuyenKhoa;
import com.example.quanlybenhvien.Service.ChuyenKhoaService;

@Controller
@RequestMapping("/nguoidung/chuyenkhoa")
public class ChuyenKhoaNDController {
    @Autowired
    private ChuyenKhoaService chuyenKhoaService;

    @GetMapping
    public String hienthiChuyenKhoa(Model model) {
        List<ChuyenKhoa> listCK = chuyenKhoaService.getAllChuyenKhoa();
        model.addAttribute("chuyenkhoaND", listCK);
        return "/chuyenkhoaND";
    }
}
