package ru.job4j.dreamjob.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private AtomicInteger count = new AtomicInteger(3);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Junior", "05.03.22"));
        posts.put(2, new Post(2, "Middle Java Job", "Middle", "05.03.22"));
        posts.put(3, new Post(3, "Senior Java Job", "Senior", "05.03.22"));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(count.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void update(Post post) {
        for (Map.Entry<Integer, Post> postEntry : posts.entrySet()) {
            if (postEntry.getValue().getId() == post.getId()) {
                posts.put(postEntry.getKey(), post);
            }
        }
    }
}
