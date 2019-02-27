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

    @Override
    public ResponseDTO sendEmail(String email, String token, Integer roleAccount) {
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
        switch (roleAccount){
            case 1:
                //Admin Account
            case 2:
                //Supervisor Account
                urlVerify = Const.DOMAIN + Const.ACCOUNT + Const.SET_PASSWORD_PAGE + "?email=" + email +"&token=" + token;
                break;
            case 3:
                //Driver Account
                urlVerify = Const.DOMAIN + Const.DRIVER_ACCOUNT + Const.VERIFY_DRIVER_ACCOUNT + "?email=" + email +"&token=" + token;
                break;
            default:
                //Exception
                break;
        }

        EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, Const.MAIL_CONTENT + ": " + urlVerify);
        responseDTO.setStatus(true);
        responseDTO.setMessage(Const.SEND_EMAIL_SUCCESSFUL);
        return responseDTO;
    }
}
