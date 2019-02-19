package Core.Controller;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Service.AccountService;
import Core.Service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * Get Account
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_ACCOUNT, method = RequestMethod.GET)
    public AccountDTO getAccount(@PathVariable Integer id){
        return accountService.getAccount(id);
    }

    /**
     * Update Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.UPDATE_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO,
                                     @PathVariable Integer id){
        return accountService.updateAccount(id, accountDTO);
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

}
