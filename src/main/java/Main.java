import java.time.Clock;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
       DateProvider dateProvider = new SystemDateProvider(Clock.systemDefaultZone());
        System.out.println(dateProvider.GetCurrentDate());
    }
}
