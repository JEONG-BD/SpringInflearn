package hellojpa.domain;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String creadtedBy;
    private String lastModifiedBy;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
