package io.pivotal.microservices.saleRegistration

import io.pivotal.microservices.accounts.Account
import io.pivotal.microservices.fraudDetection.SaleValidator
import java.util.*
import javax.money.MonetaryAmount


/*
    ● Usuario comprador
    ● Fecha de compra
    ● Pasajero/s
    ● Titular de la tarjeta de crédito
    ● Número de tarjeta de crédito
    ● Persona a facturar
    ● Destino (puede ser ida o ida y vuelta)
    ● Fecha de Viaje (si es sólo ida será una sola, si es ida y vuelta serán dos)
    ● Importe de la compra

*/
class Sale (val buyer: Account,
            val purchaseDate: Date,
            val passengers: List<String>,
            val cardHolder: String,
            val creditCardNumber: Number,
            val personToInvoice: String,
            val destination: String,
            val roundTrip: Boolean,
            val travelDates: List<Date>,
            val amount: MonetaryAmount,
            var number: UUID = UUID.randomUUID(),
            var valid: Boolean = false) {

    fun validate(saleValidator: SaleValidator) : Boolean = saleValidator.validate(this)
}