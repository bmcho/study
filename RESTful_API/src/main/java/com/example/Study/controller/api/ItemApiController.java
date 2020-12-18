package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.Item;
import com.example.Study.model.network.request.ItemApiRequest;
import com.example.Study.model.network.response.ItemApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {
//    @Autowired
//    private ItemApiLogicService itemApiLogicService;
//
//    @PostConstruct
//    private void init(){
//        this.baseService = itemApiLogicService;
//    }

//
//public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {
//
//    @Autowired
//    private ItemApiLogicService itemApiLogicService;
//
//    @Override
//    @PostMapping("")
//    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
//        System.out.println(request);
//        return itemApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<ItemApiResponse> read(@PathVariable Long id) {
//        return itemApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
//        System.out.println(request);
//        return itemApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header<ItemApiResponse> delete(@PathVariable Long id) {
//        return itemApiLogicService.delete(id);
//    }
}
