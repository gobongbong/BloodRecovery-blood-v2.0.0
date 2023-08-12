package com.potatoes.bloodrecovery.domain.model.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetUserRequestsQuery {
    private String customerId;
}
