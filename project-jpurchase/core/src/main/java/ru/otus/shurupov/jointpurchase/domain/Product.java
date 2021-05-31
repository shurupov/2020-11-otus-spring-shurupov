package ru.otus.shurupov.jointpurchase.domain;

import lombok.Data;
import ru.otus.shurupov.jointpurchase.converter.StringListConverter;
import ru.otus.shurupov.jointpurchase.converter.MapConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "c_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Purchase.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Convert(converter = MapConverter.class)
    @Column(name = "properties")
    private Map<String, Object> properties;

    @Column(name = "option_name")
    private String optionName;

    @Column(name = "options")
    @Convert(converter = StringListConverter.class)
    private List<String> options;

    @Column(name = "price")
    private Integer price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
