package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double score = 0;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
                count++;
            }
        }
        return score / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> label = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            label.add(new Label(pupil.name(), score / pupil.subjects().size()));
        }
        return label;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> label = new ArrayList<>();
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                Integer value = tempMap.getOrDefault(subject.name(), 0);
                tempMap.put(subject.name(), value + subject.score());
            }
        }
        for (String key : tempMap.keySet()) {
            label.add(new Label(key, tempMap.get(key) / pupils.size()));
        }
        return label;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double rsl = 0;
            for (Subject subject : pupil.subjects()) {
                rsl += subject.score();
            }
            list.add(new Label(pupil.name(), rsl));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                Integer value = tempMap.getOrDefault(subject.name(), 0);
                tempMap.put(subject.name(), value + subject.score());
            }
        }
        for (String subject : tempMap.keySet()) {
            list.add(new Label(subject, tempMap.get(subject)));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }
}