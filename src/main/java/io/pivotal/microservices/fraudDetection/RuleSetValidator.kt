package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.saleRegistration.Sale

class RuleSetValidator constructor(val ruleSet : Set<Rule>, val threshold : Int): SaleValidator {

    override fun validate(sale: Sale): Boolean {
        var initial : Int = 0

        val score = ruleSet.stream().reduce(initial, { t: Int, u: Rule -> t + u.execute(sale) }, { a1: Int, a2: Int -> a1 + a2 })

        return score <= threshold
    }
}