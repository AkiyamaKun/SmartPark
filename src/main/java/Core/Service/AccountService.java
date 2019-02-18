package Core.Service;

import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.ResponseDTO;

/**
 * Account Service Interface
 *
 * Author: DangNHH - 19/02/2019
 */
public interface AccountService {
    ResponseDTO register(AccountDTO accountDTO);
    ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO);
    ResponseDTO authorize(AccountDTO accountDTO);
    AccountDTO getAccount(Integer id);
    ResponseDTO createAccount(AccountDTO accountDTO);
    ResponseDTO updateAccount(Integer id, AccountDTO accountDTO);
    ResponseDTO deleteAccount(Integer id);
}
