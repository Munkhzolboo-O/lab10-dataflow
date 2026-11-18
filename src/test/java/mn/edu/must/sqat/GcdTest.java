
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GcdTest {

    @Test
    void gcd_zeroIterations_x13_to20_y13_to15() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    void gcd_oneIteration_x13_to16_y13_to17_tmp16_to18() {
        assertEquals(5, DataFlowTargets.gcd(10, 5));
    }

    @Test
    void gcd_multipleIterations_x17_to16_y18_to17() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}
