package io.pivotal.microservices.salesRegistration

import io.pivotal.microservices.accounts.Account
import io.pivotal.microservices.services.web.SaleDTO
import org.javamoney.moneta.Money
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.money.Monetary

@RestController
class SaleRegistrationController constructor(val saleRegistrationService: SaleRegistrationService) {

    @PostMapping("/sales/register")
    fun register(@RequestBody saleDTO: SaleDTO): Sale = saleRegistrationService.register(map(saleDTO))

    private fun map(saleDTO: SaleDTO): Sale = Sale(
                    // TODO it should do a request to Accounts microservice to fetch account by number
                    Account(saleDTO.buyer, "Sarlanguita Ermindo Perez"),
                    saleDTO.purchaseDate,
                    saleDTO.passengers,
                    saleDTO.cardHolder,
                    saleDTO.creditCardNumber,
                    saleDTO.personToInvoice,
                    saleDTO.destination,
                    saleDTO.roundTrip,
                    saleDTO.travelDates,
                    Money.of(saleDTO.amount, Monetary.getCurrency("ARS"))
                )
}