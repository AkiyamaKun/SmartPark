package Core.DTO;

public class UserLoginResponseDTO {
    private Integer accountId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String token;
    private Integer roleId;
    private byte[] avatar;
    private Integer cash;

    public UserLoginResponseDTO() {
    }

    public UserLoginResponseDTO(Integer accountId, String email, String firstName,
                                String lastName, String phoneNumber, String token,
                                Integer roleId, byte[] avatar, Integer cash) {
        this.accountId = accountId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.roleId = roleId;
        this.avatar = avatar;
        this.cash = cash;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }
}
