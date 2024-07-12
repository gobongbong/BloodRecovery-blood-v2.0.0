package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String cid;

    private String bloodStatus;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;
    @Embedded
    private Post post;

}
