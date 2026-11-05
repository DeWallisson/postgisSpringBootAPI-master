package br.com.example.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
public class PortosVO implements Serializable {
    private Integer codMun;
    private String municipio;
    private String uf;
    private String nomeUf;
    private Geometry geometria;
}