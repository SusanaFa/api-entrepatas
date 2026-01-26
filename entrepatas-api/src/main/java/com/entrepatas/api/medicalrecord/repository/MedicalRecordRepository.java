package com.entrepatas.api.medicalrecord.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrepatas.api.medicalrecord.model.MedicalRecord;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    List<MedicalRecord> findByPetIdOrderByRecordDateDescCreatedAtDesc(String petId);
}
