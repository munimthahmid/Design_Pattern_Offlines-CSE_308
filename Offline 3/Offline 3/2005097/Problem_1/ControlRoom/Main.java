package Problem_1.ControlRoom;

import Problem_1.PassengerDecorator.ImposterDecorator;
import Problem_1.Passengers.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Passengers passengers = null;

        while (true) {
            String command = scanner.nextLine();
            switch (command.split(" ")[0].toLowerCase()) {
                case "work":
                    passengers.work();
                    break;
                case "repair":
                    passengers.repair();
                    break;
                case "login":
                    passengers = new Crewmates();
                    String name = command.split(" ")[1];
                    if (name.contains("imp")) {
                        passengers = new ImposterDecorator(passengers);
                    }
                    passengers.login();
                    break;
                case "logout":
                    passengers.logout();
                    passengers = null;
                    break;

                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }

    }


}

