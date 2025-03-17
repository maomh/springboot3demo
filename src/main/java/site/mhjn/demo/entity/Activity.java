package site.mhjn.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "activity")
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CustomerItem> customerItems;

}
