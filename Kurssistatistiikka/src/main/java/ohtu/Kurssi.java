package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Kurssi {
    private String name;
    private String term;
    List<Integer> exercises;
    List<WeeklyReturn> weeklyReturnStats;

    public Kurssi(String nimi, String term, ArrayList<Integer> exercises) {
        this.name = nimi;
        this.term = term;
        this.exercises = exercises;
        this.weeklyReturnStats = new ArrayList<WeeklyReturn>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public String getTerm() {
        return term;
    }

    public String toString() {
        return this.name + ", " + this.term;
    }

    public int getWeeklyMax(int week) {
        return this.exercises.get(week - 1);
    }

    public void addWeeklyreturn(WeeklyReturn weeklyReturn) {
        if (this.weeklyReturnStats == null) {
            this.weeklyReturnStats = new ArrayList<WeeklyReturn>();
        }
        this.weeklyReturnStats.add(weeklyReturn);
    }


    public int getAllSubmissions() {
        int submissions = 0;

        for (WeeklyReturn w : this.weeklyReturnStats) {
            submissions += w.getStudents();
        }
        return submissions;

    }

    public int getAllDoneExercises() {
        int exercises = 0;

        for (WeeklyReturn w : this.weeklyReturnStats) {
            exercises += w.getExercise_total();
        }

        return exercises;
    }
}
