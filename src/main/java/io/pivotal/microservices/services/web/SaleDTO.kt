package io.pivotal.microservices.services.web

import java.math.BigDecimal
import java.util.*


/*
    Sale DTO
*/
data class SaleDTO (val buyer: String,
            val purchaseDate: Date,
            val passengers: List<String>,
            val cardHolder: String,
            val creditCardNumber: Number,
            val personToInvoice: String,
            val destination: String,
            val roundTrip: Boolean,
            val travelDates: List<Date>,
            val amount: BigDecimal,
            var number: String = "",
            var valid: Boolean = false)