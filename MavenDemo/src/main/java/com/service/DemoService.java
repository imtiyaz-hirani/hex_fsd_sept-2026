package com.service;

import com.exception.EmptyListException;
import com.exception.InvalidInputException;

import java.util.List;
import java.util.stream.Collectors;

public class DemoService {

    public int sum(int x, int y){
        return x+y;
    }

    /*
    * If percent >= 75: A
    * percent >= 60 : B
    * else C
    *
    * marks per subject are out of 100
    * */
    public String computeGrade(List<Double> list){ //[44,66,77,87] : total : numSubjects
        // Focus on validation -- second
        if(list == null)
            throw new NullPointerException("List cannot be null");

        if(list.isEmpty())
            throw new EmptyListException("List has no elements");

        long incorrectEntries = list
                                    .stream()
                                    .filter(e-> e>100 || e<0)
                                    .count();

        if(incorrectEntries > 0)
            throw new InvalidInputException("Marks have to be between 0 and 100");

        // Focus on functionality --- First
        double totalMarks = list
                            .stream()
                            .mapToDouble(m -> m)
                            .sum();
        int numSubjects = list.size();

        double percent = totalMarks / numSubjects ;

        if(percent >= 75)
            return "A";
        if(percent >= 60)
            return "B";
        return "C";
    }
}
