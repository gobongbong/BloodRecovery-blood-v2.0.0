package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodCardJPARepository extends JpaRepository<BloodCard, Long>, BloodCardRepository{
    List<BloodCard> findByCid(String cid);
    Optional<BloodCard> findBloodCardByCidAndBloodCardId(String cid, Long bloodCardId);
    void deleteByBloodCardId(Long bloodCardId);
}
