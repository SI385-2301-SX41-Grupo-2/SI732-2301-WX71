package com.agripure.agripurebackend.tests.service;

import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.repository.IplantRepository;
import com.agripure.agripurebackend.service.impl.PlantServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//CORREGIR ERROR NULLPOINTEREXECPTION LINEA 48

@ExtendWith(MockitoExtension.class)
public class PlantServiceImplTest {
    @InjectMocks
    private PlantServiceImpl plantService;

    @Mock
    private IplantRepository plantRepository;

    @Test
    @DisplayName("Testeando Save de PlantServiceImpl")
    public void testSave() throws Exception{
        Plant expected = new Plant(1L,"manzana","imagen manzana",
                true,"manzana cientifica","israel","arenosa",
                4F,"cada 4 metros","4 metros",
                "30 centimetros","30 cm","parece nublado",
                "nublado","de buena marca",3,
                2,null);
        Plant plant = new Plant(1L,"manzana","imagen manzana",
                true,"manzana cientifica","israel","arenosa",
                4F,"cada 4 metros","4 metros",
                "30 centimetros","30 cm","parece nublado",
                "nublado","de buena marca",3,
                2,null);
        Mockito.when(plantRepository.save(Mockito.any(Plant.class))).thenReturn(plant);
        Plant actual = plantService.save(new Plant(1L,"manzana","imagen manzana",
                true,"manzana cientifica","israel","arenosa",
                4F,"cada 4 metros","4 metros",
                "30 centimetros","30 cm","parece nublado",
                "nublado","de buena marca",3,
                2,null));
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getVariety(),actual.getVariety());
    }

    @Test
    @DisplayName("Testeando Delete de PlantServiceImpl")
    public void testDelete() throws Exception{
        Long id = 1L;
        plantService.delete(id);
        verify(plantRepository, times(1)).deleteById(id);
    }
}
