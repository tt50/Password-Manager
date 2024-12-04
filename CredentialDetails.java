public class CredentialDetails {
    private String nickname;
    private String username;
    private String password;
    private String notes;

    // Constructor
    public CredentialDetails(String nickname, String username, String password, String notes) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    // Getters and Setters
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
