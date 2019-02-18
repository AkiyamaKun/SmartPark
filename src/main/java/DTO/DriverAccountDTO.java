package DTO;

import Entity.DriverAccount;

import java.util.Date;

/**
 *  Class Driver Account DTO
 *
 * Author: DangNHH - 16/02/2019
 */
public class DriverAccountDTO extends AccountDTO {
    private String email;

    public DriverAccountDTO() {
    }

    /**
     * Constructor with full arguments
     * @param id
     * @param name
     * @param password
     * @param phoneNumber
     * @param createDate
     * @param isActive
     * @param email
     */
    public DriverAccountDTO(Integer id, String name, String password, String phoneNumber, Date createDate, boolean isActive, String email) {
        super(id, name, password, phoneNumber, createDate, isActive);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
