package com.entrepatas.api.intakerequests.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.entrepatas.api.intakerequests.dto.CreateIntakeRequestRequest;
import com.entrepatas.api.intakerequests.dto.UpdateIntakeRequestStatusRequest;
import com.entrepatas.api.intakerequests.model.IntakeRequest;
import com.entrepatas.api.intakerequests.service.IntakeRequestService;

import jakarta.validation.Valid;

@RestController
public class IntakeRequestController {

    private final IntakeRequestService intakeRequestService;

    public IntakeRequestController(IntakeRequestService intakeRequestService) {
        this.intakeRequestService = intakeRequestService;
    }

    // Público: enviar solicitud
    @PostMapping("/public/intake-requests")
    public IntakeRequest create(@Valid @RequestBody CreateIntakeRequestRequest dto) {
        return intakeRequestService.create(dto);
    }

    // Admin: listar solicitudes (opcional filtrar por status)
    @GetMapping("/admin/intake-requests")
    public List<IntakeRequest> list(@RequestParam(required = false) String status) {
        return intakeRequestService.list(status);
    }

    // Admin: actualizar status
    @PostMapping("/admin/intake-requests/{id}/status")
    public IntakeRequest updateStatus(@PathVariable String id,
            @Valid @RequestBody UpdateIntakeRequestStatusRequest dto) {
        return intakeRequestService.updateStatus(id, dto);
    }
}
