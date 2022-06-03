package com.scando.learning.modules.teacher.models;

import com.scando.learning.common.models.AbstractModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_class")
public class ClassRoom  extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "class_description")
    private String classDescription;

    @Column(name = "class_name")
    private String className;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "status")
    private int status;

    @Column(name = "class_type")
    private int classType;

    @Column(name = "is_scheduled")
    private int isScheduled;

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
