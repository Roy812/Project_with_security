package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coins, Long> {
    Coins findCoinsByCoinsKey(String coinsKey);
}
