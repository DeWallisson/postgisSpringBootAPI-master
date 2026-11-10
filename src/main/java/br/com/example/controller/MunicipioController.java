package br.com.example.controller;

import java.util.List;
import br.com.example.repository.MunicipioRepository;
import br.com.example.model.MunicipioVO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@OpenAPIDefinition(info = @Info(title = "Municípios API", version = "1.0", description = "Dados de Municípios"))
public class MunicipioController {
    
    @Autowired
    private MunicipioRepository repository;   
    
    @GetMapping("/municipiosQueSobrepoemOutro/{nome}")
    public List<MunicipioVO> listarMunicipiosQueSobrepoem(@PathVariable String nome) {
        return repository.listarMunicipiosQueSobrepoem(nome).stream().map(m -> new MunicipioVO(m.getCodigo(), m.getNome(), m.getGeometria())).toList();
    }

    @GetMapping("/municipiosQueCruzamOutro/{nome}")
    public List<MunicipioVO> listarMunicipiosQueCruzam(@PathVariable String nome) {
        return repository.listarMunicipiosQueCruzam(nome).stream().map(m -> new MunicipioVO(m.getCodigo(), m.getNome(), m.getGeometria())).toList();
    }

    @GetMapping("/municipiosQueNaoSeInterceptam/{nome}")
    public List<MunicipioVO> listarMunicipiosDisjuntos(@PathVariable String nome) {
        return repository.listarMunicipiosDisjuntos(nome).stream().map(m -> new MunicipioVO(m.getCodigo(), m.getNome(), m.getGeometria())).toList();
    }

    @GetMapping("/municipiosCobrem/{nome}")
    public List<MunicipioVO> listarMunicipiosQueCoverem(@PathVariable String nome) {
        return repository.listarMunicipiosQueCoverem(nome).stream().map(m -> new MunicipioVO(m.getCodigo(), m.getNome(), m.getGeometria())).toList();
    }
}