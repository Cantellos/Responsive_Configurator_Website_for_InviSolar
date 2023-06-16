package com.cantelli.invisolar.service.impl;

import com.cantelli.invisolar.domain.Profile;
import com.cantelli.invisolar.repository.ProfileRepository;
import com.cantelli.invisolar.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile create(Profile profile){

        int startPower = profile.getStartPower();

        profileRepository.save(profile);

        return profile;
    }

}
