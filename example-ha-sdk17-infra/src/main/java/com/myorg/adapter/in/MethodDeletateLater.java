package com.myorg.adapter.in;

import java.util.Comparator;
import java.util.List;

public class MethodDeletateLater {
    public String firstName;
    public String lastName;
    public int age;

    public static List<MethodDeletateLater> filterAndSortPersons(List<MethodDeletateLater> persons) {
        return persons
                .stream()
                .filter(p->(p.age<30))
                .sorted(Comparator.comparing(MethodDeletateLater::getLastName).reversed())
                .toList();
    }
    public String getLastName() {
        return lastName;
    }
}
