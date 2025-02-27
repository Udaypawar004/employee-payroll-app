package com.payroll.app.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @Id
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty(message = "Employee name can't be empty")
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String name;

    @NotNull
    @PositiveOrZero
    @Min(value = 500, message = "Min wage should be more than 500")
    private double salary;
}
