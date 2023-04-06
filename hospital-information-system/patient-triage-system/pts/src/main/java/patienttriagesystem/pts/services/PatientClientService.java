package patienttriagesystem.pts.services;

import com.his.entities.Patient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/pms/patients")
public interface PatientClientService {

    @GetExchange("/get/{id}")
    Patient getPatient(@PathVariable int id);
}
