package ua.andrew.hellocity.time;

public enum TimesOfDay {
    MORNING("morning"), DAY("day"), EVENING("evening"), NIGHT("night");

    public String getTimeOfDay() {
        return timeOfDay;
    }

    TimesOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;

    }

    private String timeOfDay;
}