import java.util.*;
import java.util.stream.*;


class Student {
    int id;
    String name;
    List<String> courses;
    Map<String, Integer> scores;


    Student(int id, String name, List<String> courses, Map<String, Integer> scores) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>(courses);
        this.scores = new HashMap<>(scores);
    }


    public double averageScore() {
        return scores.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }


    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Avg: %.2f", id, name, averageScore());
    }
}


public class StudentPerformanceAnalyzer {


    public static List<Student> getTopNStudents(List<Student> students, int n) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::averageScore).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }


    public static Map<String, Double> getAverageScorePerCourse(List<Student> students) {
        Map<String, List<Integer>> courseScores = new HashMap<>();
        for (Student s : students) {
            for (String course : s.courses) {
                courseScores.putIfAbsent(course, new ArrayList<>());
                int score = s.scores.getOrDefault(course, 0);
                courseScores.get(course).add(score);
            }
        }
        return courseScores.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(Integer::intValue).average().orElse(0.0)
                ));
    }


    public static Set<String> getAllUniqueCourses(List<Student> students) {
        return students.stream()
                .flatMap(s -> s.courses.stream())
                .collect(Collectors.toSet());
    }


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();


        students.add(new Student(101, "Alice",
                Arrays.asList("Math", "Physics", "Chemistry"),
                Map.of("Math", 95, "Physics", 88, "Chemistry", 92)));


        students.add(new Student(102, "Bob",
                Arrays.asList("Math", "Physics"),
                Map.of("Math", 78, "Physics", 85)));


        students.add(new Student(103, "Charlie",
                Arrays.asList("Math", "Chemistry"),
                Map.of("Math", 88, "Chemistry", 90)));


        System.out.println("Top 2 Students:");
        getTopNStudents(students, 2).forEach(System.out::println);


        System.out.println("\nAverage Scores per Course:");
        getAverageScorePerCourse(students)
                .forEach((course, avg) -> System.out.printf("%s: %.2f%n", course, avg));


        System.out.println("\nAll Unique Courses:");
        System.out.println(getAllUniqueCourses(students));
    }
}
