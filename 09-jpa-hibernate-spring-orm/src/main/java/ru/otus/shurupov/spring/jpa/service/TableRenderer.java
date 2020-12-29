package ru.otus.shurupov.spring.jpa.service;

import java.util.List;
import java.util.Map;

public interface TableRenderer {
    <T> String render(String title, List<String> headers, RowRenderer<T> rowRenderer, List<T> rows);

    String render(String title, Map<String, Object> data);

    @FunctionalInterface
    interface RowRenderer<T> {
        List<Object> renderRow(T row);
    }
}
