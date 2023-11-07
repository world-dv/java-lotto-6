package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    public void LottoStart() {
        Money money = inputMoney();
        money.printLottoCount();

        LottoNumbers lottoNumbers = new LottoNumbers();
        LottoList lottoList = new LottoList(lottoNumbers.makeLottoList(money.getLottoCount()));
        lottoList.printLottoList();

        Lotto input_lotto = inputLotto();
        List<Integer> compare_lotto = lottoList.compareLotto(input_lotto);

        BonusLotto bonus_lotto = inputBonusLotto(input_lotto);
        List<Boolean> compare_bonus_lotto = lottoList.compareBonusLotto(bonus_lotto);

        Result result = new Result(compare_lotto, compare_bonus_lotto);
        result.calculateRateOfReturn(money.getMoney());
        result.printResult();
    }

    public static Money inputMoney() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요");
            String input = Console.readLine();
            try {
                int input_money = Integer.parseInt(input);
                Money money = new Money(input_money);
                return money;
            } catch (NumberFormatException e) {
                System.out.println(ExceptionList.MONEYEXCEPTION.content());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Lotto inputLotto() {
        while (true) {
            System.out.println("\n당첨 번호를 입력해 주세요.");
            String input = Console.readLine();
            try {
                List<String> input_lotto = List.of(input.split(","));
                List<Integer> winning_lotto = input_lotto.stream().map(x -> Integer.parseInt(x))
                        .collect(Collectors.toList());
                Lotto lotto = new Lotto(winning_lotto);
                return lotto;
            } catch (NumberFormatException e) {
                System.out.println(ExceptionList.LOTTOEXCEPTION.content());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static BonusLotto inputBonusLotto(Lotto lotto) {
        while (true) {
            System.out.println("\n보너스 번호를 입력해 주세요.");
            String input = Console.readLine();
            try {
                Integer bonus = Integer.parseInt(input);
                BonusLotto bonusLotto = new BonusLotto(bonus);
                bonusLotto.validateDuplicate(lotto, bonus);
                return bonusLotto;
            } catch (NumberFormatException e) {
                System.out.println(ExceptionList.BONUSLOTTOEXCETPION.content());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}