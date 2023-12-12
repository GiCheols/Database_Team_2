package DAO;

import response.DiagnosisResponse;
import response.InjectionResponse;
import response.MedicineResponse;

import java.sql.*;
import java.util.*;

public class ReadingDiagnosisDAO {
    private final Connection CONNECTION = ConnectionDB.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet result = null;

    // 시나리오 1: 환자가 앓고 있는 질병 정보를 가져옴
    // 진단서 ID와 의사 이름, 환자 이름, 날짜 정보 가져오기
    // 즉, 환자의 아이디로 "환자가 갖고 있는 모든 진단서의 헤더 정보"를 가져옴
    // 진단서 미리보기 부분에서 보일 부분
    public List<DiagnosisResponse> getDiagnosisWithPatientId(int userId){
        String sql = "select diagnosis_id, d.name, p.name, date " +
                "from diagnosis, doctor d, patient p " +
                "where p.id = diagnosis.patient_id " +
                "and d.id = diagnosis.doctor_id " +
                "and p.id = ?";
        List<DiagnosisResponse> res = new ArrayList<>();
        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                DiagnosisResponse diagnosis = DiagnosisResponse
                        .builder()
                        .diagnosisId(result.getInt("diagnosis_id"))
                        .doctorName(result.getString("d.name"))
                        .patientName(result.getString("p.name"))
                        .date(result.getTimestamp("date")).build();

                res.add(diagnosis);
            }
            return res;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    // 시나리오 2: 환자가 갖고 있는 진단서 목록 중 하나를 클릭 함
    // 해당 진단서에 포함된 질병을 가져옴
    public List<String> getDiseaseForDiagnosisId(int diagnosisId){
        String sql = "select disease_code.disease_name " +
                "from disease, diagnosis, disease_code " +
                "where diagnosis.diagnosis_id = ? " +
                "and disease.diagnosis_id = diagnosis.diagnosis_id " +
                "and disease.id = disease_code.disease_id";
        List<String> diseases = new ArrayList<>();

        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, diagnosisId);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                String disease = result.getString("disease_code.disease_name");
                diseases.add(disease);
            }
            return diseases;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<MedicineResponse> getMedicineWithDiagnosisId(int diagnosisId){
        String sql = "SELECT * " +
                "FROM medicine m, diagnosis d, medicine_type mt " +
                "where m.medicine_diagnosis_id = d.diagnosis_id " +
                "and m.medicine_name = mt.medicine_name " +
                "and d.diagnosis_id = ?";
        List<MedicineResponse> responses = new ArrayList<>();

        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, diagnosisId);
            result = preparedStatement.executeQuery();

            while (result.next()){
                MedicineResponse res = MedicineResponse
                        .builder()
                        .medicineName(result.getString("medicine_name"))
                        .usageMedicine(result.getString("mt.usage_medicine"))
                        .dateMedicine(result.getDate("date_medicine"))
                        .dailyDosage(result.getInt("daily_dosage"))
                        .onceDosage(result.getInt("once_dosage"))
                        .totalDosage(result.getInt("total_dosage")).build();
                responses.add(res);
            }
            return responses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<InjectionResponse> getInjectionWithDiagnosisId(int diagnosisId){
        String sql = "SELECT * " +
                "FROM injection i, diagnosis d, injection_type it " +
                "where i.injection_diagnosis_id = d.diagnosis_id " +
                "and i.injection_name = it.injection_name " +
                "and d.diagnosis_id = ?";
        List<InjectionResponse> responses = new ArrayList<>();

        try {
            preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setInt(1, diagnosisId);
            result = preparedStatement.executeQuery();

            while (result.next()){
                InjectionResponse res = InjectionResponse
                        .builder()
                        .usageInjection(result.getString("usage_injection"))
                        .injectionName(result.getString("injection_name"))
                        .dateInjection(result.getDate("date_injection"))
                        .dailyDosage(result.getInt("daily_dosage"))
                        .onceDosage(result.getInt("once_dosage"))
                        .totalDosage(result.getInt("total_dosage")).build();
                responses.add(res);
            }
            return responses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
