package com.appsdeveloperblog.app.ws.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, hashCode
@Builder // Generates a builder pattern for object creation
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor
public class UpdateUserRequestDetailModel {

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank") // Added @NotBlank
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank") // Added @NotBlank
    private String lastName;
}
