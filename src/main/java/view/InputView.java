package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final InputView instance = new InputView();

    private InputView() {}

    public static InputView getInstance() {
        return instance;
    }

    private static final Scanner SCANNER = new Scanner(System.in);

    public String askExpression() {
        return askMessage("계산할 수식을 입력해 주세요. 숫자와 연산자 사이에 공백을 입력해야 합니다.");
    }

    public int askPriceToPay() {
        String input = askMessage("구입 금액을 입력해 주세요.");

        try {
            return Integer.parseInt(input);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구매 금액은 숫자만 입력 가능합니다.");
        }
    }

    public Integer askManualPurchaseAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return SCANNER.nextInt();
    }

    public List<String> askManualPurchaseNumbers(int amount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        ArrayList<String> manualNumbers = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            manualNumbers.add(SCANNER.nextLine());
        }

        return manualNumbers;
    }

    public String askWinnerLottoNumber() {
        return askMessage("지난 주 당첨 번호를 입력해 주세요.(예 - 1, 3, 5, 7, 9, 11)");
    }

    public String askBonusNumber() {
        return askMessage("보너스 볼을 입력해 주세요.");
    }

    private String askMessage(String message) {
        System.out.println(message);

        return SCANNER.nextLine();
    }
}
