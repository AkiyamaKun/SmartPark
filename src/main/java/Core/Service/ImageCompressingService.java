package Core.Service;

import java.io.File;

public interface ImageCompressingService {

    //Expect about 60 - 80kb, and multiplier should be 0.5f (half the size per compress time)
    //Will return null if the file is not an image, will convert any image to jpg.
    public byte[] compressImage(File input, float expectedKilobyte, float multiplier);
}
