package Core.DTO;

import java.util.Date;

/**
 * Account DTO
 *
 * Author: DangNHH - 16/02/2019
 */
public class AccountDTO{
    private Integer accountId;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Date createdDate;
    private Integer roleId;
    private boolean isActive;
    private byte[] avatar;
    private String token;
    private Integer cash;
    private String plateNumber;

    public AccountDTO() {
    }

    /**
     * Constructor full arguments
     * @param accountId
     * @param email
     * @param password
     * @param phoneNumber
     * @param firstName
     * @param lastName
     * @param createdDate
     * @param roleId
     * @param isActive
     */
    public AccountDTO(Integer accountId, String email, String password, String phoneNumber, String firstName,
                      String lastName, Date createdDate, Integer roleId, boolean isActive, byte[] avatar, String token,
                      Integer cash, String plateNumber) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.roleId = roleId;
        this.isActive = isActive;
        this.avatar = avatar;
        this.token = token;
        this.cash = cash;
        this.plateNumber = plateNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
