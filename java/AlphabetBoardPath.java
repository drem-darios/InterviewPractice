import java.util.HashMap;
import java.util.Map;

public class AlphabetBoardPath {

    public class Board {
        Map<Integer, Integer> alphabetBoard = new HashMap<>();
        int boardPointer = 0;

        public Board() {
            this.init();
        }

        private void init() {
            int a = 97;
            for (int i = 0; i < 26; i++) {
                alphabetBoard.put(i, a + i);
            }
        }

        public String getPath(Character character) {
            int targetVal = character;
            StringBuilder result = new StringBuilder();
            int storedVal = alphabetBoard.get(boardPointer);
            while(storedVal != targetVal) {
                if (isInRow(targetVal)) {
                    if (isLeft(targetVal)) {
                        result.append(movePointerLeft());
                    } else{
                        result.append(movePointerRight());
                    }
                }
                else if (isBelow(targetVal)) {
                    if ((targetVal - 97)/5 == 5 && (storedVal - 97)/5 == 4) { // Zth row so move to correct column
                        if (targetVal == 122 && storedVal != 117) { // Zth row so move to correct column
                            result.append(movePointerLeft());
                        }
                        else {
                            result.append(movePointerDown());
                        }
                    }
                    else {
                        result.append(movePointerDown());
                    }
                } else if (isAbove(targetVal)) {
                    result.append(movePointerUp());
                }
                storedVal = alphabetBoard.get(boardPointer);
            }

            return result.toString();
        }

        private boolean isInRow(int targetVal) {
            int currentVal = alphabetBoard.get(boardPointer);
            int targetRow = (targetVal - 97)/5;
            int currentRow = (currentVal - 97)/5;

            return currentRow == targetRow;
        }

        private boolean isBelow(int targetVal) {
            int belowIndex = boardPointer + 5 > 25? 25 : (boardPointer + 5);
            int belowVal = alphabetBoard.get(belowIndex);
            if (belowVal <= targetVal) {
                return true;
            }
            int currentVal = alphabetBoard.get(boardPointer);
            return (targetVal - currentVal > 0 && targetVal - currentVal <= 4) && (belowVal - targetVal <= 4);
        }

        private boolean isAbove(int targetVal) {
            int aboveIndex = boardPointer - 5 < 0? 0 : (boardPointer - 5);
            int aboveVal = alphabetBoard.get(aboveIndex);
            if (aboveVal >= targetVal) {
                return true;
            }
            int currentVal = alphabetBoard.get(boardPointer);
            return (currentVal - targetVal > 0 && currentVal - targetVal <= 4) && (targetVal - aboveVal <= 4);
        }

        private boolean isLeft(int targetVal) {
            int leftIndex = boardPointer - 1 < 0? 0 : (boardPointer - 1);
            int leftVal = alphabetBoard.get(leftIndex);
            return leftVal >= targetVal;
        }

        private boolean isRight(int targetVal) {
            int rightIndex = boardPointer + 1 > 25? 25 : (boardPointer + 1);
            int rightVal = alphabetBoard.get(rightIndex);
            return rightVal <= targetVal;
        }

        private String movePointerDown() {
            boardPointer += 5;
            if (boardPointer > 25) {
                boardPointer = 25;
            }
            return "D";
        }

        private String movePointerUp() {
            boardPointer -= 5;
            if (boardPointer < 0) {
                boardPointer = 0;
            }
            return "U";
        }

        private String movePointerLeft() {
            boardPointer -= 1;
            if (boardPointer < 0) {
                boardPointer = 0;
            }
            return "L";
        }

        private String movePointerRight() {
            boardPointer += 1;
            if (boardPointer > 25) {
                boardPointer = 25;
            }
            return "R";
        }

    }
    public String alphabetBoardPath(String target) {
        Board board = new Board();
        char[] characters = target.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            String path = board.getPath(c);
            result.append(path);
            result.append("!");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        AlphabetBoardPath alphabetBoardPath = new AlphabetBoardPath();

//        System.out.println(alphabetBoardPath.alphabetBoardPath("leet")); // DDR!UURRR!!DDD!
//        System.out.println(alphabetBoardPath.alphabetBoardPath("zb")); // DDDDD!UUUUUR!
//        System.out.println(alphabetBoardPath.alphabetBoardPath("zez")); // DDDDD!UUUUUR!
//        System.out.println(alphabetBoardPath.alphabetBoardPath("zdz")); // DDDDD!UUUUURRR!DDDDLLLD!
//        System.out.println(alphabetBoardPath.alphabetBoardPath("code")); // RR!DDRR!UUL!R!
        System.out.println(alphabetBoardPath.alphabetBoardPath("grfgruuzjrktmqkziczvhezkpjzzxrdofdsksssvqoqpvwybrfigkfekcuzqdopwkgwtajelpkpxymvzikrcyoglzejtgsgzstun")); // RR!DDRR!UUL!R!
    }
}
