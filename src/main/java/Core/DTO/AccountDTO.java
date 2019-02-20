package Core.DTO;

import java.util.Date;

/**
 * Account DTO
 *
 * Author: DangNHH - 16/02/2019
 */
public class AccountDTO {
    private Integer id;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date createdDate;
    private Integer roleId;
    private boolean isActive;

    public AccountDTO() {
    }

    /**
     * Constructor full arguments
     * @param id
     * @param email
     * @param password
     * @param phoneNumber
     * @param firstName
     * @param middleName
     * @param lastName
     * @param createdDate
     * @param roleId
     * @param isActive
     */
    public AccountDTO(Integer id, String email, String password, String phoneNumber,
                      String firstName, String middleName, String lastName,
                      Date createdDate, Integer roleId, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.roleId = roleId;
        this.isActive = isActive;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
}
