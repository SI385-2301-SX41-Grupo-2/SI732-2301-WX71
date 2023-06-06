package com.agripure.agripurebackend.service.impl;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.repository.IeventRepository;
import com.agripure.agripurebackend.service.IeventService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implement for Event.
 * */

@Service
@Transactional(readOnly = true)
public class EventServiceImpl implements IeventService {
  private final IeventRepository eventRepository;

  public EventServiceImpl(IeventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  @Transactional
  public Event save(Event event) throws Exception {
    return eventRepository.save(event);
  }

  @Override
  @Transactional
  public void delete(Long id) throws Exception {
    eventRepository.deleteById(id);
  }

  @Override
  public List<Event> findAllByDate(LocalDate date) throws Exception {
    return eventRepository.findAllByDate(date);
  }

  @Override
  public List<Event> getAll() throws Exception {
    return eventRepository.findAll();
  }

  @Override
  public Optional<Event> getById(Long id) throws Exception {
    return eventRepository.findById(id);
  }
}