package io.pivotal.microservices.services.web

import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*


/*
    Sale DTO
*/
data class SaleDTO (var buyer: String = "",
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            var purchaseDate: Date = Date(),
            var passengers: List<String> = listOf(),
            var cardHolder: String = "",
            var creditCardNumber: Number = 0,
            var personToInvoice: String = "",
            var destination: String = "",
            var roundTrip: Boolean = false,
            var travelDates: List<Date> = listOf(Date(), Date()),
            var amount: BigDecimal = BigDecimal(0),
            var number: String = "",
            var valid: Boolean = false)