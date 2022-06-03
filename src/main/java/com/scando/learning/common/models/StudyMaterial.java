package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "tbl_study_material")
@NoArgsConstructor
@AllArgsConstructor
public class StudyMaterial extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Long materialId;

    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "scheduled_date")
    private Date scheduledDate;

    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "material_type")
    private Integer materialType;

    @Column(name = "material_url")
    private String materialUrl;

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
