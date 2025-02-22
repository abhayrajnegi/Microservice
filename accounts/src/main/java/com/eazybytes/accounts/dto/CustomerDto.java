package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Cusomter and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "Eazy Bytes"
    )
    @NotEmpty(message = "Name can not be empty")
    @Size(min=5,max=30,message = "Name must be in size between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "tutor@eazybytes.com"
    )
    @NotEmpty(message = "Name can not be empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema(
        description = "Mobile Number of the customer",
        example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Accounts of the customer"
    )
    private AccountsDto accountsDto;

     public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }



    @Override
    public String toString() {
        return "CustomerDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
