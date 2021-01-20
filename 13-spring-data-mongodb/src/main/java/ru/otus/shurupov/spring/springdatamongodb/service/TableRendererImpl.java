package ru.otus.shurupov.spring.springdatamongodb.service;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class TableRendererImpl implements TableRenderer {
    @Override
    public <T> String render(String title, List<String> headers, RowRenderer<T> rowRenderer, List<T> rows) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        List<String> titleRow = new ArrayList<>(headers.size());
        titleRow.addAll(Collections.nCopies(headers.size() - 1, null));
        titleRow.add(title);
        table.addRow(titleRow);
        table.addRule();
        table.addRow(headers);
        table.addRule();
        for (T row : rows) {
            AT_Row tableRow = table.addRow(rowRenderer.renderRow(row));
            tableRow.getCells().get(0).getContext().setTextAlignment(TextAlignment.RIGHT);
            table.addRule();
        }
        table.getRenderer().setCWC(new CWC_LongestLine());
        table.setPaddingLeftRight(1);
        return table.render();
    }

    @Override
    public String render(String title, Map<String, Object> data) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(null, title);
        table.addRule();
        for (Map.Entry<String, Object> line : data.entrySet()) {
            table.addRow(line.getKey(), line.getValue().toString());
            table.addRule();
        }
        table.getRenderer().setCWC(new CWC_LongestLine());
        table.setPaddingLeftRight(1);
        return table.render();
    }
}
