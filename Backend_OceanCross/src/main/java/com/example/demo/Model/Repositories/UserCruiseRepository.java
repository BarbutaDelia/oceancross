package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.UserCruises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserCruiseRepository extends JpaRepository<UserCruises, Long>
{
    @Query("SELECT uc.id FROM UserCruises uc WHERE uc.userId = :userId")
    List<Long> findIdByUserId(Long userId);

    @Query("SELECT uc.id FROM UserCruises uc WHERE uc.userId = :userId AND uc.cruiseId = :cruiseId")
    List<Long> findIdByUserIdAndCruiseId(Long userId, Long cruiseId);
}