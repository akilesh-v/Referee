package com.pickyourtrial.interview.referee.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tier_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tier_id")
    private Long tierId;

    @Column(name="tier_name")
    private String tierName;

    @Column(name="tier_range")
    private Long tierRange;

    @Column(name="itl_reward_pts")
    private Long imternationalRewardPoints;

    @Column(name="dom_reward_pts")
    private Long domesticRewardPoints;
}
