package com.kong.bike.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public String getFormattedCreatedDate() {
        if (createdDate == null){
            return "";
        }
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
    public String getFormattedModifiedDate() {
        if (modifiedDate == null){
            return "";
        }
        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
