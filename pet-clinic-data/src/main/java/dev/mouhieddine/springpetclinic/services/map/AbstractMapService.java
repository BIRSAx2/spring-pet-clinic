package dev.mouhieddine.springpetclinic.services.map;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public abstract class AbstractMapService<T, ID> {
    protected Map<ID, T> map = new HashMap<>();

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(ID id, T object) {
        return map.put(id, object);
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
}
