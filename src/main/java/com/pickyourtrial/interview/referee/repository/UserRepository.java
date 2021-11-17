package com.pickyourtrial.interview.referee.repository;

import com.pickyourtrial.interview.referee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByUserTierId(Long tierId);

    @Query(value = "select * from user_master where user_credit_points !=0  order by user_credit_points desc limit :userLimit",nativeQuery = true)
    List<UserEntity> findTopReferees(@Param("userLimit") Long userLimit);

    @Transactional
    @Modifying
    @Query(value = "update user_master set USER_CREDIT_POINTS = USER_CREDIT_POINTS + book.TOTAL_REWARD_PTS_EARNED from (select TRIP_REFERRAL_ID,sum(REWARD_PTS_EARNED) TOTAL_REWARD_PTS_EARNED  from booking where booking_end_date = :currentDate and booking_status = 'COMPLETED' group by TRIP_REFERRAL_ID) as book where book.TRIP_REFERRAL_ID = user_master.user_id",nativeQuery = true)
    void updateUserCreditPoints(@Param(value="currentDate") Date currentDate);

    @Transactional
    @Modifying
    @Query(value="update user_master set TOTAL_AMOUNT_SPENT= TOTAL_AMOUNT_SPENT + book.tamount_spent from (select trip_user_id,sum(amount_spent) as tamount_spent from booking where booking_end_date = :currentDate and booking_status = 'COMPLETED' group by trip_user_id) as book where book.trip_user_id = user_master.user_id",nativeQuery = true)
    void updateTotalAmountSpent(@Param(value="currentDate") Date currentDate);

    @Transactional
    @Modifying
    @Query(value = "update user_master usr set user_tier_id = (select tier_id from  tier_master where tier_range < usr.total_amount_spent order by tier_range desc limit 1 ) from booking book where book.trip_user_id = usr.user_id and booking_end_date = :currentDate and booking_status = 'COMPLETED';",nativeQuery = true)
    void updateUserTier(@Param(value="currentDate") Date currentDate);
}
