package de.htwberlin.webtech.einundzwanzig.web.api;

public class UserCreateRequest {

    private String username;
    private String email;
    private int coins;
    private int wins;
    private int losses;
    private int draws;

    public UserCreateRequest(long id, String username, String email, int coins, int wins, int losses, int draws) {
        this.username = username;
        this.email = email;
        this.coins = coins;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
}

