package com.fm.wardservice.repository;

import com.fm.wardservice.model.Ward;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WardRepository {
    private List<Ward> wards = new ArrayList<>();

    public Ward addWard(Ward ward){
        wards.add(ward);
        return ward;
    }

    public Ward findById(long id){
        return wards.stream()
                .filter(ward -> ward.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Ward> findAll(){
        return wards;
    }
}
