package response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class MedicineResponse {
    private String medicineName;
    private Date dateMedicine;
    private String usageMedicine;
    private int dailyDosage;
    private int onceDosage;
    private int totalDosage;
}
