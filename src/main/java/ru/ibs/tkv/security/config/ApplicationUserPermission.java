package ru.ibs.tkv.security.config;

public enum ApplicationUserPermission { // Это доступы
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    TASK_READ("task:read"),
    TASK_WRITE("task:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
