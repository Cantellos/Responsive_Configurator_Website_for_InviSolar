package com.cantelli.invisolar.service.impl;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.Visit;
import com.cantelli.invisolar.repository.VisitRepository;
import com.cantelli.invisolar.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    VisitRepository visitRepository;

    @Override
    public Visit findById(long id){
        return visitRepository.findById(id);
    }

    @Override
    public Visit findByUser(User user){
        return visitRepository.findByUser(user);
    }

    @Override
    public Visit create (Visit visit){

        visit = visitRepository.save(visit);

        return visit;
    }

    @Override
    public Visit save(Visit visit){
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit){
        visitRepository.delete(visit);
    }

}