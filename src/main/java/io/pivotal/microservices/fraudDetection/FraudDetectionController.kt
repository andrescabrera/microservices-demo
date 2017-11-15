package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.saleRegistration.Sale
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FraudDetectionController constructor (val saleValidator : SaleValidator) {

    @PostMapping
    fun validate(@RequestBody sale : Sale) : Boolean = saleValidator.validate(sale)
}