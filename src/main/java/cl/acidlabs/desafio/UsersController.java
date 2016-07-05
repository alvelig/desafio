package cl.acidlabs.desafio;

import ch.qos.logback.classic.Logger;
import org.apache.commons.logging.Log;
import org.omg.CORBA.Object;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alex on 7/5/16.
 */
@RestController
public class UsersController {

    @RequestMapping(
            value="/users",
            method=RequestMethod.POST
    )
    public ResponseEntity<?> postUser(@RequestBody User user) {

        if(user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getImage() == null || user.getImage().isEmpty()) {

            return new ResponseEntity("Missing required fields! Go away!!!", HttpStatus.BAD_REQUEST);

        }

        if(user.getUsername().equals("usuario1")) {

            HttpHeaders headers = new HttpHeaders();

            //TODO: persistence layer

            headers.set("Location", "/users/" + 1);

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } else {

            return new ResponseEntity("UNAUTHORIZED! Go away!!!", HttpStatus.UNAUTHORIZED);

        }
    }

}
