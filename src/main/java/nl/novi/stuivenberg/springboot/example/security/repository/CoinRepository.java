package com.customuserdetailsservice.demo.repository;


import com.customuserdetailsservice.demo.model.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coins, Long> {
    Coins findCoinsByCoinsKey(String coinsKey);
}
