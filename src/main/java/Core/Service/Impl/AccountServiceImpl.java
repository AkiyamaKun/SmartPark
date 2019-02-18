package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Account Service Implements
 *
 * Author: DangNHH - 19/02/2019
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    /**
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(AccountDTO dto, Account entity){
        dto.setId(entity.getAccountId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setCreateDate(entity.getCreateDate());
        dto.setRole(entity.getRole());
        dto.setActive(entity.isActive());
    }

    /**
     *
     * @param entity
     * @param dto
     */
    public void convertEntityFromDTO(Account entity, AccountDTO dto){
        entity.setAccountId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setCreateDate(dto.getCreateDate());
        entity.setRole(dto.getRole());
        entity.setActive(dto.isActive());
    }

    /**
     * Register New Account
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO register(AccountDTO accountDTO) {
        return null;
    }

    /**
     * Change Password
     * @param changePasswordDTO
     * @return
     */
    @Override
    public ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO) {
        return null;
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
    public AccountDTO getAccount(Integer id) {
        Account account = accountRepository.findByAccountId(id);
        if(account != null){
            AccountDTO dto = new AccountDTO();
            convertDTOFromEntity(dto, account);
            return dto;
        }
        return null;
    }

//    /**
//     * Get All Accounts
//     * @return
//     */
//    @Override
//    public List<AccountDTO> getAllAccounts() {
//        List<Account> accounts = accountRepository.findAll();
//        if(!accounts.isEmpty()){
//            List<AccountDTO> accountDTOS = new ArrayList<>();
//            for (Account account: accounts) {
//                AccountDTO tmp = new AccountDTO();
//                convertDTOFromEntity(tmp, account);
//                accountDTOS.add(tmp);
//            }
//            return accountDTOS;
//        }
//        return null;
//    }

    /**
     * Create Driver Account
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO createAccount(AccountDTO accountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            Account account = accountRepository.findByEmail(accountDTO.getEmail());
            if(account == null){
                Date createDate = new Date();
                account = new Account(accountDTO.getEmail(), accountDTO.getPassword(), accountDTO.getPhoneNumber(), accountDTO.getFirstName(),
                        accountDTO.getMiddleName(), accountDTO.getLastName(), createDate, accountDTO.getRole(), true);
                accountRepository.save(account);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                responseDTO.setObjectResponse(account);
            }else{
                responseDTO.setStatus(false);
                responseDTO.setMessage(Const.DRIVER_ACCOUNT_EXISTED);
                responseDTO.setObjectResponse(account);
            }
        }catch (Exception e){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.CREATE_ACCOUNT_FAIL);
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
        Account account = accountRepository.findByAccountId(id);
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            account.setFirstName(accountDTO.getFirstName());
            account.setMiddleName(accountDTO.getMiddleName());
            account.setLastName(accountDTO.getLastName());
            account.setPhoneNumber(accountDTO.getPhoneNumber());
            accountRepository.save(account);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.UPDATE_ACCOUNT_SUCCESS);
            responseDTO.setObjectResponse(account);
        }catch (Exception e){
            responseDTO.setStatus(false);
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
        try{
            Account account = accountRepository.findByAccountId(id);
            if(account != null){
                accountRepository.delete(account);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.DELETE_ACCOUNT_SUCCESS);
            }
        }catch (Exception e){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.DELETE_ACCOUNT_FAIL);
        }
        return responseDTO;
    }
}
