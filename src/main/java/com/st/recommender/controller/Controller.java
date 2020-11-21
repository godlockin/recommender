package com.st.recommender.controller;

import com.st.recommender.model.input.Param;
import com.st.recommender.model.output.ResponseWrapper;
import com.st.recommender.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Autowired
    @Resource(name = "predictServiceImpl")
    private PredictService predictService;

    @PostMapping(value = "predict")
    ResponseWrapper predict(@RequestBody Param param) {

        return ResponseWrapper.success(predictService.predict(param));
    }

}
