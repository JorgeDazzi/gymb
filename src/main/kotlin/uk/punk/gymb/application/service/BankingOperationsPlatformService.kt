package uk.punk.gymb.application.service

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.binding
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uk.punk.gymb.application.mapper.toTransaction
import uk.punk.gymb.application.port.inbound.AccountCreditPortInbound
import uk.punk.gymb.application.port.inbound.AccountDebitPortInbound
import uk.punk.gymb.application.port.inbound.BankOperationPlatformPortInbound
import uk.punk.gymb.application.port.outbound.StatementPortOutbound
import uk.punk.gymb.domain.dto.Account
import uk.punk.gymb.domain.dto.DebitOperation
import uk.punk.gymb.domain.dto.Operation
import java.math.BigDecimal
import java.util.*

@Service
class BankingOperationsPlatformService (
    private val accountDebitPortInbound: AccountDebitPortInbound,
    private val accountCreditPortInbound: AccountCreditPortInbound,
    private val statementPortOutbound: StatementPortOutbound
) : BankOperationPlatformPortInbound{

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    override fun debit(debitOperation: DebitOperation ): Result<Account, Throwable> = binding {

        val accDebit = debitOperation
            .let { accountDebitPortInbound.debit( it.accountDebitID, it.amount ).bind() }

        val accCredit = debitOperation
            .let { accountCreditPortInbound.credit( debitOperation.accountCreditID, debitOperation.amount ).bind() }

        logger.info(
            "Debit Operation has been finished, " +
            "debit account current balance is ${accDebit.balance} " +
            "and credit account current balance is ${accCredit.balance}"
        )

        registerTransactionForDebit(accDebit, debitOperation.amount, accCredit)

        accDebit
    }

    private fun registerTransactionForDebit(accDebit: Account, amount: BigDecimal, accCredit: Account) {
        accDebit
            .toTransaction(opt = Operation.DEBIT, amount = amount, targetAccount = accCredit.id)
            .andThen { statementPortOutbound.save(it) }

        accCredit
            .toTransaction(opt = Operation.CREDIT, amount = amount, targetAccount = accDebit.id)
            .andThen { statementPortOutbound.save(it) }
    }


    override fun credit(debitAccountID: UUID, creditAccountID: UUID, amount: BigDecimal): Result<Account, Throwable> {
        TODO("Not yet implemented")
    }

}