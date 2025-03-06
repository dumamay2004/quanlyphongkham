package com.example.quanlybenhvien.Controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quanlybenhvien.Entity.BacSi;
import com.example.quanlybenhvien.Entity.ChuyenKhoa;
import com.example.quanlybenhvien.Service.ChuyenKhoaService;
import com.example.quanlybenhvien.Service.BacSiService;

@Controller
@RequestMapping("/quanly/trangchu/bacsi")
public class BacSiController {

    @Autowired
    private BacSiService bacSiService;

    @Autowired
    private ChuyenKhoaService chuyenkhoaService;

    // Hiển thị danh sách bác sĩ
    @GetMapping
    public String listBacSi(Model model) {
        List<BacSi> listBacSi = bacSiService.getAllNhanVien();
        List<ChuyenKhoa> listChuyenKhoa =
        chuyenkhoaService.getAllChuyenKhoa();

        model.addAttribute("dsBacSi", listBacSi);
        model.addAttribute("dsChuyenKhoa", listChuyenKhoa);
        model.addAttribute("bacsi", new BacSi());
        model.addAttribute("isEdit", false);
        return "admin/bacsi";
    }

    // Thêm bác sĩ
    @PostMapping("/add")
    public String addBacSi(@Validated @ModelAttribute("bacsi") BacSi bacsi,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors() || bacsi.getHoTen().trim().isEmpty() || bacsi.getEmail().trim().isEmpty()) {
            model.addAttribute("error", "Không được để trống các trường!");
            model.addAttribute("dsBacSi", bacSiService.getAllNhanVien());
            model.addAttribute("isEdit", false);
            return "/admin/bacsi";
        }

        // Kiểm tra xem mã bác sĩ đã tồn tại chưa
        if (bacSiService.existsById(bacsi.getMaBacSi())) {
            model.addAttribute("error", "Mã bác sĩ đã tồn tại!");
            model.addAttribute("dsBacSi", bacSiService.getAllNhanVien());
            model.addAttribute("isEdit", false);
            return "/admin/bacsi";
        }

        // Kiểm tra email đã tồn tại chưa
        if (bacSiService.existsByEmail(bacsi.getEmail())) {
            model.addAttribute("error", "Email đã tồn tại!");
            model.addAttribute("dsBacSi", bacSiService.getAllNhanVien());
model.addAttribute("isEdit", false);
            return "/admin/bacsi";
        }

        // Xử lý lưu ảnh vào static/imageBS
        if (!file.isEmpty()) {
            try {
                // Tạo tên file duy nhất
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = new File("src/main/resources/static/images/").getAbsolutePath();

                // Tạo thư mục nếu chưa tồn tại
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                // Lưu file vào thư mục
                File destinationFile = new File(uploadFolder, fileName);
                file.transferTo(destinationFile);

                // Chỉ lưu tên file vào database
                bacsi.setHinh(fileName);

            } catch (Exception e) {
                model.addAttribute("error", "Lỗi khi tải ảnh lên!");
                return "/admin/bacsi";
            }
        } else {
            bacsi.setHinh("default.png"); // Ảnh mặc định nếu không tải lên
        }
        // Lưu bác sĩ mới
        bacSiService.save(bacsi);
        redirectAttributes.addFlashAttribute("success", "Bác sĩ đã được thêm thành công!");
        return "redirect:/bacsi";
    }

    // Chỉnh sửa bác sĩ
    @GetMapping("/edit/{id}")
    public String editBacSi(@PathVariable String id, Model model) {
        Optional<BacSi> bacsiOpt = bacSiService.findById(id);
        if (bacsiOpt.isPresent()) {
            model.addAttribute("bacsi", bacsiOpt.get());
            model.addAttribute("isEdit", true);
        } else {
            model.addAttribute("bacsi", new BacSi());
            model.addAttribute("isEdit", false);
        }
        model.addAttribute("dsBacSi", bacSiService.getAllNhanVien());
        model.addAttribute("dsChuyenKhoa",
        chuyenkhoaService.getAllChuyenKhoa());
        return "/admin/bacsi";
    }
    @PostMapping("/update")
    public String updateBacSi(@Validated @ModelAttribute("bacsi") BacSi bacsiEntity,
            BindingResult result,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Model model, RedirectAttributes redirect) {

        if (result.hasErrors() || bacsiEntity.getHoTen().trim().isEmpty()) {
            model.addAttribute("error", "Không được để trống các trường!");
            model.addAttribute("bacsi", bacSiService.getAllNhanVien());
            model.addAttribute("isEdit", true);
            return "/admin/bacsi";
        }

        Optional<BacSi> existingBacSi = bacSiService.findById(bacsiEntity.getMaBacSi());
        if (existingBacSi.isPresent()) {
            BacSi existingEntity = existingBacSi.get();

            // Cập nhật thông tin
            existingEntity.setHoTen(bacsiEntity.getHoTen());
            existingEntity.setEmail(bacsiEntity.getEmail());
            existingEntity.setChuyenKhoa((bacsiEntity.getChuyenKhoa()));
            // existingEntity.setChucvu(bacsiEntity.getChucvu());

            // Kiểm tra xem có ảnh mới được tải lên không
            if (file != null && !file.isEmpty()) {
                try {
                    // Tạo tên file duy nhất
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    String uploadDir = new File("src/main/resources/static/images/").getAbsolutePath();

                    // Tạo thư mục nếu chưa tồn tại
                    File uploadFolder = new File(uploadDir);
                    if (!uploadFolder.exists()) {
                        uploadFolder.mkdirs();
                    }

                    // Lưu file vào thư mục
                    File destinationFile = new File(uploadFolder, fileName);
                    file.transferTo(destinationFile);

                    // Xóa ảnh cũ nếu có
                    if (existingEntity.getHinh() != null && !existingEntity.getHinh().equals("default.png")) {
                        File oldFile = new File(uploadFolder, existingEntity.getHinh());
                        if (oldFile.exists()) {
                            oldFile.delete();
                        }
                    }

                    // Cập nhật ảnh mới vào database
                    existingEntity.setHinh(fileName);
                } catch (Exception e) {
                    model.addAttribute("error", "Lỗi khi tải ảnh lên!");
                    return "/admin/bacsi";
                }
            }

            // Lưu dữ liệu cập nhật vào database
            bacSiService.save(existingEntity);
            redirect.addFlashAttribute("success", "Bác sĩ đã được cập nhật thành công!");
        } else {
            redirect.addFlashAttribute("error", "Không tìm thấy bác sĩ để cập nhật!");
        }

        return "redirect:/bacsi";
    }
// Xóa bác sĩ
    @GetMapping("/delete/{id}")
    public String deleteBacSi(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Optional<BacSi> bacsiOpt = bacSiService.findById(id);
        if (bacsiOpt.isPresent()) {
            bacSiService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Bác sĩ đã được xóa thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bác sĩ để xóa!");
        }
        return "redirect:/bacsi";
    }

    // Tìm kiếm bác sĩ theo tên
    @GetMapping("/search")
    public String searchBacSi(@RequestParam("keyword") String keyword, Model model) {
        List<BacSi> result;
        if (keyword.trim().isEmpty()) {
            result = bacSiService.getAllNhanVien();
        } else {
            result = bacSiService.searchByName(keyword);
        }

        model.addAttribute("dsBacSi", result);
        model.addAttribute("bacsi", new BacSi());
        model.addAttribute("isEdit", false);
        return "/admin/bacsi";
    }
}
