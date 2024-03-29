package com.MatiasSilva.autoservice.repository;

import com.MatiasSilva.autoservice.entidades.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    List<Auto> findByUsuarioId(Long usuarioId);
}
