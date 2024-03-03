package com.potatoes.bloodrecovery.mock;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;

import java.util.ArrayList;
import java.util.List;

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
}
