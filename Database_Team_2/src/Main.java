import DAO.ReadingDiagnosisDAO;
import response.DiagnosisResponse;
import response.InjectionResponse;
import response.MedicineResponse;

import java.util.List;



public class Main {
    public static void main(String[] args) {
        int userId = 17;
        int diagnosisIdForUserId = 0;
        int iterator = 1;
        ReadingDiagnosisDAO uv = new ReadingDiagnosisDAO();

        System.out.println("### USER ID로 진단서 HEADER 부분 검색 ###");
        List<DiagnosisResponse> responses = uv.getDiagnosisWithPatientId(17);
        for (DiagnosisResponse r : responses){
            System.out.print(iterator + ": ");
            System.out.println("Diagnosis ID: " + r.getDiagnosisId() +
                    ", Doctor Name: " + r.getDoctorName() +
                    ", Patient Name: " + r.getPatientName() +
                    ", Date: " + r.getDate());
            // 클릭 이벤트 설정(UI 만들고 할 부분)
            diagnosisIdForUserId = r.getDiagnosisId();
            iterator++;
        }

        System.out.println("\n### USER ID로부터 진단서 ID 받기: " + diagnosisIdForUserId + " ###");

        System.out.println("\n### 진단서 ID로 진단서 DETAIL 부분 검색 ###");
        // 1 부분에 diagnosisIdForUserId 넣으면 됨
        List<String> res = uv.getDiseaseForDiagnosisId(1);
        iterator = 1;
        for (String s : res){
            System.out.print(iterator + ": ");
            System.out.println("Disease Name : " + s);
            iterator++;
        }

        List<MedicineResponse> medicines = uv.getMedicineWithDiagnosisId(1);
        iterator = 1;
        for (MedicineResponse r : medicines){
            System.out.print(iterator + ": ");
            System.out.println("Medicine Name: " + r.getMedicineName() +
                    ", Usage Medicine: " + r.getUsageMedicine() +
                    ", Date: " + r.getDateMedicine() +
                    ", Daily Dosage: " + r.getDailyDosage() +
                    ", Once Dosage: " + r.getOnceDosage() +
                    ", Total Dosage: " + r.getTotalDosage());
            iterator++;
        }
        ReservationUI reservationUI = new ReservationUI();
        reservationUI.show();

        List<InjectionResponse> injections = uv.getInjectionWithDiagnosisId(1);
        iterator = 1;
        for (InjectionResponse r : injections){
            System.out.print(iterator + ": ");
            System.out.println("Injection Name: " + r.getInjectionName() +
                    ", Usage Injection: " + r.getUsageInjection() +
                    ", Date: " + r.getDateInjection() +
                    ", Daily Dosage: " + r.getDailyDosage() +
                    ", Once Dosage: " + r.getOnceDosage() +
                    ", Total Dosage: " + r.getTotalDosage());
            iterator++;
        }

        /** Test for Reservation Query */
//        ReservationDAO reservationDAO = new ReservationDAO();
//        try {
//            /* 시나리오 1: 부서 선택(UI) -> 의사 선택 */
//            List<SelectDoctorResponse> doctors = reservationDAO.selectDoctor(1);
//            for (SelectDoctorResponse r : doctors){
//                System.out.println("Doctor ID: " + r.getDoctorId() +
//                        ", Doctor Name: " + r.getDoctorName() +
//                        ", Phone Number: " + r.getPhoneNumber());
//            }
//
//            /* 시나리오 2: 의료진 예약 가능 시간 확인 */
//            List<ReservationResponse> res = reservationDAO.isCanReservation(2);
//            for (ReservationResponse r : res){
//                System.out.println("Doctor Name: " + r.getDoctorName()
//                + ", Schedule Time: " + r.getSchedule());
//            }
//
//            /* 시나리오 3, 4: 의료진 예약 및 예약한 시간 삭제 */
//            reservationDAO.insertReservation(2, 24, Timestamp.valueOf("2023-12-28 19:00:00"));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

/*
        Connection connection = ConnectionDB.getConnection();

        UserView user = new UserView();
        //유저 아이디로 해당 Diagnosis 검색
        System.out.println("유저 아이디로 진단서 검색");
        int i = 1;
        for (ArrayList<Object> entry : user.setDiagnosis(connection,17)) {
            System.out.print(i+":\t");
            for (Object value : entry) {
                System.out.print(value + "\t");
            }
            System.out.println();
            i++;
        }

        int d = (int) user.setDiagnosis(connection,17).get(0).get(0);
        i = 1;
        //diagnosis_id 처방 지병명 검색
        System.out.println("유저 아이디로 병명 검색");

        for (String entry : user.setDisease(connection,d)) {
            System.out.println(i+":\t"+entry);
            i++;
        }

        //diagnosis_id로 처방 받은 약 검색
        Connection c = ConnectionDB.getConnection();
        System.out.println("진단서 아이디로 처방약 검색");
        int i = 1;
        for (ArrayList<Object> entry : uv.setMedicine(c, i)) {
            System.out.print(i+":\t");
            for (Object value : entry) {
                System.out.print(value + "\t");
            }
            System.out.println();
            i++;
        }
        //diagnosis_id로 처방 받은 주사제 검색
        System.out.println("진단서 아이디로 주사제 검색");
        i = 1;
        for (ArrayList<Object> entry : user.setInjection(connection,d)) {
            System.out.print(i+":\t");
            for (Object value : entry) {
                System.out.print(value + "\t");
            }
            System.out.println();
            i++;
        }
*/

    }
}
