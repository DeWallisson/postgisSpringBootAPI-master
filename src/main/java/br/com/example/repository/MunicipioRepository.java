/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.example.repository;

import br.com.example.model.Municipio;
import br.com.example.model.MunicipioVO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author caian
 */
public interface MunicipioRepository extends JpaRepository<Municipio, Integer>{

    // 1. Listar todos os municípios que se sobrepõem à referência
    @Query(value = "SELECT m.* FROM br_municipios_2025 m, br_municipios_2025 ref WHERE ref.nm_mun = ?1 AND ST_Overlaps(m.geom, ref.geom) = true", nativeQuery = true)
    List<Municipio> listarMunicipiosQueSobrepoem(String nomeMunicipioRef);

    // 2. Listar todos os municípios cujas fronteiras cruzam com a referência
    @Query(value = "SELECT m.* FROM br_municipios_2025 m, br_municipios_2025 ref WHERE ref.nm_mun = ?1 AND ST_Crosses(m.geom, ref.geom) = true", nativeQuery = true)
    List<Municipio> listarMunicipiosQueCruzam(String nomeMunicipioRef);

    // 3. Listar todos os municípios disjuntos da referência
    @Query(value = "SELECT m.* FROM br_municipios_2025 m, br_municipios_2025 ref WHERE ref.nm_mun = ?1 AND ST_Disjoint(m.geom, ref.geom) = true", nativeQuery = true)
    List<Municipio> listarMunicipiosDisjuntos(String nomeMunicipioRef);

    // 4. Listar todos os municípios que cobrem a referência
    @Query(value = "SELECT m.* FROM br_municipios_2025 m, br_municipios_2025 ref WHERE ref.nm_mun = ?1 AND ST_Covers(m.geom, ref.geom) = true", nativeQuery = true)
    List<Municipio> listarMunicipiosQueCoverem(String nomeMunicipioRef);
}