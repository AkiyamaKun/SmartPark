package Core.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ownerId")
    private Integer ownerId;


    @Column(name = "firstName", columnDefinition = "NVARCHAR(50)")
    private String firstName;

    @Column(name = "lastName", columnDefinition = "NVARCHAR(50)")
    private String lastName;

    @Column(name = "sex", columnDefinition = "NVARCHAR(50)")
    private String sex;

    @Column(name = "address", columnDefinition = "NVARCHAR(250)")
    private String address;

    @Column(name = "phoneNumber", columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    @Column(name = "yearOfBirth", columnDefinition = "DATETIME")
    private Date yearOfBirth;

    @Column(name = "createdDate", columnDefinition = "DATETIME")
    private Date createdDate;

    //Active: 1 , deactive: 0
    @Column(name = "isActive", columnDefinition = "BIT")
    private boolean isActive;

    public Owner() {
    }

    public Owner(String firstName, String lastName, String sex, String address,
                 String phoneNumber, Date yearOfBirth, Date createdDate, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.yearOfBirth = yearOfBirth;
        this.createdDate = createdDate;
        this.isActive = isActive;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Date yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
