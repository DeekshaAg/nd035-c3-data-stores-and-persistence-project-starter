package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public Employee getEmployeeById(Long id);

    public void updateAvailability(Set<DayOfWeek> daysAvailable,Long employeeId);

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> activities);
}
