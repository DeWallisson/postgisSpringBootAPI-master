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

    @GetMapping("/portosPorUf/{sigla}")
    public List<PortosVO> portosPorUf(@PathVariable String sigla) {
        return repository.listarPortosPorUf(sigla);
    }

    @GetMapping("/portosPorNomeContendo/{nome}")
    public List<PortosVO> portosPorNomeContendo(@PathVariable String nome) {
        return repository.listarPortosPorNomeContendo(nome);
    }

    @GetMapping("/portosPorNomeEstado/{nomeEstado}")
    public List<PortosVO> portosPorNomeEstado(@PathVariable String nomeEstado) {
        return repository.listarPortosPorNomeEstado(nomeEstado);
    }
}