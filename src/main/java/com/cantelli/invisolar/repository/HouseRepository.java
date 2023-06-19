package com.cantelli.invisolar.repository;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<House, Integer> {

    House findById(long id);

    House findByUser(User user);

    //@Query(value = "SELECT c FROM Componente c WHERE c.impianto = :impianto and c.stato ='installato'")
    //    List<Componente> findComponentiAttivi(@Param("impianto") Impianto impianto);
}