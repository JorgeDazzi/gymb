package uk.punk.gymb.application.port.inbound

import com.github.michaelbull.result.Result
import uk.punk.gymb.domain.dto.Account
import uk.punk.gymb.domain.dto.Statement
import java.math.BigDecimal
import java.util.*

interface StatementPortInbound  {

    fun getStatement( accountID : UUID) : Result< Statement, Throwable >

}