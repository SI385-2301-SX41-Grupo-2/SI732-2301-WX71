package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.Plant;

/**
 * Comentario.
 */
public interface IplantService extends CrudService<Plant> {

  Plant findByName(String name) throws Exception;
}
