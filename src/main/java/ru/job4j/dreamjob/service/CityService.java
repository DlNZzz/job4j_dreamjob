package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;

import java.util.*;

@Service
public class CityService {

    private static Map<Integer, City> cities = new HashMap<Integer, City>();

    static {
        cities.put(1, new City(1, "Москва"));
        cities.put(2, new City(2, "СПб"));
        cities.put(3, new City(3, "Екб"));
    }

    public static List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    public static City findById(int id) {
        return cities.get(id);
    }
}