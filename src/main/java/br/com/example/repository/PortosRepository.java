package br.com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.example.model.Portos;
import br.com.example.model.PortosVO;

@Repository
public interface PortosRepository extends JpaRepository<Portos, Integer> {

    // listar portos por Estado UF
    @Query(value = "select new br.com.example.model.PortosVO(p.codMun, p.municipio, p.uf, p.nomeUf, p.geometria) " + "from Portos p where upper(p.uf) = upper(:sigla)")
    List<PortosVO> listarPortosPorUf(String sigla);

    // listar portos q o nome do município contenha um termo específico
    @Query(value = "select new br.com.example.model.PortosVO(p.codMun, p.municipio, p.uf, p.nomeUf, p.geometria) " + "from Portos p where lower(p.municipio) like lower(concat('%', :nome, '%'))")
    List<PortosVO> listarPortosPorNomeContendo(String nome);

    // listar portos por Nome do Estado
    @Query(value = "select new br.com.example.model.PortosVO(p.codMun, p.municipio, p.uf, p.nomeUf, p.geometria) " + "from Portos p where lower(p.nomeUf) like lower(concat('%', :nomeEstado, '%'))")
    List<PortosVO> listarPortosPorNomeEstado(String nomeEstado);
}