package com.scando.learning.common.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "tbl_class_video")
@NoArgsConstructor
@AllArgsConstructor
public class ClassVideo extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id", nullable = false)
    private Long videoId;

    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "day")
    private Long day;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "url")
    private String url;

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
