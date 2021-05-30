import main.java.org.example.models.DateProvider;
import main.java.org.example.models.FakeDateProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeDateProviderTest {
    @Test
    void itShould_VerifyGivenDateTime_shouldBeTrue() {
        //Given
        LocalDate localDate = LocalDate.of(1500,1, 4);
        DateProvider dateProvider = new FakeDateProvider(localDate);
        //When
        LocalDate result = dateProvider.GetCurrentDate();
        //Then
        assertTrue(localDate.isEqual(result));
    }
}