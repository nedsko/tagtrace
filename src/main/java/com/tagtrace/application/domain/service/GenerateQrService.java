package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.exception.MissingEntityException;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.port.inbound.generate_qr.GenerateQrUseCase;
import com.tagtrace.application.port.outbound.LoadTagPort;
import com.tagtrace.application.port.outbound.QrCodePort;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GenerateQrService implements GenerateQrUseCase {
    private final QrCodePort qrCodePort;
    private final LoadTagPort loadTagPort;
    private final String QR_CODE_BASE_URL;

    @Autowired
    public GenerateQrService(
            QrCodePort qrCodePort,
            LoadTagPort loadTagPort,
            @Value("${com.tagtrace.qrcode.baseUrl}") String qrCodeBaseUrl) {
        this.qrCodePort = qrCodePort;
        this.loadTagPort = loadTagPort;
        QR_CODE_BASE_URL = qrCodeBaseUrl;
    }

    @Override
    public byte[] generateQrCode(TagId tagId) throws IOException {
        if (!loadTagPort.doesTagExist(tagId)) {
            throw new MissingEntityException("Tag with id %s does not exist".formatted(tagId.value()));
        }
        // This should probably be some different URL but this will do for now.
        final var urlToEncode = QR_CODE_BASE_URL + "/%s".formatted(tagId.value().toString());
        return qrCodePort.generateQrCode(urlToEncode);
    }
}
