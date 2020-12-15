package com.example.Study.controller.api;

import com.example.Study.controller.CrudController;
import com.example.Study.model.entity.Partner;
import com.example.Study.model.network.request.PartnerApiRequest;
import com.example.Study.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

//    @Autowired
//    private PartnerLogicService partnerLogicService;
//
//    @PostConstruct
//    private void init(){
//        this.baseService = partnerLogicService;
//    }
}

//public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {
//
//    @Autowired
//    private PartnerLogicService partnerLogicService;
//
//    @Override
//    @PostMapping("")
//    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
//        return partnerLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<PartnerApiResponse> read(@PathVariable Long id) {
//        return partnerLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
//        return partnerLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        return partnerLogicService.delete(id);
//    }
//}
