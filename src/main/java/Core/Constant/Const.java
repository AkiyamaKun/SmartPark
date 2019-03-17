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
    public static final String LIST_ACCOUNTS = "/list_accounts/{roleId}";
    public static final String LOGIN = "/login";
    public static final String REGISTER =  "/register";
    public static final String SET_FIRST_PASSWORD = "/set_first_password";
    public static final String SET_PASSWORD_PAGE ="/set_password_page";

    //Account Message Response
    public static final String DELETE_ACCOUNT_SUCCESS = "Delete Account Successful";
    public static final String DELETE_ACCOUNT_FAIL = "Delete Account Fail";
    public static final String ACTIVATE_ACCOUNT_SUCCESS = "Activate Account Successful";
    public static final String ACTIVATE_ACCOUNT_FAIL = "Activate Account Fail";
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
    public static final String PASSWORD_OLD_IS_NOT_EXACTLY = "Old password is not exactly";
    public static final String ROLE_IS_NOT_EXISTED = "Role is not existed";
    public static final String NOTHING_DATA_ON_SERVER = "Nothing data on server";
    public static final String GET_LIST_ACCOUNTS_SUCCESS = "Get List Account Successful";
    public static final String AVATAR_NAME_DEFAULT = "default_avatar";
    public static final String EDIT_PROFILE_SUCCESS = "Edit Profile Successful";

    //Driver Account API
    public static final String DRIVER_ACCOUNT = "/driver";
    public static final String GET_ALL_DRIVER = "/all_driver";
    public static final String GET_DRIVER_ACCOUNT = "/{id}";
    public static final String CREATE_DRIVER_ACCOUNT = "/create_driver";
    public static final String VERIFY_DRIVER_ACCOUNT = "/verify";
    public static final String FORGET_PASSWORD = "/forget_password";
    public static final String SET_NEW_PASSWORD ="/set_new_password";

    //Driver Account Message Response
    public static final String VERIFY_ACCOUNT_SUCCESS = "Verify Account Successful";
    public static final String VERIFY_ACCOUNT_FAIL = "Verify Account Fail";
    public static final String GET_DRIVER_ACCOUNT_SUCCESS = "Get Driver Account Success";
    public static final String GET_DRIVER_ACCOUNT_FAIL = "Get Driver Account Fail";
    public static final String SEND_MESSAGE_SET_FIRST_PASSWORD = "We have send link set new password for you";

    //Admin Account API
    public static final String ADMIN_ACCOUNT = "/admin";
    public static final String GET_ADMIN = "/get_admin/{id}";
    public static final String CREATE_ADMIN_ACCOUNT = "/create_admin";
    public static final String UPDATE_ADMIN_ACCOUNT = "/update_admin/{id}";
    public static final String DEACTIVE_ADMIN_ACCOUNT = "/deactive_admin/{id}";
    public static final String ACTIVE_ADMIN_ACCOUNT = "/active_admin/{id}";
    public static final String GET_SUPERVISOR = "/get_supervisor/{id}";
    public static final String GET_ALL_SUPERVISOR = "/all_supervisor";
    public static final String CREATE_SUPERVISOR_ACCOUNT = "/create_supervisor";
    public static final String UPDATE_SUPERVISOR_ACCOUNT = "/update_supervisor/{id}";
    public static final String DEACTIVE_SUPERVISOR_ACCOUNT = "/deactive_supervisor/{id}";
    public static final String GET_ALL_ADMIN = "/all_admin";
    public static final String ASSIGN_PARKING_LOT_FOR_SUPERVISOR = "/assign_parking_lot_for_supervisor";

    public static final String CREATE_OWNER = "/create_owner";
    public static final String UPDATE_OWNER = "/update_owner/{id}";
    public static final String GET_OWNER = "get_owner/{id}";
    public static final String GET_ALL_OWNER = "/all_owners";
    public static final String DEACTIVE_OWNER = "/deactive_owner/{id}";
    public static final String SEARCH_OWNER = "/search_owner";

    public static final String PARKING_LOT = "/parking_lot";
    public static final String GET_PARKING_LOT ="get_parking_lot/{id}";
    public static final String GET_ALL_SLOT_OF_PARKING_LOT = "/list_parking_slot";
    public static final String GET_ALL_PARKING_LOT = "/all_parking_lot";
    public static final String CREATE_PARKING_LOT = "/create_parking_lot/{adminId}";
    public static final String UPDATE_PARKING_LOT = "/update_parking_lot/{accountId}";
    public static final String DEACTIVE_PARKING_LOT = "/deactive_parking_lot/{parkingLotId}";
    public static final String ACTIVE_PARKING_LOT = "/active_parking_lot/{parkingLotId}";

    //Owner Message
    public static final String GET_OWNER_SUCCESS = "Get Owner Successful";
    public static final String GET_OWNER_FAIL = "Get Owner Fail";
    public static final String OWNER_IS_NOT_EXISTED = "Owner is not existed";
    public static final String GET_LIST_OWNER_SUCCESS = "Get List Owner Successful";
    public static final String GET_LIST_OWNER_FAIL = "Get List Owner Fail";
    public static final String CREATE_OWNER_SUCCESS = "Create Owner Successful";
    public static final String CREATE_OWNER_FAIL = "Create Owner Fail";
    public static final String UPDATE_OWNER_SUCCESS = "Update Owner Successful";
    public static final String UPDATE_OWNER_FAIL = "Update Owner Fail";
    public static final String DEACTIVE_OWNER_SUCCESS = "Deacitve Owner Successful";
    public static final String DEACTIVE_OWNER_FAIL = "Deacitve Owner Fail";
    public static final String SEARCH_OWNER_SUCCESS = "Search Owner Successful";
    public static final String SEARCH_OWNER_FAIL = "Search Owner Fail";
    public static final String SEARCH_OWNER_FIND_NOT_FOUND = "Owner is not found";

    //Parking Lot Message
    public static final String GET_PARKING_LOT_SUCCESS = "Get Parking Lot Successful";
    public static final String GET_PARKING_LOT_FAIL = "Get Parking Lot Fail";
    public static final String PARKING_LOT_IS_NOT_EXISTED = "Parking Lot is not existed";
    public static final String PARKING_LOT_IS_DEACTIVE = "Parking Lot is deactive";
    public static final String PARKING_LOT_DEACTIVE_FAIL = "Parking Lot deactivate fail";
    public static final String PARKING_LOT_IS_ACTIVE = "Parking Lot is active";
    public static final String PARKING_LOT_ACTIVE_FAIL = "Parking Lot activate fail";
    public static final String GET_LIST_PARKING_LOT_SUCCESS = "Get List Parking Lot Successful";
    public static final String GET_LIST_PARKING_LOT_FAIL = "Get List Parking Lot Fail";
    public static final String CREATE_PARKING_LOT_SUCCESS = "Create Parking Lot Successful";
    public static final String CREATE_PARKING_LOT_FAIL = "Create Parking Lot Fail";
    public static final String UPDATE_PARKING_LOT_SUCCESS = "Update Parking Lot Successful";
    public static final String UPDATE_PARKING_LOT_FAIL = "Update Parking Lot Fail";
    public static final String GET_ALL_SLOT_OF_PARKING_LOT_SUCCESS = "Get All Slot Success";

    //Supervision Message
    public static final String ASSIGN_PARKING_LOT_FOR_SUPERVISOR_SUCCESS = "Assign Parking Lot for Supervisor Successful";
    public static final String ASSIGN_PARKING_LOT_FOR_SUPERVISOR_FAIL = "Assign Parking Lot for Supervisor Fail";

    //Supervisor Account API
    public static final String SUPERVISOR_ACCOUNT = "supervisor";

    //Account Gmail vs Mail Properties
    public static final String MAIL_ACCOUNT = "smartparking50@gmail.com";
    public static final String MAIL_PASSWORD = "Dang7183496";
    public static final String MAIL_TILLE = "Verify Register Your Mail";
    public static final String MAIL_PERSONAL = "Smart Parking Support";
    public static final String MAIL_CONTENT_VERIFY_DRIVER_ACCOUNT = "Verify your account by click here";
    public static final String MAIL_CONTENT_SET_PASSWORD_PAGE = "Click to go page set first password";
    public static final String MAIL_CONTENT_SET_NEW_PASSWORD = "Click to go page set new password";
    public static final String SEND_EMAIL_CREATE_ACCOUNT_ERROR = "Can not send email verify";
    public static final String SEND_EMAIL_TYPE_IS_NOT_SUPPORT = "This type is not support";
    public static final String SEND_EMAIL_SUCCESSFUL = "Send Email Successful";
    public static final String SEND_EMAIL_SET_NEW_PASSWORD_FAIL = "Send Email for set new password fail";

    //Parking Lot API
    public static final String UPDATE_PARKING_LOT_FOR_SUPERVISOR = "/update_parking_lot_for_supervisor/{parkingLotId}";

    //Parking Slot API
    public static final String PARKING_SLOT = "/parking_slot";
    public static final String GET_PARKING_SLOT = "/{id}";

    //Parking Slot Message
    public static final String PARKING_SLOT_IS_NOT_EXISTED = "Parking Slot is not existed";
    public static final String GET_PARKING_SLOT_SUCCESS = "Get Parking Slot Successful";
}
