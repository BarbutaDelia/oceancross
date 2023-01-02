package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Exceptions.Ports.CollectionOfPortsNotFound;
import com.example.demo.Model.Exceptions.Ports.PortNotFound;
import com.example.demo.Model.Repositories.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PortService {
    @Autowired
    private PortRepository portRepository;

    public List<Port> listAllPorts() {
        if(!portRepository.findAll().isEmpty())
            return portRepository.findAll();
        else
            throw new CollectionOfPortsNotFound();
    }

    public void savePort(Port port) {
        portRepository.save(port);
    }

    public Port getPort(Long id) {
        if(portRepository.findById(id).isPresent())
            return portRepository.findById(id).get();
        else {
            throw new PortNotFound(id);
        }
    }
}
