package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Money money = inputMoney();
        int count = money.getLottoCount();

        Lotto input_lotto = inputLotto();
        BonusLotto bonus_lotto = inputBonusLotto(input_lotto);
    }

    public static Money inputMoney() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요");
            String input = Console.readLine();
            try {
                int input_money = Integer.parseInt(input);
                try {
                    Money money = new Money(input_money);
                    return money;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 구입 금액은 정수로만 이루어져야 합니다.");
            }
        }
    }

    public static Lotto inputLotto() {
        while (true) {
            System.out.println("당첨 번호를 입력해 주세요.");
            String input = Console.readLine();
            try {
                List<String> input_lotto = List.of(input.split(","));
                List<Integer> winning_lotto = input_lotto.stream().map(x -> Integer.parseInt(x))
                        .collect(Collectors.toList());
                try {
                    Lotto lotto = new Lotto(winning_lotto);
                    return lotto;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 당첨 로또는 정수여야 합니다.");
            }
        }
    }

    public static BonusLotto inputBonusLotto(Lotto lotto) {
        while (true) {
            System.out.println("보너스 번호를 입력해 주세요.");
            String input = Console.readLine();
            try {
                Integer bonus = Integer.parseInt(input);
                try {
                    BonusLotto bonusLotto = new BonusLotto(bonus);
                    bonusLotto.validateDuplicate(lotto, bonus);
                    return bonusLotto;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 보너스 로또는 정수여야 합니다.");
            }
        }
    }
}
