    package com.appsdeveloperblog.app.ws.model.request;

    import jakarta.validation.constraints.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.validator.constraints.UUID; // Import UUID constraint

    @Data // Generates getters, setters, toString, equals, hashCode
    @Builder // Generates a builder pattern for object creation
    @NoArgsConstructor // Generates a no-argument constructor
    @AllArgsConstructor // Generates a constructor with all fields as arguments
    public class UserDetailRequestModel {

        // Changed type from Long to String to match the @UUID validator
        // Removed redundant @NotNull and @NotEmpty as @NotBlank implies them for String
        @UUID(message = "User ID must be a valid UUID format") // Add a custom message
        private String userId; // User ID should likely be String if it's a UUID

        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be blank") // Added @NotBlank for email
        @Email(message = "Email must be a valid email format") // Add custom message
        private String email;

        @NotNull(message = "First name cannot be null")
        @NotBlank(message = "First name cannot be blank") // Added @NotBlank
        private String firstName;

        @NotNull(message = "Last name cannot be null")
        @NotBlank(message = "Last name cannot be blank") // Added @NotBlank
        private String lastName;

        @NotNull(message = "Password cannot be null") // Add message
        @NotBlank(message = "Password cannot be blank") // Added @NotBlank
        @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters") // Corrected message wording slightly
        private String password;
    }