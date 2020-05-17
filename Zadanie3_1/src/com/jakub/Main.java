package com.jakub;

import com.jakub.model.Course;
import com.jakub.model.Faculty;
import com.jakub.model.Student;

import java.util.ArrayList;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {

        var students = new ArrayList<Student>();
        students.add(new Student(21, "Jan", "Kowalski", Student.Gender.MALE));
        students.add(new Student(20, "Jan", "Nowak", Student.Gender.MALE));
        students.add(new Student(22, "Jan", "Zieliński", Student.Gender.MALE));
        students.add(new Student(22, "Jan", "Kacperczyk", Student.Gender.MALE));
        students.add(new Student(20, "Anna", "Zielińska", Student.Gender.FEMALE));
        students.add(new Student(19, "Agnieszka", "Kacperczyk", Student.Gender.FEMALE));
        students.add(new Student(20, "Julia", "Kowal", Student.Gender.FEMALE));
        students.add(new Student(21, "Weronika", "Nowak", Student.Gender.FEMALE));

        var courses = new ArrayList<Course>();
        courses.add(new Course("Podstawy Programowania", 4, Course.Type.LECTURE, students.subList(0, 3)));
        courses.add(new Course("Zaawansowane techniki programowania", 4, Course.Type.CLASS, students.subList(3, 7)));
        courses.add(new Course("Paradygmaty programowania", 4, Course.Type.LECTURE, students.subList(2, 5)));
        courses.add(new Course("Algorytmy i struktury danych", 4, Course.Type.LAB, students.subList(0, 2)));
        courses.add(new Course("Bazy danych", 4, Course.Type.LECTURE, students.subList(5, 8)));

        var faculties = new ArrayList<Faculty>();
        faculties.add(new Faculty("W8", courses.subList(0, 4), students.subList(0, 4)));
        faculties.add(new Faculty("W4", courses.subList(3, 5), students.subList(4, 7)));
        faculties.add(new Faculty("W11", courses.subList(1, 4), students.subList(2, 5)));

        System.out.println("Filtrowanie listy studentów, wyświetlenie jedynie kobiet.");
        students.stream()
                .filter(s -> s.getGender().equals(Student.Gender.FEMALE))
                .forEach(System.out::println);

        System.out.println("\nWypisanie dla każdego wydziału oferowanych kursów");
        faculties.forEach(f -> {
            System.out.println(f);
            f.getOfferedCourses().forEach(System.out::println);
        });

        System.out.println("\nWypisanie wydziału z nawiększą liczbę oferowanych kursów.");
        var max = faculties
                .stream()
                .max((f1, f2) -> {
                    var f1size = f1.getOfferedCourses().size();
                    var f2size = f2.getOfferedCourses().size();
                    return f1size < f2size ? -1 : f1size == f2size ? 0 : 1;
                })
                .get();
        System.out.println(max);

        System.out.println("\nWypisanie wydziału z najmniejszą liczbę oferowanych kursów.");
        var min = faculties
                .stream()
                .min((f1, f2) -> {
                    var f1size = f1.getOfferedCourses().size();
                    var f2size = f2.getOfferedCourses().size();
                    return f1size < f2size ? -1 : f1size == f2size ? 0 : 1;
                })
                .get();
        System.out.println(min);

        System.out.println("\nSrednia dlugość nazwisk studentow pogrupowanych według wieku.");
        students.stream()
                .collect(groupingBy(Student::getAge, averagingInt(s -> s.getLastName().length())))
                .forEach((age, avg) -> System.out.println(age.toString() +": " +avg.toString()));
    }
}
