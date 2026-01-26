package com.entrepatas.api.adoptionapplications.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrepatas.api.adoptionapplications.enums.ApplicationStatus;
import com.entrepatas.api.adoptionapplications.model.AdoptionApplication;

public interface AdoptionApplicationRepository extends MongoRepository<AdoptionApplication, String> {

    List<AdoptionApplication> findByPetIdOrderByCreatedAtDesc(String petId);

    List<AdoptionApplication> findByOrganizationIdOrderByCreatedAtDesc(String organizationId);

    List<AdoptionApplication> findByOrganizationIdAndStatusOrderByCreatedAtDesc(String organizationId,
            ApplicationStatus status);

    boolean existsByPetIdAndApplicantEmail(String petId, String applicantEmail);

}
