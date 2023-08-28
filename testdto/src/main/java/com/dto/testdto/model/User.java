package com.dto.testdto.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
public class User {
    

    @Id
    @Column(name="id_account")
    private String idAccount;
    private String password;
    @Column(name="ic_number")
    private String icNumber;
    private String role;
    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
   
    public User(String password, String icNumber, String role) {
        this.idAccount = UUID.randomUUID().toString();
        this.password = password;
        this.icNumber = icNumber;
        this.role = role;
    }
    
    public String getIdAccount()
    {
        return idAccount;
    }
    public void setIdAccount(String idAccount)
    {
        this.idAccount = idAccount;
    }
     public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
     public String getIcNumber()
    {
        return icNumber;
    }
    public void setIcNumber(String icNumber)
    {
        this.icNumber = icNumber;
    }
     public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
}
