package green.seagull.very.good.purchase.controllers

import green.seagull.very.good.purchase.domain.Health
import green.seagull.very.good.purchase.domain.HealthColours
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HealthController {

    @RequestMapping("/health")
    @ResponseBody
    fun health() = Health(HealthColours.Green)
    
}