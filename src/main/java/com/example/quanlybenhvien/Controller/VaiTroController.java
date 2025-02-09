package com.example.quanlybenhvien.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.quanlybenhvien.Service.VaiTroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/vaitro")
public class VaiTroController {
    @Autowired
    VaiTroService vaiTroService;

    @GetMapping
    public String ListVaiTro(Model model) {
        return "quanly/vaitro/list";
    }
    
}
