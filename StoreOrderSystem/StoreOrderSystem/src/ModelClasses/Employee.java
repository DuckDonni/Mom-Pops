package ModelClasses;

public class Employee {

    private String username;
    private String password;

    // must include default constructor for object mapper
    public Employee() {}

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
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

}
