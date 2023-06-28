package com.agripure.agripurebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

/**
 * Resumen de las dependencias Lombok usadas.
 *
 * @Entity Some javadoc. // OK
 * @Table Some javadoc. // OK
 * @Data Some javadoc. // OK
 * @NoArgsConstructor Some javadoc. // OK
 * @AllArgsConstructor
 * @Getter Some javadoc. // OK
 * @Setter Some javadoc. // OK
 * @With Some javadoc. // OK
 */
@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "date", nullable = false)
  private LocalDate date;
  @Column(name = "description", nullable = false, length = 200)
  private String description;
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private User user;
}