package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.view.CustomerInfoView;
import com.potatoes.bloodrecovery.domain.model.view.CustomerRequestInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRepository;
import com.potatoes.bloodrecovery.domain.repository.CustomerRepository;
import com.potatoes.exception.ApiException;
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
    private CustomerRepository customerRepository;

    @Mock
    private BloodRepository bloodRepository;

    @Test
    @DisplayName("회원의 헌혈 요청 정보 조회에 성공한다.")
    void getCustomerRequests(){
        //given
        String customerId = "1111";

        GetCustomerRequestsQuery getCustomerRequestsQuery = GetCustomerRequestsQuery.builder()
                .customerId(customerId)
                .build();

        CustomerInfoView customerInfoView = CustomerInfoView.builder()
                .gradeSn("Siver")
                .userBlood("A")
                .build();

        List<Blood> requests = new ArrayList<>();
        Blood blood = Blood.builder()
                .customerId("1111")
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

        given(customerRepository.getCustomerInfo(any())).willReturn(customerInfoView);
        given(bloodRepository.findByCustomerId(any())).willReturn(Optional.of(requests));

        //when
        List<CustomerRequestInfoView> result = customerRequestsQueryService.getCustomerRequests(getCustomerRequestsQuery);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("회원의 헌혈 요청 정보 조회에 성공한다. (헌혈 요청 정보가 없는 경우)")
    void getCustomerRequests_noContent(){
        //given
        String customerId = "1111";

        GetCustomerRequestsQuery getCustomerRequestsQuery = GetCustomerRequestsQuery.builder()
                .customerId(customerId)
                .build();

        CustomerInfoView customerInfoView = CustomerInfoView.builder()
                .gradeSn("Siver")
                .userBlood("A")
                .build();

        List<CustomerRequestInfoView> expect = new ArrayList<>();

        given(customerRepository.getCustomerInfo(any())).willReturn(customerInfoView);
        given(bloodRepository.findByCustomerId(any())).willReturn(Optional.empty());

        //when
        List<CustomerRequestInfoView> result = customerRequestsQueryService.getCustomerRequests(getCustomerRequestsQuery);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("회원의 헌혈 요청 정보 조회에 실패한다.")
    void getCustomerRequest_fail(){
        //given
        String customerId = "1111";

        GetCustomerRequestsQuery getCustomerRequestsQuery = GetCustomerRequestsQuery.builder()
                .customerId(customerId)
                .build();

        given(bloodRepository.findByCustomerId(any())).willThrow(new ApiException(NO_DATA));

        //when
        Throwable throwable = catchThrowable(()-> customerRequestsQueryService.getCustomerRequests(getCustomerRequestsQuery));

        //then
        assertThat(throwable)
                .isInstanceOf(ApiException.class);

        assertThat(((ApiException)throwable).getResultCode())
                .isEqualTo(NO_DATA.getResponseCode());
    }
}