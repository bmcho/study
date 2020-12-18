package com.example.Study.service;

import com.example.Study.model.entity.OrderGroup;
import com.example.Study.model.entity.User;
import com.example.Study.model.enumclass.UserStatus;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.Pagination;
import com.example.Study.model.network.request.UserApiRequest;
import com.example.Study.model.network.response.ItemApiResponse;
import com.example.Study.model.network.response.OrderGroupApiResponse;
import com.example.Study.model.network.response.UserApiResponse;
import com.example.Study.model.network.response.UserOrderInfoApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    //1. request data
    //2. user 생성
    //3. 생성된 데이터 -> UserApiResponse
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return;
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id -> repository getOne, getById
        Optional<User> optional = baseRepository.findById(id);

        // user -> userApiResponse return
        return optional
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        //1. Data
        UserApiRequest userApiRequest = request.getData();

        //2. id -> user 데이터 search
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
                    return user;
                })
                .map(user -> baseRepository.save(user))  //3. update
                .map(user -> Header.OK(response(user)))           //4. userApiResponse
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header delete(Long id) {

        // 1.id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        // 2.repository -> delete
        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()-> Header.ERROR("NONE DATE"));
    }

//    private Header<UserApiResponse> response(User user){
//        //user -> userApiResponse
//
//        UserApiResponse userApiResponse = UserApiResponse.builder()
//                .id(user.getId())
//                .account(user.getAccount())
//                .password(user.getPassword())
//                .status(user.getStatus())
//                .email(user.getEmail())
//                .phoneNumber(user.getPhoneNumber())
//                .registeredAt(user.getRegisteredAt())
//                .unregisteredAt(user.getUnregisteredAt())
//                .build();
//
//        return Header.OK(userApiResponse);
//    }
    private UserApiResponse response(User user){
        //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return userApiResponse;
    }

    public Header<List<UserApiResponse>> serach(Pageable pageable) {

        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponses = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPage(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponses);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id){

        //user
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        //orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();

                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponsesList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponsesList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }
}
