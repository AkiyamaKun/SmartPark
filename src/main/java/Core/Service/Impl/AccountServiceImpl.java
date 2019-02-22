package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.Role;
import Core.Repository.AccountRepository;
import Core.Repository.RoleRepository;
import Core.Service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<AccountDTO> getListAccount(Integer roleId) {
        Role role = roleRepository.findByRoleId(roleId);
        List<Account> accounts = accountRepository.findAllByRole(role);
        List<AccountDTO> accountDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        accounts.stream().forEach(e -> {
            AccountDTO dto = modelMapper.map(e, AccountDTO.class);
            accountDTOS.add(dto);
        });
        return accountDTOS;
    }
}
