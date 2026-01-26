package com.entrepatas.api.medicalrecord.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entrepatas.api.medicalrecord.dto.CreateMedicalRecordRequest;
import com.entrepatas.api.medicalrecord.model.MedicalRecord;
import com.entrepatas.api.medicalrecord.repository.MedicalRecordRepository;
import com.entrepatas.api.pets.model.Pet;
import com.entrepatas.api.pets.repository.PetRepository;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PetRepository petRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PetRepository petRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.petRepository = petRepository;
    }

    public MedicalRecord create(String petId, CreateMedicalRecordRequest dto) {

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet no encontrado"));

        MedicalRecord record = new MedicalRecord();
        record.setPetId(petId);
        record.setOrganizationId(pet.getOrganizationId()); // ✅ regla: hijo guarda orgId

        record.setType(dto.getType());
        record.setRecordDate(dto.getRecordDate());

        record.setSterilized(dto.getSterilized());
        record.setVaccinesUpToDate(dto.getVaccinesUpToDate());
        record.setDewormed(dto.getDewormed());

        record.setConditions(dto.getConditions());
        record.setDisabilities(dto.getDisabilities());

        record.setSummary(dto.getSummary());
        record.setNotes(dto.getNotes());

        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> listByPet(String petId) {
        return medicalRecordRepository.findByPetIdOrderByRecordDateDescCreatedAtDesc(petId);
    }
}
