package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.DirectedBloodDonationReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.DonationBloodCardReqDto;
import com.potatoes.config.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public abstract class DonationBloodMapper {

    public abstract DonationBloodCardCommand donationBloodCardReqtoCommand(String cid, DonationBloodCardReqDto donationBloodCardReqDto);

    public abstract DirectedBloodDonationCommand directedDonationReqtoCommand(String cid, DirectedBloodDonationReqDto directedBloodDonationReqDto);
}
