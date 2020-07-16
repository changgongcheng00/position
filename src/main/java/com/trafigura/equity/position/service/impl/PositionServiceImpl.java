package com.trafigura.equity.position.service.impl;

import com.trafigura.equity.position.dao.PositionDao;
import com.trafigura.equity.position.dto.PositionDto;
import com.trafigura.equity.position.entity.Position;
import com.trafigura.equity.position.service.PositionService;
import com.trafigura.equity.position.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PositionServiceImpl
 * @Description TODO
 * @Author cheng
 * @Date 2020/7/16 17:32
 **/
@Service
public class PositionServiceImpl implements PositionService {

    Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    private PositionDao positionDao;

    @Override
    public ResponseData<PositionDto> list(int current,int rowCount) {
        ResponseData<PositionDto> responseData = ResponseData.getPageData(current, rowCount);
        try {
            List<Position> positions = positionDao.list();
            List<PositionDto> pdList = new ArrayList<>();
            for (Position position : positions) {
                PositionDto pd = new PositionDto();
                BeanUtils.copyProperties(position,pd);
                pdList.add(pd);
            }
            ResponseData.setPageData(responseData,pdList);
        } catch (BeansException e) {
            logger.error("PositionServiceImpl - list throw exception :" +e.getMessage());
            return ResponseData.error(500,"查询展示异常");
        }
        return responseData;
    }
}
