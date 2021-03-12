package ru.otus.shurupov.spring.batch.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchShell {

    private final JobLauncher jobLauncher;
    private final Job fromMongoToPostgres;

    @ShellMethod(value = "migrateMongoToPostgres", key = "m-p")
    public void migrateMongoToPostgres() throws Exception {
        JobExecution execution = jobLauncher.run(fromMongoToPostgres, new JobParameters());
        System.out.println(execution);
    }
}
