package com.bonacamp.ecasystem.domain.access.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "ACCESS_INFO")
@EntityListeners(AuditingEntityListener.class)
public class AccessInfo {
	
	@Id
    private Long svr_id;

    @Column(nullable = false)
    private String ws_id;
    
    @Column(nullable = false)
    private String poc_id;

    @Column(nullable = false)
    private String access_token;
    
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime create_at;
    
    @Column(nullable = true)
    @LastModifiedDate
    private LocalDateTime update_at;
	
}
