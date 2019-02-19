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
    private Date createDate;
    private Integer role;
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
     * @param createDate
     * @param role
     * @param isActive
     */
    public AccountDTO(Integer id, String email, String password, String phoneNumber,
                      String firstName, String middleName, String lastName,
                      Date createDate, Integer role, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.createDate = createDate;
        this.role = role;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
