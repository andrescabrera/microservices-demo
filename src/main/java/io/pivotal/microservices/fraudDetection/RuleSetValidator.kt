package io.pivotal.microservices.fraudDetection

import io.pivotal.microservices.fraudDetection.rules.Rule
import io.pivotal.microservices.services.web.SaleDTO
import org.springframework.stereotype.Component

@Component
class RuleSetValidator(val ruleSet: Set<Rule>, val threshold: Int) : SaleValidator {

    override fun score(sale: SaleDTO) : Int =
        ruleSet.stream().reduce(0,
                { t: Int, u: Rule -> t + u.execute(sale) },
                { a1: Int, a2: Int -> a1 + a2 })

    override fun validate(sale: SaleDTO): Boolean =
            threshold < score(sale)
}