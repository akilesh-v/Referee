package com.pickyourtrial.interview.referee.entity;

import com.pickyourtrial.interview.referee.model.request.UserRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER_MASTER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_ADDRESS")
    private String userAddress;

    @Column(name = "USER_PHONENO")
    private Long userPhone;

    @Column(name = "USER_CITY")
    private String userCity;

    @Column(name = "USER_CREDIT_POINTS")
    private Long userCreditPoints;

    @Column(name = "TOTAL_AMOUNT_SPENT")
    private double totalAmountSpent;

    @Column(name = "USER_TIER_ID")
    private Long userTierId;

    public UserEntity(UserRequestModel userData) {
        this.userName = userData.getUserName();
        this.userAddress = userData.getUserAddress();
        this.userPhone = userData.getUserPhone();
        this.userCity = userData.getUserCity();
        this.userCreditPoints = userData.getUserCreditPoints();
        this.userTierId = userData.getUserTierId();
        this.totalAmountSpent = 0.00;
    }

}
