package com.scando.learning.common.repository;

import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledClassRepository extends JpaRepository<ScheduledClass, Long> {
    List<ScheduledClass> getByTeacherId(Long teacherId);

    ScheduledClass getByClassRoomId(Long classId);

    void deleteByClassRoomId(Long classId);
    @Query("select new com.scando.learning.modules.teacher.models.ScheduledClass"
            +"("
            + "t.teacherId,"
            + "t.day,"
            + "t.startHour,"
            + "t.startMin,"
            + "t.endHour,"
            + "t.endMin"
            +")"
            + "from ScheduledClass t where t.classRoomId = :classRoomId order by t.classRoomId")
    List<ScheduledClass> getTimetableByClassId(@Param("classRoomId") Long classRoomId);

    @Query("select new com.scando.learning.modules.teacher.models.ScheduledClass"
            +"("
            + "t.teacherId,"
            + "t.day,"
            + "t.startHour,"
            + "t.startMin,"
            + "t.endHour,"
            + "t.endMin"
            +")"
            + "from ScheduledClass t where t.classRoomId = :classRoomId and t.day = :day order by t.classRoomId")
    List<ScheduledClass> getTimetableByClassIdAndDay(@Param("classRoomId") Long classRoomId,@Param("day") String day);

}
