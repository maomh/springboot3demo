package site.mhjn.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import site.mhjn.demo.dict.CustomerLevel;
import site.mhjn.demo.dict.CustomerType;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "activity_customer_type")
@Data
public class ActivityCustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityCustomerTypeId;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Activity activity;

    private CustomerType customerType;

    @ElementCollection
    @CollectionTable(
            name = "activity_customer_type_level",
            joinColumns = @JoinColumn(name = "customer_activity_type_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"customer_activity_type_id", "customer_level"}
            )
    )
    @Column(name = "customer_level")
    private Set<CustomerLevel> customerLevels;

    private LocalDateTime createTime;
}
