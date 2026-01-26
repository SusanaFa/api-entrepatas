package com.entrepatas.api.adoptionapplications.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.entrepatas.api.adoptionapplications.dto.CreateAdoptionApplicationRequest;
import com.entrepatas.api.adoptionapplications.dto.UpdateApplicationStatusRequest;
import com.entrepatas.api.adoptionapplications.model.AdoptionApplication;
import com.entrepatas.api.adoptionapplications.service.AdoptionApplicationService;

import jakarta.validation.Valid;

@RestController
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;

    public AdoptionApplicationController(AdoptionApplicationService adoptionApplicationService) {
        this.adoptionApplicationService = adoptionApplicationService;
    }

    // Público: crear solicitud para una mascota
    @PostMapping("/public/pets/{petId}/adoption-applications")
    public AdoptionApplication create(@PathVariable String petId,
            @Valid @RequestBody CreateAdoptionApplicationRequest dto) {
        return adoptionApplicationService.create(petId, dto);
    }

    // Admin: listar solicitudes por mascota
    @GetMapping("/admin/pets/{petId}/adoption-applications")
    public List<AdoptionApplication> listByPet(@PathVariable String petId) {
        return adoptionApplicationService.listByPet(petId);
    }

    // Admin: listar solicitudes por organización (opcional status)
    @GetMapping("/admin/organizations/{organizationId}/adoption-applications")
    public List<AdoptionApplication> listByOrganization(
            @PathVariable String organizationId,
            @RequestParam(required = false) String status) {
        return adoptionApplicationService.listByOrganization(organizationId, status);
    }

    // Admin: actualizar status
    @PostMapping("/admin/adoption-applications/{applicationId}/status")
    public AdoptionApplication updateStatus(
            @PathVariable String applicationId,
            @Valid @RequestBody UpdateApplicationStatusRequest dto) {
        return adoptionApplicationService.updateStatus(applicationId, dto);
    }
}
