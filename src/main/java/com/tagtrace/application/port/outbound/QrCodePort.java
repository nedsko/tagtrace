package com.tagtrace.application.port.outbound;

import java.io.IOException;

public interface QrCodePort {
    byte[] generateQrCode(String valueToEncode) throws IOException;
}
