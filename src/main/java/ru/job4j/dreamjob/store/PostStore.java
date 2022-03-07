package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private int count = 3;

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Junior", "05.03.22"));
        posts.put(2, new Post(2, "Middle Java Job", "Middle", "05.03.22"));
        posts.put(3, new Post(3, "Senior Java Job", "Senior", "05.03.22"));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        posts.put(++count, post);
    }
}
