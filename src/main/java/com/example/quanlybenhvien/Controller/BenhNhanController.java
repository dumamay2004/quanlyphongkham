package com.example.quanlybenhvien.Controller;

import com.example.quanlybenhvien.Dao.BenhNhanDao;
import com.example.quanlybenhvien.Entity.BenhNhan;
import com.example.quanlybenhvien.Service.BenhNhanService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenhNhanController {
    @Autowired
    private BenhNhanDao benhNhanDao;

    @Autowired
    private BenhNhanService benhNhanService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login_1(@RequestParam("email") String email,
            @RequestParam("matkhau") String matKhau,
            HttpSession session,
            Model model) {
        // Kiểm tra người dùng trong cơ sở dữ liệu
        BenhNhan user = benhNhanDao.findBenhNhanByEmail(email).orElse(null);
        if (user != null) {
            // Kiểm tra mật khẩu
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(matKhau, user.getMatkhau())) {
                session.setAttribute("user", user);
                return "redirect:/index";
            } else {
                model.addAttribute("error", "Mật khẩu không đúng.");
                return "login";
            }
        } else {

            model.addAttribute("error", "Email không tồn tại.");
            return "login";
        }
    }

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

    @GetMapping("/dangky")
    public String dangky() {
        return "dangky";
    }

    @PostMapping("/dangky")
    public String dangky(BenhNhan benhNhan, Model model) {
        // Kiểm tra và xử lý đăng ký
        String message = benhNhanService.dangKyNguoiDung(benhNhan);

        if (message.equals("Đăng ký thành công!")) {
            return "redirect:/index";
        } else {
            model.addAttribute("error", message);
            return "dangky"; // Trả về lại trang đăng ký
        }
    }
}
