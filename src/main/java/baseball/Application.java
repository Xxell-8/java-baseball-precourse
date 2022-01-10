package baseball;
import nextstep.utils.Randoms;

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

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
    }
}