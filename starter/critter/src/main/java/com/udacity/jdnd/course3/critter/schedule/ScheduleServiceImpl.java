package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    @Transactional
    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = new ArrayList<>();
        for(Long id : employeeIds){
            if(employeeRepository.findById(id).isPresent())
                employees.add(employeeRepository.findById(id).get());
        }
        schedule.setEmployees(employees);
        List<Pet> pets = new ArrayList<>();
        for(Long id : petIds){
            if(petRepository.findById(id).isPresent())
                pets.add(petRepository.findById(id).get());
        }
        schedule.setPets(pets);

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getSchedulesForPet(Pet pet) {
        return scheduleRepository.findAllByPetsContaining(pet);
    }

    @Override
    public List<Schedule> getSchedulesForEmployee(Employee employee) {
        return scheduleRepository.findAllByEmployeesContaining(employee);
    }

    @Override
    public List<Schedule> getSchedulesForCustomer(Customer customer) {
        return scheduleRepository.findAllByPetsOwner(customer);
    }
}