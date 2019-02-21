package Core.Service;

import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.ResponseDTO;

import java.util.List;

/**
 * Account Service Interface
 *
 * Author: DangNHH - 19/02/2019
 */
public interface AccountService {
    ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO);
    ResponseDTO authorize(AccountDTO accountDTO);
    AccountDTO getAccount(Integer id);
    ResponseDTO updateAccount(Integer id, AccountDTO accountDTO);
    ResponseDTO deleteAccount(Integer id);

    List<AccountDTO> getListAccount(Integer roleId);
}
