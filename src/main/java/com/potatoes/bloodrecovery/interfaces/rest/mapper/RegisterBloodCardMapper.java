package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import com.potatoes.config.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public abstract class RegisterBloodCardMapper {

    public abstract RegisterBloodCardCommand reqDtoToCommand(String customerId, RegisterBloodCardReqDto registerBloodCardReqDto);
}
