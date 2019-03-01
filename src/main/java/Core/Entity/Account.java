package Core.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Column(name = "accountId")
    private Integer accountId;

    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "phoneNumber", columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    @Column(name = "password", columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(name = "firstName", columnDefinition = "NVARCHAR(50)")
    private String firstName;

    @Column(name = "lastName", columnDefinition = "NVARCHAR(50)")
    private String lastName;

    @Column(name = "createdDate", columnDefinition = "DATETIME")
    private Date createdDate;

    //Admin: 1, Supervisor: 2, Driver: 3
    @OneToOne(targetEntity=Role.class,cascade=CascadeType.ALL)
    @JoinColumn(name="roleId",referencedColumnName="roleId")
    private Role role;

    //Active: 1 , deactive: 0
    @Column(name = "isActive", columnDefinition = "BIT")
    private boolean isActive;

    //Token is used verify account
    @Column(name = "token", columnDefinition = "VARCHAR(100)")
    private String token;

    public Account() {
    }

    /**
     * Constructor full arguments
     */
    public Account(String email, String phoneNumber, String password,
                   String firstName, String lastName, Date createdDate,
                   Role role, boolean isActive, String token) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.role = role;
        this.isActive = isActive;
        this.token = token;
    }

    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return authorities;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
