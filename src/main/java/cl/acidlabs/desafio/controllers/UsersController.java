package cl.acidlabs.desafio.controllers;

import cl.acidlabs.desafio.model.DAO.UserService;
import cl.acidlabs.desafio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;

/**
 * Created by alex on 7/5/16.
 */
@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @RequestMapping( value="${url.users}", method=RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody @Valid User user) {

        if(user.getUsername().equals("usuario1")) {

            HttpHeaders headers = new HttpHeaders();

            userService.saveUser(user);

            headers.set("Location", "/users/" + user.getId());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } else {

            return new ResponseEntity("UNAUTHORIZED! Go away!!!", HttpStatus.UNAUTHORIZED);

        }
    }

    @RequestMapping( value="${url.users}/{id}" )
    public ResponseEntity<?> getUsers(@PathVariable("id") BigInteger id) {
        if(id == null) {
            return ResponseEntity.ok(userService.findAllUsers());
        } else {
            return ResponseEntity.ok(userService.findById(id));
        }
    }

    @RequestMapping( value="${url.users}/all" )
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
