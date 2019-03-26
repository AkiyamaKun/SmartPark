package Core.Controller.MVC;

import Core.Constant.Const;
import Core.Controller.REST.AccountController;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Service.AccountService;
import Core.Service.OwnerService;
import Core.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    OwnerService ownerService;

    @Autowired
    ParkingLotService parkingLotService;

    /**
     * Login Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/login", "/"})
    public String toLogin(Model model) {
        model.addAttribute("title", "Smart Parking - Login");
        return "admin/login";
    }

    /**
     * Home Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home")
    public ModelAndView toHome(Model model) {
        model.addAttribute("Title", "Home");
        ModelAndView view = new ModelAndView("admin/home");
        ResponseDTO listDrivers = accountService.getListAccount(3);
        ResponseDTO listSupervisors = accountService.getListAccount(2);
        ResponseDTO listAdmins = accountService.getListAccount(1);
        ResponseDTO listParkingLots = parkingLotService.getAllParkingLotForAdmin();
        view.addObject("listDriver", listDrivers.getObjectResponse());
        view.addObject("listSupervisors", listSupervisors.getObjectResponse());
        view.addObject("listAdmins", listSupervisors.getObjectResponse());
        view.addObject("listParkingLots", listParkingLots.getObjectResponse());
        return view;
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
        return "admin/change-password";
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
        return "admin/create-manager";
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
        return "admin/create-supervisor";
    }

    /**
     * Edit Profile Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit-profile")
    public ModelAndView toEditProfile(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/edit-profile");
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
        return "admin/list-managers";
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
        return "admin/list-parking-lots";
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
        return "admin/list-supervisors";
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
        return "admin/list-owners";
    }

    /**
     * Manager Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/manager-detail")
    public ModelAndView toManagerDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/manager-detail");
        ResponseDTO managerAccount = accountService.getAccount(id);
        view.addObject("manager", managerAccount.getObjectResponse());
        return view;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/supervisor-detail")
    public ModelAndView toSupervisorDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/supervisor-detail");
        ResponseDTO supervisorAccount = accountService.getAccount(id);
        ResponseDTO plotOfSupervisor = parkingLotService.getListParkingLotControlBySupervisor(id);
        view.addObject("supervisor", supervisorAccount.getObjectResponse());
        view.addObject("plotOfSupervisor", plotOfSupervisor.getObjectResponse());
        return view;
    }

    /**
     * Manager Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/owner-detail")
    public ModelAndView toOwnerDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/owner-detail");
        ResponseDTO owner = ownerService.getOwner(id);
        ResponseDTO plotOwner = parkingLotService.getListParkingLotOfOwner(id);
        view.addObject("owner", owner.getObjectResponse());
        view.addObject("plotOwner", plotOwner.getObjectResponse());
        return view;
    }

    /**
     * Manager Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/parking-lot-detail")
    public ModelAndView toParkinglotDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/parking-lot-detail");
        ResponseDTO parkinglot = parkingLotService.getParkingLot(id);
        ResponseDTO supervisors = parkingLotService.getListSupervisorOfParkingLot(id);
        view.addObject("plot", parkinglot.getObjectResponse());
        view.addObject("supervisors", supervisors.getObjectResponse());
        return view;
    }

    @RequestMapping(value = "/create-parking-lot")
    public ModelAndView toCreateParkingLot(Model model) {
        ModelAndView view = new ModelAndView("admin/create-parking-lot");
        ResponseDTO owner = ownerService.getAllOwner();
        view.addObject("ownerName", owner.getObjectResponse());
        return view;
    }

    @RequestMapping(value = "/create-owner")
    public String toCreateOwner(Model model) {
        //Excute anything here
        return "admin/create-owner";
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

    @RequestMapping(value = Const.DRIVER_ACCOUNT + Const.SET_NEW_PASSWORD)
    public String toNewPasswordForDriver(@RequestParam(value = "email", required = true) String email,
                                    @RequestParam(value = "token", required = true) String token,
                                    Model model) {
        String url = "verify-account-fail-page";
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            String oldToken = account.getToken();
            if (oldToken != null) {
                if (oldToken.equals(token)) {
                    model.addAttribute("checkToken", true);
                    url = "set-new-password-for-driver";
                }
            }
        }
        return url;
    }

    //SUPERVISOR PAGE
    /**
     * Login Supervisor Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login-supervisor", method = RequestMethod.GET)
    public String toLoginSupervisor(Model model) {
        //Excute anything here
        return "login-supervisor";
    }
}


