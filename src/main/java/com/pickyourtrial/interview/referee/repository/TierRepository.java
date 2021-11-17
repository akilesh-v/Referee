package com.pickyourtrial.interview.referee.repository;

import com.pickyourtrial.interview.referee.entity.TierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends JpaRepository<TierEntity,Long> {

}
