package com.agripure.agripurebackend.tests.service;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.repository.IEventRepository;
import com.agripure.agripurebackend.service.impl.EventServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private IEventRepository eventRepository;

    @Test
    @DisplayName("Testeando Save de EventServiceImpl")
    public void testSave() throws Exception{
        LocalDate date = LocalDate.parse("2022-11-01");
        Event expected = new Event(1L, date, "Regar la parcela de lechugas", new User());

        given(eventRepository.save(expected)).willReturn(expected);

        Event actual = eventService.save(expected);

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testeando Delete de EventServiceImpl")
    public void testDelete() throws Exception{
        Long id = 1L;
        eventService.delete(id);
        verify(eventRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Testeando FindAllByDate de EventServiceImpl")
    public void testFindAllByDate() throws Exception{
        LocalDate date = LocalDate.parse("2022-11-01");
        List<Event> events = new ArrayList<>();
        events.add(new Event(1L, date, "Regar la parcela de lechugas", new User()));
        events.add(new Event(2L, date, "Regar la parcela de papas", new User()));
        events.add(new Event(3L, date, "Regar la parcela de tomates", new User()));

        given(eventRepository.findAllByDate(date)).willReturn(events);

        List<Event> eventsExpected = eventService.findAllByDate(date);
        assertEquals(eventsExpected, events);
    }

    @Test
    @DisplayName("Testeando getByID de EventServiceImpl")
    public void testGetByID() throws Exception{
        LocalDate date = LocalDate.parse("2022-11-01");
        Event evento = new Event(1L, date, "Regar la parcela de lechugas", new User());
        Optional<Event> expected = Optional.of(evento);

        Mockito.when(eventRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(evento));

        Optional<Event> actual = eventService.getById(1L);

        assertEquals(expected, actual);
        Mockito.verify(eventRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Testeamos getAll de EventServiceImpl")
    public void testGetAll() throws Exception{
        LocalDate date1 = LocalDate.parse("2022-11-01");
        LocalDate date2 = LocalDate.parse("2022-11-02");
        List<Event> expected = new ArrayList<>();
        expected.add(new Event(1L, date1, "Regar la parcela de lechugas", new User()));
        expected.add(new Event(2L, date2, "Regar la parcela de tomates", new User()));

        List<Event> eventos = new ArrayList<>();
        eventos.add(new Event(1L, date1, "Regar la parcela de lechugas", new User()));
        eventos.add(new Event(2L, date2, "Regar la parcela de tomates", new User()));

        Mockito.when(eventRepository.findAll()).thenReturn(eventos);

        List<Event> actual = eventService.getAll();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(1).getId(), actual.get(1).getId());

        Mockito.verify(eventRepository, Mockito.times(1)).findAll();
    }
}