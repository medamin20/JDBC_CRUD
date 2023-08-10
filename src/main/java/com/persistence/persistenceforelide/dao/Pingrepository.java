package com.persistence.persistenceforelide.dao;


import com.persistence.persistenceforelide.entities.Ping;

import java.util.List;

public interface Pingrepository {

    Ping savaPing(Ping ping) ;
    int updatePing(Ping ping);
    Ping getPingById(int id);

    String deleteById(int id);
    List<Ping> allPings();
}
