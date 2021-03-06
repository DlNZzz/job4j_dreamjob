package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateStore;
import ru.job4j.dreamjob.store.CandidateDbStore;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Service
public class CandidateService {

    private final CandidateDbStore store;

    public CandidateService(CandidateDbStore store) {
        this.store = store;
    }

    public List<Candidate> findAll() {
        return new ArrayList<>(store.findAll());
    }

    public void add(Candidate candidate) {
        store.add(candidate);
    }

    public Object findById(int id) {
        return store.findById(id);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

    public void create(Candidate candidate) {
        store.add(candidate);
    }

    public Candidate getById(Integer candidateId) {
        return store.findById(candidateId);
    }
}
