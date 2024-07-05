package com.example.sisvita.api.ubigeo.domain;

import com.example.sisvita.api.ubigeo.infrastructure.JpaUbigeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoService {
    private final JpaUbigeoRepository jpaUbigeoRepository;

    @Autowired
    public UbigeoService(JpaUbigeoRepository jpaUbigeoRepository) {
        this.jpaUbigeoRepository = jpaUbigeoRepository;
    }

    public Ubigeo saveUbigeo(Ubigeo ubigeo) {
        return jpaUbigeoRepository.save(ubigeo);
    }

    public Ubigeo findByUbigeo(String ubigeo) {
        return jpaUbigeoRepository.findById(ubigeo).orElse(null);
    }

    public List<Ubigeo> findAll() {
        return jpaUbigeoRepository.findAll();
    }

    public List<String> findProvincesByDepartment(String department) {
        return jpaUbigeoRepository.findProvincesByDepartment(department);
    }

    public List<String> findDistrictsByDepartmentAndProvince(String department, String province) {
        return jpaUbigeoRepository.findDistrictsByDepartmentAndProvince(department, province);
    }

    public Ubigeo findUbigeoByDepartmentAndProvinceAndDistrictObject(String department, String province, String district) {
        return jpaUbigeoRepository.findUbigeoByDepartmentAndProvinceAndDistrict(department, province, district).orElse(null);
    }

    public String findUbigeoByDepartmentAndProvinceAndDistrict(String department, String province, String district) {
        Ubigeo ubigeo = jpaUbigeoRepository.findUbigeoByDepartmentAndProvinceAndDistrict(department, province, district).orElse(null);
        if (ubigeo == null) {
            return null;
        }
        return ubigeo.getUbigeo();
    }

    public List<String> findAllDepartments() {
        return jpaUbigeoRepository.findAllDepartments();
    }
}
