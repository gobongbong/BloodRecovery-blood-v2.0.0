package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.queries.GetBloodCardsQuery;
import com.potatoes.bloodrecovery.interfaces.rest.dto.DeleteBloodCardReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodCardsReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BloodCardMapper {
    public abstract GetBloodCardsQuery getBloodCardsReqtoQuery(GetBloodCardsReqDto getBloodCardsReqDto);

    @Mapping(target = "bloodCardId", ignore = true)
    public abstract RegisterBloodCardCommand registerReqtoCommand(RegisterBloodCardReqDto registerBloodCardReqDto);

    @Mapping(target = "code", ignore = true)
    @Mapping(target = "donationType", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "date", ignore = true)
    public abstract DeleteBloodCardCommand deleteReqtoCommand(DeleteBloodCardReqDto deleteBloodCardReqDto);
}
