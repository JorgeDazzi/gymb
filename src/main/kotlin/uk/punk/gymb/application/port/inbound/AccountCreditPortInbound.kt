package uk.punk.gymb.application.port.inbound

import com.github.michaelbull.result.Result
import uk.punk.gymb.domain.dto.Account
import java.math.BigDecimal
import java.util.*

interface AccountCreditPortInbound  {

    fun credit( accountID : UUID, amount : BigDecimal) : Result< Account, Throwable >

}