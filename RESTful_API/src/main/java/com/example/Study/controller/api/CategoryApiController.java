package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.Category;
import com.example.Study.model.network.request.CategoryApiRequest;
import com.example.Study.model.network.response.CategoryApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {

//    @Autowired
//    private CategoryApiLogicService categoryApiLogicService;
//
//    @PostConstruct
//    private void init(){
//        this.baseService = categoryApiLogicService;
//    }

//public class CategoryApiController implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

//    @Autowired
//    private CategoryApiLogicService categoryApiLogicService;
//
//    @Override
//    @PostMapping("")
//    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
//        return categoryApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<CategoryApiResponse> read(@PathVariable Long id) {
//        return categoryApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
//        return categoryApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        return categoryApiLogicService.delete(id);
//    }
}
