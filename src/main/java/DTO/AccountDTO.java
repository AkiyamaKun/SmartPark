package DTO;

import java.util.Date;

/**
 * Abstract Class Account DTO
 *
 * Author: DangNHH - 16/02/2019
 */
public abstract class AccountDTO {
    private Integer id;
    private String name;
    private String password;
    private String phoneNumber;
    private Date createDate;
    private boolean isActive;

    public AccountDTO() {
    }

    /**
     * Constructor with full arguments
     * @param id
     * @param name
     * @param password
     * @param phoneNumber
     * @param createDate
     * @param isActive
     */
    public AccountDTO(Integer id, String name, String password, String phoneNumber, Date createDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
