package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.OrderGroup;
import com.example.Study.model.network.request.OrderGroupApiRequest;
import com.example.Study.model.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

//    @Autowired
//    private OrderGroupApiLogicService orderGroupApiLogicService;
//
//    @PostConstruct
//    private void init(){
//        this.baseService = orderGroupApiLogicService;
//    }
}

//public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
//
//    @Autowired
//    private OrderGroupApiLogicService orderGroupApiLogicService;
//
//    @Override
//    @PostMapping("")
//    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
//        System.out.println(request);
//        return orderGroupApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
//        return orderGroupApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
//        return orderGroupApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        return orderGroupApiLogicService.delete(id);
//    }
//}
