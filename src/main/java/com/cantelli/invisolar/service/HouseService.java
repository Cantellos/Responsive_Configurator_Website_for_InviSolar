package com.cantelli.invisolar.service;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.User;

public interface HouseService {

    House findById(long id);

    House findByUser(User user);

    House create (House house);

    House save(House house);

}
