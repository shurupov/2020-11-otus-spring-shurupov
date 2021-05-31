package ru.otus.shurupov.jointpurchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.shurupov.jointpurchase.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
