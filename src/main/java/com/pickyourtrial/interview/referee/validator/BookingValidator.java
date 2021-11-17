package com.pickyourtrial.interview.referee.validator;

import com.pickyourtrial.interview.referee.common.constants.CommonConstants;
import com.pickyourtrial.interview.referee.constants.BookingConstants;
import com.pickyourtrial.interview.referee.model.request.BookingRequestModel;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookingValidator {

    public String validateBookingDetails(BookingRequestModel bookingRequestModel){
        if(Objects.equals(bookingRequestModel.getTripUserId(), bookingRequestModel.getTripReferralId())) {
            return BookingConstants.SAME_USER_REFERRAL_NOT_ALLOWED;
        }
        return CommonConstants.SUCCESS;
    }
}
