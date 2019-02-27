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

}
