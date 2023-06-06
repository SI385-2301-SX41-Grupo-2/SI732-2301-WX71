package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.Event;
import java.time.LocalDate;
import java.util.List;

/**
 * Comentario.
 */
public interface IeventService extends CrudService<Event> {
  List<Event> findAllByDate(LocalDate date) throws Exception;
}