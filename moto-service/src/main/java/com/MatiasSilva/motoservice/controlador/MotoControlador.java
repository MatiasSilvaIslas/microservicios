package com.MatiasSilva.motoservice.controlador;

import com.MatiasSilva.motoservice.entidades.Moto;
import com.MatiasSilva.motoservice.servicio.MotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/moto")
public class MotoControlador {
    @Autowired
    private MotoServicio motoServicio;

    @GetMapping
    public ResponseEntity<List<Moto>> obtenerMotos(){
        List<Moto> motos = motoServicio.obtenerMotos();
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerUsuario(@PathVariable("id") Long id){
        Moto moto = motoServicio.obtenerMoto(id);
        if(moto == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> obtenerMotosPorUsuarioId(@PathVariable("usuarioId") Long usuarioId){
        List<Moto> motos = motoServicio.obtenerMotosByUsuarioId(usuarioId);
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto){
        Moto nuevoMoto = motoServicio.guardarMoto(moto);
        return ResponseEntity.ok(nuevoMoto);
    }
}
