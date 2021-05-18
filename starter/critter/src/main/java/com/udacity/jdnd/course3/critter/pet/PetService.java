package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;

import java.util.List;

public interface PetService {

    public Pet savePet(Pet pet);

    public Pet findPetById(Long id);

    public List<Pet> getAllPets();

    public List<Pet> getPetsByOwner(Long ownerId);

}
