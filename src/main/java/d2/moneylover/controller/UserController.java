package d2.moneylover.controller;


import d2.moneylover.dto.LoginResquest;
import d2.moneylover.dto.RegisterResquest;
import d2.moneylover.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("")
    public String index(){
        return "/admin/index";
    }
    @GetMapping(value = {"/","/home"})
    public String homePage(Model model, Authentication authentication){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getName();
        //lấy đối tượng Authentication thông qua tham số của phương thức home()
        if (authentication != null && authentication.isAuthenticated()) {
            //kiểm tra xem người dùng đã được xác thực hay chưa
            String username = authentication.getName();
            model.addAttribute("username", username);
            //Authentication và truyền vào biến model để hiển thị trên giao diện
        }
        return "home";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegisterResquest());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegisterResquest userDto,Model model) {
        System.out.println(userDto.getBirthday());
        userService.registerUser(userDto);
        model.addAttribute("registrationSuccess", true);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user",new LoginResquest());
        return "login"; // Trả về tên của tệp HTML đăng nhập
    }

    @PostMapping("/loginn")
    public String login(@ModelAttribute("user") LoginResquest userDto, Model model) {
        System.out.println(userDto.getName());
        System.out.println("Vào đây");
        // Thực hiện xác thực người dùng và xử lý logic đăng nhập
        return "redirect:/home"; // Chuyển hướng sau khi đăng nhập thành công
    }

}

