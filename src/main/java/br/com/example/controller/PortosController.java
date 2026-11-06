package br.com.example.controller;

import java.util.List;
import br.com.example.repository.PortosRepository;
import br.com.example.model.PortosVO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@OpenAPIDefinition(info = @Info(title = "Portos API", version = "1.0", description = "Dados de Portos"))
public class PortosController {

    @Autowired
    private PortosRepository repository;

   @GetMapping("/raio/{lon}/{lat}/{raio}")
   public List<PortosVO> listarPortosNoRaio(@PathVariable Double lon, @PathVariable Double lat, @PathVariable Double raio) {
       return portosRepository.listarPortosNoRaioDePonto(lon, lat, raio);
   }

   @GetMapping("/municipio/{nome}")
   public List<PortosVO> listarPortosPorMunicipio(@PathVariable String nome) {
       return portosRepository.listarPortosPorNomeMunicipio(nome);
   }  

    @GetMapping("/portosPorNomeContendo/{nome}")
    public List<PortosVO> portosPorNomeContendo(@PathVariable String nome) {
        return repository.listarPortosPorNomeContendo(nome);
    }
}
