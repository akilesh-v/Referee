package com.pickyourtrial.interview.referee.service;

import com.pickyourtrial.interview.referee.common.constants.CommonConstants;
import com.pickyourtrial.interview.referee.constants.BookingConstants;
import com.pickyourtrial.interview.referee.entity.BookingEntity;
import com.pickyourtrial.interview.referee.entity.UserEntity;
import com.pickyourtrial.interview.referee.model.UserDataModel;
import com.pickyourtrial.interview.referee.model.request.BookingRequestModel;
import com.pickyourtrial.interview.referee.model.request.UserRequestModel;
import com.pickyourtrial.interview.referee.model.response.BookingResponseModel;
import com.pickyourtrial.interview.referee.model.response.TierUsersResponseModel;
import com.pickyourtrial.interview.referee.model.response.UserResponseModel;
import com.pickyourtrial.interview.referee.repository.BookingRepository;
import com.pickyourtrial.interview.referee.repository.UserRepository;
import com.pickyourtrial.interview.referee.validator.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingValidator bookingValidator;

    public UserResponseModel addUser(UserRequestModel userData) {
        try {
            userData.setUserCreditPoints(BookingConstants.DEFAULT_CREDIT_POINT);
            userData.setUserTierId(BookingConstants.DEFAULT_TIER_ID);
            UserEntity userSaveData = userRepository.save(new UserEntity(userData));
            return UserResponseModel.success(userSaveData.getUserId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserResponseModel.error("Exception Occurred while Processing User Data");
        }
    }

    public BookingResponseModel addBooking(BookingRequestModel bookingData) {
        try {
            String validateStatus = bookingValidator.validateBookingDetails(bookingData);
            if (CommonConstants.SUCCESS.equals(validateStatus)) {
                if (!bookingRepository.findByTripUserId(bookingData.getTripUserId()).isEmpty()) {
                    bookingData.setTripReferralId(0L);
                }
                bookingData.setBookingStatus(BookingConstants.BOOKING_STATUS_BOOKED);
                BookingEntity bookingResponse = bookingRepository.save(new BookingEntity(bookingData));
                return BookingResponseModel.success(bookingResponse.getBookingId());
            }
            return BookingResponseModel.failed(validateStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookingResponseModel.error("Exception occurred while Booking");
        }
    }

    public TierUsersResponseModel getTierUserData(Long tierId) {
        try {
            List<UserEntity> userData = userRepository.findByUserTierId(tierId);
            List<UserDataModel> userTierDatas = new ArrayList<>();
            for (UserEntity userTierData : userData) {
                userTierDatas.add(new UserDataModel(userTierData.getUserId(), userTierData.getUserName(), userTierData.getUserAddress(), userTierData.getUserCreditPoints(), userTierData.getTotalAmountSpent()));
            }
            return TierUsersResponseModel.success(userTierDatas);
        } catch (Exception ex) {
            ex.printStackTrace();
            return TierUsersResponseModel.error("Exception Occurred while retrieving user details");
        }
    }

    public TierUsersResponseModel getTopReferrers(Long userLimit) {
        try {
            List<UserEntity> userdata = userRepository.findTopReferees(userLimit);
            List<UserDataModel> userTierDatas = new ArrayList<>();
            for (UserEntity userTierData : userdata) {
                userTierDatas.add(new UserDataModel(userTierData.getUserId(), userTierData.getUserName(), userTierData.getUserAddress(), userTierData.getUserCreditPoints(), userTierData.getTotalAmountSpent()));
            }
            return TierUsersResponseModel.success(userTierDatas);
        } catch (Exception ex) {
            ex.printStackTrace();
            return TierUsersResponseModel.error("Exception Occurred while retrieving Top Referrers Details");
        }
    }

}
