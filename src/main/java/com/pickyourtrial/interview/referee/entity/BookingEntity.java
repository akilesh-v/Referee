package com.pickyourtrial.interview.referee.entity;

import com.pickyourtrial.interview.referee.constants.BookingConstants;
import com.pickyourtrial.interview.referee.model.request.BookingRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="BOOKING")
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKING_ID")
    private Long bookingId;

    @Column(name="BOOKING_START_DATE")
    private Date bookingStartDate;

    @Column(name="BOOKING_END_DATE")
    private Date bookingEndDate;

    @Column(name="TRIP_USER_ID")
    private Long tripUserId;

    @Column(name="TRIP_REFERRAL_ID")
    private Long tripReferralId;

    @Column(name="TRIP_TYPE")
    private Integer tripType;

    @Column(name="BOOKING_STATUS")
    private String bookingStatus;

    @Column(name="REWARD_PTS_EARNED")
    private Long rewardPtsEarned;

    @Column(name="AMOUNT_SPENT")
    private Double amountSpent;

    public BookingEntity(BookingRequestModel bookingRequestModel){
        this.bookingStartDate=bookingRequestModel.getStartDate();
        this.bookingEndDate=bookingRequestModel.getEndDate();
        this.tripUserId=bookingRequestModel.getTripUserId();
        this.tripReferralId=bookingRequestModel.getTripReferralId();
        this.tripType=bookingRequestModel.getTripType();
        this.bookingStatus= bookingRequestModel.getBookingStatus();
        this.amountSpent = bookingRequestModel.getAmountSpent();
    }
}
