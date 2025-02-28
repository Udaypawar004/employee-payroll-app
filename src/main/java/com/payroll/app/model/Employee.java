package com.payroll.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty(message = "Employee name can't be empty")
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String name;

    @NotNull
    @PositiveOrZero
    @Min(value = 500, message = "Min wage should be more than 500")
    private double salary;
    public String gender;

    public LocalDate startDate;

    public String note;

    public String profilePic;

    public List<String> department;
}