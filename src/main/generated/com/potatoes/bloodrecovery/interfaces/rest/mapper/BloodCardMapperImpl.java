package com.potatoes.bloodrecovery.interfaces.rest.mapper;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-28T15:26:19+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.20 (Amazon.com Inc.)"
)
@Component
public class BloodCardMapperImpl extends BloodCardMapper {

    @Override
    public RegisterBloodCardCommand registerReqtoCommand(String customerId, RegisterBloodCardReqDto registerBloodCardReqDto) {
        if ( customerId == null && registerBloodCardReqDto == null ) {
            return null;
        }

        String customerId1 = null;
        if ( customerId != null ) {
            customerId1 = customerId;
        }
        String code = null;
        String donationType = null;
        String name = null;
        String date = null;
        if ( registerBloodCardReqDto != null ) {
            code = registerBloodCardReqDto.getCode();
            donationType = registerBloodCardReqDto.getDonationType();
            name = registerBloodCardReqDto.getName();
            date = registerBloodCardReqDto.getDate();
        }

        RegisterBloodCardCommand registerBloodCardCommand = new RegisterBloodCardCommand( code, donationType, name, date, customerId1 );

        return registerBloodCardCommand;
    }
}
