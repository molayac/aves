package co.com.ias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marlon Olaya on 19/03/2017.
 */
@Controller
public class MainController {

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/about")
    public String about(){
        return "about";
    }

}
