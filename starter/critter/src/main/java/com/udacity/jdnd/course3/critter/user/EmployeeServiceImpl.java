package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        if(employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        }
        return null;
    }

    @Override
    @Transactional
    public void updateAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if(employee != null) {
            employee.setDaysAvailable(daysAvailable);
            saveEmployee(employee);
        }
    }

    @Override
    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> activities) {
        List<Employee> employeeList;
        List<Employee> result = new ArrayList<>();
        DayOfWeek day =date.getDayOfWeek();
        employeeList = employeeRepository.findAllByDaysAvailableContaining(day);
        for(Employee employee: employeeList){
            if(employee.getSkills().containsAll(activities))
                result.add(employee);
        }
        return result;
    }
}
