package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestDetailView;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodRequestDetailRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodRequestsRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.ModifyBloodRequestReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodRequestReqDto;
import com.potatoes.config.MapstructConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public abstract class BloodRequestMapper {

    public abstract RegisterBloodRequestCommand registerReqToCommand(String cid, RegisterBloodRequestReqDto registerBloodRequestReqDto);
    public abstract ModifyBloodRequestCommand modifyReqToCommand(String cid, Long requestId, ModifyBloodRequestReqDto modifyBloodRequestReqDto);
    public abstract GetBloodRequestDetailRspDto bloodRequsetDetailViewToDto(BloodRequestDetailView bloodRequestDetailView);
    public abstract GetBloodRequestsRspDto bloodRequseViewToDto(List<BloodRequestView> list);



//    @Mapping(target = "code", ignore = true)
//    @Mapping(target = "donationType", ignore = true)
//    @Mapping(target = "name", ignore = true)
//    @Mapping(target = "date", ignore = true)
//    public abstract DeleteBloodCardCommand deleteReqtoCommand(String cid, String bloodCardId);
}
