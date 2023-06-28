package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.IplotService;
import com.agripure.agripurebackend.service.IuserService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plot Controller.
 * */
@CrossOrigin
@RestController
@RequestMapping("api/plots")
@Api(tags = "Plots", value = "Web Service RESTful - Plots")
public class PlotController {
  private IplotService plotService;
  private IuserService userService;
  
  public PlotController(IplotService plotService, IuserService userService) {
    this.plotService = plotService;
    this.userService = userService;
  }
  
  /**
   * Method Get find for find all Plots.
   * */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Plot>> findAllPlots() {
    try {
      List<Plot> plots = plotService.getAll();
      if (plots.size() > 0) {
        return new ResponseEntity<>(plots, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /**
   * Method Get for find Plot by Id.
   * */
  
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Plot> findPlotById(@PathVariable("id") Long id) {
    try {
      Optional<Plot> plot = plotService.getById(id);
      if (!plot.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(plot.get(), HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /**
   * Method Post for insert new Plot.
   * */
  
  @PostMapping(value = "/{userId}", consumes =
      MediaType.APPLICATION_JSON_VALUE, produces =
      MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plot> insertPlot(@PathVariable("userId") Long userId,
                                           @Valid @RequestBody Plot plot) {
    try {
      Optional<User> user = userService.getById(userId);
      if (user.isPresent()) {
        plot.setUser(user.get());
        Plot newPlot = plotService.save(plot);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlot);
      } else {
        return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /**
   * Method Delete for eliminated a Plot.
   * */

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Plot> deletePlot(@PathVariable("id") Long id) {
    try {
      Optional<Plot> plotDelete = plotService.getById(id);
      if (!plotDelete.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      plotService.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
