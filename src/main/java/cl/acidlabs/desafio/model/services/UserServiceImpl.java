package cl.acidlabs.desafio.model.services;

import cl.acidlabs.desafio.model.DAO.UserDAO;
import cl.acidlabs.desafio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO dao;

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public void deleteUserById(BigInteger id) {
        dao.deleteUserById(id);
    }

    public User findById(BigInteger id) {
        return dao.findById(id);
    }

    public void updateUser(User user){
        dao.updateUser(user);
    }

}