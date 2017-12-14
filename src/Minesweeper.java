public class Minesweeper {

    private PlayingField playingField;

    private IUserInterface userInterface;

    private boolean isRunning;

    public Minesweeper() {
        playingField = new PlayingField();
        userInterface = new CommandUserInterface();
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.play();
    }


    private void play() {

        if (!isRunning) {
            initializePlayingField();
            isRunning = true;
        }

        userInterface.showHelloMessage();

        while(isRunning) {
            playingField.drawPlayingField();

            userInterface.showInputRequestMessage();

            String input = userInterface.readInput();

            if (input.equals("end") || input.equals("stop")) {
                endGame();
            }

            try {
                decideNextAction(input);
            } catch (NumberFormatException exception) {
                userInterface.showErrorMessage();
            }
        }
    }

    private void decideNextAction(String input) {
        String[] textParts = input.split("\\s");

        int x = Integer.parseInt(textParts[1]);
        int y = Integer.parseInt(textParts[2]);

        String action = textParts[0];
        if (action.equals("T")) {
            if (playingField.isCellABomb(x, y)) {
                endGame();
            }

            playingField.chooseCell(x, y);
        }
        else if (action.equals("M")) {
            playingField.markCell(x, y);
        }
        else {
            userInterface.showErrorMessage();
        }
    }

    private void initializePlayingField() {
        playingField.createField();
        playingField.placeBombs();
        playingField.calculateValues();
    }

    private void endGame() {
        isRunning = false;
        userInterface.showGameCompleteMessage();
    }
}
