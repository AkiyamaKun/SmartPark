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
    public static final String LIST_ACCOUNTS = "/list_accounts/{roleId}";

    //Account Message Response
    public static final String DELETE_ACCOUNT_SUCCESS = "Delete Account Successful";
    public static final String DELETE_ACCOUNT_FAIL = "Delete Account Fail";
    public static final String CREATE_ACCOUNT_SUCCESS = "Create Account Successful";
    public static final String CREATE_ACCOUNT_FAIL = "Create Account Fail";
    public static final String ACCOUNT_IS_EXISTED = "Account is Existed";
    public static final String ACCOUNT_IS_NOT_EXISTED = "Account is not existed";
    public static final String UPDATE_ACCOUNT_SUCCESS = "Update Account Successful";
    public static final String UPDATE_ACCOUNT_FAIL = "Update Account Fail";
    public static final String GET_ACCOUNT_SUCCESS = "Get Account Successful";
    public static final String GET_ACCOUNT_FAIL = "Get Account Fail";
    public static final String CHANGE_PASSWORD_SUCCESS = "Change Password Successful";
    public static final String LACK_OF_DATA = "Lack of data";
    public static final String PASSWORD_IDENTICAL = "New password is the same old password";
    public static final String PASSWORD_CONFIRM_FAIL = "New password and confirm password are not the same";
    public static final String ROLE_IS_NOT_EXISTED = "Role is not existed";
    public static final String NOTHING_DATA_ON_SERVER = "Nothing data on server";
    public static final String GET_LIST_ACCOUNTS_SUCCESS = "Get List Account Successful";

    //Driver Account API
    public static final String DRIVER_ACCOUNT = "/driver_account";
    public static final String GET_ALL_DRIVER = "/all_driver";
    public static final String CREATE_DRIVER_ACCOUNT = "/create_driver";
    public static final String VERIFY_DRIVER_ACCOUNT = "/verify";
    public static final String SET_PASSWORD_PAGE ="/set_password_page";
    public static final String REGISTER =  "/register";
    public static final String SET_FIRST_PASSWORD = "/set_first_password";

    //Driver Accoount Message Response
    public static final String VERIFY_ACCOUNT_SUCCESS = "Verify Account Successful";
    public static final String VERIFY_ACCOUNT_FAIL = "Verify Account Fail";

    //Admin Account API
    public static final String ADMIN_ACCOUNT = "admin_account";
    public static final String CREATE_ADMIN_ACCOUNT = "/create_admin";

    //Supervisor Account API
    public static final String SUPERVISOR_ACCOUNT = "supervisor_account";
    public static final String CREATE_SUPERVISOR_ACCOUNT = "/create_supervisor";

    //Account Gmail vs Mail Properties
    public static final String MAIL_ACCOUNT = "smartparking50@gmail.com";
    public static final String MAIL_PASSWORD = "Dang7183496";
    public static final String MAIL_TILLE = "Verify Register Your Mail";
    public static final String MAIL_PERSONAL = "Smart Parking Support";
    public static final String MAIL_CONTENT = "Verify your account by click here";
    public static final String SEND_EMAIL_CREATE_ACCOUNT_ERROR = "Can not send email verify";
    public static final String SEND_EMAIL_SUCCESSFUL = "Send Email Successful";

    //Parking Lot API
    public static final String PARKING_LOT = "/parking_lot";
    public static final String GET_PARKING_LOT ="/{id}";
    public static final String LIST_PARKING_LOT = "/list_parking_lots";
    public static final String CREATE_PARKING_LOT = "/create_parking_lot";
    public static final String UPDATE_PARKING_LOT_FOR_SUPERVISOR = "/update_parking_lot_for_supervisor/{parkingLotId}";
    public static final String UPDATE_PARKING_LOT_FOR_ADMIN = "/update_parking_lot_for_admin/{parkingLotId}";
    public static final String GET_ALL_SLOT_OF_PARKING_LOT = "/list_parking_slot";

    //Parking Lot Message
    public static final String GET_PARKING_LOT_SUCCESS = "Get Parking Lot Successful";
    public static final String GET_PARKING_LOT_FAIL = "Get Parking Lot Fail";
    public static final String PARKING_LOT_IS_NOT_EXISTED = "Parking Lot is not existed";
    public static final String GET_LIST_PARKING_LOT_SUCCESS = "Get List Parking Lot Successful";
    public static final String CREATE_PARKING_LOT_SUCCESS = "Create Parking Lot Successful";
    public static final String CREATE_PARKING_LOT_FAIL = "Create Parking Lot Fail";
    public static final String UPDATE_PARKING_LOT_SUCCESS = "Update Parking Lot Successful";
    public static final String UPDATE_PARKING_LOT_FAIL = "Update Parking Lot Fail";
    public static final String GET_ALL_SLOT_OF_PARKING_LOT_SUCCESS = "Get All Slot Success";

    //Parking Slot API
    public static final String PARKING_SLOT = "/parking_slot";
    public static final String GET_PARKING_SLOT = "/{id}";

    //Parking Slot Message
    public static final String PARKING_SLOT_IS_NOT_EXISTED = "Parking Slot is not existed";
    public static final String GET_PARKING_SLOT_SUCCESS = "Get Parking Slot Successful";


}
