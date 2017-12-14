import java.util.Random;

public class PlayingField {

    private static final int fieldSize = 7;

    private Cell[][] mineField;

    public PlayingField() {
        mineField = new Cell[fieldSize][fieldSize];
    }

    public void drawPlayingField() {

        // draw header on x axis.
        System.out.print("\t");
        for (int headerX = 0; headerX < fieldSize; headerX++) {
            System.out.print(headerX + "\t");
        }
        System.out.println();

        // draw cell rows.
        for (int y = 0; y < fieldSize; y++) {
            System.out.print(y + "\t");

            for (int x = 0; x < fieldSize; x++) {
                Cell cell = mineField[x][y];

                if (cell.isMarked) {
                    System.out.print("M\t");
                }
                else if (cell.isRevealed) {
                    System.out.print(cell.value + "\t");
                }
                else {
                    System.out.print("■\t");
                }
            }

            System.out.println();
        }
    }

    public void createField() {
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                mineField[x][y] = new Cell();
            }
        }
    }

    public void placeBombs() {

        int amountOfBombs = fieldSize * fieldSize / 10 + 4;

        for (int i = 0; i < amountOfBombs; i++) {
            Random rand = new Random();
            int x = rand.nextInt(fieldSize);
            int y = rand.nextInt(fieldSize);

            boolean isOccupied = mineField[x][y].isBomb;

            if (!isOccupied) {
                Cell cell = mineField[x][y];
                cell.isBomb = true;
            } else {
              amountOfBombs++;
            }
        }
    }

    public void calculateValues() {
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                setValueForCell(x, y);
            }
        }
    }

    public void setValueForCell(int x, int y) {
        Cell cell = mineField[x][y];

        int totalValue = 0;

        if (y > 0) {
            Cell cellAbove = mineField[x][y - 1];
            totalValue += cellAbove.isBomb ? 1 : 0;
        }
        if (y != fieldSize - 1) {
            Cell cellBelow = mineField[x][y + 1];
            totalValue += cellBelow.isBomb ? 1 : 0;
        }
        if (x > 0) {
            Cell cellLeft = mineField[x - 1][y];
            totalValue += cellLeft.isBomb ? 1 : 0;
        }
        if (x != fieldSize - 1) {
            Cell cellRight = mineField[x + 1][y];
            totalValue += cellRight.isBomb ? 1 : 0;
        }

        // TODO: corners.

        cell.value = totalValue;
    }

    public boolean isCellABomb(int x, int y) {
        return mineField[x][y].isBomb;
    }

    public void chooseCell(int x, int y) {
        Cell cell = mineField[x][y];
        cell.isRevealed = true;
    }

    public void markCell(int x, int y) {
        Cell cell = mineField[x][y];
        cell.isMarked = true;
    }
}
