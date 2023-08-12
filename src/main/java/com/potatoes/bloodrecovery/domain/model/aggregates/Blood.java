package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "blood")
@ToString
public class Blood {

    @Id
    private String bloodId;

    private String customerId;
    //customerId????? 흠.. 요청자 아이디 넣는게 맞는거같은데...이렇게 하면 customerId로 요청자 아이디 조회하게 다시 해와야하는게 맞나..

    private String bloodStatus;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;
    private Post post;

}
