package com.authtoken.authtoken.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class SignUpDto {

    private Long id;

    @NotEmpty(message = "fullName is required")
    private String fullName;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotEmpty(message = "MobileNumber must include the prefix as country code, e.g., +919876543210")
    @Size(min = 13, message = "MobileNumber must include the prefix as country code, e.g., +919876543210")
    @Column(unique = true)
    private String mobileNumber;

    private String invitationCode;

    @NotEmpty(message = "Role is required")
    private String roles;

    @AssertTrue(message = "You must accept the terms & condition")
    private boolean termsCondition;

    private String errorMessage;

    public void setMobileNumber(String mobileNumber) {
        if (isValidMobileNumber(mobileNumber)) {
            this.mobileNumber = mobileNumber;
            this.errorMessage = null;
        } else {
            this.errorMessage = "Invalid mobile number format. Please use the format '+919876543210'";
        }
    }
    private boolean isValidMobileNumber(String number) {
        // Define a regex pattern for the valid mobile number format
        Pattern pattern = Pattern.compile("^\\+\\d{12}$"); // + followed by 12 digits
        return pattern.matcher(number).matches();
    }
}
