package site.mhjn.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import site.mhjn.demo.consts.CustomerLevel;
import site.mhjn.demo.consts.CustomerType;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "customer_item")
@Data
public class CustomerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerItemId;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @ToString.Exclude
    private Activity activity;

    private CustomerType customerType;

    @ElementCollection
    @CollectionTable(
            name = "customer_item_include_levels",
            joinColumns = @JoinColumn(name = "customer_item_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"customer_item_id", "customer_level"}
            )
    )
    @Column(name = "customer_level")
    private Set<CustomerLevel> customerLevels;

    private LocalDateTime createTime;
}
