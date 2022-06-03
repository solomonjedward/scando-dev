package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "tbl_archive_class_enroll")
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveClassEnroll extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id", nullable = false)
    private Long archiveId;

    @Column(name = "enroll_id", nullable = false)
    private Long enrollId;

    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "enroll_status")
    private int enrollStatus;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "enrollTime")
    private Long enrollTime;

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
