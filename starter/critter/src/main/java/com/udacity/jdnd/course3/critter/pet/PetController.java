package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Pet pet =convertPetDTOToPet(petDTO);
        if(petDTO.getOwnerId()>0) {
            pet.setOwner(customerService.findCustomerById(petDTO.getOwnerId()));
        }
        return convertPetEntityToPetDTO(petService.savePet(pet));
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetEntityToPetDTO(petService.findPetById(petId));
        //throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOS = new ArrayList<>();
        List<Pet> petList = petService.getAllPets();
        for(Pet pet: petList){
            petDTOS.add(convertPetEntityToPetDTO(pet));
        }
        return petDTOS;
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> petsByOwner = petService.getPetsByOwner(ownerId);
        List<PetDTO> pets = new ArrayList<>();
        for(Pet pet: petsByOwner){
            pets.add(convertPetEntityToPetDTO(pet));
        }
        return pets;
        //throw new UnsupportedOperationException();
    }

    private static Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        return pet;
    }

    private static PetDTO convertPetEntityToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        if(pet.getOwner() != null) {
            petDTO.setOwnerId(pet.getOwner().getId());
        }
        return petDTO;
    }

}
