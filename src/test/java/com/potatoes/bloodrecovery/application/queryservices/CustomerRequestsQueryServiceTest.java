package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.model.view.CustomerRequestInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.potatoes.constants.ResponseCode.NO_DATA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerRequestsQueryServiceTest {

    @InjectMocks
    private CustomerRequestsQueryService customerRequestsQueryService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BloodRepository bloodRepository;

    @Test
    @DisplayName("회원의 헌혈 요청 정보 조회에 성공한다.")
    @Disabled
    void getCustomerRequests(){
        //given
        String cid = "1111";

        GetCustomerRequestsQuery getCustomerRequestsQuery = GetCustomerRequestsQuery.builder()
                .cid(cid)
                .build();

        UserInfoView userInfoView = UserInfoView.builder()
                .build();

        List<Blood> requests = new ArrayList<>();
        Blood blood = Blood.builder()
                .cid("1111")
                .bloodId("1111")
                .bloodDonationCnt(1)
                .bloodReqCnt(3)
                .bloodStatus("요청")
                .build();
        requests.add(blood);

        List<CustomerRequestInfoView> expect = new ArrayList<>();
        CustomerRequestInfoView customerRequestInfoView = CustomerRequestInfoView.builder()
                .gradeSn("Siver")
                .bloodDonationCnt(1)
                .bloodReqCnt(3)
                .bloodStatus("요청")
                .build();
        expect.add(customerRequestInfoView);

        given(userRepository.getUserInfo(any())).willReturn(userInfoView);
        given(bloodRepository.findByCid(any())).willReturn(Optional.of(requests));

        //when
        List<CustomerRequestInfoView> result = customerRequestsQueryService.getCustomerRequests(getCustomerRequestsQuery);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("회원의 헌혈 요청 정보 조회에 성공한다. (헌혈 요청 정보가 없는 경우)")
    @Disabled
    void getCustomerRequests_noContent(){
        //given
        String cid = "1111";

        GetCustomerRequestsQuery getCustomerRequestsQuery = GetCustomerRequestsQuery.builder()
                .cid(cid)
                .build();

        UserInfoView userInfoView = UserInfoView.builder()
                .userId("1111")
                .nickname("고봉이")
                .name("최고봉")
                .phone("01099999999")
                .fileNm(".....img")
                .email("gobong@naver.com")
                .build();

        List<CustomerRequestInfoView> expect = new ArrayList<>();

        given(userRepository.getUserInfo(any())).willReturn(userInfoView);
        given(bloodRepository.findByCid(any())).willReturn(Optional.empty());

        //when
        List<CustomerRequestInfoView> result = customerRequestsQueryService.getCustomerRequests(getCustomerRequestsQuery);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("회원의 헌혈 요청 정보 조회에 실패한다.")
    @Disabled
    void getCustomerRequest_fail(){
        //given
        String cid = "1111";

        GetCustomerRequestsQuery getCustomerRequestsQuery = GetCustomerRequestsQuery.builder()
                .cid(cid)
                .build();

        given(bloodRepository.findByCid(any())).willThrow(new ApiException(NO_DATA));

        //when
        Throwable throwable = catchThrowable(()-> customerRequestsQueryService.getCustomerRequests(getCustomerRequestsQuery));

        //then
        assertThat(throwable)
                .isInstanceOf(ApiException.class);

        assertThat(((ApiException)throwable).getResultMessage())
                .isEqualTo(NO_DATA.getMessage());
    }
}