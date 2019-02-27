package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.InformationAccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Account Controller
 *
 * Author: DangNHH - 17/02/2019
 */

@RestController
@RequestMapping(value = Const.ACCOUNT)
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     * Convert InformationAccountDTO form AccountDTO
     * @param informationAccountDTO
     * @param accountDTO
     */
    public void convertInformationAccountDTOFromAccountDTO(InformationAccountDTO informationAccountDTO, AccountDTO accountDTO){
        informationAccountDTO.setAccountId(accountDTO.getAccountId());
        informationAccountDTO.setEmail(accountDTO.getEmail());
        informationAccountDTO.setPassword(accountDTO.getPassword());
        informationAccountDTO.setPhoneNumber(accountDTO.getPhoneNumber());
        informationAccountDTO.setFirstName(accountDTO.getFirstName());
        informationAccountDTO.setLastName(accountDTO.getLastName());
    }

    /**
     * Get Account
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO getAccount(@PathVariable Integer id){
        ResponseDTO responseDTO = accountService.getAccount(id);
        if(responseDTO.isStatus()){
            InformationAccountDTO dto = new InformationAccountDTO();
            convertInformationAccountDTOFromAccountDTO(dto, (AccountDTO)responseDTO.getObjectResponse());
            responseDTO.setObjectResponse(dto);
        }
        return responseDTO;
    }

    /**
     * Update Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.UPDATE_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO,
                                     @PathVariable Integer id){
        ResponseDTO responseDTO = accountService.updateAccount(id, accountDTO);
        if(responseDTO.isStatus()){
            InformationAccountDTO dto = new InformationAccountDTO();
            convertInformationAccountDTOFromAccountDTO(dto, (AccountDTO)responseDTO.getObjectResponse());
            responseDTO.setObjectResponse(dto);
        }
        return responseDTO;
    }

    /**
     * Delete Account
     * @param id
     * @return
     */
    @RequestMapping(value = Const.DELETE_ACCOUNT, method = RequestMethod.DELETE)
    public ResponseDTO deleteAccount(@PathVariable Integer id){
        return accountService.deleteAccount(id);
    }

    /**
     * Get List Account
     * @param roleId
     * @return
     */
    @RequestMapping(value = Const.LIST_ACCOUNTS, method = RequestMethod.GET)
    public ResponseDTO getListAccount(@PathVariable Integer roleId){
        return accountService.getListAccount(roleId);
    }

    /**
     * Change Password
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.CHANGE_PASSWORD, method = RequestMethod.PUT)
    public ResponseDTO changePassword(@RequestBody @Valid ChangePasswordDTO dto){
        return accountService.changePassword(dto);
    }

    /**
     * Register Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.REGISTER, method = RequestMethod.PUT)
    public ResponseDTO register(@RequestBody @Valid AccountDTO accountDTO){
        ResponseDTO responseDTO = accountService.registerAccount(accountDTO);
        return responseDTO;
    }

    /**
     * Set First Password for Admin/Supervisor Account
     * @param email
     * @param password
     * @param passwordConfirm
     * @return
     */
    @RequestMapping(value = Const.SET_FIRST_PASSWORD, method = RequestMethod.PUT)
    public ResponseDTO setFirstPassword(@RequestParam(value = "email", required = true) String email,
                                        @RequestParam(value = "password", required = true) String password,
                                        @RequestParam(value = "passwordConfirm", required = true) String passwordConfirm) {
        ResponseDTO responseDTO = new ResponseDTO();
        if(!password.equals(passwordConfirm)){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.PASSWORD_CONFIRM_FAIL);
        }else{
            responseDTO = accountService.setFirstPassword(email,password);
        }
        return responseDTO;
    }

}
