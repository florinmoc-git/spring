package healthinformationsystem.his.controllers;

import healthinformationsystem.his.entities.Hospital;
import healthinformationsystem.his.services.HospitalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/add")
    public Hospital createHospital(@RequestBody Hospital hospital){
        return hospitalService.createHospital(hospital);
    }
}
