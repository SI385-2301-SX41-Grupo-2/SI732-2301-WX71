package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.Event;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Comentario.
 */
@Repository
public interface IeventRepository extends JpaRepository<Event, Long> {
  List<Event> findAllByDate(LocalDate date);
}