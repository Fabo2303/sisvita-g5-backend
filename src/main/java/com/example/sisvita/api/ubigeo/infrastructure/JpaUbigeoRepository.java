package com.example.sisvita.api.ubigeo.infrastructure;

import com.example.sisvita.api.ubigeo.domain.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUbigeoRepository extends JpaRepository<Ubigeo, String> {
    @Query("SELECT DISTINCT u.province FROM Ubigeo u WHERE u.department =:department")
    List<String> findProvincesByDepartment(@Param("department") String department);

    @Query("SELECT DISTINCT u.district FROM Ubigeo u WHERE u.department =:department AND u.province =:province")
    List<String> findDistrictsByDepartmentAndProvince(@Param("department") String department, @Param("province") String province);

    @Query("SELECT u FROM Ubigeo u WHERE u.department =:department AND u.province =:province AND u.district =:district")
    Optional<Ubigeo> findUbigeoByDepartmentAndProvinceAndDistrict(@Param("department") String department, @Param("province") String province, @Param("district") String district);

    @Query("SELECT DISTINCT u.department FROM Ubigeo u")
    List<String> findAllDepartments();
}
