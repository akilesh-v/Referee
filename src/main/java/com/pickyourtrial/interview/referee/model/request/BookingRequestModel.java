package com.pickyourtrial.interview.referee.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequestModel {
    private String bookingStartDate;
    private String bookingEndDate;
    private Long tripUserId;
    private Integer tripType;
    private Double amountSpent;
    @JsonInclude(NON_NULL)
    private Long tripReferralId;

    @JsonIgnore
    private String bookingStatus;
    @JsonIgnore
    private Date startDate;
    @JsonIgnore
    private Date endDate;
    @JsonIgnore
    private Long rewardPtsEarned;
}
