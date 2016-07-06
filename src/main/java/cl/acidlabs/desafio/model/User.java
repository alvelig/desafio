package cl.acidlabs.desafio.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotEmpty
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @NotEmpty
    @Column(name = "IMAGE", nullable = false)
    private String image;

    public User() {}

    public User(BigInteger id, String username, String image) {
        this.id = id;
        this.username = username;
        this.image = image;
    }

    public User(String username, String image) {
        this.username = username;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigInteger getId() {

        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
