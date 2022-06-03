package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "tbl_application")
@NoArgsConstructor
@AllArgsConstructor
public class Application extends AbstractModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id", nullable = false)
    private Long appId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "os_info")
    private String osInfo;

    @Column(name = "device_info")
    private String deviceInfo;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "count")
    private Integer count;

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


    @Transient
    public void appUpdateSave() {
        count = count+1;
        updated = System.currentTimeMillis();
    }
}
