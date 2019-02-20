package Core.Constant;

/**
 * Constants Class
 *
 * Author: DangNHH - 17/02/2019
 */
public class Const {
    //Public API
    public static final String PUBLIC = "/public";
    public static final String SEND_EMAIL ="/send_email";
    public static final String AUTHORIZE = "/authorize";

    public static final String DOMAIN = "http://localhost:8080";

    //Account API
    public static final String ACCOUNT = "/account";
    public static final String CHANGE_PASSWORD = "/change_password";
    public static final String GET_ACCOUNT = "/{id}";
    public static final String UPDATE_ACCOUNT = "/update/{id}";
    public static final String DELETE_ACCOUNT = "/delete/{id}";
    public static final String LIST_ACCOUNTS = "/listaccounts/{roleId}";

    //Accoount Message Response
    public static final String DELETE_ACCOUNT_SUCCESS = "Delete Account Successful";
    public static final String DELETE_ACCOUNT_FAIL = "Delete Account Fail";
    public static final String CREATE_ACCOUNT_SUCCESS = "Create Account Successful";
    public static final String CREATE_ACCOUNT_FAIL = "Create Account Fail";
    public static final String DRIVER_ACCOUNT_EXISTED = "Account Is Existed";
    public static final String UPDATE_ACCOUNT_SUCCESS = "Update Account Successful";
    public static final String UPDATE_ACCOUNT_FAIL = "Update Account Fail";

    //Driver Account API
    public static final String DRIVER_ACCOUNT = "/driver_account";
    public static final String GET_ALL_DRIVER = "/all_driver";
    public static final String CREATE_DRIVER_ACCOUNT = "/create";
    public static final String VERIFY_DRIVER_ACCOUNT = "/verify";

    //Driver Accoount Message Response
    public static final String VERIFY_ACCOUNT_SUCCESS = "Verify Account Successful";
    public static final String VERIFY_ACCOUNT_FAIL = "Verify Account Fail";

    //Account Gmail vs Mail Properties
    public static final String MAIL_ACCOUNT = "smartparking50@gmail.com";
    public static final String MAIL_PASSWORD = "Dang7183496";
    public static final String MAIL_TILLE = "Verify Register Your Mail";
    public static final String MAIL_PERSONAL = "Smart Parking Support";
    public static final String MAIL_CONTENT = "Verify your account by click here";
    public static final String SEND_EMAIL_CREATE_ACCOUNT_ERROR = "Can not send email verify";

}
