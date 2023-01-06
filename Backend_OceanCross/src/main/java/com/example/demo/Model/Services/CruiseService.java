package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Exceptions.Cruises.CollectionOfCruisesNotFound;
import com.example.demo.Model.Exceptions.Cruises.CruiseNotFound;
import com.example.demo.Model.Exceptions.Cruises.CruisePortNotFound;
import com.example.demo.Model.Exceptions.OnboardActivities.OnboardActivityNotFound;
import com.example.demo.Model.Repositories.CruisePortsRepository;
import com.example.demo.Model.Repositories.CruiseRepository;
import com.example.demo.Model.Repositories.OnBoardActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CruiseService {
    @Autowired
    private CruiseRepository cruiseRepository;
    @Autowired
    private CruisePortsRepository cruisePortsRepository;
    @Autowired
    private OnBoardActivityRepository onBoardActivityRepository;

    public List<Cruise> listAllCruises() {
        if(!cruiseRepository.findAll().isEmpty())
            return cruiseRepository.findAll();
        else
            throw new CollectionOfCruisesNotFound();
    }

    public void saveCruise(Cruise cruise) {
        cruiseRepository.save(cruise);
    }

    public Cruise getCruise(Long id) {
        if(cruiseRepository.findById(id).isPresent())
            return cruiseRepository.findById(id).get();
        else
            throw new CruiseNotFound(id);
    }

    public void deleteCruise( Long id){
        if(cruiseRepository.findById(id).isPresent()) {
            cruiseRepository.deleteById(id);
        }
        else
            throw new CruiseNotFound(id);
    }
    public void deleteCruisePort( Long id){
        if(cruisePortsRepository.findById(id).isPresent()) {
            cruisePortsRepository.deleteById(id);
        }
        else
            throw new CruisePortNotFound(id);
    }
    public void deleteCruiseOnBoardActivity(Long id){
        if(onBoardActivityRepository.findById(id).isPresent()) {
            onBoardActivityRepository.deleteById(id);
        }
        else
            throw new OnboardActivityNotFound(id);
    }
}
