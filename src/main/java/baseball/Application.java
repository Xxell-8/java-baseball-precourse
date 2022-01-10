package baseball;
import java.util.Arrays;
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

    // 사용자 입력 받기
    public static void getInput(int[] userInput) {
        boolean isValid = false;
        while (!isValid) {
            // 유효성 검사 + 올바른 입력값이 들어올 때까지 반복
            isValid = validateInput(userInput);
        }
    }

    // 사용자 입력 유효성 검사
    public static boolean validateInput(int[] userInput) {
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

    // 사용자 입력과 랜덤 넘버 비교
    public static boolean checkInput(int[] answer, int[] userInput) {
        // 1. 스트라이크, 볼 카운트 초기화
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            // 2-1. 같은 자리에 동일 숫자면 스트라이크
            if (answer[i] == userInput[i]) {
                strike++;
                // 2-2. 자리는 다르지만 포함되어 있으며 볼
            } else if (contains(answer, userInput[i])) {
                ball++;
            }
        }
        return printResult(strike, ball);
    }

    // 해당 숫자가 포함되어 있는지 확인
    private static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    // 계산 결과에 따른 출력
    public static boolean printResult(int strike, int ball) {
        // 1. 일치하는 숫자가 없는 경우
        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
            // 2. 일치하는 숫자가 있는 경우, 결과 출력
        } else {
            if (strike > 0) {
                System.out.print(strike + "스트라이크 ");
            }
            if (ball > 0) {
                System.out.print(ball + "볼");
            }
            System.out.println();

            // 3. 3스트라이크이면, 이번 게임 종료
            if (strike == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
                return true;
            }
        }
        return false;
    }

    // 게임 재시작 결정
    public static boolean restartGame() {
        while (true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String input = Console.readLine();
            if (input.equals("1")) {
                return true;
            } else if (input.equals("2")) {
                return false;
            } else {
                System.out.println("[ERROR] 1 또는 2를 입력해 주세요.");
            }
        }
    }

    public static void main(String[] args) {
        // 1. 게임 진행 확인을 위한 isPlaying 초기화
        boolean isPlaying = true;

        while (isPlaying) {
            // 2-1. 정답 숫자 3개를 담을 answer 초기화
            int[] answer = new int[3];
            // 2-2. 사용자 입력 값을 기록할 input 초기화
            int[] userInput = new int[3];

            // 3-1. 랜덤 숫자 선택
            pickNumbers(answer);
            // 3-2. 사용자 입력 받기
            do {
                getInput(userInput);
            } while (!checkInput(answer, userInput));
            // 3-3. 게입 종료 결정
            isPlaying = restartGame();
        }
    }
}