/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FlightManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value="/vols")
public class VolService {
    
    @Value("${tParam}")
    private int xParam;
    @Value("${zParam}")
    private int yParam;
    @Value("${me}")
    private String me;
    @GetMapping("/myConfig")
    public Map<String,Object> myConfig(){
        Map<String,Object> params=new HashMap<>();
        params.put("tParam",xParam);
        params.put("zParam",yParam);
        params.put("me",me);
        params.put("threadName",Thread.currentThread().getName());
        return params;
    }


    @Autowired
    private VolRepository volRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Vol> getAllVols() {
        return volRepository.findAll();
    }

    @RequestMapping(value="/create",method=RequestMethod.POST)
    public ResponseEntity<Vol> addVol(@RequestBody Vol v) {
        Vol vol = volRepository.save(v);
        if (vol == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(vol, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Vol updateVol(@RequestBody Vol v) {
        return volRepository.saveAndFlush(v);
    }

    @RequestMapping(value = "/annule/{numVol}", method = RequestMethod.PUT)
    public int annulerVol(@PathVariable int numVol) {
        return volRepository.annulerVol(numVol);
    }

    @RequestMapping(value = "/verifPlaces/{numVol}", method = RequestMethod.GET)
    int verifPlaces(@PathVariable int numVol){
        return volRepository.nbrePlaceRes(numVol);
    }

    @RequestMapping(value="/searchVols/",method=RequestMethod.POST)
    public List<Vol> searchVols(@RequestBody Vol vol) {
        return volRepository.findAllByDateDepartAndVilleDepartAndVilleArrive(vol.getDateDepart(),vol.getVilleDepart(),vol.getVilleArrive());
    }
}

