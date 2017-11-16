package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.salesRegistration.Sale
import io.pivotal.microservices.services.web.SaleDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FraudDetectionController constructor (val saleValidator : SaleValidator) {

    @PostMapping
    fun validate(@RequestBody sale : SaleDTO) : Boolean = saleValidator.validate(sale)
}