import java.time.LocalDate;

public class Event {
    public Event(LocalDate date, String category, String description) {
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.date + ": "
            + this.description
            + " (" + this.category + ")";
    }

    // getDifferenceString begins
    public String getDifferenceString(Period period) {
        StringBuilder result = new StringBuilder();

        // creating the variables for the date
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        // this if statement fixes the issue where
        // if the event is in the future it prints the
        // numbers negative
        if (period.isNegative()) {
            years = -years;
            months = -months;
            days = -days;
        }

        // if the difference is over a year it prints out the years
        if (years != 0) {
            result.append(years + " years ");
        }
        // if the months equals 0 the print excludes the months print
        if (months != 0) {
            result.append(months + " months and ");
        }
        // if the days equals 0 the print excludes the days print
        if (days != 0) {
            result.append(days + " days");
        }

        // if the date given is today, the print is "today"
        if (result.length() == 0) {
            return "today";
        }

        // if the result is in the future, system prints out "in" before the date
        // if the result is in the past, system prints out "ago" after the date (else statement)
        if (period.isNegative()) {
            result.insert(0, "in ");
        } else {
            result.append(" ago");
        }

        return result.toString();
    }

        public static void main(String[] args) {
            // first version commented out since I wanted it to
            // print out something from the future and the past
            // not only the other to test out the code
            /*
            LocalDate today = LocalDate.now();
            Event event = new Event(LocalDate.of(2000, 10, 04), "Hello World from me :D", "This was the day I was born!");
            Period difference = Period.between(event.getDate(), today);
            String result = event.getDifferenceString(difference);
            System.out.println("Event: " + event);
            System.out.println(result);
            */
            // event 1 is something from the past
            Event event1 = new Event(LocalDate.of(2000, 10, 04), "Hello World from me :D", "This was the day I was born!");
            // event 2 is something from the future
            Event event2 = new Event(LocalDate.of(2023, 05, 19), "Please don't move it anymore...", "A long awaited Sabaton consert");

            // first the system prints out the events for the user
            System.out.println("Event 1: " + event1);
            System.out.println("Event 2: " + event2);

            // to get the present date if needed
            LocalDate today = LocalDate.now();

            // printing the event 1 difference from present date
            Period difference1 = Period.between(event1.getDate(), today);
            String result1 = event1.getDifferenceString(difference1);
            System.out.println("Event 1 was " + result1);

            // printing the event 2 difference from present date
            Period difference2 = Period.between(event2.getDate(), today);
            String result2 = event2.getDifferenceString(difference2);
            System.out.println("Event 2 is " + result2);
        }


    private LocalDate date;
    private String category;
    private String description;
}