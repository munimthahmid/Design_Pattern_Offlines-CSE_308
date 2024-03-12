package Problem_1.PassengerDecorator;

import Problem_1.Passengers.Passengers;

public class PassengerDecorator implements Passengers {
    private Passengers passengers;

    PassengerDecorator(Passengers passengers)
    {
        this.passengers=passengers;
    }

    @Override
    public void repair() {
        passengers.repair();
    }

    @Override
    public void work() {
        passengers.work();
    }

    @Override
    public void login() {
        passengers.login();
    }

    @Override
    public void logout() {
        passengers.logout();
    }
}
