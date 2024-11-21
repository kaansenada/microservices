package com.kaansenada.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Card",description = "Schema for card information")
@Data
public class CardDto {
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number should be 10 digits")
    @Schema(description = "Mobile number of the card holder",example = "1234567890")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be empty")
    @Pattern(regexp = "(^[0-9]{12}$)", message = "Card number should be 12 digits")
    @Schema(description = "Card number of the card holder",example = "1234567890123456")
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be empty")
    @Schema(description = "Card type of the card holder",example = "Credit")
    private String cardType;

    @Positive(message = "Total limit cannot be empty")
    @Schema(description = "Total limit of the card holder",example = "1000")
    private int totalLimit;

    @PositiveOrZero(message = "Amount used cannot be empty")
    @Schema(description = "Amount used of the card holder",example = "500")
    private int amountUsed;

    @PositiveOrZero(message = "Available amount cannot be empty")
    @Schema(description = "Available amount of the card holder",example = "500")
    private int availableAmount;

}
