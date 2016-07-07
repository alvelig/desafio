package cl.acidlabs.desafio.controllers;

import cl.acidlabs.desafio.model.services.UserService;
import cl.acidlabs.desafio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @Value("${url.users}/")
    String usersUrl;

    @RequestMapping( value="${url.users}", method=RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody @Valid User user) {

        if(user.getUsername().equals("usuario1")) {

            HttpHeaders headers = new HttpHeaders();

            userService.saveUser(user);

            headers.set("Location", usersUrl + user.getId());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } else {

            return new ResponseEntity("UNAUTHORIZED! Go away!!!", HttpStatus.UNAUTHORIZED);

        }
    }

    @RequestMapping( value="${url.users}/all" )
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
