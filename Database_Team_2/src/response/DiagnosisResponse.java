package response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Builder
public class DiagnosisResponse {
    private int diagnosisId;
    private String doctorName;
    private String patientName;
    private Timestamp date;
}
