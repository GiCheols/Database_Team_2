package response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class InjectionResponse {
    private String injectionName;
    private String usageInjection;
    private Date dateInjection;
    private int dailyDosage;
    private int onceDosage;
    private int totalDosage;
}
