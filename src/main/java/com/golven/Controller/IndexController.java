package com.golven.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/admin")
    public String admin(){
        return "admin/admin";
    }
    @RequestMapping("/index")
    public String index(){
        //跳转index.html
        return "index";
    }
    @RequestMapping("/front")
    public String front(){
        return "front/home_index";
    }
}
