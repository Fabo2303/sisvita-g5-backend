package com.example.sisvita.api.ubigeo.domain;

import com.example.sisvita.api.ubigeo.infrastructure.UbigeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoService {
    private final UbigeoRepository ubigeoRepository;

    @Autowired
    public UbigeoService(UbigeoRepository ubigeoRepository) {
        this.ubigeoRepository = ubigeoRepository;
    }

    public Boolean saveUbigeo(Ubigeo ubigeo) {
        return ubigeoRepository.save(ubigeo);
    }

    public Ubigeo findByUbigeo(String ubigeo) {
        return ubigeoRepository.findByUbigeo(ubigeo);
    }

    public List<Ubigeo> findAll() {
        return ubigeoRepository.findAll();
    }

    public List<String> findProvincesByDepartment(String department) {
        return ubigeoRepository.findProvincesByDepartment(department);
    }

    public List<String> findDistrictsByDepartmentAndProvince(String department, String province) {
        return ubigeoRepository.findDistrictsByDepartmentAndProvince(department, province);
    }

    public Ubigeo findUbigeoByDepartmentAndProvinceAndDistrictObject(String department, String province, String district) {
        return ubigeoRepository.findUbigeoByDepartmentAndProvinceAndDistrict(department, province, district);
    }

    public String findUbigeoByDepartmentAndProvinceAndDistrict(String department, String province, String district) {
        Ubigeo ubigeo = ubigeoRepository.findUbigeoByDepartmentAndProvinceAndDistrict(department, province, district);
        if (ubigeo == null) {
            return null;
        }
        return ubigeo.getUbigeo();
    }

    public List<String> findAllDepartments() {
        return ubigeoRepository.findAllDepartments();
    }
}
