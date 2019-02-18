package Controller;

import DTO.DriverAccountDTO;
import Entity.DriverAccount;
import Repository.DriverAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Driver Account Controller
 *
 * Author: DangNHH - 16/02/2019
 */
@RestController
public class DriverAccountController {
    @Autowired
    DriverAccountRepository driverAccountRepository;

    /**
     * Convert Driver Account DTO from Entity Driver Account
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(DriverAccountDTO dto, DriverAccount entity){
        dto.setId(entity.getDriverID());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCreateDate(entity.getCreateDate());
        dto.setActive(entity.isActive());
    }

    /**
     * Convert Driver Account Entity from Driver Account DTO
     * @param entity
     * @param dto
     */
    public void convertEntityFromDTO(DriverAccount entity, DriverAccountDTO dto){
        entity.setDriverID(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCreateDate(dto.getCreateDate());
        entity.setActive(dto.isActive());
    }

    /**
     * Check Login
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Boolean checkLogin(@RequestParam String email,
                              @RequestParam String password){
        if(driverAccountRepository.findByEmailAndPassword(email, password) != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Get Driver Account By ID
     * @param driverID
     * @return DriverAccountDTO
     */
    @RequestMapping(value = "driver/{driverID}", method = RequestMethod.GET)
    public DriverAccountDTO getDriverAccountByDriverID(@PathVariable Integer driverID){
        DriverAccountDTO dto = new DriverAccountDTO();
        DriverAccount driverAccount = driverAccountRepository.findByDriverID(driverID);
        if(driverAccount != null){
            convertDTOFromEntity(dto,driverAccount);
            return dto;
        }
        return null;
    }

    /**
     * Delete Driver Account By ID
     * @param driverID
     * @return
     */
    @RequestMapping(value = "/{driverID}", method = RequestMethod.DELETE)
    public Boolean deleteDriverAccountByID(@PathVariable Integer driverID){
        DriverAccount driverAccount = driverAccountRepository.findByDriverID(driverID);
        if(driverAccount != null){
            driverAccountRepository.delete(driverAccount);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Create Driver Account
     * @param body
     * @return Success 1, Existed 0, Data invalid -1
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Integer createDriverAccount(@RequestBody Map<String, String> body){
        try{
            String email = body.get("email");
            String password = body.get("password");
            String name = body.get("name");
            String phoneNumber = body.get("phoneNumber");
            Date createDate = new Date();
            DriverAccount tmp = driverAccountRepository.findByEmail(email);
            if(tmp == null){
                //tmp is not existed
                DriverAccount driverAccount = new DriverAccount(email,password,name,phoneNumber,createDate,true);
                driverAccountRepository.save(driverAccount);
                return 1;
            }
            return 0;
        }catch (Exception e) {
            return -1;
        }
    }

    /**
     * Update Driver Account
     * @param body
     * @param driverID
     * @return Success: true, Not Existed: false
     */
    @RequestMapping(value = "/{driverID}", method = RequestMethod.PUT)
    public Boolean updateDriverAccount(@RequestBody Map<String, String> body,
                                       @PathVariable Integer driverID){
        DriverAccount driverAccount = driverAccountRepository.findByDriverID(driverID);
        if(driverAccount != null){
            String name = body.get("name");
            String phoneNumber = body.get("phoneNumber");

            driverAccount.setName(name);
            driverAccount.setPhoneNumber(phoneNumber);
            driverAccountRepository.save(driverAccount);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Change Password
     * @param body
     * @param driverID
     * @return Success: 1, newPass = oldPass: 0, newPass != confirmPass: -1, password invalid: -2
     */
    @RequestMapping(value = "/{driverID}", method = RequestMethod.PUT)
    public Integer changePassword(@RequestBody Map<String, String> body,
                                  @PathVariable Integer driverID){
        DriverAccount driverAccount = driverAccountRepository.findByDriverID(driverID);
        String newPassword = body.get("newPassword");
        String confirmPassword = body.get("confirmPassword");
        String oldPassword = driverAccount.getPassword();

        if(driverAccount != null){
            if(!newPassword.equals(confirmPassword)){
                return -1;
            }else if (newPassword.equals(oldPassword)){
                return 0;
            }else{
                driverAccount.setPassword(newPassword);
                driverAccountRepository.save(driverAccount);
                return 1;
            }
        }
        return -2;
    }
}
