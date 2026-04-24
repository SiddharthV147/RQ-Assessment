package com.challenge.api.service;

import com.challenge.api.dto.EmployeeRequestDTO;
import com.challenge.api.exception.ResourceNotFoundException;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeModel;
import java.time.Instant;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final Map<UUID, Employee> employees = new HashMap<>();

    public EmployeeService() {
        Employee e1 = new EmployeeModel(
                UUID.randomUUID(),
                "Aniket",
                "Tote",
                "Aniket Tote",
                70000,
                28,
                "SDE 1",
                "aniket123@gmail.com",
                Instant.now(),
                null);
        Employee e2 = new EmployeeModel(
                UUID.randomUUID(),
                "Virat",
                "Yaduvanshi",
                "Virat Yaduvanshi",
                80000,
                30,
                "SDE 2",
                "virat123@gmail.com",
                Instant.now(),
                null);

        employees.put(e1.getUuid(), e1);
        employees.put(e2.getUuid(), e2);
    }

    public List<Employee> fetchAll() {
        logger.info("Returning the list of existing employees.");
        return new ArrayList<>(employees.values());
    }

    public Employee fetchById(UUID uuid) {
        Employee employee = employees.get(uuid);
        if (employee == null) {
            logger.error("User requested employee with UUID: {} did not exist.", uuid);
            throw new ResourceNotFoundException(
                    "Employee with UUID does not exists. If there has been any mistake please contact support.");
        }
        logger.info("Returning employee with UUID: {}", employee.getUuid());
        return employee;
    }

    public Employee createNew(EmployeeRequestDTO dto) {
        logger.info("Creating a new employee");
        Employee employee = new EmployeeModel(
                UUID.randomUUID(),
                dto.firstName(),
                dto.lastName(),
                (dto.fullName() == null || dto.fullName().isBlank()
                        ? (dto.firstName() + dto.lastName())
                        : dto.fullName()),
                dto.salary(),
                dto.age(),
                dto.jobTitle(),
                dto.email(),
                dto.contractHireDate(),
                dto.contractTerminationDate());
        employees.put(employee.getUuid(), employee);
        logger.info("Successfully created a new employee");
        return employees.get(employee.getUuid());
    }
}
