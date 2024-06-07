package uk.punk.gymb.adapter.inbound.rest.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.util.UUID

data class DebitOperationRequest(
    @JsonProperty("account_debit_id") @NotBlank val accountDebitID : UUID,
    @JsonProperty("account_credit_id") @NotBlank val accountCreditID : UUID,
    @NotBlank val amount: BigDecimal
)
