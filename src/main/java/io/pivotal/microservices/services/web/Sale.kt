package io.pivotal.microservices.services.web

import io.pivotal.microservices.services.sales.SaleValidator
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
class Sale constructor(val buyer: Account,
                       val purchaseDate: Date,
                       val passengers: List<String>,
                       val cardHolder: String,
                       val creditCardNumber: Number,
                       val personToInvoice: String,
                       val destination: String,
                       val roundTrip: Boolean,
                       val travelDates: List<Date>,
                       val amount: MonetaryAmount) {

    fun validate(saleValidator: SaleValidator) : Boolean {
        return saleValidator.validate(this)
    }
}