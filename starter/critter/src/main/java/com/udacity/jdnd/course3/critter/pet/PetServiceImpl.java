package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;



    @Override
    @Transactional
    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        if(pet.getOwner() != null) {
            Long ownerId = pet.getOwner().getId();
            Customer ownerDBCopy = customerRepository.getOne(ownerId);
            List<Pet> savedPets = ownerDBCopy.getPets();
            if (savedPets == null) {
                savedPets = new ArrayList<>();
            }
            savedPets.add(savedPet);
            ownerDBCopy.setPets(savedPets);
            customerRepository.save(ownerDBCopy);
        }
        return savedPet;
    }

    @Override
    public Pet findPetById(Long id) {
        if(petRepository.findById(id).isPresent()){
            return petRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

}
