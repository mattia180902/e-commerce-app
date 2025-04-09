package com.sincon.ecommerce.customer;

public record CustomerResponse(

         String id,
         String firstname,
         String lastnamem,
         String email,
         Address address
) {
} 
