package cl.acidlabs.desafio.model.DAO;

/**
 * Created by alex on 7/6/16.
 */
import cl.acidlabs.desafio.model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserDAO {

    void saveUser(User user);

    List<User> findAllUsers();

    void deleteUserById(BigInteger id);

    User findById(BigInteger id);

    void updateUser(User user);
}