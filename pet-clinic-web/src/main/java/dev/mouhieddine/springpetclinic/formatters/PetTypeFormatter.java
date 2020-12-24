package dev.mouhieddine.springpetclinic.formatters;

import dev.mouhieddine.springpetclinic.model.PetType;
import dev.mouhieddine.springpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * @author : Mouhieddine.dev
 * @since : 12/24/2020, Thursday
 **/
@Component
public class PetTypeFormatter implements Formatter<PetType> {

  private final PetTypeService petTypeService;

  public PetTypeFormatter(PetTypeService petTypeService) {
    this.petTypeService = petTypeService;
  }

  @Override
  public String print(PetType petType, Locale locale) {
    return petType.getName();
  }

  @Override
  public PetType parse(String text, Locale locale) throws ParseException {
    Collection<PetType> findPetTypes = petTypeService.findAll();

    for (PetType type : findPetTypes) {
      if (type.getName().equals(text)) {
        return type;
      }
    }

    throw new ParseException("type not found: " + text, 0);
  }
}