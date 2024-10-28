package com.tagtrace.adapter.outbound;

import com.tagtrace.application.port.outbound.QrCodePort;
import io.nayuki.qrcodegen.QrCode;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;

@Component
public class QrCodeAdapter implements QrCodePort {
    private static final int BLACK_COLOR = 0xFFFFFF;
    private static final int WHITE_COLOR = 0x000000;

    @Override
    public byte[] generateQrCode(String valueToEncode) throws IOException {
        var qrCode = QrCode.encodeText(valueToEncode, QrCode.Ecc.HIGH);
        var bufferedImage = toImage(qrCode, 10, 2, WHITE_COLOR, BLACK_COLOR);
        var outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return outputStream.toByteArray();
    }

    // Taken from the QR-Code-generator demo on GitHub.
    // https://github.com/nayuki/QR-Code-generator/blob/master/java/QrCodeGeneratorDemo.java#L172
    private static BufferedImage toImage(QrCode qr, int scale, int border, int lightColor, int darkColor) {
        Objects.requireNonNull(qr);
        if (scale <= 0 || border < 0) {
            throw new IllegalArgumentException("Value out of range");
        }
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale) {
            throw new IllegalArgumentException("Scale or border too large");
        }

        BufferedImage result = new BufferedImage(
                (qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }
        return result;
    }
}
