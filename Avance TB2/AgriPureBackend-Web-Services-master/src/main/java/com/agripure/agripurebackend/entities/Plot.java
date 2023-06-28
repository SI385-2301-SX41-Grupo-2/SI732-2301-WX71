package com.agripure.agripurebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comentario.
 */
@Entity
@Table(name = "plot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plot implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "plant", nullable = false)
  private String plantName;
  private Long size;
  @Column(name = "quantity", nullable = false)
  private Long quantity;
  @Column(name = "latitude", nullable = false)
  private Double latitude;
  @Column(name = "longitude", nullable = false)
  private Double longitude;
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private User user;
}
