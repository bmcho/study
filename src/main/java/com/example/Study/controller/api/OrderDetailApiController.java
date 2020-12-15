package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.OrderDetail;
import com.example.Study.model.network.request.OrderDetailApiRequest;
import com.example.Study.model.network.response.OrderDetailApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderDetail")

public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
//    @Autowired
//    private OrderDetailApiLogicService orderDetailApiLogicService;
//
//    private void init(){
//        this.baseService = orderDetailApiLogicService;
//    }
}

//public class OrderDetailApiController implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {
//
//    @Autowired
//    private OrderDetailApiLogicService orderDetailApiLogicService;
//
//    @Override
//    @PostMapping("")
//    public Header<OrderDetailApiResponse> create(@RequestBody Header<OrderDetailApiRequest> request) {
//        return orderDetailApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<OrderDetailApiResponse> read(@PathVariable Long id) {
//        return orderDetailApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
//        return orderDetailApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(Long id) {
//        return orderDetailApiLogicService.delete(id);
//    }
//}
