package Core.Controller.MVC;

import Core.Entity.Account;
import Core.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * View Controller for MVC
 *
 * Author: DangNHH - 21/02/2019
 */
@Controller
public class ViewController {

    @Autowired
    AccountRepository accountRepository;


    /**
     * Login Page
     * @param model
     * @return
     */
    @RequestMapping(value = {"/login","/"})
    public String toLogin(Model model){
        //Excute anything here
        return "login";
    }

    /**
     * Home Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/home")
    public String toHome(Model model){
        model.addAttribute("Title", "Home");
        return "home";
    }

    /**
     * Change Password Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/change-password")
    public String toChangPassword(Model model){
        //Excute anything here
        return "change-password";
    }

    /**
     * Create Manager Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/create-manager")
    public String toCreateManager(Model model){
        //Excute anything here
        return "create-manager";
    }

    /**
     * Create Supervisor Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/create-supervisor")
    public String toCreateSupervisor(Model model){
        //Excute anything here
        return "create-supervisor";
    }

    /**
     * Edit Profile Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit-profile")
    public String toEditProfile(Model model){
        //Excute anything here
        return "edit-profile";
    }

    /**
     * List Managers Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-managers")
    public String toListManagers(Model model){
        //Excute anything here
        return "list-managers";
    }

    /**
     * List Parking Lots Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-parking-lots", method = RequestMethod.GET)
    public String toListParkingLots(Model model){
        //Excute anything here
        return "list-parking-lots";
    }

    /**
     * List Superviros Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-supervisors")
    public String toListSupervisors(Model model){
        //Excute anything here
        return "list-supervisors";
    }

    /**
     * Manager Detail Page
     * @param model
     * @return
     */
    @RequestMapping(value = "/manager-detail")
    public String toManagerDetail(Model model){
        //Excute anything here
        return "manager-detail";
    }

    @RequestMapping(value = "/create-parking-lot")
    public String toCreateParkingLot(Model model){
        //Excute anything here
        return "create-parking-lot";
    }

    @RequestMapping(value = "account/set_password_page")
    public String toSetPasswordPage(@RequestParam (value = "email", required = true) String email,
                                    @RequestParam (value = "token", required = true) String token,
                                    Model model){
        String url = "verify-account-fail-page";
        Account account = accountRepository.findByEmail(email);
        if(account != null){
            String oldToken = account.getToken();
            if(oldToken != null){
                if(oldToken.equals(token)){
                    model.addAttribute("checkToken", true);
                    url = "set-password-page";
                }
            }
        }
        return url;
    }
}


