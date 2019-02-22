package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manager Account Controller
 *
 * Author: DangNHH - 17/02/2019
 */
@RestController
@RequestMapping(value = Const.ACCOUNT)
public class ManagerAccountController {

    @Autowired
    AccountService accountService;

    /**
     * Get List Managers
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = Const.LIST_MANAGERS, method = RequestMethod.GET)
    public List<AccountDTO> getListAccount(@PathVariable Integer roleId) {
        return accountService.getListManagers(roleId);
    }
}
