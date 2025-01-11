package com.example.quanlybenhvien.Controller;

import com.example.quanlybenhvien.Dao.BenhNhanDao;
import com.example.quanlybenhvien.Entity.BenhNhan;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BenhNhanController {
    @Autowired
    private BenhNhanDao benhNhanDao;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // @PostMapping("/login")
    // public String login(@RequestParam("sodienthoai") String sodienthoai,
    // @RequestParam("mat_khau") String matKhau,
    // HttpSession session, Model model) {
    // System.out.println("Đã gọi đến Controller");
    // System.out.println("Số điện thoại: " + sodienthoai);
    // System.out.println("Mật khẩu: " + matKhau);

    // NguoiDung nguoiDung = nguoiDungService.login(sodienthoai, matKhau);

    // if (nguoiDung != null) {
    // session.setAttribute("nguoiDung", nguoiDung);
    // return "redirect:/index"; // Chuyển hướng đến trang index
    // } else {
    // model.addAttribute("error", "Số điện thoại hoặc mật khẩu không đúng.");
    // return "login"; // Quay lại trang đăng nhập
    // }
    // }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User principal, Model model) {
        // Lấy thông tin từ Google OAuth2User
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");
        String picture = principal.getAttribute("picture");

        // Kiểm tra người dùng trong cơ sở dữ liệu
        BenhNhan user = benhNhanDao.findBenhNhanByEmail(email).orElse(null);

        if (user == null) {
            // Nếu chưa tồn tại, tạo mới người dùng
            user = new BenhNhan();
            user.setEmail(email);
            user.setHoten(name);
            user.setHinh(picture);
            benhNhanDao.save(user);
        }

        // Thêm thông tin người dùng vào model để hiển thị
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
