package com.udacity.timezones.service;

import com.udacity.timezones.client.ChargeUserApiHttpClient;
import com.udacity.timezones.model.TicketItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ChargeServiceTest {

    private ChargeService chargeService;
    @Mock
    ChargeUserApiHttpClient chargeUserApiHttpClient;

    @BeforeEach
    void init() {
        chargeService = new ChargeService(chargeUserApiHttpClient);
    }

    @Test
    void chargeUser(){
        chargeService.chargeUser("UserId123",
                List.of(new TicketItem("Durex", new BigDecimal("10.0"), new BigDecimal("1.0")),
                        new TicketItem("Durex", new BigDecimal("12.0"), new BigDecimal("1.2"))),
                new BigDecimal("1.50"),
                new BigDecimal("0.25")
                );
        verify(chargeUserApiHttpClient, times(1)).charge("UserId123", new BigDecimal("25.45"));
    }
}
