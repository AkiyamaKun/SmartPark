package Core.Entity;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Account Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_date")
    private Date createdDate;

    //Admin: 1, Supervisor: 2, Driver: 3
    @OneToOne(targetEntity=Role.class)
    @JoinColumn(name="role_id",referencedColumnName="role_id")
    private Role role;

    //Active: 1 , deactive: 0
    @Column(name = "is_active", columnDefinition = "TINYINT(1)")
    private boolean isActive;

    //Token is used verify account
    @Column(name = "token")
    private String token;

    //Image of driver user avatar
    @Column(name = "avatar", columnDefinition = "MEDIUMBLOB")
    private byte[] avatar;

    @Column(name = "cash")
    private Integer cash;

    @Column(name = "plate_number")
    private String plateNumber;

    public Account() {
    }

    /**
     * Constructor full arguments
     */
    public Account(String email, String phoneNumber, String password,
                   String firstName, String lastName, Date createdDate,
                   Role role, boolean isActive, String token, byte[] avatar) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.role = role;
        this.isActive = isActive;
        this.token = token;
        this.avatar = avatar;
    }

    public Account(String email, String phoneNumber, String password, String firstName,
                   String lastName, Date createdDate, Role role, boolean isActive,
                   String token, byte[] avatar, Integer cash, String plateNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.role = role;
        this.isActive = isActive;
        this.token = token;
        this.avatar = avatar;
        this.cash = cash;
        this.plateNumber = plateNumber;
    }

//    public List<GrantedAuthority> getAuthorities(){
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        return authorities;
//    }

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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
