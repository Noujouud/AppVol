package FlightManagement;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Reservation implements Serializable {
    
    @Value("${zParam}")
    private int xParam;
    @Value("${tParam}")
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRes;
    private Date dateRes;
    private int nbrePlace;

    
    private Integer clientId;

    
    private List<Integer> ListPassagersId;

    @ManyToOne
    @JoinColumn(name = "vol")
    private Vol vol;

    public Reservation() {
    }

    public Reservation(Date dateRes, int nbrePlace) {
        this.dateRes = dateRes;
        this.nbrePlace = nbrePlace;
    }

    public Reservation(Date dateRes, int nbrePlace, Integer clientId, List<Integer> listPassagersId, Vol vol) {
        this.dateRes = dateRes;
        this.nbrePlace = nbrePlace;
        this.clientId = clientId;
        ListPassagersId = listPassagersId;
        this.vol = vol;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public Date getDateRes() {
        return dateRes;
    }

    public void setDateRes(Date dateRes) {
        this.dateRes = dateRes;
    }

    public int getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(int nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public Integer getClient() {
        return clientId;
    }

    public void setClient(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Integer> getListPassagers() {
        return ListPassagersId;
    }

    public void setListPassagers(List<Integer> listPassagersId) {
        ListPassagersId = listPassagersId;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idRes=" + idRes +
                ", dateRes=" + dateRes +
                ", nbrePlace=" + nbrePlace +
                ", clientId=" + clientId +
                ", ListPassagersId=" + ListPassagersId +
                ", vol=" + vol +
                '}';
    }
}
