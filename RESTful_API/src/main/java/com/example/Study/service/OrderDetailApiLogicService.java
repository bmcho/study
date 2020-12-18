package com.example.Study.service;

import com.example.Study.model.entity.OrderDetail;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.request.OrderDetailApiRequest;
import com.example.Study.model.network.response.OrderDetailApiResponse;
import com.example.Study.repository.ItemRepository;
import com.example.Study.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

//    @Autowired
//    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .item(itemRepository.getOne(body.getItemID()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {

        Optional<OrderDetail> optional = baseRepository.findById(id);

        return optional
                .map(detail -> response(detail))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        Optional<OrderDetail> optional = baseRepository.findById(body.getId());

        return optional
                .map(detail -> {
                    detail
                    .setArrivalDate(body.getArrivalDate())
                    .setStatus(body.getStatus())
                    .setItem(itemRepository.getOne(body.getItemID()))
                    .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                    ;

                    return detail;
                })
                .map(detail -> baseRepository.save(detail))
                .map(detail -> response(detail))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header delete(Long id) {

        Optional<OrderDetail> optional = baseRepository.findById(id);

        return optional
                .map(detail -> {
                    baseRepository.delete(detail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail){

        OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .totalPrice(orderDetail.getTotalPrice())
                .quantity(orderDetail.getQuantity())
                .arrivalDate(orderDetail.getArrivalDate())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemID(orderDetail.getItem().getId())
                .build();

        return Header.OK(orderDetailApiResponse);
    }
}
