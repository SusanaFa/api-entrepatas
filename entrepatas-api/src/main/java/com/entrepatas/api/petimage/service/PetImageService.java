package com.entrepatas.api.petimage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entrepatas.api.petimage.dto.CreatePetImageRequest;
import com.entrepatas.api.petimage.model.PetImage;
import com.entrepatas.api.petimage.repository.PetImageRepository;
import com.entrepatas.api.pets.model.Pet;
import com.entrepatas.api.pets.repository.PetRepository;

@Service
public class PetImageService {

    private final PetImageRepository petImageRepository;
    private final PetRepository petRepository;

    public PetImageService(PetImageRepository petImageRepository, PetRepository petRepository) {
        this.petImageRepository = petImageRepository;
        this.petRepository = petRepository;
    }

    public PetImage addImage(String petId, CreatePetImageRequest dto) {

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet no encontrado"));

        // Si esta imagen será primary, desmarcamos la actual primary (si existe)
        if (Boolean.TRUE.equals(dto.getIsPrimary())) {
            petImageRepository.findByPetIdAndIsPrimaryTrue(petId).ifPresent(existing -> {
                existing.setIsPrimary(false);
                petImageRepository.save(existing);
            });
        }

        PetImage img = new PetImage();
        img.setPetId(petId);
        img.setOrganizationId(pet.getOrganizationId()); // ✅ regla: hijo guarda orgId
        img.setUrl(dto.getUrl());
        img.setIsPrimary(dto.getIsPrimary() != null ? dto.getIsPrimary() : false);
        img.setOrderIndex(dto.getOrderIndex() != null ? dto.getOrderIndex() : 0);

        return petImageRepository.save(img);
    }

    public List<PetImage> listByPet(String petId) {
        return petImageRepository.findByPetIdOrderByOrderIndexAscCreatedAtAsc(petId);
    }
}
