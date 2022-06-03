package com.scando.learning.common.models;

import com.scando.learning.common.models.AbstractModel;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id", nullable = false)
    private Long userId;

    @Column(name ="name")
    public String name;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "user_status")
    private Integer userStatus;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "created")
    public Long created;

    @Column(name = "updated")
    public Long updated;

    @PrePersist
    public void prePersist() {
        created = System.currentTimeMillis();
        updated = System.currentTimeMillis();
    }

    @PreUpdate
    public void preUpdate() {
        updated = System.currentTimeMillis();
    }

}
