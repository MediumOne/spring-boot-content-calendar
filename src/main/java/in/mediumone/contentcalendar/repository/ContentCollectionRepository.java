package in.mediumone.contentcalendar.repository;

import in.mediumone.contentcalendar.model.Content;
import in.mediumone.contentcalendar.model.Status;
import in.mediumone.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(it -> it.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        Content c = new Content(1,
                "Yellowstone",
                "A modern mid-western",
                Status.IN_PROGRESS,
                Type.VIDEO,
                LocalDateTime.now(),
                null,
                "https://netflix.com/yellowstone");

        contentList.add(c);
    }

    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(it -> it.id().equals(id)).count() == 1;
    }
}
