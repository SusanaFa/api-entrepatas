package com.entrepatas.api.petimage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrepatas.api.petimage.model.PetImage;

public interface PetImageRepository extends MongoRepository<PetImage, String> {

    List<PetImage> findByPetIdOrderByOrderIndexAscCreatedAtAsc(String petId);

    Optional<PetImage> findByPetIdAndIsPrimaryTrue(String petId);

    List<PetImage> findByOrganizationId(String organizationId);
}
