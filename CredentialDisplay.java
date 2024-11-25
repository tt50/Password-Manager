// Get nickname and username for the credentials table view
public class CredentialDisplay {
    private String nickname;
    private String username;

    public CredentialDisplay(String nickname, String username) {
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

