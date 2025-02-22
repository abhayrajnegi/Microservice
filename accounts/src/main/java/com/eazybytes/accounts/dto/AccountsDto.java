package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "Account Number can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number should be 10 digits")
    @Schema(
            description = "Account number of Eazy Bank Account",
            example = "3455433465"
    )
    private Long accountNumber;;

    @NotEmpty(message = "Account Type can not be empty")
    @Schema(
            description ="Accounts type of Eazy Bank Account",
            example = "Savings"
    )
    private String accountType;

    @Schema(
            description = "Eazy Bank branch address",
            example = "123 New york"
    )
    @NotEmpty(message = "Branch Address can not be empty"
    )
    private String branchAddress;


}
