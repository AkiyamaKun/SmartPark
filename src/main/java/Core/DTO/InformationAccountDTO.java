package Core.DTO;


public class InformationAccountDTO {
    private Integer accountId;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String avatar;
    private boolean isActive;

    public InformationAccountDTO() {
    }

    public InformationAccountDTO(Integer accountId, String email, String phoneNumber,
                                 String firstName, String lastName, String avatar, boolean isActive) {
        this.accountId = accountId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.isActive = isActive;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
