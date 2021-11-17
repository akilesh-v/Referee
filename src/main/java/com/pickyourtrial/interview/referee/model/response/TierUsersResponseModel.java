package com.pickyourtrial.interview.referee.model.response;

import com.pickyourtrial.interview.referee.entity.UserEntity;
import com.pickyourtrial.interview.referee.model.UserDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TierUsersResponseModel extends StatusAndMessageAPIResponse{
    private List<UserDataModel> userData;

    public TierUsersResponseModel(boolean pstatus, String pMessage, List<UserDataModel> userData) {
        super(pstatus, pMessage);
        this.userData = userData;
    }

    public TierUsersResponseModel(boolean pStatus, String pMessage) {
        super(pStatus, pMessage);
    }

    public static TierUsersResponseModel success(List<UserDataModel> userData) {
        return new TierUsersResponseModel(true, "Success", userData);
    }

    public static TierUsersResponseModel error(String pMessage) {
        return new TierUsersResponseModel(false, pMessage);
    }

    public static TierUsersResponseModel failed(String pMessage) {
        return new TierUsersResponseModel(false, pMessage);
    }
}
