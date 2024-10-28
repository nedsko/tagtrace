package com.tagtrace.application.port.inbound.generate_qr;

import com.tagtrace.application.domain.model.value_object.TagId;
import java.io.IOException;

public interface GenerateQrUseCase {
    byte[] generateQrCode(TagId tagId) throws IOException;
}
