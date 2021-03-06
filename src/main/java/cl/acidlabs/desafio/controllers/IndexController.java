package cl.acidlabs.desafio.controllers;

import cl.acidlabs.desafio.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Value("${url.users}")
    String usersUrl;

    @RequestMapping("${url.index}")
    String index(Model model){
        return "index";
    }

    @RequestMapping( value="${url.users}/{id}", method=RequestMethod.GET )
    public String getUsers(@PathVariable("id") BigInteger id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }
}