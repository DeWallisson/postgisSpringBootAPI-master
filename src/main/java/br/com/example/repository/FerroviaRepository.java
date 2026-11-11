package br.com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.example.model.Ferrovia;
import br.com.example.model.FerroviaVO;

@Repository
public interface FerroviaRepository extends JpaRepository<Ferrovia, Integer> {

     // listar ferrovias que cruzam um município específico
    @Query(value = "SELECT new br.com.example.model.FerroviaVO(f.gid, f.ferrovia, f.operadora, f.geometria) " + "FROM Ferrovia f, Municipio m WHERE m.nome = ?1 AND function('ST_Intersects', f.geometria, m.geometria) = true")
    List<FerroviaVO> listarFerroviasPorMunicipio(String nomeMunicipio);

    // listar ferrovias que passam a menos de X metros de distância de um porto específico
    @Query(value = "SELECT new br.com.example.model.FerroviaVO(f.gid, f.ferrovia, f.operadora, f.geometria) " + "FROM Ferrovia f, Portos p " + "WHERE p.municipio = :nomePortsMunicipio " + "AND function('ST_DWithin', f.geometria, p.geometria, :raioMetros) = true")
    List<FerroviaVO> listarFerroviasProximasAoPorto(@Param("nomePortsMunicipio") String nomePortsMunicipio, @Param("raioMetros") Double raioMetros);

    // listar todas as ferrovias ordenadas pelo nome
    @Query("SELECT new br.com.example.model.FerroviaVO(f.gid, f.ferrovia, f.operadora, f.geometria) " + "FROM Ferrovia f ORDER BY f.ferrovia ASC")
    List<FerroviaVO> listarTodasFerroviasOrdenadas();
}
