package com.pickyourtrial.interview.referee.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {
    private String userName;
    private String userAddress;
    private Long userPhone;
    private String userCity;
    @JsonIgnore
    private Long userCreditPoints;
    @JsonIgnore
    private Long userTierId;
}
