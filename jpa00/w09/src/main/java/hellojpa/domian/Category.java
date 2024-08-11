package hellojpa.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
    joinColumns = @JoinColumn(name="CATEGORY_ID"),
    inverseJoinColumns = @JoinColumn(name="ITEM_ID"))
    private List<Item> items = new ArrayList<>();
}
