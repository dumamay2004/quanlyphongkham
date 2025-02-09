package com.example.quanlybenhvien.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quanlybenhvien.Service.NguoiDungService;

@RestController
@RequestMapping("/api/users")
public class NguoiDungRestController {
    private final NguoiDungService nguoiDungService;

    public NguoiDungRestController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        nguoiDungService.updatePassword(email, newPassword);
        return "Cập nhật mật khẩu thành công!";
    }
}
