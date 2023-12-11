package response;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@Setter
public class ViewResponse {
    private int diagnosisId;
    private String patientName;
    private String doctorName;
    private Date date;
    private String diseaseName;
    private String medicine;
}
