package com.scando.learning.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_archive_user_login_info")
public class ArchiveUserLoginInfo extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id", nullable = false)
    private Long archiveId;

    @Column(name = "login_id", nullable = false)
    private Long userLoginId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "app_id")
    private Long appId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", insertable = false, updatable = false)
    private Application application;

    @Column(name = "token")
    private String token;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expiry_time")
    private Long expiryTime;

    @Column(name = "created")
    public Long created;

    @Column(name = "updated")
    public Long updated;

    @Column(name = "archived_date")
    public Long archivedDate;

    @PrePersist
    public void prePersist() {
        archivedDate = System.currentTimeMillis();
        created = System.currentTimeMillis();
        updated = System.currentTimeMillis();
    }

}
