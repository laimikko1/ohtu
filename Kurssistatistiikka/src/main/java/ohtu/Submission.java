package ohtu;

import java.util.List;

public class Submission {
    private int week;
    private int hours;
    private List<Integer> exercises;

    public Submission(int week, int hours, List<Integer> exercises) {
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek()
    {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString()
    {
        return "Viikko " + week + ": tehtyjä tehtäviä yhteensä: " + this.exercises.size()
                + ", aikaa kului " + this.hours + ", tehdyt tehtävät " + this.exercises.toString();
    }
    
}