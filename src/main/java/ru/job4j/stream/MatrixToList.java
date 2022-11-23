package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixToList {
    public static List<Integer> convert(Integer[][] matrix) {
        List<Integer> rsl = Stream.of(matrix)
                .flatMap(integers -> Stream.of(integers))
                .collect(Collectors.toList());
        return rsl;
    }
}
