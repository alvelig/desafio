package cl.acidlabs.desafio;

import cl.acidlabs.desafio.controllers.UsersController;
import cl.acidlabs.desafio.model.User;
import com.sun.deploy.net.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.net.URL;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@PropertySource("classpath:application.properties")
public class DemoApplicationTests {

    @Value("${local.server.port}")
    private int port;

	//private MockMvc mvc;
    private URL base;

    @Value("${url.users}")
    private String users;

    private String usersurl;

    private RestTemplate template;

    static User userok = new User("usuario1", "imagepath");
    static User user401 = new User("usuario401", "imagepath");
    static User testuser = new User("usuario1", "imagepath");

	@Before
	public void setUp() throws Exception {

        base = new URL("http://localhost:" + port + "/");

        usersurl = base + users;

        template = new TestRestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        template.getMessageConverters().add(new StringHttpMessageConverter());

	}

	/* http://localhost:8080/users */
	@Test
	public void Users_shouldNotAcceptGET() throws Exception {
        /* Este servicio debe recibir un request POST */
        ResponseEntity<String> response = template.getForEntity( usersurl, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.METHOD_NOT_ALLOWED));
    }

    /*
		Este servicio debe recibir un request POST con el siguiente contenido JSON:
		{
		  “username”: String, "image": String
		}
		Si se trata de un usuario autorizado y los parámetros image y username contienen datos,
		se procederá a persistir esta información, en cuyo caso el webservice retornara
		código 201 (Created) sin body en el response, pero con el header location, el cual contiene la url donde se podrá consultar la información.
    */

    @Test
    public void Users_shouldReturn201andEmpty() throws Exception {
        ResponseEntity<String> response = template.postForEntity(usersurl, userok, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody(), equalTo(null));
    }

    /*
		cualquier otro nombre de usuario, se espera que el webservice devuelva 401 (Unauthorized).
    */
    @Test
    public void Users_shouldReturn401forUnauthorized() throws Exception {
        ResponseEntity<String> response = template.postForEntity(usersurl, user401 , String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

    /*
        Si alguno de los parámetros no contiene datos se retorna 400 (Bad Request)
     */
    @Test
    public void Users_shouldReturn400ifAnyFieldAbsent() throws Exception {

        testuser.setImage(null);
        assertThat(template.postForEntity(usersurl, testuser, String.class).getStatusCode(),
                equalTo(HttpStatus.BAD_REQUEST));

        testuser.setUsername(null);
        assertThat(template.postForEntity(usersurl, testuser, String.class).getStatusCode(),
                equalTo(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void Users_shouldReturn200andUser() throws Exception {
        ResponseEntity<User> response = template.getForEntity(usersurl + "/1", User.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getId(), equalTo(BigInteger.ONE));
        assertThat(response.getBody().getUsername(), equalTo("usuario1"));

        testuser.setUsername(null);
        assertThat(template.postForEntity(usersurl, testuser, String.class).getStatusCode(),
                equalTo(HttpStatus.BAD_REQUEST));
    }
}
