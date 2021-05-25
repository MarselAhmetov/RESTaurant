package team404.restaurant.general.service;

import java.io.File;

public interface QRService {
    File generateQRCode(String barcodeText, String fileName);
    File getQRCode(String barcodeText, String fileName);
}
