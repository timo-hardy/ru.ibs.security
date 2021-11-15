package ru.ibs.tkv.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ibs.tkv.security.model.Employee;
import ru.ibs.tkv.security.model.Task;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/manager/api")
public class ManagerController {

    private static final List<Employee> EMPLOYEES = Arrays.asList(
            new Employee(1, "Mr. Smith"),
            new Employee(2, "Mr. Proper"),
            new Employee(3, "Mr. Bean")
    );

    private static final List<Task> TASKS = Arrays.asList(
            new Task(1L, "Create app", "Need new application"),
            new Task(2L , "Update properties", "Update properties of db in dev stand")
    );

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Integer employeeId) {
        return EMPLOYEES.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee " + employeeId + "not found"
                ));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable("id") Integer taskId) {
        return TASKS.stream()
                .filter(task -> taskId.equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task " + taskId + "not found"
                ));
    }

    @DeleteMapping("/employee/{id}")
    public void fireEmployee(@PathVariable("id") Integer employeeId) {
        System.out.println("Emplyee " + employeeId + "is fired");
    }

    @PostMapping("/task/{id}")
    public void createTask(@PathVariable("id") String taskId, @RequestBody Task task) {
        System.out.println("Created new task" + task);
    }
}