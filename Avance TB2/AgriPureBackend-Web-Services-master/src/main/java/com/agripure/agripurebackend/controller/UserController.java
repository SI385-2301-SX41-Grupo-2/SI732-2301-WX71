package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.IplantService;
import com.agripure.agripurebackend.service.IuserService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/users")
@Api(tags = "Users", value = "Web Service RESTful - Users")
public class UserController {
  private IuserService userService;
  private IplantService plantService;

  public UserController(IuserService userService, IplantService plantService) {
    this.userService = userService;
    this.plantService = plantService;
  }

  /**
   * findAllUsers.
   *
   * @return // Retorna todos los usuarios
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> findAllUsers() {
    try {
      List<User> users = userService.getAll();
      if (users.size() > 0) {
        return new ResponseEntity<>(users, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findUserById.
   *
   * @return //Retorna usuario por id
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
    try {
      Optional<User> user = userService.getById(id);
      if (!user.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  /**
   * findAllPlantsByUserId.
   *
   * @return //encuentra plantas por usuario de id
   */
  @GetMapping(value = "/{id}/plants", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Plant>> findAllPlantsByUserId(@PathVariable("id") Long id) {
    try {
      List<Plant> plants = userService.getPlantsByUserId(id);
      Optional<User> user = userService.getById(id);
      if (user.isPresent()) {
        if (plants.size() > 0) {
          return new ResponseEntity<>(plants, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * insertPlantIntoUser.
   *
   * @return //Inserta nueva planta en repo de usuario
   */
  @PostMapping(value = "{id}/plants",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Plant> insertPlantIntoUser(@PathVariable("id") Long id, 
        @Valid @RequestBody Plant plant) {
    try {
      Optional<User> user = userService.getById(id);
      if (user.isPresent()) {
        user.get().getPlants().add(plant);
        Plant newPlant = plantService.save(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlant);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findAllEventsByUserId.
   *
   * @return //findAllEventsByUserId
   */
  @GetMapping(value = "{id}/events", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Event>> findAllEventsByUserId(@PathVariable("id") Long id) {
    try {
      List<Event> events = userService.getEventsByUserId(id);
      Optional<User> user = userService.getById(id);
      if (user.isPresent()) {
        if (events.size() > 0) {
          return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findAllPlotsByUserId.
   *
   * @return //findAllPlotsByUserId
   */
  @GetMapping(value = "{id}/plots", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Plot>> findAllPlotsByUserId(@PathVariable("id") Long id) {
    try {
      List<Plot> plots = userService.getPlotsByUserId(id);
      Optional<User> user = userService.getById(id);
      if (user.isPresent()) {
        if (plots.size() > 0) {
          return new ResponseEntity<>(plots, HttpStatus.OK);   
        } else {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findByEmail.
   *
   * @return //findByEmail
   */
  @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
    try {
      Optional<User> user = userService.findByEmail(email);
      if (!user.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * findByPremium.
   *
   * @return //findByPremium
   */
  @GetMapping(value = "/searchPremium", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> findByPremium() {
    try {
      List<User> users = userService.findByPremium(true);
      if (users.size() > 0) {
        return new ResponseEntity<>(users, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * deleteUser.
   *
   * @return //deleteUser
   */
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
    try {
      Optional<User> deleteUser = userService.getById(id);
      if (!deleteUser.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      userService.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * insertUser.
   *
   * @return //insertUser
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> insertUser(@Valid @RequestBody User user) {
    try {
      User newUser = userService.save(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}