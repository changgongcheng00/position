package com.trafigura.equity.position.dao;

import com.trafigura.equity.position.entity.Position;

public interface PositionDao {

    void saveOrUpdate(Position position);

    Position getBySecurityCode(String securityCode);

}