package Core.Constant;

public class Const {
    //Public API
    public static final String AUTHORIZE = "/authorize";
    public static final String CHANGE_PASSWORD = "/change_password";
    public static final String GET_ACCOUNT = "/account/{id}";
    public static final String CREATE_ACCOUNT = "/account/create";
    public static final String UPDATE_ACCOUNT = "/account/update/{id}";
    public static final String DELETE_ACCOUNT = "/account/delete/{id}";

    //Public Message Response
    public static final String DELETE_ACCOUNT_SUCCESS = "Delete Account Successful";
    public static final String DELETE_ACCOUNT_FAIL ="Delete Account Fail";
    public static final String CREATE_ACCOUNT_SUCCESS = "Create Account Successful";
    public static final String CREATE_ACCOUNT_FAIL ="Create Account Fail";
    public static final String DRIVER_ACCOUNT_EXISTED ="Account Is Existed";
    public static final String UPDATE_ACCOUNT_SUCCESS = "Update Account Successful";
    public static final String UPDATE_ACCOUNT_FAIL ="Update Account Fail";

    //Account API
    public static final String GET_LIST_DRIVER = "/driver/listDriver";

    //Message of Driver Account




}
