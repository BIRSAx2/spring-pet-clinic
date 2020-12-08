package dev.mouhieddine.springpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity implements Serializable {
  
  @Column(name = "description")
  private String description;

  @Builder
  public Specialty(Long id, String description) {
    super(id);
    this.description = description;
  }
}
