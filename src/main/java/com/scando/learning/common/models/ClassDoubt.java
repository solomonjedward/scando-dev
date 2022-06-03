package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "tbl_class_doubt")
@NoArgsConstructor
@AllArgsConstructor
public class ClassDoubt extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doubt_id", nullable = false)
    private Long doubtId;

    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "doubt_code")
    private String doubtCode;

    @Column(name = "doubt_description")
    private String doubtDescription;

    @Column(name = "doubt_answer")
    private String doubtAnswer;

    @Column(name = "doubt_status")
    private Integer doubtStatus;

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
