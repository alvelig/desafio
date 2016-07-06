package cl.acidlabs.desafio.model.DAO;

import cl.acidlabs.desafio.model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> findAllUsers();

    void deleteUserById(BigInteger id);

    User findById(BigInteger id);

    void updateUser(User user);

}
