package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.PortActivitiesSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortActivitiesScheduleRepository extends JpaRepository<PortActivitiesSchedule,Long>
{
    //@Query("Select a FROM port_activities_schedule a WHERE a.port_activity_id IN (:ids) ")
        List<PortActivitiesSchedule> findByPortActivityId(Long id);

}
