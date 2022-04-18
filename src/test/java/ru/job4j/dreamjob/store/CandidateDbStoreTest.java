package ru.job4j.dreamjob.store;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class CandidateDbStoreTest {

    @Test
    public void whenCreateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate("name", new byte[2], "description", "created");
        store.add(candidate);
        Candidate received = store.findById(1);
        assertThat(received.getName(), is(candidate.getName()));
    }

    @Test
    public void whenFindAllCandidate() {
        List<Candidate> expected = new ArrayList<>();
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate("name", new byte[2], "description", "created");
        Candidate candidate2 = new Candidate("name2", new byte[3], "description2", "created2");
        expected.add(candidate);
        expected.add(candidate2);
        store.add(candidate);
        store.add(candidate2);
        boolean received = store.findAll().containsAll(expected);
        assertTrue(received);
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate expected = new Candidate("name", new byte[23], "description", "created3");
        int id = store.add(expected).getId();
        expected.setDescription("new d");
        store.update(expected);
        Candidate received = store.findById(id);
        assertThat(received, is(expected));
    }
}
