package com.scando.learning.common.repository;

import com.scando.learning.modules.teacher.models.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassRoom, Long> {

    List<ClassRoom> getByTeacherId(Long teacherId);

    @Query(value = "select tc.class_id,tc.class_name,tc.teacher_id ,  (select count(tsm.class_id) from tbl_study_material tsm where tsm.class_id = tc.class_id), tc.class_type, tc.is_scheduled from\n" +
            " tbl_class tc  where tc.teacher_id = :id", nativeQuery = true)
    List<List> getOwnClass(Long id);

    ClassRoom getByClassId(Long classId);

    @Query(value = "select tc.class_id,tc.class_name,tc.teacher_id ,  (select count(tsm.class_id) from tbl_study_material tsm where tsm.class_id = tc.class_id), tc.class_type from\n" +
            " tbl_class tc where tc.teacher_id = :id and  tc.class_id in (select tcs.class_id from tbl_class_scheduled tcs where tcs.day = :day )", nativeQuery = true)
    List<List> getTeacherOwnClassOnSpecificDay(Long id, String day);

    @Query(value = "select tce.class_id,tc.class_type,tc.class_name,  " +
            "(select count(tsm.class_id) from tbl_study_material tsm where tsm.class_id = tc.class_id) " +
            "from tbl_class tc \n" +
            " join tbl_class_enroll tce on tc.class_id = tce.class_id where tce.student_id = :id and tce.enroll_status = 1 and tc.class_id in (select tcs.class_id from tbl_class_scheduled tcs where tcs.day = :day ) ", nativeQuery = true)
    List<List> getStudentOwnClassOnSpecificDay(Long id, String day);

    @Query(value = "select tc.class_id, tc.class_name, tc.class_type,\n" +
            "(select count(tsm.class_id) as documentCount from tbl_study_material tsm where tsm.class_id = tc.class_id),\n" +
            "(select count(tcv.class_id) as videoCount from tbl_class_video tcv where tcv.class_id = tc.class_id ),\n" +
            "(select count(tcd.class_id) as doubtSessions from tbl_class_doubt tcd where tcd.class_id = tc.class_id), tc.is_scheduled\n" +
            "from tbl_class tc \n" +
            "where tc.class_id = :classId ", nativeQuery = true)
    List<List> getSpecificClassDetails(Long classId);

    @Query(value = "select tce.class_id, tc.class_name, tc.class_type,\n" +
            "(select count(tsm.class_id) as documentCount from tbl_study_material tsm where tsm.class_id = tc.class_id),\n" +
            "(select count(tcv.class_id) as videoCount from tbl_class_video tcv where tcv.class_id = tc.class_id ),\n" +
            "(select count(tcd.class_id) as doubtSessions from tbl_class_doubt tcd where tcd.class_id = tc.class_id),tc.is_scheduled, tce.enroll_status \n" +
            "from tbl_class tc \n" +
            "left join tbl_class_enroll tce\n" +
            "on tc.class_id = tce.class_id\n" +
            "where tce.student_id = :studentId ", nativeQuery = true)
    List<List> getStudentAllClassDetails(Long studentId);

    @Query(value = "select tc.class_id, tc.class_name, tc.class_type,\n" +
            "(select count(tsm.class_id) as documentCount from tbl_study_material tsm where tsm.class_id = tc.class_id),\n" +
            "(select count(tcv.class_id) as videoCount from tbl_class_video tcv where tcv.class_id = tc.class_id ),\n" +
            "(select count(tcd.class_id) as doubtSessions from tbl_class_doubt tcd where tcd.class_id = tc.class_id), tc.is_scheduled, tc.subject_name\n" +
            "from tbl_class tc \n" +
            "where tc.teacher_id = :teacherId ", nativeQuery = true)
    List<List> getTeacherAllClassDetails(Long teacherId);

    @Query(value = "select tc.class_id,tc.class_name,tc.teacher_id ,  (select count(tsm.class_id) from tbl_study_material tsm where tsm.class_id = tc.class_id), tc.class_type, tc.is_scheduled from\n" +
            " tbl_class tc  where tc.teacher_id = :id and tc.is_scheduled = :isScheduled", nativeQuery = true)
    List<List> getOwnClass(Long id, Long isScheduled);

    @Query(value = "select tc.class_id, tc.class_name, tc.class_type,\n" +
            "(select count(tsm.class_id) as documentCount from tbl_study_material tsm where tsm.class_id = tc.class_id),\n" +
            "(select count(tcv.class_id) as videoCount from tbl_class_video tcv where tcv.class_id = tc.class_id ),\n" +
            "(select count(tcd.class_id) as doubtSessions from tbl_class_doubt tcd where tcd.class_id = tc.class_id), tc.is_scheduled, tc.subject_name\n" +
            "from tbl_class tc \n" +
            "where tc.teacher_id = :teacherId and tc.is_scheduled = :isScheduled ", nativeQuery = true)
    List<List> getTeacherAllClassDetails(Long teacherId, Long isScheduled);

    @Query(value = "select tce.class_id, tc.class_name, tc.class_type,\n" +
            "(select count(tsm.class_id) as documentCount from tbl_study_material tsm where tsm.class_id = tc.class_id),\n" +
            "(select count(tcv.class_id) as videoCount from tbl_class_video tcv where tcv.class_id = tc.class_id ),\n" +
            "(select count(tcd.class_id) as doubtSessions from tbl_class_doubt tcd where tcd.class_id = tc.class_id), tc.is_scheduled, tce.enroll_status\n" +
            "from tbl_class tc \n" +
            "left join tbl_class_enroll tce\n" +
            "on tc.class_id = tce.class_id\n" +
            "where tce.student_id = :studentId and tc.is_scheduled = :isScheduled ", nativeQuery = true)
    List<List> getStudentAllClassDetails(Long studentId, Long isScheduled);
}
