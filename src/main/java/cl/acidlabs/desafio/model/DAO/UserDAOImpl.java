package cl.acidlabs.desafio.model.DAO;

import java.math.BigInteger;
import java.util.List;

import cl.acidlabs.desafio.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("employeeDao")
public class UserDAOImpl extends AbstractDAO implements UserDAO{

    public void saveUser(User user) {
        persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = getSession().createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    public void deleteUserById(BigInteger id) {
        Query query = getSession().createSQLQuery("delete from Users where id = :id");
        query.setBigInteger("id", id);
        query.executeUpdate();
    }


    public User findById(BigInteger id){
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id",id));
        return (User) criteria.uniqueResult();
    }

    public void updateUser(User user){
        getSession().update(user);
    }

}