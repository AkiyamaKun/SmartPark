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
    public static final String UPDATE_STATUS_SLOT = "/update_status_slot";
    public static final String UPLOAD_IMAGE_FOR_PARKING_LOT = "/upload_image_for_parking_lot/{parkingLotId}";

    //Account API
    public static final String ACCOUNT = "/account";
    public static final String CHANGE_PASSWORD = "/change_password";
    public static final String GET_ACCOUNT = "/{id}";
    public static final String GET_ACCOUNT_BY_EMAIL = "/get_by_email";
    public static final String UPDATE_ACCOUNT = "/update/{id}";
    public static final String LIST_ACCOUNTS = "/list_accounts/{roleId}";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String LOGOUT_ANDROID = "/logout/{id}";
    public static final String LOGOUT_SUCCESS = "Logout Successful";
    public static final String LOGOUT_FAIL = "Logout Fail";
    public static final String REGISTER =  "/register";
    public static final String SET_FIRST_PASSWORD = "/set_first_password";
    public static final String SET_PASSWORD_PAGE ="/set_password_page";
    public static final String DEASSIGN_PARKING_LOT ="/deassign_parking_lot";

    //Message Response
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
    public static final String SET_PASSWORD_SUCCESS = "Set Password Success";
    public static final String UPDATE_STATUS_SLOT_SUCCESS = "Update Status Slot Success";
    public static final String UPLOAD_IMAGE_SUCCESS = "Upload Image Success";
    public static final String ADD_CASH_SUCCESS = "Add cash successfull";
    public static final String BOOKING_IS_NOT_EXISTED = "Booking is not existed";
    public static final String GET_BOOKING_SUCCESS = "Get Booking Success";
    public static final String GET_BOOKING_FAIL = "Get Booking Fail";
    public static final String LOGIN_SUCCESS = "Login Successful";
    public static final String WRONG_USERNAME_PASSWORD = "Wrong username or password";
    public static final String ACCESS_DENIED = "Access Denied";

    //Driver Account API
    public static final String DRIVER_ACCOUNT = "/driver";
    public static final String GET_ALL_DRIVER = "/all_driver";
    public static final String GET_DRIVER_ACCOUNT = "/{id}";
    public static final String CREATE_DRIVER_ACCOUNT = "/create_driver";
    public static final String VERIFY_DRIVER_ACCOUNT = "/verify";
    public static final String FORGET_PASSWORD_ADMIN = "/forget_password_admin";
    public static final String FORGET_PASSWORD_DRIVER = "/forget_password_driver";
    public static final String SET_NEW_PASSWORD = "/set_new_password";
    public static final String TOTAL_ACCOUNT = "/total_account";
    public static final String BOOKING_SLOT = "/booking_slot";
    public static final String ADD_CASH = "add_cash";
    public static final String SAVE_TRANSACTION = "/save_transaction";
    public static final String CHECK_PAYMENT = "/check_payment";
    public static final String GENERATE_TOKEN_CLIENT = "/generate_token_client";
    public static final String CHECK_BOOKING_USE = "/check_booking_use";
    public static final String GET_TRANSACTION_BY_ACCOUNT_ID = "/get_transaction_account";
    public static final String GET_TRANSACTION_BY_BOOKING_ID = "/get_transaction_booking";
    public static final String GET_BOOKING_BY_ACCOUNT_ID = "/get_booking_by_account";

    //Driver Account Message Response
    public static final String VERIFY_ACCOUNT_SUCCESS = "Verify Account Successful";
    public static final String VERIFY_ACCOUNT_FAIL = "Verify Account Fail";
    public static final String GET_DRIVER_ACCOUNT_SUCCESS = "Get Driver Account Success";
    public static final String GET_DRIVER_ACCOUNT_FAIL = "Get Driver Account Fail";
    public static final String SEND_MESSAGE_SET_FIRST_PASSWORD = "We have send link set new password for you";
    public static final String BOOKING_SUCCESS = "Booking success";
    public static final String BOOKING_FAIL = "Booking fail";
    public static final String BOOKING_CHECK_IN_SUCCESS = "Check in success";
    public static final String BOOKING_CHECK_OUT_SUCCESS = "Check out success";
    public static final String MONEY_NOT_ENOUGH = "Money not enough";
    public static final String BOOKING_HAD_CHECK_IN = "This QRCode Had Check In";
    public static final String BOOKING_HAD_FINISH = "This session booking had finished";
    public static final String BOOKING_TIME_OUT_CHECK_IN = "Check-in time has expired";
    public static final String BOOKING_CHECK_IN_FAIL = "Check in fail";
    public static final String BOOKING_CHECK_OUT_FAIL = "Check out fail";
    public static final String BOOKING_OUT_OF_SLOT = "No one slot can booking";
    public static final String PAYPAL = "PAYPAL";
    public static final String CHECKIN = "CHECKIN";
    public static final String CHECKOUT = "CHECKOUT";
    public static final String REFUND = "REFUND";
    public static final String PAYMENT = "PAYMENT";
    public static final String BOOKED = "BOOKED";
    public static final String REFUND_FAIL = "Refund fail";

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
    public static final String LIST_PARKINGLOT_OF_OWNER = "/list_parking_lot_of_owner";
    public static final String LIST_PARKINGLOT_CONTROL_BY_SUPERVISOR = "/list_parking_lot_control_by_supervisor";
    public static final String LIST_SUPERVISOR_OF_PARKING_LOT = "/list_supervisor_of_parking_lot";

    public static final String CREATE_OWNER = "/create_owner";
    public static final String UPDATE_OWNER = "/update_owner/{id}";
    public static final String GET_OWNER = "get_owner/{id}";
    public static final String GET_ALL_OWNER = "/all_owners";
    public static final String DEACTIVE_OWNER = "/deactive_owner/{id}";
    public static final String ACTIVE_OWNER = "/active_owner/{id}";
    public static final String SEARCH_OWNER = "/search_owner";

    public static final String PARKING_LOT = "/parking_lot";
    public static final String GET_PARKING_LOT ="/get_parking_lot/{id}";
    public static final String GET_ALL_SLOT_OF_PARKING_LOT = "/list_parking_slot";
    public static final String GET_ALL_PARKING_LOT = "/all_parking_lot";
    public static final String CREATE_PARKING_LOT = "/create_parking_lot/{adminId}";
    public static final String UPDATE_PARKING_LOT = "/update_parking_lot/{accountId}";
    public static final String DEACTIVE_PARKING_LOT = "/deactive_parking_lot/{parkingLotId}";
    public static final String ACTIVE_PARKING_LOT = "/active_parking_lot/{parkingLotId}";

    public static final String CREATE_CAMERA = "/create_camera";
    public static final String UPDATE_CAMERA = "/update_camera/{id}";
    public static final String GET_ALL_CAMERA = "/all_cameras";
    public static final String ASSIGN_CAMERA_FOR_PARKING_LOT = "/assign_camera_for_parking_lot";

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
    public static final String DEACTIVE_OWNER_SUCCESS = "Deactive Owner Successful";
    public static final String DEACTIVE_OWNER_FAIL = "Deactive Owner Fail";
    public static final String ACTIVE_OWNER_SUCCESS = "Active Owner Successful";
    public static final String ACTIVE_OWNER_FAIL = "Active Owner Fail";
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
    public static final String GET_LIST_PARKING_LOT_OF_OWNER_SUCCESS = "Get List Parking Lot Of Owner Successful";
    public static final String GET_LIST_PARKING_LOT_OF_OWNER_FAIL = "Get List Parking Lot Of Owner Fail";
    public static final String GET_LIST_SUPERVISOR_SUCCESS = "Get List Supervisor Successful";
    public static final String GET_LIST_SUPERVISOR_FAIL = "Get List Supervisor Fail";

    //Supervision Message
    public static final String ASSIGN_PARKING_LOT_FOR_SUPERVISOR_SUCCESS = "Assign Parking Lot for Supervisor Successful";
    public static final String ASSIGN_PARKING_LOT_FOR_SUPERVISOR_FAIL = "Assign Parking Lot for Supervisor Fail";
    public static final String DEASSIGN_PARKING_LOT_FOR_SUPERVISOR_SUCCESS = "Deassign Parking Lot for Supervisor Successful";
    public static final String DEASSIGN_PARKING_LOT_FOR_SUPERVISOR_FAIL = "Deassign Parking Lot for Supervisor Fail";

    //Supervisor Account API
    public static final String SUPERVISOR_ACCOUNT = "/supervisor";
    public static final String LIST_CAMERA = "/list_camera";
    public static final String LIST_CAMERA_BY_PARKINGLOTID = "/list_camera/{parkingLotId}";
    public static final String COUNT_BOOKING = "/count_booking";
    public static final String LIST_BOOKING_FOR_REPORT = "/list_booking_report";
    public static final String REFUND_FOR_DRIVER = "/refund_for_driver";

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

    //Booking API
    public static final String BOOKING_CHECK_IN = "/check_in";
    public static final String BOOKING_CHECK_OUT = "/check_out";
    public static final String LIST_BOOKING_BY_PARKING_LOT = "/list_booking";
    public static final String LIST_BOOKING_BY_ACCOUNT_ID = "/list_booking/{accountId}";
    public static final String BOOKING_CANCEL = "/booking_cancel";
    public static final String GET_BOOKING = "/get_booking/{bookingId}";


    //Booking Message
    public static final String GET_LIST_BOOKING_SUCCESS = "Get list booking success";
    public static final String GET_LIST_BOOKING_FAIL = "Get list booking fail";
    public static final String CANCEL_BOOKING_SUCCESS = "Cancel booking success";
    public static final String CANCEL_BOOKING_FAIL = "Cancel booking fail";


    //Parking Slot Message
    public static final String PARKING_SLOT_IS_NOT_EXISTED = "Parking Slot is not existed";
    public static final String GET_PARKING_SLOT_SUCCESS = "Get Parking Slot Successful";

    //Camera message
    public static final String GET_CAMERA_SUCCESS = "Get Camera Successful";
    public static final String CAMERA_IS_NOT_EXISTED = "Camera is not existed";
    public static final String GET_LIST_CAMERA_SUCCESS = "Get List Camera Successful";
    public static final String GET_LIST_CAMERA_FAIL = "Get List Camera Fail";
    public static final String CREATE_CAMERA_SUCCESS = "Create Camera Successful";
    public static final String CREATE_CAMERA_FAIL = "Create Camera Fail";
    public static final String UPDATE_CAMERA_SUCCESS = "Update Camera Successful";
    public static final String UPDATE_CAMERA_FAIL = "Update Camera Fail";
    public static final String ASSIGN_CAMERA_FOR_PARKING_LOT_SUCCESS = "Assign Camera for Parking Lot Successful";
    public static final String ASSIGN_CAMERA_FOR_PARKING_LOT_FAIL = "Assign Camera for Parking Lot Fail";
    public static final String GET_LIST_CAMERA_OF_PARKING_LOT_SUCCESS = "Get List Camera Of Parking Lot Successful";
    public static final String GET_LIST_CAMERA_OF_PARKING_LOT_FAIL = "Get List Camera Of Parking Lot Fail";

    //Transaction message
    public static final String SAVE_TRANSACTION_SUCCESS = "Save Transaction Successful";
    public static final String SAVE_TRANSACTION_FAIL = "Save Transaction Fail";
    public static final String CHECK_PAYMENT_SUCCESS = "Check Payment Successful";
    public static final String CHECK_PAYMENT_FAIL = "Check Payment Fail";
    public static final String GENERATE_SUCCESS = "Generate Successful";
    public static final String GENERATE_FAIL = "Generate Fail";
    public static final String GET_TRANSACTION_SUCCESS = "Get Transaction Successful";
    public static final String GET_TRANSACTION_FAIL = "Get Transaction Fail";

    //Common
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SUPERVISOR = "ROLE_SUPERVISOR";
    public static final String ROLE_DRIVER  = "ROLE_DRIVER";
    public static final String STATUS_SLOT_EMPTY = "empty";
    public static final String STATUS_SLOT_OCCUPIED = "occupied";
    public static final String STATUS_SLOT_UNDEFINED = "undefined";
    public static final String STATUS_SLOT_BOOKED = "booked";
    public static final String STATUS_BOOKING_BOOK = "BOOK";
    public static final String STATUS_BOOKING_USE = "USE";
    public static final String STATUS_BOOKING_FINISH = "FINISH";
    public static final String STATUS_BOOKING_CANCEL = "CANCEL";
    public static final String ACCOUNT_ADMIN_DEFAULT = "admin@gmail.com";
    public static final String ACCOUNT_SUPERVISOR_DEFAULT = "supervisor@gmail.com";
    public static final String ACCOUNT_DRIVER_DEFAULT = "driver@gmail.com";
    public static final String ACCOUNT_PASSWORD_DEFAULT = "123456";
    public static final String BT_ENVIRONMENT="sandbox";
    public static final String BT_MERCHANT_ID="d3k583gm5jx7msfb";
    public static final String BT_PUBLIC_KEY="55hgc8vprysjvzw5";
    public static final String BT_PRIVATE_KEY="4f8d97cd751a72724e7fd0b84e93664d";

    //Time is counted as seconds
    //1800 seconds = 30 minutes
    public static final long DEFAULT_TIME_OUT_CHECK_IN = 1800;
}
