CREATE TABLE IF NOT EXISTS tier_master
(
    tier_id        SERIAL NOT NULL PRIMARY KEY,
    tier_name      text COLLATE pg_catalog.default,
    tier_range     numeric,
    itl_reward_pts numeric,
    dom_reward_pts numeric
);

CREATE TABLE IF NOT EXISTS USER_MASTER
(
    USER_ID            SERIAL NOT NULL PRIMARY KEY,
    USER_NAME          text COLLATE pg_catalog.default,
    USER_ADDRESS       text COLLATE pg_catalog.default,
    USER_PHONENO       bigint,
    USER_CITY          text COLLATE pg_catalog.default,
    USER_CREDIT_POINTS numeric default 0,
    TOTAL_AMOUNT_SPENT double precision default 0,
    USER_TIER_ID       bigint,
    CONSTRAINT USER_MASTER_USER_TIER_ID_fkey FOREIGN KEY (USER_TIER_ID)
        REFERENCES tier_master (tier_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS BOOKING
(
    BOOKING_ID         SERIAL                          NOT NULL PRIMARY KEY,
    BOOKING_START_DATE timestamp without time zone     NOT NULL,
    BOOKING_END_DATE   timestamp without time zone     NOT NULL,
    AMOUNT_SPENT       double precision                NOT NULL,
    TRIP_USER_ID       bigint                          NOT NULL,
    TRIP_REFERRAL_ID   bigint,
    TRIP_TYPE          smallint                        NOT NULL,
    BOOKING_STATUS     text COLLATE pg_catalog.default NOT NULL,
    REWARD_PTS_EARNED  numeric default 0,
    CONSTRAINT BOOKING_TRIP_USER_ID_fkey FOREIGN KEY (TRIP_USER_ID)
        REFERENCES USER_MASTER (USER_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO tier_master (tier_id, tier_name, tier_range, itl_reward_pts, dom_reward_pts)
VALUES (DEFAULT, 'SILVER', 0, 1000, 500);
INSERT INTO tier_master (tier_id, tier_name, tier_range, itl_reward_pts, dom_reward_pts)
VALUES (DEFAULT, 'GOLD', 250001, 2000, 1000);
INSERT INTO tier_master (tier_id, tier_name, tier_range, itl_reward_pts, dom_reward_pts)
VALUES (DEFAULT, 'PLATINUM', 1000001, 4000, 2000);