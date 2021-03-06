package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.ParkingSlotDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Entity.ParkingSlot;
import Core.Entity.ParkingSlotStatus;
import Core.Repository.AccountRepository;
import Core.Repository.ParkingLotRepository;
import Core.Repository.ParkingSlotRepository;
import Core.Repository.ParkingSlotStatusRepository;
import Core.Service.PublicService;
import Core.Utils.EmailUtil;
import Core.Utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Properties;

@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    ParkingSlotStatusRepository parkingSlotStatusRepository;

    /**
     * @param email
     * @param token
     * @param type  (1: Register Admin,
     *              2: Register Supervisor,
     *              3: Register Driver,
     *              4: Forget Password)
     * @return
     */
    @Override
    public ResponseDTO sendEmail(String email, String token, Integer type, String nameAccount) {
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
        Account account = accountRepository.findByEmail(email);

        String welcomeMail = "Hello " + nameAccount + ",\n";
        String congratulationMail = "Congratulations on successful registration of your account " + email + "\n";
        switch (type) {
            case 1:
                //Admin Account
            case 2:
                //Supervisor Account
                //Forget Passowrd of Admin and Supervisor Account
                urlVerify = Const.DOMAIN + Const.ACCOUNT + Const.SET_PASSWORD_PAGE + "?email=" + email + "&token=" + token;
                EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, welcomeMail + Const.MAIL_CONTENT_SET_PASSWORD_PAGE + ": " + urlVerify);
                break;
            case 3:
                //Driver Account
                urlVerify = Const.DOMAIN + Const.ACCOUNT + Const.VERIFY_DRIVER_ACCOUNT + "?email=" + email + "&token=" + token;
                EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, welcomeMail + congratulationMail + Const.MAIL_CONTENT_VERIFY_DRIVER_ACCOUNT + ": " + urlVerify);
                break;
            case 4:
                urlVerify = Const.DOMAIN + Const.ACCOUNT + Const.SET_NEW_PASSWORD + "?email=" + email + "&token=" + token;
                EmailUtil.sendEmail(session, toEmail, Const.MAIL_TILLE, welcomeMail + Const.MAIL_CONTENT_SET_NEW_PASSWORD + ": " + urlVerify);
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

    /**
     * Update Status of Parking Slot From Deep Learning
     *
     * @param parkingLotId
     * @param listParkingSlot
     * @return
     */
    @Override
    public ResponseDTO updateStatusSlot(Integer parkingLotId, List<ParkingSlotDTO> listParkingSlot) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (parkingLot != null) {
                List<ParkingSlot> listSlotEntity = parkingSlotRepository.findByParkingLot(parkingLot);
                //int sizeListParkingSlot = listParkingSlot.size();
                int sizeListSlotEntity = listSlotEntity.size();
                //int minSize = sizeListParkingSlot < sizeListSlotEntity ? sizeListParkingSlot : sizeListSlotEntity;
                for (int i = 0; i < sizeListSlotEntity; i++) {
                    ParkingSlot parkingSlotEntity = listSlotEntity.get(i);
                    ParkingSlotDTO parkingSlotDTO;
                    try {
                        parkingSlotDTO = listParkingSlot.get(i);
                    }
                    catch(Exception e){
                        parkingSlotDTO = null;
                    }
                    ParkingSlotStatus parkingSlotStatus;
                    if(parkingSlotDTO != null) {
                        parkingSlotEntity.setSlotLane(parkingSlotDTO.getLane());
                        parkingSlotEntity.setSlotRow(parkingSlotDTO.getRow());
                        parkingSlotStatus = parkingSlotStatusRepository.findByStatusName(parkingSlotDTO.getStatus());
                    }
                    else{
                        parkingSlotStatus = null;
                    }

                    if (parkingSlotStatus != null) {
                        parkingSlotEntity.setParkingSlotStatus(parkingSlotStatus);
                    } else {
                        parkingSlotStatus = parkingSlotStatusRepository.findByStatusName(Const.STATUS_SLOT_UNDEFINED);
                        parkingSlotEntity.setParkingSlotStatus(parkingSlotStatus);
                    }
                    parkingSlotRepository.save(parkingSlotEntity);
                }
                responseDTO.setMessage(Const.UPDATE_STATUS_SLOT_SUCCESS);
                responseDTO.setStatus(true);
            } else {
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Update Status Parking Slot Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Upload Image For Parking Lot
     *
     * @param multipartFile
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO uploadImageForParkingLot(MultipartFile multipartFile, Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (parkingLot != null) {
                File file = Utilities.multipartToFile(multipartFile);
                byte[] image = Utilities.fileToByteArray(file);
                parkingLot.setParklotImage(image);
                parkingLotRepository.save(parkingLot);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.UPLOAD_IMAGE_SUCCESS);

            } else {
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Upload Image Error : " + e.getMessage());
        }
        return responseDTO;
    }
}
