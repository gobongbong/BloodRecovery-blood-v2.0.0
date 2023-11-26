package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import com.potatoes.config.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public abstract class BloodCardMapper {

    @Mapping(target = "bloodCardId", ignore = true)
    public abstract RegisterBloodCardCommand registerReqtoCommand(String customerId, RegisterBloodCardReqDto registerBloodCardReqDto);
    @Mapping(target = "cardInfo", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "donationType", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "date", ignore = true)
    public abstract DeleteBloodCardCommand deleteReqtoCommand(String customerId, String bloodCardId);
}
