package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Manager Account Controller
 *
 * Author: DangNHH - 17/02/2019
 */
@RestController
@RequestMapping(value = Const.ADMIN_ACCOUNT)
public class ManagerAccountController {

    @Autowired
    AccountService accountService;

    /**
     * Create Admin Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.CREATE_ADMIN_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createAccountAdmin(@RequestBody @Valid AccountDTO accountDTO){
        Integer roleId = 1;
        return accountService.createAccount(roleId, accountDTO);
    }
}
