package com.example.quanlybenhvien.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.quanlybenhvien.Service.VaiTroService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/nguoidung/trangchu")
public class VaiTroController {
    @Autowired
    VaiTroService vaiTroService;

    @GetMapping("/vaitro")
    public String findAllVaiTro(Model model) {
        model.addAttribute("VaiTroList", vaiTroService.findAll());
        return "admin";
    }
    
}
