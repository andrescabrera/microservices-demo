package io.pivotal.microservices.fraudDetection.rules

import io.pivotal.microservices.services.web.SaleDTO
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class TravelDateBetweenNext24HoursRule : Rule {
    override fun execute(sale: SaleDTO): Int {
        val tomorrowHere = LocalDateTime.now().plusHours(24)
        val tomorrow = Date.from(tomorrowHere.atZone(ZoneId.systemDefault()).toInstant())
        when(sale.travelDates.first().before(tomorrow)) {
            true -> return 30
            false -> return 0
        }
    }
}