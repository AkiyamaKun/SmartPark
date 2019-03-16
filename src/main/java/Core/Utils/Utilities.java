package Core.Utils;

import Core.DTO.InformationAccountDTO;
import Core.Entity.Account;
import org.apache.commons.io.FileUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;

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
            return  dto;
        }
        return null;
    }

    /**
     * Excute Reduce Image Size
     * @param input
     * @param expectedKilobyte
     * @param multiplier
     * @return
     */
    public byte[] compressImage(File input, float expectedKilobyte, float multiplier) {
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
}
