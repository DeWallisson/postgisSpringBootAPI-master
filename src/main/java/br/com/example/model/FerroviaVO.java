package br.com.example.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
public class FerroviaVO implements Serializable {
    private Integer gid;
    private String ferrovia;
    private String operadora;
    private Geometry geometria;
}