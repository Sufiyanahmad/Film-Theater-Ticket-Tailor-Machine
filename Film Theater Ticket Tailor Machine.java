import java.util.*;

public class FilmTheaterTicketMachine {

    static class Movie {
        String name, time;
        int totalSeats;
        int bookedSeats;

        Movie(String name, String time, int totalSeats) {
            this.name = name;
            this.time = time;
            this.totalSeats = totalSeats;
            this.bookedSeats = 0;
        }

        int getAvailableSeats() {
            return totalSeats - bookedSeats;
        }
    }

    static class Ticket {
        String customerName;
        Movie movie;

        Ticket(String customerName, Movie movie) {
            this.customerName = customerName;
            this.movie = movie;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static List<Movie> movies = new ArrayList<>();
    static List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Film Theater Ticket Tailor Machine ===");
            System.out.println("1. Add New Movie");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Booked Tickets");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View Movie List");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: addMovie(); break;
                case 2: bookTicket(); break;
                case 3: viewTickets(); break;
                case 4: cancelTicket(); break;
                case 5: viewMovies(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    static void addMovie() {
        System.out.print("Enter movie name: ");
        String name = sc.nextLine();
        System.out.print("Enter show time (e.g., 6:30 PM): ");
        String time = sc.nextLine();
        System.out.print("Enter total seats: ");
        int seats = Integer.parseInt(sc.nextLine());

        movies.add(new Movie(name, time, seats));
        System.out.println("Movie added successfully.");
    }

    static void bookTicket() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        viewMovies();
        System.out.print("Select movie number to book ticket: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;

        if (index >= 0 && index < movies.size()) {
            Movie selected = movies.get(index);
            if (selected.getAvailableSeats() > 0) {
                System.out.print("Enter customer name: ");
                String custName = sc.nextLine();
                tickets.add(new Ticket(custName, selected));
                selected.bookedSeats++;
                System.out.println("Ticket booked successfully.");
            } else {
                System.out.println("No available seats for this movie.");
            }
        } else {
            System.out.println("Invalid movie selection.");
        }
    }

    static void viewTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }

        System.out.println("\n--- Booked Tickets ---");
        int i = 1;
        for (Ticket t : tickets) {
            System.out.println(i++ + ". Customer: " + t.customerName +
                               " | Movie: " + t.movie.name +
                               " | Time: " + t.movie.time);
        }
    }

    static void cancelTicket() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets to cancel.");
            return;
        }

        viewTickets();
        System.out.print("Enter ticket number to cancel: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;

        if (index >= 0 && index < tickets.size()) {
            Ticket t = tickets.remove(index);
            t.movie.bookedSeats--;
            System.out.println("Ticket canceled successfully.");
        } else {
            System.out.println("Invalid ticket number.");
        }
    }

    static void viewMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies added yet.");
            return;
        }

        System.out.println("\n--- Movie List ---");
        int i = 1;
        for (Movie m : movies) {
            System.out.println(i++ + ". " + m.name + " | Time: " + m.time +
                               " | Available Seats: " + m.getAvailableSeats());
        }
    }
}
