package com.pickyourtrial.interview.referee.scheduler;

import com.pickyourtrial.interview.referee.common.utils.DateUtils;
import com.pickyourtrial.interview.referee.constants.BookingConstants;
import com.pickyourtrial.interview.referee.repository.BookingRepository;
import com.pickyourtrial.interview.referee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PointUpdateScheduler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Scheduled(cron = "0 1 1 * * ?")
    public void updateRewardPointsAndTier() {
        updateTripStatus();
        updateBookingCreditPoint();
        updateUserPointsAndAmountAndTier();
    }

    private void updateTripStatus() {
        bookingRepository.updateInProgressBooking(DateUtils.getCurrentDate(), BookingConstants.BOOKING_STATUS_IN_PROGRESS);
        bookingRepository.updateCompletedBooking(DateUtils.getCurrentDate(), BookingConstants.BOOKING_STATUS_COMPLETED);
    }

    private void updateBookingCreditPoint() {
        bookingRepository.updateRewardPointsEarned(DateUtils.getCurrentDate());
    }

    private void updateUserPointsAndAmountAndTier() {
        userRepository.updateUserCreditPoints(DateUtils.getCurrentDate());
        userRepository.updateTotalAmountSpent(DateUtils.getCurrentDate());
        userRepository.updateUserTier(DateUtils.getCurrentDate());
    }

}
