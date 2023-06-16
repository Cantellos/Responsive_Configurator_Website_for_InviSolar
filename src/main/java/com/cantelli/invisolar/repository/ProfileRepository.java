package com.cantelli.invisolar.repository;

import com.cantelli.invisolar.domain.Quote;
import com.cantelli.invisolar.domain.User;
import org.springframework.data.repository.CrudRepository;

import com.cantelli.invisolar.domain.Profile;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    Profile findById(long id);

    Profile findByProfileOwner(User user);

    //@Query(value="")
   //List...

}