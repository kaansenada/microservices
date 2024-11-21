package com.kaansenada.cards.controller;

import com.kaansenada.cards.dto.CardDto;
import com.kaansenada.cards.dto.ErrorResponseDto;
import com.kaansenada.cards.dto.ResponseDto;
import com.kaansenada.cards.enums.CardEnums;
import com.kaansenada.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path ="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardController {
    private ICardService cardService;

    @Operation(summary = "Create card", description = "Create card to the bank with given mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Card created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "(^?|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardEnums.MESSAGE_201.toString(), CardEnums.STATUS_201.toString()));
    }
    @Operation(
            summary = "Fetch Card Details REST API",
            description = "Get the details of the card with given mobile number"
    )
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/details")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam @Pattern(regexp = "(^?|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        CardDto cardDto = cardService.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardDto);
    }
    @Operation( summary = "Update Card", description = "Update card with given mobile number")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "417", description = "HTTP Status Expectation Failed", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid@RequestBody CardDto cardDto) {
        boolean isUpdated = cardService.updateCard(cardDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardEnums.STATUS_200.toString(), CardEnums.MESSAGE_200.toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardEnums.STATUS_417.toString(), CardEnums.MESSAGE_417_UPDATE.toString()));
        }
    }

    @Operation( summary = "Delete Card", description = "Delete card with given mobile number")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "417", description = "HTTP Status Expectation Failed", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardEnums.STATUS_200.toString(), CardEnums.MESSAGE_200.toString()));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardEnums.STATUS_417.toString(), CardEnums.MESSAGE_417_DELETE.toString()));
        }
    }
}
