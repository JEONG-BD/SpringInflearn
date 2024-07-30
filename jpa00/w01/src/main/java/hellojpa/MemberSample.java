package hellojpa;

import javax.persistence.*;

//@Entity
//@Table(uniqueConstraints = )
//public class Member {
//
//    public Member(){
//
//    }
////    public Member(Long id, String name) {
////        this.id = id;
////        this.name = name;
////    }
//
//    @Id
//    private Long id;
//
//    @Column(name="name",
//            updatable = false,
//            nullable = false,
//            unique = true,
//            length = 10)
//    private String name ;
//
//    private Integer age;
//
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testDate;
//
//    private LocalDateTime testDateTime;
//    @Lob
//    private String description;
//
//    @Transient
//    private int temp;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}

@Entity
//@SequenceGenerator(name= "member_seq_generator", sequenceName = "member_seq",initialValue = 1,
//        allocationSize = 50)
public class MemberSample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public MemberSample(){

    }

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
}
