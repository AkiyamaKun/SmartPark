package Core.DTO;


public class InformationAccountDTO {
    private Integer accountId;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private byte[] avatar;
    private boolean isActive;
    private Integer cash;

    public InformationAccountDTO() {
    }

    public InformationAccountDTO(Integer accountId, String email, String phoneNumber,
                                 String firstName, String lastName, byte[] avatar, boolean isActive, Integer cash) {
        this.accountId = accountId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.isActive = isActive;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }
}
