package Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Driver Account Entity
 *
 * Author: DangNHH - 16/02/2019
 */
@Entity
@Table(name = "DriverAccount")
public class DriverAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer driverID;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Date createDate;
    //Active: 1 , deactive: 0
    private boolean isActive;

    public DriverAccount() {
    }

    /**
     * Constructor full arguments
     */
    public DriverAccount(String email, String password, String name, String phoneNumber, Date createDate, boolean isActive) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.isActive = isActive;
    }

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "DriverAccount{" +
                "driverID=" + driverID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createDate=" + createDate +
                ", isActive=" + isActive +
                '}';
    }
}
