package cl.acidlabs.desafio;

/**
 * Created by alex on 7/5/16.
 */
public class User {

    private long id;
    private String username;
    private String image;

    public User() {}

    public User(long id, String username, String image) {
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

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
