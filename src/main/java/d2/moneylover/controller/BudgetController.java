package d2.moneylover.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BudgetController {
    @GetMapping
    public String index(){
        return "/admin/index";
    }


}
