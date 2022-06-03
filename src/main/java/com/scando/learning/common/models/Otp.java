package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "tbl_otp")
@NoArgsConstructor
@AllArgsConstructor
public class Otp extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "otp_id", nullable = false)
    private Long otpId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "otp_generated")
    private int otpGenerated;

    @Column(name = "status")
    private int status;

    @Column(name = "expiry_time")
    private Long expiryTime;

    @Column(name = "regenerated_status")
    private int regeneratedStatus;

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
