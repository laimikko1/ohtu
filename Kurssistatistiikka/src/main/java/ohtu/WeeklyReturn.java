package ohtu;

public class WeeklyReturn {
    private Integer students;
    private Integer hour_total;
    private Integer exercise_total;

    public WeeklyReturn(Integer students, Integer hour_total, Integer exercise_total) {
        this.students = students;
        this.hour_total = hour_total;
        this.exercise_total = exercise_total;
    }

    public Integer getStudents() {
        return students;
    }

    public void setStudents(Integer students) {
        this.students = students;
    }

    public Integer getHour_total() {
        return hour_total;
    }

    public void setHour_total(Integer hour_total) {
        this.hour_total = hour_total;
    }

    public Integer getExercise_total() {
        return exercise_total;
    }

    public void setExercise_total(Integer exercise_total) {
        this.exercise_total = exercise_total;
    }
}
