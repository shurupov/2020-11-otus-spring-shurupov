package ru.otus.shurupov.spring.jpa.shell;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jpa.domain.Author;
import ru.otus.shurupov.spring.jpa.domain.BookComment;
import ru.otus.shurupov.spring.jpa.service.BookCommentService;
import ru.otus.shurupov.spring.jpa.service.TableRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class BookCommentShell {

    private final BookCommentService bookCommentService;
    private final TableRenderer tableRenderer;

    @ShellMethod(value = "Get book comments count", key = {"cc", "comments-count"})
    public void commentsCount() {
        System.out.println("Book comments count in library: " + bookCommentService.count());
    }

    @Transactional
    @ShellMethod(value = "Get comments list", key = {"cl", "comment-list"})
    public void commentsList() {
        List<BookComment> comments = bookCommentService.getAll();
        System.out.println(
                tableRenderer.render(
                        "Book comments list",
                        Arrays.asList("id", "Book", "Comment"),
                        (comment) -> Arrays.asList(comment.getId(), comment.getBookCaption(), comment.getText()),
                        comments
                )
        );
    }

    @ShellMethod(value = "Add comment", key = {"ca", "comment-add"})
    public void addComment(@ShellOption Long bookId, @ShellOption String comment) {
        bookCommentService.insert(bookId, comment);
        System.out.println("Comment successfully added to the book");
    }

    @Transactional
    @ShellMethod(value = "Get comment", key = {"cg", "comment-get"})
    public void getById(@ShellOption Long id) {
        Optional<BookComment> optionalAuthor = bookCommentService.getById(id);
        if (optionalAuthor.isPresent()) {
            BookComment comment = optionalAuthor.get();
            System.out.println(
                    tableRenderer.render(
                            "Comment",
                            ImmutableMap.of(
                                    "id", comment.getId(),
                                    "Book", comment.getBookCaption(),
                                    "Comment", comment.getText()
                            )
                    )
            );
        } else {
            System.out.println("Comment with id " + id + " not found");
        }
    }

    @ShellMethod(value = "Remove comment", key = {"cr", "comment-remove"})
    public void removeById(@ShellOption Long id) {
        bookCommentService.removeById(id);
        System.out.println("Comment successfully removed");
    }

    @ShellMethod(value = "Update comment", key = {"cu", "comment-update"})
    public void updateById(@ShellOption Long id, @ShellOption String comment) {
        bookCommentService.update(id, comment);
        System.out.println("Comment successfully updated");
    }
}
