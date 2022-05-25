package com.bonacamp.ecasystem.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "ACESSINFO")
@EntityListeners(AuditingEntityListener.class)
public class AcessInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String ws_id;
    
    @Column(nullable = false)
    private String poc_id;

    @Column(nullable = false)
    private String access_token;
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime create_at;
    
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime update_at;
	
}
