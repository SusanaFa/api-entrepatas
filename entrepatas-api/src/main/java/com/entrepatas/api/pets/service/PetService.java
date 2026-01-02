package com.entrepatas.api.pets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entrepatas.api.pets.dto.CreatePetRequest;
import com.entrepatas.api.pets.enums.BirthDateApproxLevel;
import com.entrepatas.api.pets.enums.PetPriority;
import com.entrepatas.api.pets.enums.PetStatus;
import com.entrepatas.api.pets.model.Pet;
import com.entrepatas.api.pets.repository.PetRepository;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // crear pet mediante dto.
    public Pet createFromDto(CreatePetRequest dto) {
        Pet pet = new Pet();

        // campos base
        pet.setOrganizationId(dto.getOrganizationId());
        pet.setName(dto.getName());
        pet.setSpecies(dto.getSpecies());
        pet.setSex(dto.getSex());

        // campos de edad
        pet.setBirthDateApprox(dto.getBirthDateApprox());
        // pet.setAgeEstimateText(dto.getAgeEstimateText());

        // Regla: una sola fuente de verdad para edad
        // Si hay fecha aprox, no guardamos texto estimado
        if (dto.getBirthDateApprox() != null) {
            pet.setAgeEstimateText(null);
        } else {
            pet.setAgeEstimateText(dto.getAgeEstimateText());
        }

        // regla de consistencia birthDateApproxLevel
        if (dto.getBirthDateApprox() == null) {
            // si no hay fecha, no tiene sentido tener nivel
            pet.setBirthDateApproxLevel(null);
        } else {
            // si hay fecha, usar el level del dto o poner default
            if (dto.getBirthDateApproxLevel() != null) {
                pet.setBirthDateApproxLevel(dto.getBirthDateApproxLevel());
            } else {
                pet.setBirthDateApproxLevel(BirthDateApproxLevel.MONTH_ONLY);
            }
        }

        // status/priority (si dto viene null, el modelo ya tiene defaults)
        if (dto.getStatus() != null) {
            pet.setStatus(dto.getStatus());
        }
        if (dto.getPriority() != null) {
            pet.setPriority(dto.getPriority());
        }

        return petRepository.save(pet);
    }

    // Listar todos los pet por status
    public List<Pet> list(String status) {
        if (status != null && !status.isBlank()) {
            PetStatus enumStatus = PetStatus.valueOf(status.toUpperCase());
            return petRepository.findByStatus(enumStatus);
        }
        return petRepository.findAll();
    }

    // buscar pet por id.. si no lo encuentra, lanza una excepcion en tiempo real
    public Pet getById(String id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet no encontrado"));
    }

    // lista pet por urgencia y estado disponible
    public List<Pet> urgent() {
        return petRepository.findByStatusAndPriority(PetStatus.AVAILABLE, PetPriority.URGENT);
    }

}
