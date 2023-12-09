package response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SelectDoctorResponse {
    private int doctorId;
    private String doctorName;
    private String phoneNumber;
}
