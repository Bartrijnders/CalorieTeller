import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

public class SystemDateProvider implements DateProvider {

    private final Clock clock;

    public SystemDateProvider(Clock clock) {
        this.clock = clock;
    }

    @Override
    public LocalDate GetCurrentDate() {
        return LocalDate.now(clock);
    }
}
