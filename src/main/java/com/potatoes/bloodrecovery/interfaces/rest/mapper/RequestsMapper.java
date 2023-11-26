package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.queries.GetBloodCardsQuery;
import com.potatoes.bloodrecovery.domain.model.queries.GetRequestsQuery;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodCardsReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetRequestsReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RequestsMapper {
    public abstract GetRequestsQuery getBloodCardsReqtoQuery(GetRequestsReqDto getRequestsReqDto);
}
