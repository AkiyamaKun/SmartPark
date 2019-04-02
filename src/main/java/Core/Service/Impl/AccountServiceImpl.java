package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.*;
import Core.Entity.Account;
import Core.Entity.Role;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.RoleRepository;
import Core.Service.AccountService;
import Core.Service.JwtService;
import Core.Service.PublicService;
import Core.Utils.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Account Service Implements
 *
 * Author: DangNHH - 19/02/2019
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PublicService publicService;

    @Autowired
    JwtService jwtService;

    /**
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(AccountDTO dto, Account entity){
        dto.setAccountId(entity.getAccountId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRoleId(entity.getRole().getRoleId());
        dto.setActive(entity.isActive());
        dto.setAvatar(entity.getAvatar());
        dto.setToken(entity.getToken());
    }

    /**
     * Change Password for Account
     * @param changePasswordDTO
     * @return
     */
    @Override
    public ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(changePasswordDTO.getAccountId());
            if(account != null){
                if(changePasswordDTO != null){
                    String oldPassword = changePasswordDTO.getOldPassword();
                    String newPassword = changePasswordDTO.getNewPassword();
                    String confirmPassword = changePasswordDTO.getConfirmPassword();
                    if(newPassword.equals(oldPassword)){
                        responseDTO.setMessage(Const.PASSWORD_IDENTICAL);
                    }else if(!newPassword.equals(confirmPassword)){
                        responseDTO.setMessage(Const.PASSWORD_CONFIRM_FAIL);
                    }else if (!oldPassword.equalsIgnoreCase(account.getPassword())){
                        responseDTO.setMessage(Const.PASSWORD_OLD_IS_NOT_EXACTLY);
                    }else{
                        account.setPassword(newPassword);
                        accountRepository.save(account);
                        account.setPassword(null);
                        responseDTO.setStatus(true);
                        responseDTO.setMessage(Const.CHANGE_PASSWORD_SUCCESS);
                        responseDTO.setObjectResponse(account);
                    }
                }else{
                    responseDTO.setMessage(Const.LACK_OF_DATA);
                }
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Change Password Exception: " + e.getMessage());

        }
        return responseDTO;
    }

    /**
     * Check Login
     * @param dto
     * @return
     */
    @Override
    public ResponseDTO checkLogin(UserLoginDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        Account account = accountRepository.findByEmail(dto.getEmail());
        if(account != null){
            if(account.getPassword().equals(dto.getPassword()) && account.isActive()){
                UserLoginResponseDTO user = new UserLoginResponseDTO();
                user.setAccountId(account.getAccountId());
                user.setEmail(account.getEmail());
                user.setFirstName(account.getFirstName());
                user.setLastName(account.getLastName());
                user.setPhoneNumber(account.getPhoneNumber());
                user.setRoleId(account.getRole().getRoleId());
                String token = jwtService.generateTokenLogin(account.getEmail());
                user.setToken(token);
                user.setAvatar(account.getAvatar());

                responseDTO.setStatus(true);
                responseDTO.setMessage("Login Successful");
                responseDTO.setObjectResponse(user);
            }else{
                responseDTO.setMessage("Wrong userId and password");
            }
        }
        return responseDTO;
    }

    /**
     * Authentication
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO authentication(AccountDTO accountDTO) {
        return null;
    }

    /**
     * Get Information Account
     * @param id
     * @return
     */
    @Override
    public ResponseDTO getAccount(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(id);
            if(account != null){
                AccountDTO dto = new AccountDTO();
                convertDTOFromEntity(dto, account);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_ACCOUNT_SUCCESS);
                responseDTO.setObjectResponse(dto);
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.GET_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Update Driver Account
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO updateAccount(Integer id, AccountDTO accountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(id);
            if(account != null){
                account.setFirstName(accountDTO.getFirstName());
                account.setLastName(accountDTO.getLastName());
                account.setPhoneNumber(accountDTO.getPhoneNumber());
                //Update Avatar only for Driver Account
                if(account.getRole().getRoleId() == 3){
                    account.setAvatar(accountDTO.getAvatar());
                    if(accountDTO.getAvatar() != null)
                        account.setAvatar(accountDTO.getAvatar());
                    //else
                        //account.setAvatar(Const.AVATAR_NAME_DEFAULT);
                }
                accountRepository.save(account);
                account.setPassword(null);
                AccountDTO dto = new AccountDTO();
                convertDTOFromEntity(dto, account);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.UPDATE_ACCOUNT_SUCCESS);
                responseDTO.setObjectResponse(dto);
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.UPDATE_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Deactive Account
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deactiveAccount(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(id);
            if(account != null){
                account.setActive(false);
                accountRepository.save(account);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.DELETE_ACCOUNT_SUCCESS);
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.DELETE_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Active Account
     * @param id
     * @return
     */
    @Override
    public ResponseDTO activeAccount(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(id);
            if(account != null){
                account.setActive(true);
                accountRepository.save(account);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.ACTIVATE_ACCOUNT_SUCCESS);
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.ACTIVATE_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Get List Account by Role Id
     * @param roleId
     * @return
     */
    @Override
    public ResponseDTO getListAccount(Integer roleId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Role role = roleRepository.findByRoleId(roleId);
            if(role != null){
                List<Account> accounts = accountRepository.findAllByRole(role);
                if(!accounts.isEmpty()){
                    List<AccountDTO> accountDTOS = new ArrayList<>();
                    for(Account account : accounts){
                        AccountDTO tmp = new AccountDTO();
                        convertDTOFromEntity(tmp, account);
                        accountDTOS.add(tmp);
                    }
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.GET_LIST_ACCOUNTS_SUCCESS);
                    responseDTO.setObjectResponse(accountDTOS);
                }else{
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
                }
            }else{
                responseDTO.setMessage(Const.ROLE_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get List Account Exception: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Register Account
     * Admin: 1
     * Supervisor: 2
     * Driver: 3
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO registerAccount(AccountDTO accountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        responseDTO.setMessage("Nothing");
        Integer roleAccount = accountDTO.getRoleId();
        try{
            Account account = accountRepository.findByEmail(accountDTO.getEmail());
            String token = Utilities.generateToken(accountDTO.getEmail());
            Date createDate = new Date();
            Role role = roleRepository.findByRoleId(roleAccount);
            if(account == null ){
                //Account is not existed
                ResponseDTO tmp = publicService.sendEmail(accountDTO.getEmail(), token, roleAccount);
                if(tmp != null){
                    if(tmp.isStatus()){
                        //Create New Account
                        account = new Account();
                        account.setEmail(accountDTO.getEmail());
                        account.setPassword(accountDTO.getPassword());
                        account.setPhoneNumber(accountDTO.getPhoneNumber());
                        account.setFirstName(accountDTO.getFirstName());
                        account.setLastName(accountDTO.getLastName());
                        account.setCreatedDate(createDate);
                        account.setRole(role);
                        account.setActive(false);
                        account.setToken(token);
                        //Avatar for Driver Account
                        if(role.getRoleId() == 3)
                        {
                            if(accountDTO.getAvatar() != null)
                                account.setAvatar(accountDTO.getAvatar());
                            //else
                                //account.setAvatar(Const.AVATAR_NAME_DEFAULT);
                        }
                        responseDTO.setStatus(true);
                        responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                        responseDTO.setObjectResponse(account);
                        accountRepository.save(account);
                    }else{
                        responseDTO.setStatus(false);
                        responseDTO.setMessage(Const.SEND_EMAIL_CREATE_ACCOUNT_ERROR);
                    }
                }
            }else{
                //Account is existed on Database
                String lastToken = account.getToken();
                if(lastToken == null){
                    //Account is created ->response fail
                    responseDTO.setStatus(false);
                    responseDTO.setMessage(Const.ACCOUNT_IS_EXISTED);
                }else{
                    //Account had create but it is not verify -> Excute update and send again Mail Verify
                    ResponseDTO tmp = publicService.sendEmail(account.getEmail(), token, roleAccount);
                    if(tmp != null){
                        if(tmp.isStatus()){
                            account.setToken(token);
                            account.setPassword(accountDTO.getPassword());
                            account.setPhoneNumber(accountDTO.getPhoneNumber());
                            account.setFirstName(accountDTO.getFirstName());
                            account.setLastName(accountDTO.getLastName());
                            account.setCreatedDate(createDate);
                            account.setRole(role);
                            //Avatar for Driver Account
                            if(role.getRoleId() == 3)
                            {
                                if(accountDTO.getAvatar() != null)
                                    account.setAvatar(accountDTO.getAvatar());
                                //else
                                    //account.setAvatar(Const.AVATAR_NAME_DEFAULT);
                            }
                            accountRepository.save(account);

                            AccountDTO dto = new AccountDTO();
                            convertDTOFromEntity(dto, account);

                            responseDTO.setStatus(true);
                            responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                            responseDTO.setObjectResponse(dto);
                        }
                    }else{
                        responseDTO.setStatus(false);
                        responseDTO.setMessage(Const.SEND_EMAIL_CREATE_ACCOUNT_ERROR);
                    }
                }
            }
        }catch (Exception e){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.CREATE_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Set First Password For Admin/Supervisor Account
     * @param email
     * @param password
     * @return
     */
    @Override
    public ResponseDTO setFirstPassword(String email, String password) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        Account account = accountRepository.findByEmail(email);
        if(account != null){
            account.setPassword(password);
            account.setToken(null);
            account.setActive(true);
            accountRepository.save(account);
            AccountDTO accountDTO = new AccountDTO();
            convertDTOFromEntity(accountDTO,account);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.SET_PASSWORD_SUCCESS);

            responseDTO.setObjectResponse(accountDTO);
        }else{
            responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO forgetPassword(String email, Integer type) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByEmail(email);
            if(account != null){
                String token = Utilities.generateToken(email);
                //Type 5: forget password of admin and supervisor account
                ResponseDTO tmp = publicService.sendEmail(email, token, type);
                if(tmp != null){
                    if(tmp.isStatus()){
                        account.setToken(token);
                        accountRepository.save(account);
                        responseDTO.setStatus(true);
                        responseDTO.setMessage(Const.SEND_MESSAGE_SET_FIRST_PASSWORD);
                    }else{
                        responseDTO.setStatus(false);
                        responseDTO.setMessage(Const.SEND_EMAIL_SET_NEW_PASSWORD_FAIL);
                    }
                }

            }
        }catch (Exception e){
            responseDTO.setMessage("Error : " + e.getMessage());
        }
        return responseDTO;
    }
}
