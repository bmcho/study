package com.example.Study.repository;

import com.example.Study.StudyApplicationTests;
import com.example.Study.model.entity.User;
import com.example.Study.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1234-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy ="AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(UserStatus.REGISTERED);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        // builder 패턴
//        User u = User.builder()
//                .account(account)
//                .password(password)
//                .status(status)
//                .email(email)
//                .build();

        // chain
//        User u = new User().setEmail(email);
//        u.setAccount(account)
//                .setPhoneNumber(phoneNumber)
//                .setPassword(password);


        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1234-1234");

        user.getOrderGroupList().stream().forEach(o ->{
            System.out.println("------------주문요약---------");
            System.out.println("수령인 : " + o.getRevName());
            System.out.println("수령지 : " + o.getRevAddress());
            System.out.println("총금액 : " + o.getTotalPrice());
            System.out.println("총수량 : " + o.getTotalQuantity());

            System.out.println("------------주문상세---------");

            o.getOrderDetailList().forEach(d ->{
                System.out.println("파트너사 : " + d.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + d.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문상품 : " + d.getItem().getName());
                System.out.println("고객센터 : " + d.getItem().getPartner().getCallCenter());
                System.out.println("주문상태 : " + d.getStatus());
                System.out.println("도착예정일자 : " + d.getArrivalDate());

            });

        });

        Assertions.assertNotNull(user);
    }

    public void update(){

    }

    public void delete(){

    }

}
