package com.example.Study.repository;


import com.example.Study.StudyApplicationTests;
import com.example.Study.model.entity.OrderGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;


    @Test
    public void create(){
        OrderGroup orderGroup = new OrderGroup();
//        orderGroup.setStatus("COMPLETE");
//        orderGroup.setOrderType("ALL");
//        orderGroup.setRevAddress("서울시 강남구");
//        orderGroup.setRevName("홍길동");
//        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setOrderAt(LocalDateTime.now().plusDays(-2));
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
//        orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        Assertions.assertNotNull(newOrderGroup);
    }

    @Test
    public void read(){

    }

}
