package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potatoes.bloodrecovery.application.commandservices.BloodCardCommandService;
import com.potatoes.bloodrecovery.application.commandservices.DeleteBloodCardCommandService;
import com.potatoes.bloodrecovery.application.queryservices.CustomerRequestsQueryService;
import com.potatoes.bloodrecovery.application.queryservices.GetBloodCardCountQueryService;
import com.potatoes.bloodrecovery.application.queryservices.GetBloodCardsQueryService;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.BloodCardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.BLOOD_BASE_URL;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.REGISTER_BLOOD_CARD;
import static com.potatoes.constants.StaticValues.HEADER_CID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BloodCardController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
class BloodCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRequestsQueryService customerRequestsQueryService;
    @MockBean
    private BloodCardMapper bloodCardMapper;
    @MockBean
    private BloodCardCommandService bloodCardCommandService;
    @MockBean
    private GetBloodCardsQueryService getBloodCardsQueryService;
    @MockBean
    private DeleteBloodCardCommandService deleteBloodCardCommandService;
    @MockBean
    private GetBloodCardCountQueryService getBloodCardCountQueryService;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("헌혈증 등록에 성공한다.")
    void registerBloodCard() throws Exception {
        //given
        RegisterBloodCardReqDto registerBloodCardReqDto = RegisterBloodCardReqDto.builder()
                .code("1111")
                .date("20231111")
                .name("최고봉")
                .donationType("전혈")
                .build();

        RegisterBloodCardCommand registerBloodCardCommand = RegisterBloodCardCommand.builder()
                .cid("11111")
                .code("1111")
                .date("20231111")
                .name("최고봉")
                .donationType("전혈")
                .build();

        String ci = "11111";

        given(bloodCardMapper.registerReqtoCommand(any(), any())).willReturn(registerBloodCardCommand);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post(BLOOD_BASE_URL + REGISTER_BLOOD_CARD)
                        .header(HEADER_CID, ci)
                        .content(asJsonString(registerBloodCardReqDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}