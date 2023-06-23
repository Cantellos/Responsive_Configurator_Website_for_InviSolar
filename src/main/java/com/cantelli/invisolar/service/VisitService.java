package com.cantelli.invisolar.service;

import com.cantelli.invisolar.domain.Visit;
import com.cantelli.invisolar.domain.User;

import java.util.List;

public interface VisitService {
    Visit findById(long id);

    Visit findByUser(User user);

    Visit create (Visit visit);

    Visit save(Visit visit);

    void delete(Visit visit);

}
