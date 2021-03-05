package team404.restaurant.general.service;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class QRServiceImpl implements QRService{
    public File generateQRCode(String barcodeText) throws IOException {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
        File qr = new File("qr_" + UUID.randomUUID() + ".png");
        ImageIO.write(ImageIO.read(bis), "png", qr);
        return qr;
    }
}
