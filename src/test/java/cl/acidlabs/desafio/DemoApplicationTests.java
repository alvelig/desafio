package cl.acidlabs.desafio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	private MockMvc mvc;

    static User userok = new User("usuario1", "imagepath");
    static User user401 = new User("usuario401", "imagepath");
    static User testuser = new User("usuario1", "imagepath");

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new UsersController()).build();
	}

	/* http://localhost:8080/users */
	@Test
	public void Users_shouldNotAcceptGET() throws Exception {
		/* Este servicio debe recibir un request POST */
        mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(405));
    }


    /*
		Este servicio debe recibir un request POST con el siguiente contenido JSON:
		{
		  “username”: String, "image": String
		}
		Si se trata de un usuario autorizado y los parámetros image y username contienen datos, se procederá a persistir esta información, en cuyo caso el webservice retornara código 201 (Created) sin body en el response, pero con el header location, el cual contiene la url donde se podrá consultar la información.
    */
    @Test
    public void Users_shouldReturn201andEmpty() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userok))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect( content().string(equalTo("")) )
                .andExpect( status().is(201) );

    }

    /*
		cualquier otro nombre de usuario, se espera que el webservice devuelva 401 (Unauthorized).
    */
    @Test
    public void Users_shouldReturn401forUnauthorized() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(user401))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().is(401) );

    }

    /*
        Si alguno de los parámetros no contiene datos se retorna 400 (Bad Request)
     */
    @Test
    public void Users_shouldReturn400ifAnyFieldAbsent() throws Exception {

        testuser.setImage(null);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testuser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

        testuser.setUsername(null);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testuser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }
}
