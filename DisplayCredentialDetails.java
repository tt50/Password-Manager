public class DisplayCredentialDetails {
    private String nickname;
    private String username;
    private String password;
    private String notes;

    public DisplayCredentialDetails(String nickname, String username, String password, String notes) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNotes() {
        return notes;
    }
}
