package com.example.quanlybenhvien.Controller.NguoiDung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.quanlybenhvien.Entity.BenhNhan;
import com.example.quanlybenhvien.Service.BenhNhanService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/benhnhan")
public class EditBenhNhanController {

    @Autowired
    private BenhNhanService benhNhanService;

    // Hiển thị form chỉnh sửa thông tin bệnh nhân
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        // Tìm bệnh nhân theo ID
        BenhNhan benhNhan = benhNhanService.findById(id);
        if (benhNhan != null) {
            model.addAttribute("benhnhan", benhNhan);
            return "thongtincanhan"; // Trang chỉnh sửa thông tin
        }
        model.addAttribute("error", "Bệnh nhân không tồn tại");
        return "error"; // Nếu không tìm thấy bệnh nhân
    }

    // Cập nhật thông tin bệnh nhân
    @PostMapping("/update/{id}")
    public String updateBenhNhan(@PathVariable("id") Integer id, @ModelAttribute BenhNhan benhNhan, Model model, RedirectAttributes redirectAttributes) {
        try {
            // Gọi service để cập nhật bệnh nhân theo ID
            BenhNhan updatedBenhNhan = benhNhanService.updateBenhNhan(id, benhNhan);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin bệnh nhân thành công!");

            // Thêm thông tin bệnh nhân đã cập nhật vào model
            model.addAttribute("benhNhan", updatedBenhNhan);
            return "redirect:/benhnhan/edit/" + id; // Đúng
            // Chuyển đến trang thành công
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "error"; // Chuyển đến trang lỗi nếu không tìm thấy bệnh nhân
        }
    }
}
