package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * comentario.
 */
@Repository
public interface IplotRepository extends JpaRepository<Plot, Long> {
}