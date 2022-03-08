package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.persistence.PostStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {

    private final PostStore store;

    public PostService(PostStore store) {
        this.store = store;
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.findAll());
    }

    public void add(Post post) {
        store.add(post);
    }

    public Object findById(int id) {
        for (Post post : findAll()) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public void update(Post post) {
        store.update(post);
    }

    public void create(Post post) {
        store.add(post);
    }
}