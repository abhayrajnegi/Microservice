package com.eazybytes.accounts.Controller;

import com.eazybytes.accounts.Entity.Customer;
import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
      name = "CRUD REST API's for Accounts in EazyBank",
      description = "CRUD REST API's for Accounts in EazyBank to create ,update and delete account details"
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping(path="/create")
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer and account inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    public ResponseEntity<ResponseDto>createAccount(@RequestBody @Valid CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }


    @GetMapping("/fetch")
    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch account details based on a mobile number"
    )
    public ResponseEntity<CustomerDto>fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber){
        CustomerDto customerDto=iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update account details based on a mobile number"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
                )
        ),

            @ApiResponse(
                    responseCode = "417",
                    description = "EXpectation failed"
            )

    })
    public ResponseEntity<ResponseDto>updateAccountDetails(@RequestBody CustomerDto customerDto) {

        boolean isUpdated=iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete account details based on a mobile number"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),

            @ApiResponse(
                    responseCode = "417",
                    description = "EXpectation failed"
            )
    })
    public ResponseEntity<ResponseDto>deleteAccountDetails(@RequestParam  @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber){
        boolean isDeleted=iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_DELETE));
        }
    }

}
