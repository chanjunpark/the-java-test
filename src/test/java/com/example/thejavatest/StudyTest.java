package com.example.thejavatest;

import com.example.thejavatest.domain.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(FindSlowTestExtension.class)
class StudyTest {

    int value = 1;

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
//        Study study = new Study(-10);
//        assertNotNull(study);
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태가 DRAFT 여야 한다"); // 성능 입장에서 람다식이 조금 더 유리할 수 있음.
//        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.");

        // 여러개 테스트를 한번에 수행하고 싶을 땐 assertAll을 사용할 수 있다.
//        assertAll(
//                () -> assertNotNull(study),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태가 DRAFT 여야 한다"), // 성능 입장에서 람다식이 조금 더 유리할 수 있음.
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
//        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야 한다.", message);
        System.out.println(value++);

    }

    @Test
    @DisplayName("100ms 이상 걸리면 실패해야 한다.")
    void create_new_study_with_timeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
        });
        System.out.println(value++);
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @EnabledOnJre(JRE.JAVA_17)
    void create_new_study_on_condition() {
        Study study = new Study(10);
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태가 DRAFT 여야 한다");
        System.out.println(value++);
    }

    @DisplayName("테스트 반복하기 1부 - ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"one", "two"})
    void parameterized_test(String message) {
        System.out.println(message);
    }

    @DisplayName("테스트 반복하기 2부")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterized_test_advanced(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study_fast() {
        System.out.println(this);
    }

    @SlowTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study_slow() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println(this);
    }


    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Study 클래스로만 변환될 수 있음");
            return new Study(Integer.parseInt(source.toString()));
        }
    }
}