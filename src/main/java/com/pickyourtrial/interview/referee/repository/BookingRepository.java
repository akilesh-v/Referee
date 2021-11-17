package com.pickyourtrial.interview.referee.repository;

import com.pickyourtrial.interview.referee.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

    List<BookingEntity> findByTripUserId(Long tripUserId);

    @Transactional
    @Modifying
    @Query(value = "update BOOKING set BOOKING_STATUS = :bookingStatus where BOOKING_START_DATE = :startDate and BOOKING_STATUS='BOOKED'",nativeQuery = true)
    void updateInProgressBooking(@Param(value = "startDate") Date bookingStartDate, @Param("bookingStatus") String bookingStatus);

    @Transactional
    @Modifying
    @Query(value = "update BOOKING set BOOKING_STATUS = :bookingStatus where BOOKING_END_DATE = :endDate and BOOKING_STATUS='IN-PROGRESS'",nativeQuery = true)
    void updateCompletedBooking(@Param(value = "endDate") Date bookingEndDate, @Param("bookingStatus") String bookingStatus);

    @Transactional
    @Modifying
    @Query(value ="update booking set REWARD_PTS_EARNED = case when trip_type=0 then itl_reward_pts else dom_reward_pts end  from (select user_id,user_tier_id,itl_reward_pts,dom_reward_pts from user_master inner join tier_master on (user_tier_id = tier_id)) tier where trip_referral_id = user_id and booking_end_date = :currentDate and booking_status = 'COMPLETED'",nativeQuery = true)
    void updateRewardPointsEarned(@Param(value="currentDate") Date currentDate);
}
