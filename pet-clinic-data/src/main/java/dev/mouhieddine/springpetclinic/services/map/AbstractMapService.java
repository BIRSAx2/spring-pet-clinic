package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object == null) throw new RuntimeException("Object cannot be null");

        if (object.getId() == null) object.setId(getNextId());
        map.put(object.getId(), object);
        return object;
    }

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    private Long getNextId() {
        return (map.size() == 0) ? 1L : Collections.max(map.keySet()) + 1;
    }
}
