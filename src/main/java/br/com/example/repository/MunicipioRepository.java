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
    
    @Query(value = "select new br.com.example.model.MunicipioVO(mb.codigo,mb.nome,mb.geometria) from Municipio ma, Municipio mb where touches(ma.geometria, mb.geometria) = true and ma.nome = :nome")
    List<MunicipioVO> listarMunicipiosVizinhos(String nome);
     
    @Query(value ="select distance(geography(ma.geometria), geography(mb.geometria)) from Municipio ma, Municipio mb where ma.nome = :municipioA  and   mb.nome = :municipioB")
    public Double distanciaEntreMunicipios(String municipioA, String municipioB);

        // 1. Listar municípios por Estado (UF)
    @Query(value = "select new br.com.example.model.MunicipioVO(ab.codigo, ab.nome, ab.geometria) from Municipio ab where ab.sigla = :sigla")
    List<MunicipioVO> listarMunicipiosPorUf(String sigla);

    // 2. Listar municípios com área maior que um determinado valor (em km²)
    @Query(value = "select new br.com.example.model.MunicipioVO(ab.codigo, ab.nome, ab.geometria) from Municipio ab where ab.areaKm2 > :area")
    List<MunicipioVO> listarMunicipiosPorAreaMaiorQue(Double area);

    // 3. Listar municípios dentro de um raio de distância (em metros) a partir de um município de referência
    @Query(value = "select new br.com.example.model.MunicipioVO(ab.codigo, ab.nome, ab.geometria) " + "from Municipio ma, Municipio ab " + "where ma.nome = :nome " + "and function('ST_DWithin', ma.geometria, ab.geometria, :raio) = true " + "and ma.nome <> ab.nome") 
    List<MunicipioVO> listarMunicipiosEmRaio(String nome, Double raio);

    // 4. Buscar municípios cujo nome contenha um termo específico (busca parcial)
    @Query(value = "select new br.com.example.model.MunicipioVO(ab.codigo, ab.nome, ab.geometria) from Municipio ab where lower(ab.nome) like lower(concat('%', :nome, '%'))")
    List<MunicipioVO> listarMunicipiosPorNomeContendo(String nome);
}