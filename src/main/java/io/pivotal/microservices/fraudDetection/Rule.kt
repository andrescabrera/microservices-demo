package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.saleRegistration.Sale

interface Rule {

    fun execute(sale : Sale) : Int
}