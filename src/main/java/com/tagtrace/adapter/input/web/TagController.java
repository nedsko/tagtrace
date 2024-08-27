package com.tagtrace.adapter.input.web;

import com.tagtrace.application.port.input.CreateTagUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tags")

public class TagController {
    private final CreateTagUseCase createTagUseCase;

    @Autowired
    public TagController(CreateTagUseCase createTagUseCase) {
        this.createTagUseCase = createTagUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createNewTag() {
        return ResponseEntity.ok(createTagUseCase.createTag().tagId().value());
    }
}
