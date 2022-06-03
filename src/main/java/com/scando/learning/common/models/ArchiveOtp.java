package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "tbl_archive_otp")
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveOtp extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id", nullable = false)
    private Long archiveId;

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

    @Column(name = "archived_date")
    public Long archivedDate;

    @PrePersist
    public void prePersist() {
        archivedDate = System.currentTimeMillis();
    }


}
