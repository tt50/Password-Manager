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

    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setNotes(String notes) { this.notes = notes; }
}
