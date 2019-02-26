package Core.Controller.MVC;

import Core.DTO.AccountDTO;
import Core.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * View Controller for MVC
 *
 * Author: DangNHH - 21/02/2019
 */
@Controller
public class ViewController {

    @Autowired
    private AccountService accountService;

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
        //Excute anything here
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
     * @param id
     * @return
     */
    @RequestMapping(value = "/manager-detail")
    public ModelAndView toManagerDetail(@RequestParam int id){
        ModelAndView view = new ModelAndView("manager-detail");

        AccountDTO managerAccount = accountService.getAccount(id);
        view.addObject("manager", managerAccount);

        return view;
    }
}


