package uk.punk.gymb.application.service

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.binding
import org.springframework.stereotype.Service
import uk.punk.gymb.application.port.inbound.StatementPortInbound
import uk.punk.gymb.application.port.outbound.AccountPortOutbound
import uk.punk.gymb.application.port.outbound.StatementPortOutbound
import uk.punk.gymb.domain.dto.Statement
import java.util.*

@Service
class StatementService (
    private val accountPortOutbound: AccountPortOutbound,
    private val statementPortOutbound: StatementPortOutbound
) : StatementPortInbound{


    override fun getStatement(accountID: UUID): Result<Statement, Throwable> = binding {
        val acc = accountPortOutbound.findById( accountID ).bind()
        val transactions = statementPortOutbound.findByAccountId( accountID ).bind()

        Statement(
            account = acc,
            transactions = transactions
        )
    }


}