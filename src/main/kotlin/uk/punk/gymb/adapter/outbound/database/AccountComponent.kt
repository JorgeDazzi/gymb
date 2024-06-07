package uk.punk.gymb.adapter.outbound.database

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import org.springframework.stereotype.Component
import uk.punk.gymb.adapter.mapper.toDomain
import uk.punk.gymb.adapter.mapper.toEntity
import uk.punk.gymb.adapter.outbound.database.entity.AccountEntity
import uk.punk.gymb.adapter.outbound.database.repository.AccountRepository
import uk.punk.gymb.application.port.outbound.AccountPortOutbound
import uk.punk.gymb.domain.dto.Account
import uk.punk.gymb.domain.exception.AccountCommitException
import uk.punk.gymb.domain.exception.AccountFinderException
import java.util.*

@Component
class AccountComponent (
    private val accountRepository: AccountRepository
): AccountPortOutbound {

    override fun findById(id: UUID): Result<Account, AccountFinderException> =
        accountRepository.findById(id)
            .orElseThrow()
            .toDomain()
            .mapError {
                AccountFinderException("An issue has been occurred during fetching account operation", it )
            }

    override fun updateAccount(account: Account): Result<Account, AccountCommitException> =
        account
            .toEntity()
            .andThen { accountRepository.save( it ).toDomain() }
            .mapError {
                AccountCommitException("An issue has been occurred during the save/create account operation", it)
            }


}