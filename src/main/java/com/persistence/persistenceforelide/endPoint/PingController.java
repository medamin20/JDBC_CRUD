package com.persistence.persistenceforelide.endPoint;


import com.persistence.persistenceforelide.dao.Pingrepository;
import com.persistence.persistenceforelide.entities.Ping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PingController {

    @Autowired
    Pingrepository pingrepository ;


    @PostMapping("/ping")
    public ResponseEntity<String> createTutorial(@RequestBody Ping ping) {
        try {
            pingrepository.savaPing(new Ping(ping.getName(), ping.getEmail()));
            return new ResponseEntity<>("Ping was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pings")
    public ResponseEntity<List<Ping>> getallPing(@RequestParam(required = false) String title){

        try {
            List<Ping> pinglist = new ArrayList<Ping>();
            if (title == null)
                pingrepository.allPings().forEach(pinglist::add);
            if (pinglist.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pinglist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/ping/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") int id, @RequestBody Ping ping) {
        Ping iping = pingrepository.getPingById(id);

        if (iping != null) {
            iping.setId(id);
            iping.setEmail(ping.getEmail());
            iping.setName(ping.getName());

            pingrepository.updatePing(iping);
            return new ResponseEntity<>("Ping was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Ping with id=" + id, HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/ping/{id}")
    public ResponseEntity<Ping> getTutorialById(@PathVariable("id") int id) {
        Ping ping = pingrepository.getPingById(id);

        if (ping != null) {
            return new ResponseEntity<>(ping, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
