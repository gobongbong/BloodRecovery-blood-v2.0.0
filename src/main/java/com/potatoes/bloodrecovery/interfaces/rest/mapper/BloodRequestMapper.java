package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.ModifyBloodRequestReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodRequestReqDto;
import com.potatoes.config.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public abstract class BloodRequestMapper {

    public abstract RegisterBloodRequestCommand registerReqtoCommand(String cid, RegisterBloodRequestReqDto registerBloodRequestReqDto);

    public abstract ModifyBloodRequestCommand modifyReqtoCommand(String cid, String requestId, ModifyBloodRequestReqDto modifyBloodRequestReqDto);

//    @Mapping(target = "code", ignore = true)
//    @Mapping(target = "donationType", ignore = true)
//    @Mapping(target = "name", ignore = true)
//    @Mapping(target = "date", ignore = true)
//    public abstract DeleteBloodCardCommand deleteReqtoCommand(String cid, String bloodCardId);
}
