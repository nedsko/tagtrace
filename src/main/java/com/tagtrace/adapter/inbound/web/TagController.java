package com.tagtrace.adapter.inbound.web;

import com.tagtrace.adapter.inbound.web.api.TagId;
import com.tagtrace.adapter.inbound.web.api.TagName;
import com.tagtrace.application.port.inbound.create_tag.CreateTagUseCase;
import com.tagtrace.application.port.inbound.generate_qr.GenerateQrUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private final CreateTagUseCase createTagUseCase;
    private final GenerateQrUseCase generateQrUseCase;

    @Autowired
    public TagController(CreateTagUseCase createTagUseCase, GenerateQrUseCase generateQrUseCase) {
        this.createTagUseCase = createTagUseCase;
        this.generateQrUseCase = generateQrUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createNewTag(@RequestBody TagName tagName) {
        var createdTag = createTagUseCase.createTag(new com.tagtrace.application.domain.model.value_object.TagName(tagName.name()));
        return ResponseEntity.ok(createdTag.tagId().value());
    }

    @PostMapping(
            value = "/generate-qr",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public ResponseEntity<byte[]> generateQrCode(@Valid @RequestBody TagId tagId) {
        try {
            var qrCodeByteArray = generateQrUseCase.generateQrCode(new com.tagtrace.application.domain.model.value_object.TagId(tagId.id()));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCodeByteArray);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}
