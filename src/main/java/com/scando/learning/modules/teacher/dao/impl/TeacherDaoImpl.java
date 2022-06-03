package com.scando.learning.modules.teacher.dao.impl;

import com.scando.learning.common.dao.impl.PageDaoImpl;
import com.scando.learning.common.models.ArchiveClassEnroll;
import com.scando.learning.common.models.User;
import com.scando.learning.common.repository.*;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.student.models.rest.Activity;
import com.scando.learning.modules.student.models.rest.ClassDetail;
import com.scando.learning.modules.student.models.rest.StudyMaterials;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ClassRoom;
import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.Subject;
import com.scando.learning.modules.teacher.models.rest.EnrollDetails;
import com.scando.learning.modules.teacher.models.rest.GetEnrollListRequest;
import com.scando.learning.modules.teacher.models.rest.GetTeacherClassesResponseModel;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponseModel;
import com.scando.learning.modules.teacher.utils.TimeTableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class TeacherDaoImpl extends PageDaoImpl implements TeacherDao {

    private static final String GET_ENROLL_REQUEST_LIST_QUERY = "SELECT tce.enroll_id enrollId, tce.enroll_status enrollStatus, tce.class_id classId, " +
            "tce.student_id studentId, tce.enroll_time enrollTime, tu.name studentName, tu.phone_number phoneNumber FROM " +
            " tbl_class_enroll tce LEFT JOIN tbl_user tu ON tce.student_id=tu.user_id WHERE  tce.teacher_id=:teacherId ";

    private static final String GET_ENROLL_REQUEST_COUNT_QUERY = "SELECT COUNT(DISTINCT tce.enroll_id) FROM " +
            " tbl_class_enroll tce LEFT JOIN tbl_user tu ON tce.student_id=tu.user_id WHERE tce.teacher_id=:teacherId  ";

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ScheduledClassRepository scheduledClassRepository;

    @Autowired
    ClassEnrollRepository classEnrollRepository;

    @Autowired
    ClassDoubtRepository classDoubtRepository;

    @Autowired
    ClassVideoRepository classVideoRepository;

    @Autowired
    StudyMaterialRepository studyMaterialRepository;

    @Autowired
    ArchiveClassEnrollRepository archiveClassEnrollRepository;

    @Override
    public List<GetTeacherClassesResponseModel> getClass(Long userId, Long isScheduled) {
        List<GetTeacherClassesResponseModel> getOwnClassesResponseModelList = new ArrayList<>();
        List<List> classData = new ArrayList<>();
        if (isScheduled == null) {
            classData = classRepository.getOwnClass(userId);
        } else {
            classData = classRepository.getOwnClass(userId, isScheduled);
        }
        for (List list : classData) {
            GetTeacherClassesResponseModel getOwnClassesResponseModel = new GetTeacherClassesResponseModel();

            BigInteger classId = (BigInteger) list.get(0);

            List<ScheduledClass> scheduledClass = scheduledClassRepository.getTimetableByClassId(classId.longValue());

            getOwnClassesResponseModel.setClassId((BigInteger) list.get(0));
            getOwnClassesResponseModel.setClassName((String) list.get(1));
            getOwnClassesResponseModel.setTeacherId((BigInteger) list.get(2));
            getOwnClassesResponseModel.setTimeTable(TimeTableUtils.formatTimeTable(scheduledClass));
            getOwnClassesResponseModel.setDocuments((BigInteger) list.get(3));
            getOwnClassesResponseModel.setClassType((Short) list.get(4));
            getOwnClassesResponseModel.setIsScheduled((Short) list.get(5));

            getOwnClassesResponseModelList.add(getOwnClassesResponseModel);
        }
        return getOwnClassesResponseModelList;
    }

    @Override
    public List<ScheduledClass> getScheduledClass(Long userId) {
        return scheduledClassRepository.getByTeacherId(userId);
    }

    @Override
    public List<ScheduledClass> saveAll(List<ScheduledClass> scheduledClasses) {
        return scheduledClassRepository.saveAll(scheduledClasses);
    }

    @Override
    public ClassRoom save(ClassRoom object) {
        return classRepository.save(object);
    }

    @Override
    public ClassRoom getClassRoomByClassId(Long classId) {
        return classRepository.getByClassId(classId);
    }

    @Override
    public void deleteScheduledClassByClassId(ScheduledClass scheduledClass) {
        scheduledClassRepository.delete(scheduledClass);
    }

    @Override
    public ScheduledClass saveClassTimeTable(ScheduledClass scheduledClass) {
        return scheduledClassRepository.save(scheduledClass);
    }

    @Override
    public ScheduledClass getTimeTableForClass(Long classId) {
        return scheduledClassRepository.getByClassRoomId(classId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void save(List<Subject> subjects) {
        for (Subject subject : subjects) {
            subjectRepository.save(subject);
        }
    }

    @Override
    public void deleteScheduledClasses(Long classId) {
        scheduledClassRepository.deleteByClassRoomId(classId);
    }

    @Override
    public Page<EnrollDetails> getEnrollRequestList(GetEnrollListRequest enrollListRequest) {

        String classFilter = "";
        String enrollStatusFilter = "";
        boolean hasClassFilter = false;
        boolean hasEnrollStatusFilter = false;
        if (enrollListRequest.getClassId() != null) {
            classFilter = " AND tce.class_id = :classId ";
            hasClassFilter = true;
        }
        if (enrollListRequest.getEnrollStatus() != null) {
            enrollStatusFilter = " AND tce.enroll_status = :enrollStatus ";
            hasEnrollStatusFilter = true;
        }

        String listQuery = GET_ENROLL_REQUEST_LIST_QUERY + classFilter + enrollStatusFilter;
        String countQuery = GET_ENROLL_REQUEST_COUNT_QUERY + classFilter + enrollStatusFilter;

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("teacherId", WebUtils.getUserId());
        if (hasClassFilter) {
            filterMap.put("classId", enrollListRequest.getClassId());
        }
        if (hasEnrollStatusFilter) {
            filterMap.put("enrollStatus", enrollListRequest.getEnrollStatus());
        }

        List<String> resultFieldNames = List.of("enrollId", "classId", "studentId", "enrollTime", "studentName", "phoneNumber", "enrollStatus");

        LOGGER.debug("List query is :{} and countQuery is:{}", listQuery, countQuery);

        Page<EnrollDetails> enrollRequestList = getPageFromNativeQuery(listQuery, countQuery,
                enrollListRequest.getPage(), enrollListRequest.getLimit(), EnrollDetails.class, resultFieldNames, filterMap);

        LOGGER.debug("Fetched enroll requests are :{}", enrollRequestList.getContent());

        return enrollRequestList;
    }

    @Override
    public ClassEnroll getEnrollRequestByClassIdAndStudentId(Long classId, Long studentId) {
        return classEnrollRepository.getByClassIdAndStudentId(classId, studentId);
    }

    @Override
    public void save(ClassEnroll enrollRequest) {
        classEnrollRepository.save(enrollRequest);
    }

    @Override
    public List<GetTeacherClassesResponseModel> getTeacherOwnClassOnSpecificDay(Long userId, String day) {
        List<GetTeacherClassesResponseModel> getOwnClassesResponseModelList = new ArrayList<>();
        List<List> classData = classRepository.getTeacherOwnClassOnSpecificDay(userId, day);
        for (List list : classData) {
            BigInteger classId = (BigInteger) list.get(0);

            List<ScheduledClass> scheduledClass = scheduledClassRepository.getTimetableByClassId(classId.longValue());

            GetTeacherClassesResponseModel getOwnClassesResponseModel = new GetTeacherClassesResponseModel();
            getOwnClassesResponseModel.setClassId((BigInteger) list.get(0));
            getOwnClassesResponseModel.setClassName((String) list.get(1));
            getOwnClassesResponseModel.setTeacherId((BigInteger) list.get(2));
            getOwnClassesResponseModel.setTimeTable(TimeTableUtils.formatTimeTable(scheduledClass));
            getOwnClassesResponseModel.setDocuments((BigInteger) list.get(3));
            getOwnClassesResponseModel.setClassType((Short) list.get(4));
            getOwnClassesResponseModelList.add(getOwnClassesResponseModel);
        }
        return getOwnClassesResponseModelList;
    }

    @Override
    public List<ClassDetail> getSpecificClassDetails(Long classId) {
        List<List> classData = classRepository.getSpecificClassDetails(classId);
        List<ClassDetail> classDetailList = new ArrayList<>();
        for (List data : classData) {
            List<ScheduledClass> scheduledClass = scheduledClassRepository.getTimetableByClassId(classId);

            ClassDetail classDetail = new ClassDetail();
            classDetail.setClassId((BigInteger) data.get(0));
            classDetail.setClassName((String) data.get(1));
            classDetail.setTimeTable(TimeTableUtils.formatTimeTable(scheduledClass));
            classDetail.setClassType((Short) data.get(2));

            StudyMaterials studyMaterials = new StudyMaterials();
            studyMaterials.setDocumentCount((BigInteger) data.get(3));
            studyMaterials.setVideoCount((BigInteger) data.get(4));
            classDetail.setStudyMaterials(studyMaterials);

            Activity activity = new Activity();
            activity.setDoubtSession((BigInteger) data.get(5));
            classDetail.setActivity(activity);
            classDetail.setIsScheduled((Short) data.get(6));
            classDetail.setClassRecordings(0L);

            classDetailList.add(classDetail);
        }
        return classDetailList;
    }

    @Override
    public List<ClassDetail> getAllClassDetails(Long userId, Long isScheduled) {
        List<List> classData = new ArrayList<>();
        if (isScheduled == null) {
            classData = classRepository.getTeacherAllClassDetails(userId);
        } else {
            classData = classRepository.getTeacherAllClassDetails(userId, isScheduled);
        }
        List<ClassDetail> classDetailList = new ArrayList<>();
        for (List data : classData) {
            ClassDetail classDetail = new ClassDetail();
            BigInteger classId = (BigInteger) data.get(0);

            List<ScheduledClass> scheduledClass = scheduledClassRepository.getTimetableByClassId(classId.longValue());

            classDetail.setClassId(classId);
            classDetail.setClassName((String) data.get(1));
            classDetail.setClassType((Short) data.get(2));
            classDetail.setTimeTable(TimeTableUtils.formatTimeTable(scheduledClass));

            StudyMaterials studyMaterials = new StudyMaterials();
            studyMaterials.setDocumentCount((BigInteger) data.get(3));
            studyMaterials.setVideoCount((BigInteger) data.get(4));
            classDetail.setStudyMaterials(studyMaterials);

            Activity activity = new Activity();
            activity.setDoubtSession((BigInteger) data.get(5));
            classDetail.setActivity(activity);

            classDetail.setClassRecordings(0L);
            classDetail.setIsScheduled((Short) data.get(6));
            classDetail.setSubjectName((String) data.get(7));
            classDetailList.add(classDetail);
        }
        return classDetailList;
    }

    @Override
    public void deleteClassDoubtByClassId(Long classId) {
        classDoubtRepository.deleteByClassId(classId);
    }

    @Override
    public void deleteClassVideoByClassId(Long classId) {
        classVideoRepository.deleteByClassId(classId);
    }

    @Override
    public void deleteStudyMaterialByClassId(Long classId) {
        studyMaterialRepository.deleteByClassId(classId);
    }

    @Override
    public void deleteClassRoom(ClassRoom classRoom) {
        classRepository.delete(classRoom);
    }

    @Override
    public ArchiveClassEnroll saveArchiveClassEnroll(ArchiveClassEnroll archiveClassEnroll) {
        return archiveClassEnrollRepository.save(archiveClassEnroll);
    }

    @Override
    public List<ClassEnroll> getClassEnrollByClassId(Long classId) {
        return classEnrollRepository.getByClassId(classId);
    }

    @Override
    public void deleteClassEnrollById(Long enrollId) {
        classEnrollRepository.deleteByEnrollId(enrollId);
    }

    @Override
    public List<ScheduledClass> getTimetableByClassId(Long classRoomId) {
        return scheduledClassRepository.getTimetableByClassId(classRoomId);
    }

    @Override
    public void deleteClassEnrollFromTeacher(ClassEnroll classEnroll) {
        classEnrollRepository.delete(classEnroll);
    }

}
