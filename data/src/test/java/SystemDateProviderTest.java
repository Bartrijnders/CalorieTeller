import main.java.org.example.models.DateProvider;
import main.java.org.example.models.SystemDateProvider;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemDateProviderTest {

    @Test
    void itShould_GetDateFromSimulateNow_ShouldBeTrue() {
        //Given
        Instant instant = Instant.parse("2021-03-22T10:00:00Z");
        ZoneId zoneId = ZoneId.of("Europe/Amsterdam");
        Clock clock = Clock.fixed(instant,zoneId);
        DateProvider dateProvider = new SystemDateProvider(clock);
        //When
        LocalDate result = dateProvider.GetCurrentDate();
        //Then
        assertEquals(LocalDate.of(2021, 3, 22), dateProvider.GetCurrentDate());

    }
}