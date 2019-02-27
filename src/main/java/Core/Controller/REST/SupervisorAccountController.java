package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Supervisor Account Controller
 *
 * 26/02/2019
 */
@RestController
@RequestMapping(value = Const.SUPERVISOR_ACCOUNT)
public class SupervisorAccountController{
    @Autowired
    AccountService accountService;

    /**
     * Create Supervisor Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.CREATE_SUPERVISOR_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createSupervisorAccount(@RequestBody @Valid AccountDTO accountDTO){
        Integer roleId = 2;
        return accountService.createAccount(roleId, accountDTO);
    }
}
