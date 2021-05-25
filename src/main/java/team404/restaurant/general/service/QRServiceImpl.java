package team404.restaurant.general.service;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class QRServiceImpl implements QRService {
    @Override
    public File generateQRCode(String barcodeText, String fileName) {
        try {
            ByteArrayOutputStream stream = QRCode
                    .from(barcodeText)
                    .withSize(250, 250)
                    .stream();
            ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
            File qr = new File("qr_" + fileName + ".png");
            ImageIO.write(ImageIO.read(bis), "png", qr);
            return qr;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public File getQRCode(String barcodeText, String fileName) {
        try {
            ByteArrayOutputStream stream = QRCode
                    .from(barcodeText)
                    .withSize(250, 250)
                    .stream();
            ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
            File qr = new File("qr_" + fileName + ".png");
            ImageIO.write(ImageIO.read(bis), "png", qr);
            return qr;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
