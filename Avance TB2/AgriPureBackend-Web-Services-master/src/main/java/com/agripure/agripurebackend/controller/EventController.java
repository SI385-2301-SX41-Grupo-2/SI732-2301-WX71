package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.IeventService;
import com.agripure.agripurebackend.service.IuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Event Controller.
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/events")
@Api(tags = "Events", value = "Web Service RESTful - Events")
public class EventController {
  private final IeventService eventService;
  private final IuserService userService;

  public EventController(IeventService eventService, IuserService userService) {
    this.eventService = eventService;
    this.userService = userService;
  }

  /**
   * Method Get for List Events by Date.
   * */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "List Events By Date", notes = "Method for list all Events by Date")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Events found"),
      @ApiResponse(code = 404, message = "Events not found"),
      @ApiResponse(code = 501, message = "Internal Server Error")
  })
  public ResponseEntity<List<Event>> findAllByDate(@RequestParam("date")
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> req) {
    try {
      if (req.isPresent()) {
        LocalDate date = req.get();
        List<Event> events = eventService.findAllByDate(date);
        return new ResponseEntity<>(events, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Method Post for insert new Event.
   * */
  @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Add Events", notes = "Method for add new events")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Events created"),
    @ApiResponse(code = 404, message = "Plants not created"),
    @ApiResponse(code = 501, message = "Internal Server Error")
  })
  public ResponseEntity<Event> insertEvent(@PathVariable("userId") Long userId,
                                           @Valid @RequestBody Event event) {
    try {
      Optional<User> user = userService.getById(userId);
      if (user.isPresent()) {
        event.setUser(user.get());
        Event newEvent = eventService.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
      } else {
        return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Method Delete for elimineted a event.
   * */
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Delete Event by Id", notes = "Method for delete event")
  @ApiResponses({
    @ApiResponse(code = 201, message = "Event deleted"),
    @ApiResponse(code = 404, message = "Event not found"),
    @ApiResponse(code = 501, message = "Internal Server Error")
  })
  public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id) {
    try {
      Optional<Event> eventDelete = eventService.getById(id);
      if (!eventDelete.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      eventService.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}