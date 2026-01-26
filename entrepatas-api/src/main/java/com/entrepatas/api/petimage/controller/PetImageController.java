package com.entrepatas.api.petimage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.entrepatas.api.petimage.dto.CreatePetImageRequest;
import com.entrepatas.api.petimage.model.PetImage;
import com.entrepatas.api.petimage.service.PetImageService;

import jakarta.validation.Valid;

@RestController
public class PetImageController {

    private final PetImageService petImageService;

    public PetImageController(PetImageService petImageService) {
        this.petImageService = petImageService;
    }

    // Admin: agregar imagen a un pet
    @PostMapping("/admin/pets/{petId}/images")
    public PetImage add(@PathVariable String petId, @Valid @RequestBody CreatePetImageRequest dto) {
        return petImageService.addImage(petId, dto);
    }

    // Público: listar imágenes de un pet (para la ficha)
    @GetMapping("/public/pets/{petId}/images")
    public List<PetImage> list(@PathVariable String petId) {
        return petImageService.listByPet(petId);
    }
}
