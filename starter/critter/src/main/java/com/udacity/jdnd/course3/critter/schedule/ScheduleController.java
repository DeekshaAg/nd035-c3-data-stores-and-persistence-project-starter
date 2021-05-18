package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertEntityToDTO(scheduleService.saveSchedule(
                convertScheduleDTOToEntity(scheduleDTO),scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds()));
        //throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s : schedules){
            scheduleDTOS.add(convertEntityToDTO(s));
        }
        return scheduleDTOS;
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.findPetById(petId);
        List<Schedule> schedules = scheduleService.getSchedulesForPet(pet);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s : schedules){
            scheduleDTOS.add(convertEntityToDTO(s));
        }
        return scheduleDTOS;
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Schedule> schedules = scheduleService.getSchedulesForEmployee(employee);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s : schedules){
            scheduleDTOS.add(convertEntityToDTO(s));
        }
        return scheduleDTOS;

        //throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        List<Schedule> schedules = scheduleService.getSchedulesForCustomer(customer);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s : schedules){
            scheduleDTOS.add(convertEntityToDTO(s));
        }
        return scheduleDTOS;
        //throw new UnsupportedOperationException();
    }

    private static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO,schedule);
        return schedule;
    }

    private static ScheduleDTO convertEntityToDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);

        List<Long> employeeIds = new ArrayList<>();
        List<Employee> employees = schedule.getEmployees();
        for(Employee e : employees){
            employeeIds.add(e.getId());
        }
        scheduleDTO.setEmployeeIds(employeeIds);

        List<Pet> pets = schedule.getPets();
        List<Long> petIds = new ArrayList<>();
        for(Pet p : pets){
            petIds.add(p.getId());
        }
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }
}
