package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;

import java.util.List;

public interface ScheduleService {

    public List<Schedule> getAllSchedules();

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds);

    public List<Schedule> getSchedulesForPet(Pet pet);

    public List<Schedule> getSchedulesForEmployee(Employee employee);

    public List<Schedule> getSchedulesForCustomer(Customer customer);
}
