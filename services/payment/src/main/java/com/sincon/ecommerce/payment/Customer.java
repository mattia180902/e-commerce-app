package com.sincon.ecommerce.payment;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record Customer(
    String id,

    @NotNull(message = "firstname is required")
    String firstname,

    @NotNull(message = "lastname is required")
    String lastname,

    @NotNull(message = "email is required")
    @Email(message = "the customer email is not correctly formatted")
    String email
)  {

}
