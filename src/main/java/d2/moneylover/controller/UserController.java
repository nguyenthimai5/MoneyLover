package d2.moneylover.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping
    public String index() {
        return "/user/index";
    }

    @GetMapping("/about")
    public String about() {
        return "/user/about";
    }

    @GetMapping("/how")
    public String how() {
        return "/user/how";
    }

    @GetMapping("/wallet")
    public String wallet() {
        return "/user/wallet";
    }

    @GetMapping("/add")
    public String addFriend() {
        return "/user/addFriend";
    }

    @GetMapping("/currency")
    public String currencyconversion() {
        return "/user/currencyconversion";
    }
}
