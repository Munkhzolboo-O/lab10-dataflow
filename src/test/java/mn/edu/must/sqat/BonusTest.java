
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusTest {

    @Test
    void bonus_noConditionsMet_returnsZero() {
        int[] attendance = {1, 0, 1, 0};

        int result = DataFlowTargets.bonus(attendance, 3);

        assertEquals(0, result);
    }

@Test
void bonus_thresholdMet_returnsFive() {
    int[] attendance = {1, 0, 1, 0};

    int result = DataFlowTargets.bonus(attendance, 2);

    assertEquals(5, result);
}

@Test
void bonus_fiveConsecutive_returnsThree() {
    int[] attendance = {1, 1, 1, 1, 1};

    int result = DataFlowTargets.bonus(attendance, 6);

    assertEquals(3, result);
}

@Test
void bonus_bothConditionsMet_returnsEight() {
    int[] attendance = {1, 1, 1, 1, 1};

    int result = DataFlowTargets.bonus(attendance, 5);

    assertEquals(8, result);
}

@Test
void bonus_emptyAttendance_returnsZero() {
    int[] attendance = {};

    int result = DataFlowTargets.bonus(attendance, 1);

    assertEquals(0, result);
}

@Test
void bonus_nullAttendance_throwsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> DataFlowTargets.bonus(null, 3)
    );
}


@Test
void bonus_negativeThreshold_throwsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> DataFlowTargets.bonus(new int[]{1, 1}, -1)
    );
}

@Test
void bonus_streakReset_returnsZero() {
    int[] attendance = {1, 1, 0, 1, 1, 1};

    assertEquals(0, DataFlowTargets.bonus(attendance, 7));
}

}
