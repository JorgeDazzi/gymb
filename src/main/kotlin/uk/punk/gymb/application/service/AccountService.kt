package uk.punk.gymb.application.service

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.map
import org.springframework.stereotype.Service
import uk.punk.gymb.application.port.inbound.AccountCreditPortInbound
import uk.punk.gymb.application.port.inbound.AccountDebitPortInbound
import uk.punk.gymb.application.port.outbound.AccountPortOutbound
import uk.punk.gymb.domain.dto.Account
import java.math.BigDecimal
import java.util.*


@Service
class AccountService (

    private val accountPortOutbound: AccountPortOutbound

) : AccountDebitPortInbound, AccountCreditPortInbound {

    override fun debit(accountID: UUID, amount: BigDecimal): Result<Account, Throwable> =
        accountPortOutbound
            .findById(accountID)
            .map { it.debit( amount ) }
            .andThen { accountPortOutbound.updateAccount( it ) }


    override fun credit(accountID: UUID, amount: BigDecimal): Result<Account, Throwable> =
        accountPortOutbound
            .findById( accountID )
            .map { it.credit( amount ) }
            .andThen { accountPortOutbound.updateAccount( it ) }

}