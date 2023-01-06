package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.UserPortActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserPortActivitiesRepository extends JpaRepository<UserPortActivities,Long>
{
    public List<UserPortActivities> findByUserCruiseId(Long userid);
    //@Query("Select * FROM user_activities a where a.user_cruise_id IN ( :userCruiseIds)")
    //  public List<UserPortActivities> findActivitiesByIds(@Param("ids") List<Long> ids);
   //List<UserPortActivities> findByUserCruiseIds(List<Long> userCruiseIds);
}
