package com.pickyourtrial.interview.referee.service;

import com.pickyourtrial.interview.referee.constants.BookingConstants;
import com.pickyourtrial.interview.referee.model.request.BookingRequestModel;
import com.pickyourtrial.interview.referee.model.response.BookingResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Test
    public void shouldReturnFailureStatusForSelfReferralOfTripUser() {
        BookingRequestModel selfReferralUserRequest = BookingRequestModel.builder()
                .tripReferralId(123L)
                .tripUserId(123L)
                .build();
        assertEquals(BookingResponseModel.failed(BookingConstants.SAME_USER_REFERRAL_NOT_ALLOWED), bookingService.addBooking(selfReferralUserRequest));
    }
}
