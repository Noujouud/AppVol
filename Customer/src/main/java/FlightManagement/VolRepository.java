/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FlightManagement;

import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Pink Flower
 */
@Transactional()
public interface VolRepository extends JpaRepository<Vol, Integer> {

   @Modifying
   @Query("update Vol v SET v.etat=0 WHERE v.numVol = :numVol")
   int annulerVol(@Param("numVol") int numVol);

   @Query("select nbrePlaceRestant from Vol v WHERE v.numVol = :numVol")
   int nbrePlaceRes(@Param("numVol") int num_vol);

   List<Vol> findAllByDateDepartAndVilleDepartAndVilleArrive(Date dateDepart, String villeDepart, String villeArrive);
}
