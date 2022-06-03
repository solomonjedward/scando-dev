package com.scando.learning.modules.student.dao.Impl;

import com.scando.learning.common.dao.impl.PageDaoImpl;
import com.scando.learning.common.models.ArchiveClassEnroll;
import com.scando.learning.common.repository.ArchiveClassEnrollRepository;
import com.scando.learning.common.repository.ClassEnrollRepository;
import com.scando.learning.common.repository.ClassRepository;
import com.scando.learning.common.repository.ScheduledClassRepository;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.student.dao.StudentDao;
import com.scando.learning.modules.student.models.rest.*;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.rest.EnrollDetails;
import com.scando.learning.modules.teacher.models.rest.GetTeacherClassesResponseModel;
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

@Repository
@Slf4j
public class StudentDaoImpl extends PageDaoImpl implements StudentDao {

    private static final String GET_ENROLL_REQUEST_LIST_QUERY = "SELECT tce.enroll_id enrollId, tce.enroll_status enrollStatus, tce.class_id classId, " +
            "tce.teacher_id teacherId, tce.enroll_time enrollTime, tc.class_name className, tc.subject_name subjectName FROM " +
            " tbl_class_enroll tce LEFT JOIN tbl_class tc ON tce.class_id=tc.class_id WHERE  tce.student_id=:studentId ";

    private static final String GET_ENROLL_REQUEST_COUNT_QUERY = "SELECT COUNT(DISTINCT tce.enroll_id) FROM " +
            " tbl_class_enroll tce LEFT JOIN tbl_class tc ON tce.class_id=tc.class_id WHERE  tce.student_id=:studentId   ";
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassEnrollRepository classEnrollRepository;

    @Autowired
    private ArchiveClassEnrollRepository archiveClassEnrollRepository;

    @Autowired
    ScheduledClassRepository scheduledClassRepository;

    @Override
    public List<GetStudentClassesResponseModel> getStudentClassOnSpecificDay(Long userId, String day) {
        List<List> classData = classRepository.getStudentOwnClassOnSpecificDay(userId, day);
        List<GetStudentClassesResponseModel> getStudentClassesResponseModelList = new ArrayList<>();
        for (List data : classData) {

            BigInteger classId = (BigInteger) data.get(0);

            List<ScheduledClass> scheduledClass = scheduledClassRepository.getTimetableByClassId(classId.longValue());

            GetStudentClassesResponseModel getStudentClassesResponseModel = new GetStudentClassesResponseModel();
            getStudentClassesResponseModel.setClassId((BigInteger) data.get(0));
            getStudentClassesResponseModel.setClassType((Short) data.get(1));
            getStudentClassesResponseModel.setClassName((String) data.get(2));
            getStudentClassesResponseModel.setTimeTable(TimeTableUtils.formatTimeTable(scheduledClass));

            getStudentClassesResponseModel.setDocuments((BigInteger) data.get(3));

            getStudentClassesResponseModelList.add(getStudentClassesResponseModel);
        }
        return getStudentClassesResponseModelList;
    }

    @Override
    public ClassEnroll getEnrolledClassByClassIdAndStudentId(Long classId, Long studentId) {
        return classEnrollRepository.getByClassIdAndStudentId(classId, studentId);
    }

    @Override
    public ClassEnroll enrollClass(ClassEnroll classEnroll) {
        return classEnrollRepository.save(classEnroll);
    }

    @Override
    public List<ClassDetail> getStudentSpecificClassDetails(Long classId) {
        List<List> classData = classRepository.getSpecificClassDetails(classId);
        List<ClassDetail> classDetailList = new ArrayList<>();
        for (List data : classData) {
            //TODO add timetable
            List<ScheduledClass> scheduledClass = scheduledClassRepository.getTimetableByClassId(classId);

            ClassDetail classDetail = new ClassDetail();
            classDetail.setClassId((BigInteger) data.get(0));
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
            classDetail.setIsScheduled((Short) data.get(6));
            classDetail.setClassRecordings(0L);

            classDetailList.add(classDetail);
        }
        return classDetailList;
    }

    @Override
    public List<ClassDetail> getStudentAllClassDetails(Long userId, Long isScheduled) {
        List<List> classData = new ArrayList<>();
        if (isScheduled == null) {
            classData = classRepository.getStudentAllClassDetails(userId);
        } else {
            classData = classRepository.getStudentAllClassDetails(userId, isScheduled);
        }
        List<ClassDetail> classDetailList = new ArrayList<>();
        for (List data : classData) {
            //TODO add timetable details
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
            classDetail.setIsScheduled((Short) data.get(6));
            classDetail.setIsEnrolled((Short) data.get(7));
            classDetail.setClassRecordings(0L);

            classDetailList.add(classDetail);
        }
        return classDetailList;
    }

    @Override
    public void deleteClassEnroll(ClassEnroll classEnroll) {
        classEnrollRepository.delete(classEnroll);
    }

    @Override
    public ArchiveClassEnroll saveArchiveClassEnroll(ArchiveClassEnroll archiveClassEnroll) {
        return archiveClassEnrollRepository.save(archiveClassEnroll);
    }

    @Override
    public Page<StudentEnrollDetails> getEnrollList(GetEnrollListRequest enrollListRequest) {


        String enrollStatusFilter = "";
        boolean hasEnrollStatusFilter = false;

        if (enrollListRequest.getEnrollStatus() != null) {
            enrollStatusFilter = " AND tce.enroll_status = :enrollStatus ";
            hasEnrollStatusFilter = true;
        }

        String listQuery = GET_ENROLL_REQUEST_LIST_QUERY  + enrollStatusFilter;
        String countQuery = GET_ENROLL_REQUEST_COUNT_QUERY  + enrollStatusFilter;

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("studentId", WebUtils.getUserId());

        if (hasEnrollStatusFilter) {
            filterMap.put("enrollStatus", enrollListRequest.getEnrollStatus());
        }

        List<String> resultFieldNames = List.of("enrollId", "classId", "teacherId", "enrollTime", "className", "subjectName", "enrollStatus");

        LOGGER.debug("List query is :{} and countQuery is:{}", listQuery, countQuery);

        Page<StudentEnrollDetails> enrollList = getPageFromNativeQuery(listQuery, countQuery,
                enrollListRequest.getPage(), enrollListRequest.getLimit(), StudentEnrollDetails.class, resultFieldNames, filterMap);

        LOGGER.debug("Fetched enroll requests are :{}", enrollList.getContent());

        return enrollList;
    }
}
