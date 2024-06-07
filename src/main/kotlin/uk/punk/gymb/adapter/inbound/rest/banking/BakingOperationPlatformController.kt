package uk.punk.gymb.adapter.inbound.rest.banking

import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.map
import com.github.michaelbull.result.unwrap
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.punk.gymb.adapter.inbound.rest.dto.request.DebitOperationRequest
import uk.punk.gymb.adapter.inbound.rest.dto.response.GymbResponse
import uk.punk.gymb.adapter.mapper.toDomain
import uk.punk.gymb.application.port.inbound.BankOperationPlatformPortInbound
import uk.punk.gymb.application.port.inbound.StatementPortInbound
import uk.punk.gymb.domain.dto.Account
import java.util.*


@RestController
@RequestMapping("/banking")
class BakingOperationPlatformController (
    private val bankOperationPlatformPortInbound: BankOperationPlatformPortInbound,
    private val statementPortInbound: StatementPortInbound
){


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun debit(@RequestBody debitOpt: DebitOperationRequest) : ResponseEntity< GymbResponse<Account> > =
        debitOpt.toDomain()
            .andThen { bankOperationPlatformPortInbound.debit( it ) }
            .unwrap()
            .let { ResponseEntity.ok( GymbResponse(description = null, status = HttpStatus.OK, data = it) ) }


    @GetMapping(value = ["/statement/{accountID}"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun statement(@PathVariable accountID: UUID) : ResponseEntity< String > =
        statementPortInbound.getStatement( accountID )
            .map { it.getStatementComplete() }
            .unwrap()
            .let { ResponseEntity.ok(it) }


}