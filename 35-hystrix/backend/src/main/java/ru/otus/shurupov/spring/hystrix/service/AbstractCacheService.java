package ru.otus.shurupov.spring.hystrix.service;

import org.springframework.web.server.ServerErrorException;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractCacheService<T> {

    private Map<Long, T> cache = Collections.emptyMap();

    private final Function<? super T, Long> idMapper;

    protected AbstractCacheService(Function<? super T, Long> idMapper) {
        this.idMapper = idMapper;
    }

    public void updateCache(Collection<T> source) {
        cache = source.stream().collect(Collectors.toMap(
                idMapper, element -> element
        ));
    }

    public Collection<T> getAllCached() {
        return cache.values();
    }

    public T getByIdFromCache(Long id) {
        return cache.get(id);
    }

    public void throwException() {
        throw new ServerErrorException("Temporary server problems", new RuntimeException());
    }
}
