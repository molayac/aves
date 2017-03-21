package co.com.ias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Marlon Olaya on 19/03/2017.
 */
@Controller
public class ErrorController {
    @RequestMapping("/403")
    @ResponseBody
    public String genericError(){
        return "Something was wrong!!!";
    }
}
