package com.agripure.agripurebackend.service.impl;

import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.repository.IplotRepository;
import com.agripure.agripurebackend.service.IplotService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implement for Plot.
 * */
@Service
@Transactional(readOnly = true)
public class PlotServiceImpl implements IplotService {
  private final IplotRepository plotRepository;

  public PlotServiceImpl(IplotRepository plotRepository) {
    this.plotRepository = plotRepository;
  }

  @Override
  @Transactional
  public Plot save(Plot plot) throws Exception {
    return plotRepository.save(plot);
  }

  @Override
  @Transactional
  public void delete(Long id) throws Exception {
    plotRepository.deleteById(id);
  }

  @Override
  public List<Plot> getAll() throws Exception {
    return plotRepository.findAll();
  }

  @Override
  public Optional<Plot> getById(Long id) throws Exception {
    return plotRepository.findById(id);
  }
}
