package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Exceptions.CollectionOfCruisesNotFound;
import com.example.demo.Model.Exceptions.CruiseNotFound;
import com.example.demo.Model.Repositories.CruiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CruiseService {
    @Autowired
    private CruiseRepository cruiseRepository;

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

    public  List<Cruise> listSpecifiedCruises(List <Long> ids)
    {
        if(!cruiseRepository.findCruisesByIds(ids).isEmpty())
        {
            return cruiseRepository.findCruisesByIds(ids);
        }else
        {
            throw new CollectionOfCruisesNotFound();
        }
    }
}
