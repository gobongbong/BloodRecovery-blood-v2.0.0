package com.potatoes.bloodrecovery.mock;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.RequestStatus.COMPLETE;
import static com.potatoes.constants.RequestStatus.REGISTER;
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

    public static BloodRequest commonBloodRequest_Register(){
        return BloodRequest.builder()
                .requestId(111L)
                .bloodDonationCnt(1)
                .bloodReqCnt(3)
                .requestType("전혈")
                .requestStatus(REGISTER)
                .cid("111")
                .build();
    }

    public static BloodRequest commonBloodRequest_Complete(){
        return BloodRequest.builder()
                .requestId(111L)
                .bloodDonationCnt(1)
                .bloodReqCnt(3)
                .requestType("전혈")
                .requestStatus(COMPLETE)
                .cid("111")
                .build();
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
