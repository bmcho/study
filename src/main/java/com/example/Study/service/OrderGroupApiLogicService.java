package com.example.Study.service;

import com.example.Study.model.entity.OrderGroup;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.request.OrderGroupApiRequest;
import com.example.Study.model.network.response.OrderGroupApiResponse;
import com.example.Study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
//public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

//    @Autowired
//    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {

        //id -> repository getOne, getById
        Optional<OrderGroup> optional = baseRepository.findById(id);

        // user -> userApiResponse return
        return optional
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        //1. Data
        OrderGroupApiRequest body = request.getData();

        //2. id -> user 데이터 search
        Optional<OrderGroup> optional = baseRepository.findById(body.getId());

        return optional
                .map(orderGroup -> {
                    orderGroup
                    .setArrivalDate(body.getArrivalDate())
                    .setStatus(body.getStatus());
                    return orderGroup;
                })
                .map(orderGroup -> baseRepository.save(orderGroup))  //3. update
                .map(orderGroup -> response(orderGroup))             //4. userApiResponse
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header delete(Long id) {
        // 1.id -> repository -> user
        Optional<OrderGroup> optional = baseRepository.findById(id);

        // 2.repository -> delete
        return optional.map(orderGroup -> {
            baseRepository.delete(orderGroup);
            return Header.OK();
        })
                .orElseGet(()-> Header.ERROR("NONE DATE"));
    }

    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){

        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header. OK(orderGroupApiResponse);
    }
}
