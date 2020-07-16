package com.trafigura.equity.position.service;

import com.trafigura.equity.position.dto.PositionDto;
import com.trafigura.equity.position.util.ResponseData;

public interface PositionService {

    ResponseData<PositionDto> list(int current,int rowCount);
}
