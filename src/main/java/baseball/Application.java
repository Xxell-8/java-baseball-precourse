package baseball;
import nextstep.utils.Randoms;
import nextstep.utils.Console;

public class Application {
    // 랜덤 넘버 생성
    public static void pickNumbers(int[] answer) {
        // 1. 중복 체크를 위한 visited 배열 초기화
        boolean[] selected = new boolean[10];

        // 2-1. 3개의 숫자가 뽑힐 때까지 반복
        int i = 0;
        while (i < 3) {
            // 2-2. 랜덤 숫자를 하나씩 추출하고
            int num = Randoms.pickNumberInRange(1, 9);
            // 2-3. 해당 숫자를 사용하지 않았으면 추가 후 다음 숫자로 이동
            if (!selected[num]) {
                answer[i] = num;
                selected[num] = true;
                i++;
            }
        }
    }

    // 사용자 입력 유효성 검사
    public static boolean isValid(int[] userInput) {
        // 1-1. 사용자 입력 받아서 배열로 변환
        // cf. println은 줄바꿈이 생기고, print는 안 생긴다.
        System.out.print("숫자를 입력해 주세요 : ");
        char[] input = Console.readLine().toCharArray();

        // 1-2. 중복 값 확인을 위한 used 초기화
        boolean[] used = new boolean[10];

        // 2-1. 사용자 입력 값의 길이가 3인지 확인
        if (input.length != 3) {
            System.out.println("[ERROR] 3자리의 숫자를 입력해 주세요.");
            return false;
        }

        // 2-2. 입력 값을 한 자리씩 확이하면서
        for (int i = 0; i < 3; i++) {
            // 2-2-1. 입력 값이 모두 숫자인지 확인
            if (!Character.isDigit(input[i])) {
                System.out.println("[ERROR] 1부터 9까지 서로 다른 수를 입력해 주세요.");
                return false;
            }
            // 아스키 코드 값으로 정수 변환해 userInput에 할당
            userInput[i] = input[i] - '0';

            // 2-2-2. 입력 값이 1~9 범위에 있는지 확인
            // 2-2-3. 중복되는 숫자가 없는지 확인
            if (userInput[i] < 1 || used[userInput[i]]) {
                System.out.println("[ERROR] 1부터 9까지 서로 다른 수를 입력해 주세요.");
                return false;
            } else {
                used[userInput[i]] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
    }
}