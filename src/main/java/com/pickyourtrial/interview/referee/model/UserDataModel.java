package com.pickyourtrial.interview.referee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataModel {
    private Long userId;
    private String userName;
    private String address;
    private Long userCreditPoints;
    private Double totalAmountSpent;
}
