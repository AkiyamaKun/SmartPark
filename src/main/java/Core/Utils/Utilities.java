package Core.Utils;

import Core.Constant.Const;
import Core.DTO.BookingDTO;
import Core.DTO.InformationAccountDTO;
import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.ParkingLot;
import Core.Entity.ParkingSlot;
import Core.Repository.ParkingLotRepository;
import Core.Repository.ParkingSlotRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;
import java.util.List;

public class Utilities {
    /**
     * Generate token
     * @param username
     * @return
     */
    public static synchronized String generateToken( String username ) {
        SecureRandom random = new SecureRandom();
        long longToken = Math.abs(random.nextLong());
        String strRandom = Long.toString( longToken, 16 );
        return ( username + ":" + strRandom );
    }

    /**
     * Convert Information Account DTO from Account Entity
     * @param dto
     * @param account
     * @return
     */
    public static InformationAccountDTO convertInformationAccoutDTOFromAccount(InformationAccountDTO dto, Account account){
        if(account != null){
            dto.setAccountId(account.getAccountId());
            dto.setEmail(account.getEmail());
            dto.setFirstName(account.getFirstName());
            dto.setLastName(account.getLastName());
            dto.setPhoneNumber(account.getPhoneNumber());
            dto.setCash(account.getCash());
            dto.setActive(account.isActive());
            dto.setPlateNumber(account.getPlateNumber());
            dto.setAvatar(account.getAvatar());
            return  dto;
        }
        return null;
    }

    /**
     * Convert BookingDTO from Booking Entity
     * @param dto
     * @param entity
     * @return
     */
    public static BookingDTO convertBookingDTOFromBookingEntity(BookingDTO dto, Booking entity){
        dto.setBookingId(entity.getBookingId());
        dto.setAccountId(entity.getAccount().getAccountId());
        dto.setParkingLotId(entity.getParkingLot().getParkingLotId());
        dto.setBookingTime(entity.getBookingTime());
        dto.setTokenInput(entity.getTokenInput());
        dto.setTokenOutput(entity.getTokenOutput());
        dto.setTimeStart(entity.getTimeStart());
        dto.setTimeEnd(entity.getTimeEnd());
        dto.setBookingStatus(entity.getBookingStatus());
        dto.setUrlApiCheckIn(entity.getUrlApiCheckIn());
        dto.setUrlApiCheckOut(entity.getUrlApiCheckOut());
        dto.setParkingLotName(entity.getParkingLot().getDisplayName());
        dto.setPrice(entity.getParkingLot().getPrice());
        dto.setPlateNumber(entity.getPlateNumber());
        dto.setCashToPay(entity.getCashToPay());
        return dto;
    }

    /**
     * Excute Reduce Image Size
     * @param input
     * @param expectedKilobyte
     * @param multiplier
     * @return
     */
    public static byte[] compressImage(File input, float expectedKilobyte, float multiplier) {
        byte[] returnData = null;
        ByteArrayOutputStream returningByte = new ByteArrayOutputStream();
        ByteArrayInputStream inputByte;

        try {
            returnData = FileUtils.readFileToByteArray(input);

            do {
                inputByte = new ByteArrayInputStream(returnData);
                BufferedImage image = ImageIO.read(inputByte);
                OutputStream out = new DataOutputStream(returningByte);

                ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                ImageOutputStream ios = ImageIO.createImageOutputStream(out);
                writer.setOutput(ios);

                ImageWriteParam param = writer.getDefaultWriteParam();
                if (param.canWriteCompressed()) {
                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(multiplier);
                }

                writer.write(null, new IIOImage(image, null, null), param);

                out.close();
                ios.close();
                writer.dispose();
                returnData = returningByte.toByteArray();
            }
            while(returnData.length / 1024 > expectedKilobyte);
        } catch (Exception e) {
            //This is surely not an image.
            e.printStackTrace();
        }
        return returnData;
    }

    /**
     * Convert Multipart File to File
     * @param mulFile
     * @return
     * @throws IOException
     */
    public static File multipartToFile(MultipartFile mulFile) throws IOException {
        File convertFile = new File(mulFile.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(mulFile.getBytes());
        fos.close();
        return convertFile;
    }

    /**
     * Convert File To Byte Array
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] fileToByteArray(File file) throws IOException {
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytes);
        fis.close();
        return bytes;
    }

}
