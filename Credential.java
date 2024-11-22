// Get nickname and username for the credentials table view
public class Credential {
    private String nickname;
    private String username;

    public Credential(String nickname, String username) {
        this.nickname = nickname;
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() {
        return username;
    }
}

