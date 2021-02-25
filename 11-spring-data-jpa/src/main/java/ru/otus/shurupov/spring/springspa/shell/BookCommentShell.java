package ru.otus.shurupov.spring.springdata.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.springdata.service.BookCommentService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommentShell {

    private final BookCommentService bookCommentService;

    @ShellMethod(value = "Get book comments count", key = {"cc", "comments-count"})
    public void commentsCount() {
        System.out.println("Book comments count in library: " + bookCommentService.count());
    }

    @ShellMethod(value = "Get comments list", key = {"cl", "comment-list"})
    public void commentsList() {
        bookCommentService.displayList();
    }

    @ShellMethod(value = "Add comment", key = {"ca", "comment-add"})
    public void addComment(@ShellOption Long bookId, @ShellOption String comment) {
        bookCommentService.insert(bookId, comment);
        System.out.println("Comment successfully added to the book");
    }

    @ShellMethod(value = "Get comment", key = {"cg", "comment-get"})
    public void getById(@ShellOption Long id) {
        bookCommentService.displayById(id);
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
