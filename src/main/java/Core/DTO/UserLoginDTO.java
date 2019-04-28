package Core.DTO;

public class UserLoginDTO {
    private Integer id;
    private String email;
    private String password;
    /**
     * 1: web admin
     * 2: web application
     * 3: android
     */
    private Integer system;

    public UserLoginDTO() {
    }

    public UserLoginDTO(Integer id, String email, String password, Integer system) {
        this.id = id;
        this.email = email;
        this.password = password;
        String string;
        this.system = system;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }
}
