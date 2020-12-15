package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.User;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.request.UserApiRequest;
import com.example.Study.model.network.response.UserApiResponse;
import com.example.Study.model.network.response.UserOrderInfoApiResponse;
import com.example.Study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j //Log
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable){
        return userApiLogicService.serach(pageable);
    }

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }

//    @Autowired
//    private UserApiLogicService userApiLogicService;
//
//    @PostConstruct
//    private void init(){
//        this.baseService = userApiLogicService;
//    }
}

//public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {
//
//    @Autowired
//    private UserApiLogicService userApiLogicService;
//
//    @Override
//    @PostMapping("")
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        //Log.info("{}", request);
//        System.out.println(request);
//        return userApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
//        return userApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
//        return userApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header<UserApiResponse> delete(@PathVariable(name = "id") Long id) {
//        return userApiLogicService.delete(id);
//    }
//}
