package com.payroll.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    @Id
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Start Date should not be empty")
    @PastOrPresent(message = "Start date should be past or todays date")
    public LocalDate startDate;

    @NotBlank(message = "Note can't be empty")
    public String note;

    @NotBlank(message = "Profile pic can't be empty")
    public String profilePic;

    @NotNull(message = "Department should not be empty")
    public List<String> department;
}
