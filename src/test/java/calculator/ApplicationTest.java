package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 빈_입력_테스트() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 커스텀_구분자_없는_정상_입력_테스트_1() {
        assertSimpleTest(() -> {
            run("1,2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 커스텀_구분자_없는_정상_입력_테스트_2() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_없는_정상_입력_테스트_3() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_있는_정상_입력_테스트_1() {
        assertSimpleTest(() -> {
            run("//;\\n1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_있는_정상_입력_테스트_2() {
        assertSimpleTest(() -> {
            run("//;\\n1,2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_있는_정상_입력_테스트_3() {
        assertSimpleTest(() -> {
            run("// \\n1,2 3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 음수_입력_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자가_쉼표_콜론인_경우_1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//,\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자가_쉼표_콜론인_경우_2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//:\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자가_두글자_이상인_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//?!\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자가_0글자인_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자로_지정되지_않은_문자가_식에_존재하는_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//?\\n1,2!3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자_지정_양식이_틀린_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("/?/\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자를_가진_문자열의_시작_혹은_끝이_구분자일_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//?\\?n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_덧셈_대상이_숫자가_아닌_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_덧셈_대상이_소수인_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2.3,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 공백이_커스텀_구분자인_경우() {
        assertSimpleTest(() -> {
            run("// \\n1 2 3");
            assertThat(output()).contains("결과 : 6");
        });
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
