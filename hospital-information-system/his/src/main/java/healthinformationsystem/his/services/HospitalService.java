package healthinformationsystem.his.services;

import healthinformationsystem.his.entities.Hospital;
import healthinformationsystem.his.entities.Department;
import healthinformationsystem.his.repos.HospitalRepo;
import healthinformationsystem.his.repos.HospitalDepartmentRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private final HospitalRepo hospitalRepo;
    private final HospitalDepartmentRepo hospitalDepartmentRepo;

    public HospitalService(HospitalRepo hospitalRepo, HospitalDepartmentRepo hospitalDepartmentRepo) {
        this.hospitalRepo = hospitalRepo;
        this.hospitalDepartmentRepo = hospitalDepartmentRepo;
    }

    @Transactional
    public Hospital createHospital(Hospital hospital){
//        This should not be necessary, but Cascade doesn't work. Bug reports exist
//        for (Department hs : hospital.getDepartments()){
//            hospitalDepartmentRepo.save(hs);
//        }
        return hospitalRepo.save(hospital);
    }
}
