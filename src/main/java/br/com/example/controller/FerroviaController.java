package br.com.example.controller;

import java.util.List;
import br.com.example.repository.FerroviaRepository;
import br.com.example.model.FerroviaVO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@OpenAPIDefinition(info = @Info(title = "Ferrovias API", version = "1.0", description = "Dados de Ferrovias"))
public class FerroviaController {

    @Autowired
    private FerroviaRepository repository;

    @GetMapping("/municipio/{nome}")
    public List<FerroviaVO> listarFerroviasPorMunicipio(@PathVariable String nome) {
        return repository.listarFerroviasPorMunicipio(nome);
    }

    @GetMapping("/proximas-porto/{municipioPorto}/{raio}")
    public List<FerroviaVO> listarFerroviasProximasAoPorto(@PathVariable String municipioPorto, @PathVariable Double raio) {
        return repository.listarFerroviasProximasAoPorto(municipioPorto, raio);
    }

    @GetMapping("/ferroviasOrdenadas")
    public List<FerroviaVO> ferroviasOrdenadas() {
        List<FerroviaVO> result = repository.listarTodasFerroviasOrdenadas();
        return result;
    }
}