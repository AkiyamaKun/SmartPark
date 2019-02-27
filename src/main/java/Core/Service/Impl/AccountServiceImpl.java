package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.ParkingLotDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.Role;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.RoleRepository;
import Core.Service.AccountService;
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
                    }else{
                        account.setPassword(newPassword);
                        accountRepository.save(account);
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
     * Authorize Account
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO authorize(AccountDTO accountDTO) {
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
                accountRepository.save(account);
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
     * Delete Account
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteAccount(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(id);
            if(account != null){
                accountRepository.delete(account);
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
     * Create Account by RoleId
     * Manager Account: 1
     * Supervisor Account: 2
     * @param roleAccount
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO createAccount(Integer roleAccount, AccountDTO accountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Role role = roleRepository.findByRoleId(roleAccount);
            if(role != null){
                if(accountDTO != null){
                    Account accountExisted = accountRepository.findByEmail(accountDTO.getEmail());
                    if(accountExisted != null){
                        responseDTO.setMessage(Const.ACCOUNT_IS_EXISTED);
                        return responseDTO;
                    }
                    Account account = new Account();
                    Date date = new Date();
                    account.setEmail(accountDTO.getEmail());
                    account.setPassword(accountDTO.getPassword());
                    account.setCreatedDate(date);
                    account.setFirstName(accountDTO.getFirstName());
                    account.setLastName(accountDTO.getLastName());
                    account.setPhoneNumber(accountDTO.getPhoneNumber());
                    account.setActive(true);
                    account.setRole(role);
                    accountRepository.save(account);
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                    responseDTO.setObjectResponse(account);
                }else{
                    responseDTO.setMessage(Const.LACK_OF_DATA);
                }
            }else{
                responseDTO.setMessage(Const.ROLE_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.CREATE_ACCOUNT_FAIL);
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
}
