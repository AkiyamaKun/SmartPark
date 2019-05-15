package Core.Controller.MVC;

import Core.Constant.Const;
import Core.Controller.REST.AccountController;
import Core.Controller.REST.DriverAccountController;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.ParkingLotRepository;
import Core.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

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
    DriverAccountController driverAccountController;

    @Autowired
    OwnerService ownerService;

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    DriverAccountService driverAccountService;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    CameraService cameraService;

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
    public ModelAndView toHome(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("JSESSIONID")){
                Integer age = cookie.getMaxAge();
                if(age == -1){
                    cookie.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(cookie);
                }
            }
        }

        model.addAttribute("Title", "Home");
        ModelAndView view = new ModelAndView("admin/home");
        ResponseDTO listDrivers = accountService.getListAccount(3);
        ResponseDTO listSupervisors = accountService.getListAccount(2);
        ResponseDTO listAdmins = accountService.getListAccount(1);
        ResponseDTO listParkingLots = parkingLotService.getAllParkingLotForAdmin();
        ResponseDTO totalAccount = accountService.totalAccount();

        view.addObject("listDrivers", listDrivers.getObjectResponse());
        view.addObject("listSupervisors", listSupervisors.getObjectResponse());
        view.addObject("listAdmins", listAdmins.getObjectResponse());
        view.addObject("listParkingLots", listParkingLots.getObjectResponse());
        view.addObject("totalAccount", totalAccount.getObjectResponse());
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
     * Create Camera Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create-camera")
    public String toCreateCamera(Model model) {
        //Excute anything here
        return "admin/create-camera";
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
     * List Drivers Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list-drivers")
    public String toListDrivers(Model model) {
        //Excute anything here
        return "admin/list-drivers";
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
     * Driver Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/driver-detail")
    public ModelAndView toDriverDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/driver-detail");
        ResponseDTO driverAccount = driverAccountController.getDriverAccount(id);
        view.addObject("driver", driverAccount.getObjectResponse());
        return view;
    }

    /**
     * Manager Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/parking-lot-detail")
    public ModelAndView toParkinglotDetail(@RequestParam Integer id) throws IOException {
        ModelAndView view = new ModelAndView("admin/parking-lot-detail");
        ResponseDTO parkinglot = parkingLotService.getParkingLot(id);
        ResponseDTO supervisors = parkingLotService.getListSupervisorOfParkingLot(id);

        //Show Image on Parking Lot Detail Page
        ParkingLot parkingLotDetail = parkingLotRepository.findByParkingLotId(id);
        if(parkingLotDetail.getParklotImage() != null){
            byte[] picContent = parkingLotDetail.getParklotImage();
            view.addObject("IMAGE_PARKING_LOT", Base64.getEncoder().encodeToString(picContent));
        }

        ResponseDTO camera = cameraService.getListCameraOfParkingLot(id);
        System.out.println(camera.getObjectResponse());

        view.addObject("plot", parkinglot.getObjectResponse());
        view.addObject("supervisors", supervisors.getObjectResponse());
        view.addObject("cameras", camera.getObjectResponse());
        return view;
    }

    /**
     * Manager Detail Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/camera-detail")
    public ModelAndView toCameraDetail(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("admin/camera-detail");
        ResponseDTO camera = cameraService.getCamera(id);
        view.addObject("webcam", camera.getObjectResponse());
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
        String url = "admin/verify-account-fail-page";
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            String oldToken = account.getToken();
            if (oldToken != null) {
                if (oldToken.equals(token)) {
                    model.addAttribute("checkToken", true);
                    url = "admin/set-password-page";
                }
            }
        }
        return url;
    }

    @RequestMapping(value = Const.ACCOUNT + Const.SET_NEW_PASSWORD)
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
                    url = "admin/set-new-password-for-driver";
                }
            }
        }
        return url;
    }

    @RequestMapping(value = "/verify-account-success")
    public String toVerifyAccountSuccess(Model model) {
        //Excute anything here
        return "admin/verify-account-success-page";
    }

    @RequestMapping(value = "/forget-password")
    public String toForgetPassword(Model model) {
        //Excute anything here
        return "admin/forget-password";
    }

    @RequestMapping(value = "/set-password-successful")
    public String toSetPasswordSuccessfulPage(){
        return "verify-account-success-page";
    }

    /**
     * Verify Driver Account
     * @param email
     * @param token
     * @return
     */
    @RequestMapping(value = Const.ACCOUNT + Const.VERIFY_DRIVER_ACCOUNT, method = RequestMethod.GET)
    public String verifyDriverAccount(@RequestParam(value = "email", required = true) String email,
                                      @RequestParam(value = "token", required = true) String token){
        ResponseDTO responseDTO = driverAccountService.verifyAccount(email, token);
        if(responseDTO.isStatus()){
            return "admin/verify-account-success-page";
        }
        return "admin/verify-account-fail-page";
    }

    //==============SUPERVISOR PAGE==================
    /**
     * Login Supervisor Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login-supervisor", method = RequestMethod.GET)
    public String toLoginSupervisor(Model model) {
        //Excute anything here
        return "supervisor/login-supervisor";
    }

    /**
     * Logout Supervisor Page
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/logout-supervisor", method = RequestMethod.GET)
    public RedirectView toLogoutSupervisor(HttpServletRequest httpServletRequest) {
        ResponseDTO logout = accountController.logout(httpServletRequest);
        return new RedirectView("/login-supervisor");
    }

    /**
     * Change password Supervisor Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/change-password-supervisor", method = RequestMethod.GET)
    public String toChangePasswordSupervisor(Model model) {
        //Excute anything here
        return "supervisor/change-password-supervisor";
    }

    /**
     * Dashboard Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String toDashboardSupervisor(Model model) {
        return "supervisor/dashboard";
    }

    /**
     * Report Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String toReportSupervisor(Model model) {
        return "supervisor/report";
    }

    /**
     * Edit Profile Page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user")
    public ModelAndView toUser(@RequestParam Integer id) {
        ModelAndView view = new ModelAndView("supervisor/user");
        ResponseDTO supervisorAccount = accountService.getAccount(id);
        view.addObject("profile", supervisorAccount.getObjectResponse());
        return view;
    }

    /**
     * Forget Password Supervisor Page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/forget-pass-supervisor", method = RequestMethod.GET)
    public String toForgetPassSupervisor(Model model) {
        //Excute anything here
        return "supervisor/forget-pass-supervisor";
    }
}


