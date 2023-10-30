package com.MatiasSilva.motoservice.repositorio;

import com.MatiasSilva.motoservice.entidades.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findByUsuarioId(Long usuarioId);
}
