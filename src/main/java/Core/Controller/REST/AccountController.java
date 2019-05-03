package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.*;
import Core.Entity.Account;
import Core.Service.AccountService;
import Core.Service.BookingService;
import Core.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Account Controller
 * <p>
 * Author: DangNHH - 17/02/2019
 */

@RestController
@RequestMapping(value = Const.ACCOUNT)
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    JwtService jwtService;

    @Autowired
    BookingService bookingService;

    /**
     * Convert InformationAccountDTO form AccountDTO
     *
     * @param informationAccountDTO
     * @param accountDTO
     */
    public void convertInformationAccountDTOFromAccountDTO(InformationAccountDTO informationAccountDTO, AccountDTO accountDTO) {
        informationAccountDTO.setAccountId(accountDTO.getAccountId());
        informationAccountDTO.setEmail(accountDTO.getEmail());
        informationAccountDTO.setPhoneNumber(accountDTO.getPhoneNumber());
        informationAccountDTO.setFirstName(accountDTO.getFirstName());
        informationAccountDTO.setLastName(accountDTO.getLastName());
        informationAccountDTO.setAvatar(accountDTO.getAvatar());
        informationAccountDTO.setActive(accountDTO.isActive());
    }

    /**
     * Get Account
     *
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO getAccount(@PathVariable Integer id,
                                  HttpServletRequest request) {
        ResponseDTO responseDTO = accountService.getAccount(id);
        if (responseDTO.isStatus()) {
            InformationAccountDTO dto = new InformationAccountDTO();
            convertInformationAccountDTOFromAccountDTO(dto, (AccountDTO) responseDTO.getObjectResponse());
            responseDTO.setObjectResponse(dto);
        }
        return responseDTO;
    }

    /**
     * Update Account
     *
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.UPDATE_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO,
                                     @PathVariable Integer id) {
        ResponseDTO responseDTO = accountService.updateAccount(id, accountDTO);
        if (responseDTO.isStatus()) {
            InformationAccountDTO dto = new InformationAccountDTO();
            convertInformationAccountDTOFromAccountDTO(dto, (AccountDTO) responseDTO.getObjectResponse());
            responseDTO.setObjectResponse(dto);
        }
        return responseDTO;
    }

    /**
     * Get List Account
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = Const.LIST_ACCOUNTS, method = RequestMethod.GET)
    public ResponseDTO getListAccount(@PathVariable Integer roleId) {
        return accountService.getListAccount(roleId);
    }

    /**
     * Change Password
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.CHANGE_PASSWORD, method = RequestMethod.PUT)
    public ResponseDTO changePassword(@RequestBody @Valid ChangePasswordDTO dto) {
        return accountService.changePassword(dto);
    }


    /**
     * Register Account
     *
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.REGISTER, method = RequestMethod.POST)
    public ResponseDTO register(@RequestBody @Valid AccountDTO accountDTO) {
        ResponseDTO responseDTO = accountService.registerAccount(accountDTO);
        return responseDTO;
    }

    /**
     * Set First Password for Admin/Supervisor Account
     *
     * @param email
     * @param password
     * @param passwordConfirm
     * @return
     */
    @RequestMapping(value = Const.SET_FIRST_PASSWORD, method = RequestMethod.PUT)
    public ResponseDTO setFirstPassword(@RequestParam(value = "email", required = true) String email,
                                        @RequestParam(value = "password", required = true) String password,
                                        @RequestParam(value = "passwordConfirm", required = true) String passwordConfirm) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (!password.equals(passwordConfirm)) {
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.PASSWORD_CONFIRM_FAIL);
        } else {
            responseDTO = accountService.setFirstPassword(email, password);
        }
        return responseDTO;
    }

    /**
     * Set new password then forget password
     *
     * @param email
     * @param password
     * @param passwordConfirm
     * @return
     */
    @RequestMapping(value = Const.SET_NEW_PASSWORD, method = RequestMethod.PUT)
    public ResponseDTO setNewPassword(@RequestParam(value = "email", required = true) String email,
                                      @RequestParam(value = "password", required = true) String password,
                                      @RequestParam(value = "passwordConfirm", required = true) String passwordConfirm) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (!password.equals(passwordConfirm)) {
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.PASSWORD_CONFIRM_FAIL);
        } else {
            responseDTO = accountService.setFirstPassword(email, password);
            responseDTO.setMessage(Const.CHANGE_PASSWORD_SUCCESS);
        }
        return responseDTO;
    }

    /**
     * Login
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.LOGIN, method = RequestMethod.POST)
    public ResponseDTO login(@RequestBody @Valid UserLoginDTO dto,
                              HttpServletRequest request) {
        ResponseDTO responseDTO = accountService.checkLogin(dto);
        if (responseDTO.isStatus()) {
            HttpSession session = request.getSession();
            session.setAttribute("User", responseDTO.getObjectResponse());
        }
        return responseDTO;
    }

    /**
     * Logout
     *
     * @param request
     * @return
     */
    @RequestMapping(value = Const.LOGOUT, method = RequestMethod.POST)
    public ResponseDTO logout(HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpSession session = request.getSession();
        session.removeAttribute("User");
        responseDTO.setStatus(true);
        responseDTO.setMessage("Logout Success");
        return responseDTO;
    }

    /**
     * Forget Password for Driver Account
     *
     * @param email
     * @return
     */
    @RequestMapping(value = Const.FORGET_PASSWORD_DRIVER, method = RequestMethod.POST)
    public ResponseDTO forgotPasswordDriver(@RequestParam(value = "email") String email) {
        //4 is type of Admin and supervisor on public service
        return accountService.forgetPassword(email, 4);
    }

    /**
     * Forget Password
     * @param email
     * @return
     */
    @RequestMapping(value = Const.FORGET_PASSWORD_ADMIN, method = RequestMethod.POST)
    public ResponseDTO forgotPasswordAdmin(@RequestParam(value = "email") String email){
        //1 is type of Admin and supervisor on public service
        return accountService.forgetPassword(email, 1);
    }

    /**
     * Create Driver Account
     *
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.CREATE_DRIVER_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createDriver(@RequestBody @Valid AccountDTO accountDTO) {
        //Register Driver Account
        accountDTO.setRoleId(3);
        return accountService.registerAccount(accountDTO);

    }

    /**
     * Get Booking by id
     * @param bookingId
     * @return
     */
    @RequestMapping(value = Const.GET_BOOKING, method = RequestMethod.GET)
    public ResponseDTO getBookingById(@PathVariable Integer bookingId){
        return bookingService.getBookingById(bookingId);
    }
}
