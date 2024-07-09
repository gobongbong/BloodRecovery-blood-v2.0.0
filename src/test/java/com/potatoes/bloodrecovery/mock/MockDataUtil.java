package com.potatoes.bloodrecovery.mock;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.constants.RequestStatus;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.RequestStatus.*;
import static com.potatoes.constants.StaticValues.BLOOD_CARD_DONATION;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;

public class MockDataUtil {

    public static List<BloodCard> commonBloodCardList(){
        List<BloodCard> cards = new ArrayList<>();
        BloodCard bloodCard = BloodCard.builder()
                .cid("aaaa")
                .code("1111")
                .date("20230201")
                .donationType("전혈")
                .name("고봉")
                .bloodCardId(1L)
                .build();
        cards.add(bloodCard);
        return cards;
    }

    public static BloodCard commonBloodCard(){
        return BloodCard.builder()
                .cid("aaaa")
                .code("1111")
                .date("20230201")
                .donationType("전혈")
                .name("고봉")
                .bloodCardId(1L)
                .build();
    }

    public static BloodRequest commonBloodRequest(RequestStatus requestStatus){
        return BloodRequest.builder()
                .requestId(111L)
                .bloodDonationCnt(requestStatus == ONGOING ? 1 : 0)
                .bloodReqCnt(3)
                .requestType(BLOOD_CARD_DONATION)
                .requestStatus(requestStatus)
                .cid("111")
                .post(new Post())
                .directedDonation(new DirectedDonation())
                .build();
    }

    public static BloodRequest commonBloodRequest_Complete(){
        return BloodRequest.builder()
                .requestId(111L)
                .bloodDonationCnt(3)
                .bloodReqCnt(3)
                .requestType(BLOOD_CARD_DONATION)
                .requestStatus(COMPLETE)
                .cid("111")
                .post(new Post())
                .directedDonation(new DirectedDonation())
                .build();
    }

    public static Page<BloodRequest> commonBloodRequests(){
        List<BloodRequest> list = new ArrayList<>();
        list.add(commonBloodRequest(REGISTER));
        list.add(commonBloodRequest(ONGOING));

        Pageable pageable = Mockito.mock(Pageable.class);

        return new PageImpl<>(list, pageable, list.size());
    }

    public static List<DonationHistory> commonDonationHistoryList_Card() {
        List<DonationHistory> list = new ArrayList<>();
        DonationHistory donationHistory = DonationHistory.builder()
                .historyId(111L)
                .donationCnt(2)
                .requestId(111L)
                .cid("111")
                .donationCnt(1)
                .donationType(BLOOD_CARD_DONATION)
                .donationStatus(null)
                .date(LocalDateTime.now())
                .build();
        list.add(donationHistory);
        return list;
    }

    public static List<DonationHistory> commonDonationHistoryList_Directed() {
        List<DonationHistory> list = new ArrayList<>();
        DonationHistory donationHistory = DonationHistory.builder()
                .historyId(111L)
                .donationCnt(2)
                .requestId(111L)
                .cid("111")
                .donationCnt(1)
                .donationType(DIRECTED_DONATION)
                .donationStatus(null)
                .date(LocalDateTime.now())
                .build();
        list.add(donationHistory);
        return list;
    }

    public static UserInfoView commonUserInfoView(){
        return UserInfoView.builder()
                .userId("1111")
                .nickname("gobong")
                .name("고봉")
                .phone("010-1111-2222")
                .fileNm("aaaaa")
                .email("gobong@naver.com")
                .build();
    }
}
