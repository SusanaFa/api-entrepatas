package com.entrepatas.api.adoptionapplications.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entrepatas.api.adoptionapplications.dto.CreateAdoptionApplicationRequest;
import com.entrepatas.api.adoptionapplications.dto.UpdateApplicationStatusRequest;
import com.entrepatas.api.adoptionapplications.enums.ApplicationStatus;
import com.entrepatas.api.adoptionapplications.model.AdoptionApplication;
import com.entrepatas.api.adoptionapplications.repository.AdoptionApplicationRepository;
import com.entrepatas.api.pets.model.Pet;
import com.entrepatas.api.pets.repository.PetRepository;

@Service
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;
    private final PetRepository petRepository;

    public AdoptionApplicationService(AdoptionApplicationRepository adoptionApplicationRepository,
            PetRepository petRepository) {
        this.adoptionApplicationRepository = adoptionApplicationRepository;
        this.petRepository = petRepository;
    }

    // Público: crear solicitud para un pet
    public AdoptionApplication create(String petId, CreateAdoptionApplicationRequest dto) {

        String email = dto.getApplicantEmail().trim().toLowerCase();

        if (adoptionApplicationRepository.existsByPetIdAndApplicantEmail(petId, email)) {
            throw new IllegalArgumentException("Ya existe una postulación para esta mascota con ese correo.");
        }

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet no encontrado"));

        AdoptionApplication app = new AdoptionApplication();

        app.setPetId(petId);
        app.setOrganizationId(pet.getOrganizationId()); // regla hijo: guarda orgId desde Pet
        app.setStatus(ApplicationStatus.PENDING); // status siempre PENDING al crear

        app.setApplicantName(dto.getApplicantName());
        app.setApplicantEmail(email);
        app.setApplicantPhone(dto.getApplicantPhone());

        app.setMessage(dto.getMessage());
        app.setCity(dto.getCity());
        app.setHousingType(dto.getHousingType());
        app.setHasOtherPets(dto.getHasOtherPets());

        return adoptionApplicationRepository.save(app);
    }

    // Admin: listar por mascota
    public List<AdoptionApplication> listByPet(String petId) {
        return adoptionApplicationRepository.findByPetIdOrderByCreatedAtDesc(petId);
    }

    // Admin: listar por organización (opcional filtrar por status)
    public List<AdoptionApplication> listByOrganization(String organizationId, String status) {
        if (status != null && !status.isBlank()) {
            ApplicationStatus enumStatus = ApplicationStatus.valueOf(status.toUpperCase());
            return adoptionApplicationRepository.findByOrganizationIdAndStatusOrderByCreatedAtDesc(organizationId,
                    enumStatus);
        }
        return adoptionApplicationRepository.findByOrganizationIdOrderByCreatedAtDesc(organizationId);
    }

    // Admin: cambiar status
    public AdoptionApplication updateStatus(String applicationId, UpdateApplicationStatusRequest dto) {
        AdoptionApplication app = adoptionApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        app.setStatus(dto.getStatus());
        return adoptionApplicationRepository.save(app);
    }
}
