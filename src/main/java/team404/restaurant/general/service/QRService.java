package team404.restaurant.general.service;

import java.io.File;
import java.io.IOException;

public interface QRService {
    File generateQRCode(String barcodeText) throws IOException;
}
