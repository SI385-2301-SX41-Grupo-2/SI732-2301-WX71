package com.agripure.agripurebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity plants.
 * */
@Entity
@Table(name = "plants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "image", nullable = false)
  private String image;
  @Column(name = "saved", nullable = false)
  private Boolean saved;
  @Column(name = "scientifist_name", nullable = false, length = 100)
  private String scientifistName;
  @Column(name = "variety", nullable = false, length = 100)
  private String variety;
  @Column(name = "info_land_type", nullable = false, length = 1000)
  private String infoLandType;
  @Column(name = "ph", nullable = false)
  private Float ph;
  @Column(name = "info_distance_between", nullable = false, length = 1000)
  private String infoDistanceBetween;
  @Column(name = "distance_between", nullable = false)
  private String distanceBetween;
  @Column(name = "info_ideal_depth", nullable = false, length = 1000)
  private String infoIdealDepth;
  @Column(name = "depth", nullable = false)
  private String depth;
  @Column(name = "info_weather_conditions", nullable = false, length = 1000)
  private String infoWeatherConditions;
  @Column(name = "weather", nullable = false)
  private String weather;
  @Column(name = "info_fert_fumig", nullable = false, length = 1000)
  private String infoFertFumig;
  @Column(name = "intervale_fert", nullable = false)
  private Integer intervaleFert;
  @Column(name = "intervale_fumig", nullable = false)
  private Integer intervaleFumig;
  @JsonIgnore
  @ManyToMany(mappedBy = "plants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<User> users;
}
