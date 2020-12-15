package com.example.Study.repository;

import com.example.Study.StudyApplicationTests;
import com.example.Study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");
//        orderDetail.setOrderGroupId(1L);
//        orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save((orderDetail));

        Assertions.assertNotNull(newOrderDetail);
    }

    @Test
    public void read(){

    }

}