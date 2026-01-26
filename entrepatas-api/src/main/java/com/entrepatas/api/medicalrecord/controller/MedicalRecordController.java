
package com.entrepatas.api.medicalrecord.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.entrepatas.api.medicalrecord.dto.CreateMedicalRecordRequest;
import com.entrepatas.api.medicalrecord.model.MedicalRecord;
import com.entrepatas.api.medicalrecord.service.MedicalRecordService;

import jakarta.validation.Valid;

@RestController
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    // Admin: crear registro médico para un pet
    @PostMapping("/admin/pets/{petId}/medical-records")
    public MedicalRecord create(@PathVariable String petId, @Valid @RequestBody CreateMedicalRecordRequest dto) {
        return medicalRecordService.create(petId, dto);
    }

    // Público: listar registros (por ahora lo dejamos abierto; luego lo filtramos)
    @GetMapping("/public/pets/{petId}/medical-records")
    public List<MedicalRecord> list(@PathVariable String petId) {
        return medicalRecordService.listByPet(petId);
    }
}