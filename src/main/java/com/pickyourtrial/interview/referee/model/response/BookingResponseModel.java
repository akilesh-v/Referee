package com.pickyourtrial.interview.referee.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BookingResponseModel extends StatusAndMessageAPIResponse{
    private Long bookingId;
    public BookingResponseModel(boolean pstatus, String pMessage, Long bookingId){
        super(pstatus,pMessage);
        this.bookingId=bookingId;
    }
    public BookingResponseModel(boolean pStatus, String pMessage) {
        super(pStatus, pMessage);
    }

    public static BookingResponseModel success(Long bookingId) {
        return new BookingResponseModel(true, "Success", bookingId);
    }

    public static BookingResponseModel error(String pMessage) {
        return new BookingResponseModel(false, pMessage);
    }

    public static BookingResponseModel failed(String pMessage) {
        return new BookingResponseModel(false, pMessage);
    }
}
