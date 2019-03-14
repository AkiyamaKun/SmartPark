package Core.Service.Impl;

import Core.Service.ImageCompressingService;
import org.apache.commons.io.FileUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageCompressingServiceImpl implements ImageCompressingService {

    @Override
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
