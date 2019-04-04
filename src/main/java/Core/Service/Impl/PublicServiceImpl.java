package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Service.PublicService;
import Core.Utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    AccountRepository accountRepository;

    /**
     *
     * @param email
     * @param token
     * @param type (1: Register Admin,
     *             2: Register Supervisor,
     *             3: Register Driver,
     *             4: Forget Password)
     *
     * @return
     */
    @Override
    public ResponseDTO sendEmail(String email, String token, Integer type) {
        ResponseDTO responseDTO = new ResponseDTO();
        String fromEmail = Const.MAIL_ACCOUNT; //requires valid gmail id
        String password = Const.MAIL_PASSWORD; // correct password for gmail id
        String toEmail = email; // can be any email id

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        String urlVerify = "";
        switch (type){
            case 1:
                //Admin Account
            case 2:
                //Supervisor Account
                //Forget Passowrd of Admin and Supervisor Account
                urlVerify = Const.DOMAIN + Const.ACCOUNT + Const.SET_PASSWORD_PAGE + "?email=" + email +"&token=" + token;
                EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, Const.MAIL_CONTENT_SET_PASSWORD_PAGE + ": " + urlVerify);
                break;
            case 3:
                //Driver Account
                urlVerify = Const.DOMAIN + Const.DRIVER_ACCOUNT + Const.VERIFY_DRIVER_ACCOUNT + "?email=" + email +"&token=" + token;
                EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, Const.MAIL_CONTENT_VERIFY_DRIVER_ACCOUNT + ": " + urlVerify);
                break;
            case 4:
                urlVerify = Const.DOMAIN + Const.DRIVER_ACCOUNT + Const.SET_NEW_PASSWORD + "?email=" + email +"&token=" + token;
                EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, Const.MAIL_CONTENT_SET_NEW_PASSWORD + ": " + urlVerify);
                //Forget Passowrd of Driver Account
            default:
                //Exception
                responseDTO.setStatus(false);
                responseDTO.setMessage(Const.SEND_EMAIL_TYPE_IS_NOT_SUPPORT);
                break;
        }
        responseDTO.setStatus(true);
        responseDTO.setMessage(Const.SEND_EMAIL_SUCCESSFUL);
        return responseDTO;
    }
}
