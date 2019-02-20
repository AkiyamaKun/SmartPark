package Core.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Account Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date createdDate;
    //Admin: 1, Supervisor: 2, Driver: 3
    private Integer roleId;
    //Active: 1 , deactive: 0
    private boolean isActive;
    //Token is used verify account
    private String token;

    public Account() {
    }

    /**
     * Constructor full arguments
     */
    public Account(String email, String password, String phoneNumber, String firstName,
                   String middleName, String lastName, Date createdDate, Integer roleId, boolean isActive, String token) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.roleId = roleId;
        this.isActive = isActive;
        this.token = token;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
