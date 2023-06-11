package com.potatoes.bloodrecovery.domain.model.aggregates;

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
@Table(name = "sample")
@ToString
public class Sample {

    @Id
    private String sample;
}
