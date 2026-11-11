package br.com.example.controller;

import java.util.List;
import br.com.example.repository.MunicipioRepository;
import br.com.example.model.MunicipioVO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@OpenAPIDefinition(info = @Info(title = "Municípios API", version = "1.0", description = "Dados de Municípios"))
public class MunicipioController {
    
    @Autowired
    private MunicipioRepository repository;   
    
    @GetMapping("/sobreposicao/{nomeMunicipio1}/{nomeMunicipio2}")
    public ResponseEntity<Boolean> verificarSobreposicao(@PathVariable String nomeMunicipio1, @PathVariable String nomeMunicipio2) {
    Boolean resultado = repository.verificarSobreposicaoEntreDuasCidades(nomeMunicipio1, nomeMunicipio2);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/municipiosQueCruzamOutro/{nome}")
    public List<MunicipioVO> listarMunicipiosQueCruzam(@PathVariable String nome) {
        return repository.listarMunicipiosQueCruzam(nome);
    }

    @GetMapping("/municipiosQueNaoSeInterceptam/{nome}")
    public List<MunicipioVO> listarMunicipiosDisjuntos(@PathVariable String nome) {
        return repository.listarMunicipiosDisjuntos(nome);
    }

    @GetMapping("/municipiosQueSeCobrem/{nome}")
    public List<MunicipioVO> listarMunicipiosQueCoverem(@PathVariable String nome) {
        return repository.listarMunicipiosQueCoverem(nome);
    }
}