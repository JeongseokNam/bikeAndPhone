package com.kong.bike.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LikeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; //참조 작성자 id

    @Column
    private Long relatId;

    @Column
    private String relatDiv; // 구분용 (폰 & 바이크)

    }
