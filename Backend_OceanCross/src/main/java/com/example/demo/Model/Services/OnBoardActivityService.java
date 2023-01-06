package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.OnboardActivity;
import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Exceptions.Activities.CollectionOfActivitiesNotFound;
import com.example.demo.Model.Exceptions.OnboardActivities.OnboardActivityNotFound;
import com.example.demo.Model.Exceptions.Ports.PortNotFound;
import com.example.demo.Model.Repositories.OnBoardActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OnBoardActivityService {
    @Autowired
    private OnBoardActivityRepository onBoardActivityRepository;

    public List<OnboardActivity> listAllActivities() {
        if(!onBoardActivityRepository.findAll().isEmpty())
            return onBoardActivityRepository.findAll();
        else
            throw new CollectionOfActivitiesNotFound();
    }

    public void saveActivity(OnboardActivity onBoardActivity) {
        onBoardActivityRepository.save(onBoardActivity);
    }
    public void saveActivities(List<OnboardActivity> onboardActivities) {
        onBoardActivityRepository.saveAll(onboardActivities);
    }

    public OnboardActivity getActivity(Long id) {
        if(onBoardActivityRepository.findById(id).isPresent())
            return onBoardActivityRepository.findById(id).get();
        else {
            throw new OnboardActivityNotFound(id);
        }
    }

}
