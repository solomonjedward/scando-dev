package com.scando.learning.modules.teacher.models;

import com.scando.learning.common.models.AbstractModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_class_enroll")
public class ClassEnroll extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enroll_id", nullable = false)
    private Long enrollId;

    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "enroll_status")
    private Integer enrollStatus;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "enroll_time")
    private Long enrollTime;

    @Column(name = "created")
    private Long created;

    @Column(name = "updated")
    private Long updated;

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
