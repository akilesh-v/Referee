package com.pickyourtrial.interview.referee.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel extends StatusAndMessageAPIResponse {
    private Long userId;

    public UserResponseModel(boolean pstatus, String pMessage, Long userId) {
        super(pstatus, pMessage);
        this.userId = userId;
    }

    public UserResponseModel(boolean pStatus, String pMessage) {
        super(pStatus, pMessage);
    }

    public static UserResponseModel success(Long userId) {
        return new UserResponseModel(true, "Success", userId);
    }

    public static UserResponseModel error(String pMessage) {
        return new UserResponseModel(false, pMessage);
    }

    public static UserResponseModel failed(String pMessage) {
        return new UserResponseModel(false, pMessage);
    }
}
