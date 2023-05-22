package d2.moneylover.dto;

public class LoginResquest {
    private String name;
    private String password;

    public LoginResquest() {
    }

    public LoginResquest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
