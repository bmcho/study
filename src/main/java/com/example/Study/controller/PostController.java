package com.example.Study.controller;


import com.example.Study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    //HTML <Form>
    //ajax 검색
    //http post body -> data
    //json, xml, multipart-form /text-plaint

//    @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }
}
