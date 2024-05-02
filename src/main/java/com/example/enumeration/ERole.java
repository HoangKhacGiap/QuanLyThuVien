package com.example.enumeration;

public enum ERole {
    ADMIN("Role_Admin"),
    LECTURER("Role_Lecturer"),
    STUDENT("Role_Student");

    public static long roleAdmin = 1;
    public static long roleLibrary_manager = 2;
    public static long roleTeacher = 3;
    public static long roleStudent = 4;

    private final String name;

    ERole(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
