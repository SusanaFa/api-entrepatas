package com.entrepatas.api.pets.controller;

import com.entrepatas.api.pets.dto.CreatePetRequest;
import com.entrepatas.api.pets.model.Pet;
import com.entrepatas.api.pets.service.PetService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/pets")
public class AdminPetController {

    private final PetService petService;

    public AdminPetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public Pet create(@Valid @RequestBody CreatePetRequest dto) {
        return petService.createFromDto(dto);
    }
}
