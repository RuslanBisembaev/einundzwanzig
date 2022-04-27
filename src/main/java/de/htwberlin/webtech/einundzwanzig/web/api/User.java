package de.htwberlin.webtech.einundzwanzig.web.api;

public class User {

    private long id;
    private String username;
    private String email;
    private int coins;

    public User(long id, String username, String mail, int coins) {
        this.id = id;
        this.username = username;
        this.email = mail;
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", coins=" + coins +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
