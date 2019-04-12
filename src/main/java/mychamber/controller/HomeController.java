package mychamber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value="/")
    public String index(){
        return "index";
    }

    @GetMapping(value="/register")
    public String register(){
        return "register";
    }

/*    @GetMapping(value="/login")
    public String login(){
        return "login";
    }*/

}
