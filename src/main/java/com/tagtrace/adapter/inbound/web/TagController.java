package com.tagtrace.adapter.inbound.web;

import com.tagtrace.adapter.inbound.web.api.CreateTagRequestObject;
import com.tagtrace.adapter.inbound.web.api.TagDetailsResponse;
import com.tagtrace.adapter.inbound.web.mapper.ApiToDomainMapper;
import com.tagtrace.adapter.inbound.web.mapper.DomainToApiMapper;
import com.tagtrace.application.port.inbound.create_tag.CreateTagUseCase;
import com.tagtrace.application.port.inbound.generate_qr.GenerateQrUseCase;
import com.tagtrace.application.port.inbound.get_tag_details.GetTagDetailsUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private final CreateTagUseCase createTagUseCase;
    private final GenerateQrUseCase generateQrUseCase;
    private final GetTagDetailsUseCase getTagDetailsUseCase;
    private final DomainToApiMapper domainToApiMapper;
    private final ApiToDomainMapper apiToDomainMapper;

    @Autowired
    public TagController(CreateTagUseCase createTagUseCase,
                         GenerateQrUseCase generateQrUseCase,
                         GetTagDetailsUseCase getTagDetailsUseCase,
                         DomainToApiMapper domainToApiMapper,
                         ApiToDomainMapper apiToDomainMapper) {
        this.createTagUseCase = createTagUseCase;
        this.generateQrUseCase = generateQrUseCase;
        this.getTagDetailsUseCase = getTagDetailsUseCase;
        this.domainToApiMapper = domainToApiMapper;
        this.apiToDomainMapper = apiToDomainMapper;
    }

    @PostMapping
    public ResponseEntity<TagDetailsResponse> createNewTag(@RequestBody CreateTagRequestObject requestObject) {
        var createdTag = createTagUseCase.createTag(apiToDomainMapper.toCreateTagInput(requestObject));
        return ResponseEntity.ok(domainToApiMapper.toTagDetailsResponse(createdTag));
    }

    @PostMapping(
            value = "/{id}/generate-qr",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public ResponseEntity<byte[]> generateQrCode(@Valid @PathVariable UUID id) {
        try {
            var qrCodeByteArray = generateQrUseCase.generateQrCode(new com.tagtrace.application.domain.model.value_object.TagId(id));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCodeByteArray);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDetailsResponse> getTagDetails(@PathVariable UUID id) {
        var result = domainToApiMapper.toTagDetailsResponse(
                getTagDetailsUseCase.getTagById(new com.tagtrace.application.domain.model.value_object.TagId(id))
        );
        return ResponseEntity.ok(result);
    }
}
