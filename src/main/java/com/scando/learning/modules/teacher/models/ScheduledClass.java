package com.scando.learning.modules.teacher.models;

import com.scando.learning.common.models.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_class_scheduled")
public class ScheduledClass extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long timeTableId;

    @Column(name = "class_id")
    private Long classRoomId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "sl_no")
    private Integer slNo;

    @Column(name = "day")
    private String day;

    @Column(name = "s_hr")
    private String startHour;

    @Column(name = "s_min")
    private String startMin;

    @Column(name = "e_hr")
    private String endHour;

    @Column(name = "e_min")
    private String endMin;

    @Column(name = "repeat")
    private Integer repeatEnabled;

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

    public ScheduledClass(Long teacherId, String day, String startHour, String startMin, String endHour, String endMin) {
        this.teacherId = teacherId;
        this.day = day;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
    }
}
