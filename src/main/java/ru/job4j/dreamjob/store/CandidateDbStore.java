package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDbStore {

    private final BasicDataSource pool;

    public CandidateDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM candidate")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    candidates.add(new Candidate(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getBytes("photo"),
                            resultSet.getString("description"),
                            resultSet.getString("created")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO candidate(name, photo, description, created) " +
                             "VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setBytes(2, candidate.getPhoto());
            preparedStatement.setString(3, candidate.getDescription());
            preparedStatement.setString(4, candidate.getCreated());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    candidate.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE candidate " +
                     "SET name = ?, photo = ?, description = ?, created = ? " +
                     "WHERE id = ?;")) {
            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setBytes(2, candidate.getPhoto());
            preparedStatement.setString(3, candidate.getDescription());
            preparedStatement.setString(4, candidate.getCreated());
            preparedStatement.setInt(5, candidate.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Candidate findById(int id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM candidate WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Candidate(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getBytes("photo"),
                            resultSet.getString("description"),
                            resultSet.getString("created")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
