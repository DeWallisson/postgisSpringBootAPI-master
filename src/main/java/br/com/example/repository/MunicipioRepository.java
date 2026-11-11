/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.example.repository;

import br.com.example.model.Municipio;
import br.com.example.model.MunicipioVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    // listar todos os municípios que se sobrepõem e referencia
    @Query(value = "SELECT function('ST_Overlaps', m1.geometria, m2.geometria) " + "FROM Municipio m1, Municipio m2 " + "WHERE m1.nome = :nomeMunicipio1 AND m2.nome = :nomeMunicipio2")
    Boolean verificarSobreposicaoEntreDuasCidades(@Param("nomeMunicipio1") String nomeMunicipio1, @Param("nomeMunicipio2") String nomeMunicipio2);

    // listar todos os municipios cujas fronteiras cruzam com a referencia
    @Query(value = "SELECT new br.com.example.model.MunicipioVO(m.codigo, m.nome, m.geometria) " + "FROM Municipio m, Municipio ref " + "WHERE ref.nome = :nomeMunicipioRef AND function('ST_Crosses', m.geometria, ref.geometria) = true")
    List<MunicipioVO> listarMunicipiosQueCruzam(@Param("nomeMunicipioRef") String nomeMunicipioRef);

    // listar todos os municipios q nao passam na referencia
    @Query(value = "SELECT new br.com.example.model.MunicipioVO(m.codigo, m.nome, m.geometria) " + "FROM Municipio m, Municipio ref " + "WHERE ref.nome = :nomeMunicipioRef AND function('ST_Disjoint', m.geometria, ref.geometria) = true")
    List<MunicipioVO> listarMunicipiosDisjuntos(@Param("nomeMunicipioRef") String nomeMunicipioRef);

    // listar todos os municipios que cobrem a referencia
    @Query(value = "SELECT new br.com.example.model.MunicipioVO(m.codigo, m.nome, m.geometria) " + "FROM Municipio m, Municipio ref " + "WHERE ref.nome = :nomeMunicipioRef AND function('ST_Covers', m.geometria, ref.geometria) = true")
    List<MunicipioVO> listarMunicipiosQueCoverem(@Param("nomeMunicipioRef") String nomeMunicipioRef);
}