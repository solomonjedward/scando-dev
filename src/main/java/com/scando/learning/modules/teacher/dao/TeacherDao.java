package com.scando.learning.modules.teacher.dao;

import com.scando.learning.common.models.ArchiveClassEnroll;
import com.scando.learning.common.models.User;
import com.scando.learning.modules.student.models.rest.ClassDetail;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ClassRoom;
import com.scando.learning.modules.teacher.models.ScheduledClass;

import com.scando.learning.modules.teacher.models.Subject;
import com.scando.learning.modules.teacher.models.rest.EnrollDetails;
import com.scando.learning.modules.teacher.models.rest.GetEnrollListRequest;
import com.scando.learning.modules.teacher.models.rest.GetTeacherClassesResponseModel;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {

    List<GetTeacherClassesResponseModel> getClass(Long userId, Long isScheduled);

    List<ScheduledClass> getScheduledClass(Long userId);

    List<ScheduledClass> saveAll(List<ScheduledClass> scheduledClasses);


    ClassRoom save(ClassRoom classRoom);

    ClassRoom getClassRoomByClassId(Long classId);

    void deleteScheduledClassByClassId(ScheduledClass scheduledClass);

    ScheduledClass saveClassTimeTable(ScheduledClass scheduledClass);

    ScheduledClass getTimeTableForClass(Long classId);

    User save(User user);

    void save(List<Subject> subjects);

    void deleteScheduledClasses(Long classId);

    Page<EnrollDetails> getEnrollRequestList(GetEnrollListRequest enrollListRequest);

    ClassEnroll getEnrollRequestByClassIdAndStudentId(Long classId, Long studentId);

    void save(ClassEnroll enrollRequest);

    List<GetTeacherClassesResponseModel> getTeacherOwnClassOnSpecificDay(Long userId, String day);

    List<ClassDetail> getSpecificClassDetails(Long classId);

    List<ClassDetail> getAllClassDetails(Long userId,Long isScheduled);

    void deleteClassDoubtByClassId(Long classId);

    void deleteClassVideoByClassId(Long classId);

    void deleteStudyMaterialByClassId(Long classId);

    void deleteClassRoom(ClassRoom classRoom);

    ArchiveClassEnroll saveArchiveClassEnroll(ArchiveClassEnroll archiveClassEnroll);

    List<ClassEnroll> getClassEnrollByClassId(Long classId);

    void deleteClassEnrollById(Long enrollId);

    List<ScheduledClass> getTimetableByClassId(Long classRoomId);

    void deleteClassEnrollFromTeacher(ClassEnroll classEnroll);
}
