package com.finzly.fx_trading.dao;

import com.finzly.fx_trading.entity.UserData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingRepository extends JpaRepository<UserData, Long> {

}
