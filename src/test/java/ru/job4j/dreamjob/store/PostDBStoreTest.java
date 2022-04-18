package ru.job4j.dreamjob.store;


import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class PostDBStoreTest {
    @Test
    public void whenCreatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post expected = new Post("Java Job", "desc", "cret", false, CityService.findById(1));
        int id = store.add(expected).getId();
        Post received = store.findById(id);
        assertThat(received, is(expected));
    }

    @Test
    public void whenFindAllPost() {
        List<Post> expected = new ArrayList<>();
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post("Java Job", "desc", "cret", false, CityService.findById(1));
        Post post2 = new Post("Java Job5", "desc2", "cret3", false, CityService.findById(3));
        int id = store.add(post).getId();
        int id2 = store.add(post2).getId();
        post.setId(id);
        post2.setId(id2);
        expected.add(post);
        expected.add(post2);
        boolean received = store.findAll().containsAll(expected);
        assertTrue(received);
    }

    @Test
    public void whenUpdateCandidate() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post expected = new Post("Java Job", "desc", "cret", false, CityService.findById(1));
        int id = store.add(expected).getId();
        expected.setDescription("new d");
        store.update(expected);
        Post received = store.findById(id);
        assertThat(received, is(expected));
    }
}