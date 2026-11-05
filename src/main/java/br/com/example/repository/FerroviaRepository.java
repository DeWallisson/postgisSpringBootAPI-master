package br.com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.example.model.Ferrovia;
import br.com.example.model.FerroviaVO;

@Repository
public interface FerroviaRepository extends JpaRepository<Ferrovia, Integer> {

    // lista ferrovias por Operadora
    @Query(value = "select new br.com.example.model.FerroviaVO(f.gid, f.ferrovia, f.operadora, f.geometria) " + "from Ferrovia f where lower(f.operadora) like lower(concat('%', :operadora, '%'))")
    List<FerroviaVO> listarFerroviasPorOperadora(String operadora);

    // listar ferrovias por nome da Ferrovia
    @Query(value = "select new br.com.example.model.FerroviaVO(f.gid, f.ferrovia, f.operadora, f.geometria) " + "from Ferrovia f where lower(f.ferrovia) like lower(concat('%', :nome, '%'))")
    List<FerroviaVO> listarFerroviasPorNomeContendo(String nome);

    // listar todas as ferrovias ordenadas pelo nome
    @Query("SELECT new br.com.example.model.FerroviaVO(f.gid, f.ferrovia, f.operadora, f.geometria) " + "FROM Ferrovia f ORDER BY f.ferrovia ASC")
    List<FerroviaVO> listarTodasFerroviasOrdenadas();
}
