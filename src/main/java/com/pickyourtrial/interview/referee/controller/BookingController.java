package com.pickyourtrial.interview.referee.controller;

import com.pickyourtrial.interview.referee.common.controller.BaseController;
import com.pickyourtrial.interview.referee.common.deserializer.DateDeserializer;
import com.pickyourtrial.interview.referee.model.request.BookingRequestModel;
import com.pickyourtrial.interview.referee.model.request.UserRequestModel;
import com.pickyourtrial.interview.referee.model.response.BookingResponseModel;
import com.pickyourtrial.interview.referee.model.response.TierUsersResponseModel;
import com.pickyourtrial.interview.referee.model.response.UserResponseModel;
import com.pickyourtrial.interview.referee.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pickyourtrial.interview.referee.common.utils.DateUtils.parseDate;

@RestController
@RequestMapping("/booking")
public class BookingController extends BaseController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add_user")
    public ResponseEntity<UserResponseModel> addUser(UserRequestModel userData){
        UserResponseModel userResponseData = bookingService.addUser(userData);
        return  userResponseData.isStatus() ? ResponseEntity.ok(userResponseData) : ResponseEntity.badRequest().body(userResponseData);
    }

    @GetMapping("/get_user/{tier_id}")
    public ResponseEntity<TierUsersResponseModel> getTierUserDetails(@RequestParam("tier_id") Long tierId){
        TierUsersResponseModel userData = bookingService.getTierUserData(tierId);
        return userData.isStatus() ? ResponseEntity.ok(userData) : ResponseEntity.badRequest().body(userData);
    }

    @PostMapping("/book_trip")
    public ResponseEntity<BookingResponseModel> bookTrip(BookingRequestModel bookingData){
        bookingData.setStartDate(parseDate(bookingData.getBookingStartDate(), DateDeserializer.dateFormat));
        bookingData.setEndDate(parseDate(bookingData.getBookingEndDate(), DateDeserializer.dateFormat));
        BookingResponseModel bookingResponseData = bookingService.addBooking(bookingData);
        return bookingResponseData.isStatus()? ResponseEntity.ok(bookingResponseData): ResponseEntity.badRequest().body(bookingResponseData);
    }

    @GetMapping("/get_top_referrers/{user_limit}")
    public ResponseEntity<TierUsersResponseModel> getTopReferrers(@RequestParam("user_limit") Long userLimit){
        TierUsersResponseModel userResponseData = bookingService.getTopReferrers(userLimit);
        return userResponseData.isStatus() ? ResponseEntity.ok(userResponseData) : ResponseEntity.badRequest().body(userResponseData);
    }
}


