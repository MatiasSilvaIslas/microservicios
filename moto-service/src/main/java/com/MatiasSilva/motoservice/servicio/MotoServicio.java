package com.MatiasSilva.motoservice.servicio;
import com.MatiasSilva.motoservice.entidades.Moto;
import com.MatiasSilva.motoservice.repositorio.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoServicio {
    @Autowired
    private MotoRepository motoRepository;
    public List<Moto> obtenerMotos(){
        return motoRepository.findAll();
    }
    public Moto obtenerMoto(Long id){
        return motoRepository.findById(id).orElse(null);
    }
    public Moto guardarMoto(Moto moto){
        return motoRepository.save(moto);
    }
    public List<Moto> obtenerMotosByUsuarioId(Long usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
