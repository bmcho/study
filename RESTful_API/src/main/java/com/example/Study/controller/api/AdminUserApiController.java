package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.AdminUser;
import com.example.Study.model.network.request.AdminUserApiRequest;
import com.example.Study.model.network.response.AdminUserApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

//    @Autowired
//    private AdminUserApiLogicService adminUserApiLogicService;
//
//    @PostConstruct
//    private void init(){
//        this.baseService = adminUserApiLogicService;
//    }

//public class AdminUserApiController implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {
//    @Override
//    @PostMapping("")
//    public Header<AdminUserApiResponse> create(@RequestBody Header<AdminUserApiRequest> request) {
//        return adminUserApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<AdminUserApiResponse> read(@PathVariable Long id) {
//        return adminUserApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<AdminUserApiResponse> update(@RequestBody Header<AdminUserApiRequest> request) {
//        return adminUserApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(Long id) {
//        return adminUserApiLogicService.delete(id);
//    }
}
