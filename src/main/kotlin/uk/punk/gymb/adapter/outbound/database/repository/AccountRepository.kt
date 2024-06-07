package uk.punk.gymb.adapter.outbound.database.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.punk.gymb.adapter.outbound.database.entity.AccountEntity
import java.util.UUID

@Repository
interface AccountRepository  : JpaRepository<AccountEntity, UUID >