package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.*;
import Core.Entity.Account;
import Core.Service.AccountService;
import Core.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Autowired
    JwtService jwtService;

    /**
     * Convert InformationAccountDTO form AccountDTO
     * @param informationAccountDTO
     * @param accountDTO
     */
    public void convertInformationAccountDTOFromAccountDTO(InformationAccountDTO informationAccountDTO, AccountDTO accountDTO){
        informationAccountDTO.setAccountId(accountDTO.getAccountId());
        informationAccountDTO.setEmail(accountDTO.getEmail());
        informationAccountDTO.setPhoneNumber(accountDTO.getPhoneNumber());
        informationAccountDTO.setFirstName(accountDTO.getFirstName());
        informationAccountDTO.setLastName(accountDTO.getLastName());
        informationAccountDTO.setAvatar(accountDTO.getAvatar());
        informationAccountDTO.setActive(accountDTO.isActive());
    }

    /**
     * Get Account
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO getAccount(@PathVariable Integer id,
                                  HttpServletRequest request){
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
    @RequestMapping(value = Const.REGISTER, method = RequestMethod.POST)
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

    /**
     * Login
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.LOGIN, method = RequestMethod.POST)
    public ResponseDTO login(@RequestBody @Valid UserLoginDTO dto,
                             HttpServletRequest request){
        ResponseDTO responseDTO = accountService.checkLogin(dto);
        if(responseDTO.isStatus()){
            HttpSession session = request.getSession();
            session.setAttribute("User" , responseDTO.getObjectResponse());
        }
        return responseDTO;
    }

}
