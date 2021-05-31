package ru.otus.shurupov.jointpurchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.jointpurchase.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
