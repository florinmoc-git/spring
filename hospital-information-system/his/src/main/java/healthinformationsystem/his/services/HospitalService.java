package healthinformationsystem.his.services;

import healthinformationsystem.his.entities.Hospital;
import healthinformationsystem.his.entities.HospitalSpeciality;
import healthinformationsystem.his.repos.HospitalRepo;
import healthinformationsystem.his.repos.HospitalSpecialityRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private final HospitalRepo hospitalRepo;
    private final HospitalSpecialityRepo hospitalSpecialityRepo;

    public HospitalService(HospitalRepo hospitalRepo, HospitalSpecialityRepo hospitalSpecialityRepo) {
        this.hospitalRepo = hospitalRepo;
        this.hospitalSpecialityRepo = hospitalSpecialityRepo;
    }

    @Transactional
    public Hospital createHospital(Hospital hospital){
//        This should not be necessary, but Cascade doesn't work. Bug reports exist
        for (HospitalSpeciality hs : hospital.getSpecialities()){
            hospitalSpecialityRepo.save(hs);
        }
        return hospitalRepo.save(hospital);
    }
}
