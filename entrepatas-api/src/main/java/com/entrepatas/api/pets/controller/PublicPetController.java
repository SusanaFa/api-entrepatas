package com.entrepatas.api.pets.controller;

import com.entrepatas.api.pets.model.Pet;
import com.entrepatas.api.pets.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/pets") // endpoint públicas para filtrar mascotas
public class PublicPetController {

    private final PetService petService;

    public PublicPetController(PetService petService) {
        this.petService = petService;
    }

    // Get /public/pets?status=AVAILABLE
    @GetMapping
    public List<Pet> list(@RequestParam(required = false) String status) {
        return petService.list(status);
    }

    // Get public/pets/6543..
    @GetMapping("/{id}")
    public Pet getById(@PathVariable String id) {
        return petService.getById(id);
    }

    // Get public/pets/urgent (para el home)
    @GetMapping("/urgent")
    public List<Pet> urgent() {
        return petService.urgent();
    }

}
