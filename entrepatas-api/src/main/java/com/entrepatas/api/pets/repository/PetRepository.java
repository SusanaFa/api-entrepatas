package com.entrepatas.api.pets.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entrepatas.api.pets.enums.PetPriority;
import com.entrepatas.api.pets.enums.PetStatus;
import com.entrepatas.api.pets.model.Pet;

@Repository
public interface PetRepository extends MongoRepository<Pet, String> {

    // listado para el home (aquí estan los urgentes primero)
    List<Pet> findByStatusAndPriority(PetStatus status, PetPriority priority);

    // para listar aquellos que au siguen disponibles.
    List<Pet> findByStatus(PetStatus status);
}
