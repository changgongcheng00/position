package com.trafigura.equity.position.controller;

import com.trafigura.equity.position.dto.PositionDto;
import com.trafigura.equity.position.service.PositionService;
import com.trafigura.equity.position.util.ResponseData;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PositionController
 * @Description TODO
 * @Author cheng
 * @Date 2020/7/16 17:50
 **/
@Api(tags = "PositionController")
@RestController
public class PositionController {

    Logger logger = LoggerFactory.getLogger(PositionController.class);

    @Autowired
    private PositionService positionService;

    @GetMapping("/getPositionList")
    public ResponseData<PositionDto> getPositionList(@RequestParam("current") int current, @RequestParam("rowCount")int rowCount){
        return positionService.list(current,rowCount);
    }
}
