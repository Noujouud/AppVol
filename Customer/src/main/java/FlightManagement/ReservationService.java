/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FlightManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pink Flower
 */
@RefreshScope
@RestController
@RequestMapping(value="/res")
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @RequestMapping(value="/create",method=RequestMethod.POST)
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation r) {
        Reservation res = reservationRepository.save(r);
        if (res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}

