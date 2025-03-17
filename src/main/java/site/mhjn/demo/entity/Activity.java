package site.mhjn.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activityId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CustomerItem> customerItems;

}
