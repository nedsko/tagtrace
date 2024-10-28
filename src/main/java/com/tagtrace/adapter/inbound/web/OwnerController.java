package com.tagtrace.adapter.inbound.web;

import com.tagtrace.adapter.inbound.web.api.CreateOwnerRequestObject;
import com.tagtrace.adapter.inbound.web.api.CreateOwnerResponseObject;
import com.tagtrace.adapter.inbound.web.mapper.ApiToDomainMapper;
import com.tagtrace.adapter.inbound.web.mapper.DomainToApiMapper;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/owners")
public class OwnerController {
    private final CreateOwnerUseCase createOwnerUseCase;
    private final ApiToDomainMapper apiToDomainMapper;
    private final DomainToApiMapper domainToApiMapper;

    @Autowired
    public OwnerController(
            CreateOwnerUseCase createOwnerUseCase,
            ApiToDomainMapper apiToDomainMapper,
            DomainToApiMapper domainToApiMapper) {
        this.createOwnerUseCase = createOwnerUseCase;
        this.apiToDomainMapper = apiToDomainMapper;
        this.domainToApiMapper = domainToApiMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CreateOwnerResponseObject> createNewOwner(
            @Valid @RequestBody CreateOwnerRequestObject requestObject) {
        var output = createOwnerUseCase.createNewOwner(apiToDomainMapper.toCreateOwnerInput(requestObject));
        return ResponseEntity.ok(domainToApiMapper.toResponseObject(output));
    }
}
