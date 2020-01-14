/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FlightManagement;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pink Flower
 */
@Transactional()
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByClient_IdClt(int idClt);
}
