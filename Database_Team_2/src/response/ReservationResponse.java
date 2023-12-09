package response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReservationResponse {
    private String doctorName;
    private Timestamp schedule;
}
