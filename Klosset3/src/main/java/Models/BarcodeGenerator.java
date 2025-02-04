package Models;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BarcodeGenerator {

     public static String generateBarcode(String name, String category, String description) throws WriterException {
        String barcodeData =  name + "|" + category + "|" + description ;
        String barcodePath = "src/main/resources/barcodes/asset_" + name + ".png";
        
        int width = 300;
        int height = 100;

        try {
             BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeData, BarcodeFormat.QR_CODE, width, height);
            Path path = Paths.get(barcodePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return barcodePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
