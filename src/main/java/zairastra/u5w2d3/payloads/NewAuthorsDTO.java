package zairastra.u5w2d3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewAuthorsDTO(
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "Surname is required")
        String surname,
        @NotEmpty(message = "Email is required")
        @Email(message = "Insert a valid email")
        String email,
        LocalDate birthDate) {
}
