package com.example.MiniProjectGroup5.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditClass {
//
//    @CreatedBy
//    private String createdBy;
//
//    @CreatedDate
//    private Date createdDate;
//
//    @LastModifiedBy
//    private String lastModifiedBy;
//
//    @LastModifiedDate
//    private Date lastModifiedDate;
//
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public String getLastModifiedBy() {
//        return lastModifiedBy;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
}
