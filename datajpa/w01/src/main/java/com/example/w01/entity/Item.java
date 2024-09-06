package com.example.w01.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Item implements Persistable<String> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

//    public Item() {
//    }
//    @Column(name="ITEM_ID")
//    private Long id;

    @Id
    @Column(name="ITEM_ID")
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @Override
    public String getId(){
        return id;
    }

    @Override
    public boolean isNew(){
        return createdDate == null;
    }
}
