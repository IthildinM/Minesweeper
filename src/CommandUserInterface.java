import java.util.Scanner;

public class CommandUserInterface implements IUserInterface {

    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    public void showInputRequestMessage() {
        System.out.println("Please input:");
    }

    public void showErrorMessage() {
        System.out.println("some error sorry");
    }

    public void showGameCompleteMessage() {
        System.out.println("BOMB selected or stopped manually.");
    }

    public void showHelloMessage() {
        System.out.println("Hallo WURSCHT.");
    }
}