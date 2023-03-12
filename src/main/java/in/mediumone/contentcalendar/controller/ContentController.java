package in.mediumone.contentcalendar.controller;

import in.mediumone.contentcalendar.model.Content;
import in.mediumone.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;


    @Autowired
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) {
        repository.save(content);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Content content) {
        if(!repository.existsById(id)){
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        } else {
            repository.save(content);
        }
    }
}
