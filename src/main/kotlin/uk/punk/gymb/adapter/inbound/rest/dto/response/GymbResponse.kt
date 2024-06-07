package uk.punk.gymb.adapter.inbound.rest.dto.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.http.HttpStatus


//@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
data class GymbResponse <R> (
    val description: String?,
    val status : HttpStatus,
    val data : R?
)
