package Core.Controller.MVC;

import Core.Controller.REST.AccountController;
import Core.DTO.ResponseDTO;
import Core.DTO.UserLoginDTO;
import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * View Controller for MVC
 * <p>
 * Author: DangNHH - 21/02/2019
 */
@Controller
public class ViewController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountController accountController;

    /**
     * Login Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/login", "/"})
    public String toLogin(Model model) {
        model.addAttribute("title", "Smart Parking - Login");
        return "login";
    }

    /**
     * Home Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home")
    public String toHome(Model model) {
        model.addAttribute("Title", "Home");
        return "home";
    }


    /**
     * Change Password Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/change-password")
    public String toChangPassword(Model model) {
        //Excute anything here
        return "change-password";
    }

    /**
     * Create Manager Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create-manager")
    public String toCreateManager(Model model) {
        //Excute anything here
        return "create-manager";
    }

    /**
     * Create Supervisor Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create-supervisor")
    public String toCreateSupervisor(Model model) {
        //Excute anything here
        return "create-supervisor";
    }

    /**
     * Edit Profile Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit-profile")
    public ModelAndView toEditProfile(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("edit-profile");
        ResponseDTO managerAccount = accountService.getAccount(id);
        view.addObject("profile", managerAccount.getObjectResponse());
        return view;
    }

    /**
     * List Managers Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-managers")
    public String toListManagers(Model model) {
        //Excute anything here
        return "list-managers";
    }

    /**
     * List Parking Lots Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-parking-lots", method = RequestMethod.GET)
    public String toListParkingLots(Model model) {
        //Excute anything here
        return "list-parking-lots";
    }

    /**
     * List Superviros Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-supervisors")
    public String toListSupervisors(Model model) {
        //Excute anything here
        return "list-supervisors";
    }

    /**
     * List Owners Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-owners")
    public String toListOwners(Model model) {
        //Excute anything here
        return "list-owners";
    }

    /**
     * Manager Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/manager-detail")
    public ModelAndView toManagerDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("manager-detail");
        ResponseDTO managerAccount = accountService.getAccount(id);
        view.addObject("manager", managerAccount.getObjectResponse());
        return view;
    }

    @RequestMapping(value = "/supervisor-detail")
    public ModelAndView toSupervisorDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("supervisor-detail");
        ResponseDTO supervisorAccount = accountService.getAccount(id);
        view.addObject("supervisor", supervisorAccount.getObjectResponse());
        return view;
    }

    @RequestMapping(value = "/create-parking-lot")
    public String toCreateParkingLot(Model model) {
        //Excute anything here
        return "create-parking-lot";
    }

    @RequestMapping(value = "/create-owner")
    public String toCreateOwner(Model model) {
        //Excute anything here
        return "create-owner";
    }

    @RequestMapping(value = "account/set_password_page")
    public String toSetPasswordPage(@RequestParam(value = "email", required = true) String email,
                                    @RequestParam(value = "token", required = true) String token,
                                    Model model) {
        String url = "verify-account-fail-page";
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            String oldToken = account.getToken();
            if (oldToken != null) {
                if (oldToken.equals(token)) {
                    model.addAttribute("checkToken", true);
                    url = "set-password-page";
                }
            }
        }
        return url;
    }
}


