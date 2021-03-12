package ru.otus.shurupov.spring.batch.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchShell {

    @ShellMethod(value = "migrateMongoToPostgres", key = "m-p")
    public void migrateMongoToPostgres() {
        System.out.println("BatchShell.migrateMongoToPostgres");
    }
}
