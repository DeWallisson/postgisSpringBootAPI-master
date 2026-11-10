package br.com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.example.model.Portos;
import br.com.example.model.PortosVO;

@Repository
public interface PortosRepository extends JpaRepository<Portos, Integer> {

    // listar portos q o nome do municipio contenha um termo específico
    @Query(value = "select new br.com.example.model.PortosVO(p.codMun, p.municipio, p.uf, p.nomeUf, p.geometria) " + "from Portos p where lower(p.municipio) like lower(concat('%', :nome, '%'))")
    List<PortosVO> listarPortosPorNomeContendo(String nome);

    // listar portos que estão dentro de um municipio especifico buscado pelo nome
    @Query(value = "SELECT new br.com.example.model.PortosVO(p.codMun, p.municipio, p.uf, p.nomeUf, p.geometria) " + "FROM Portos p, Municipio m WHERE m.nome = ?1 AND function('ST_Intersects', p.geometria, m.geometria) = true")
    List<PortosVO> listarPortosPorNomeMunicipio(String nomeMunicipio);

    // Listar portos na coordenada exata
    @Query(value = "SELECT new br.com.example.model.PortosVO(p.codMun, p.municipio, p.uf, p.nomeUf, p.geometria) " + "FROM Portos p " + "WHERE ST_Equals(p.geometria, ST_Point(?1, ?2)) = true")
    List<PortosVO> listarPortosExatos(double longitude, double latitude);
    
}