package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.service.IplantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Anotaciones Spring usadas.
 *
 * @CrossOrigin Example Javadoc
 * @RestController Example Javadoc
 * @RequestMapping Example Javadoc
 * @Api Example Javadoc
 */
@CrossOrigin
@RestController
@RequestMapping("/api/plants")
@Api(tags = "Plant", value = "Web Service RESTful - Plants")
public class PlantController {

  private final IplantService plantService;

  public PlantController(IplantService plantService) {
    this.plantService = plantService;
  }

  /**
   * findAllPlants.
   *
   * @return // Retorna todos los plantas
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "List Plants", notes = "Method for list all plants")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Plants found"),
    @ApiResponse(code = 404, message = "Plants not found"),
    @ApiResponse(code = 501, message = "Internal Server Error")
  })
    public ResponseEntity<List<Plant>> findAllPlants() {
    try {
      List<Plant> plants = plantService.getAll();
      return new ResponseEntity<>(plants, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findPlantsById.
   *
   * @return // Retorna una planta por id
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Find plants by Id", notes = "Method for list one plants by Id")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Plant found"),
      @ApiResponse(code = 404, message = "Plant not found"),
      @ApiResponse(code = 501, message = "Internal Server Error")
  })
    public ResponseEntity<Plant> findPlantsById(@PathVariable("id") Long id) {
    try {
      Optional<Plant> plant = plantService.getById(id);
      if (!plant.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(plant.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findPlantsByName.
   *
   * @return // Retorna una planta por nombre
   */
  @GetMapping(value = "/searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Found plant by Name", notes = "Method for list one plant by Id")
  public ResponseEntity<Plant> findPlantsByName(@PathVariable("name") String name) {
    try {
      Plant plant = plantService.findByName(name);
      if (plant != null) {
        return  new ResponseEntity<>(plant, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * insertPlant.
   *
   * @return // Inserta una planta
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Insert Plant", notes = "Method for insert new plant")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Plant created"),
    @ApiResponse(code = 404, message = "Plant not created"),
    @ApiResponse(code = 501, message = "Internal Server Error")
  })
  public ResponseEntity<Plant> insertPlant(@Valid @RequestBody Plant plant) {
    try {
      Plant plantNew = plantService.save(plant);
      return ResponseEntity.status(HttpStatus.CREATED).body(plantNew);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * updatePlant.
   *
   * @return // Actualiza los datos de una planta
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Update data for Plant", notes = "Method for update data for plant")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Data for Plant updated"),
    @ApiResponse(code = 404, message = "Plant not found"),
    @ApiResponse(code = 501, message = "Internal Server Error")
  })
  public ResponseEntity<Plant> updatePlant(@PathVariable("id") Long id,
                                           @Valid @RequestBody Plant plant) {
    try {
      Optional<Plant> plantOld = plantService.getById(id);
      if (!plantOld.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        plant.setId(id);
        plantService.save(plant);
        return new ResponseEntity<>(plant, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * insertPlant.
   *
   * @return // Inserta una planta
   */
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Delete Plant by Id", notes = "Method for delete plant")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Plant deleted"),
    @ApiResponse(code = 404, message = "Plant not found"),
    @ApiResponse(code = 501, message = "Internal Server Error")
  })
  public ResponseEntity<Plant> deletePlant(@PathVariable("id") Long id) {
    try {
      Optional<Plant> bookingDelete = plantService.getById(id);
      if (bookingDelete.isPresent()) {
        plantService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}