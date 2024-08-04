package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MemberSimple {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name="USERNAME")
    private String name;
//    @Column(name="TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private TeamSimple teamSimple;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }

    public void changeTeam(TeamSimple teamSimple){
        this.teamSimple = teamSimple;
        teamSimple.getMemberSimples().add(this);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + teamSimple +
                '}';
    }
}
