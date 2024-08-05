package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String userName;

    @ManyToOne
    @JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
    private Team team;


    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

