package Problem_1.Passengers;

public class Crewmates implements Passengers {
    @Override
    public void repair() {
        System.out.println("Repairing the spaceship.");
    }

    @Override
    public void work() {
        System.out.println("Doing research.");
    }

    @Override
    public void login() {
        System.out.println("Welcome Crewmate!");
    }

    @Override
    public void logout() {
        System.out.println("Bye Bye crewmate.");
    }
}
