import DAO.ReadingDiagnosisDAO;
import DAO.ReservationDAO;
import response.DiagnosisResponse;
import response.InjectionResponse;
import response.MedicineResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DiagnosisUI {

    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static JTextField patientIdTextField;
    private ReadingDiagnosisDAO dao = new ReadingDiagnosisDAO();

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Diagnostic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = createMainPanel();
        cardPanel.add(mainPanel, "mainPanel");

        frame.getContentPane().add(cardPanel);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null); // 화면 가운데 정렬
        frame.setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel idLabel = new JLabel("환자 아이디: ");
        patientIdTextField = new JTextField(15); // 텍스트 필드의 길이를 조절

        JButton showDiagnosticButton = new JButton("진단서 보기");
        showDiagnosticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 환자 아이디를 가져와서 활용
                try {
                    int patientId = Integer.parseInt(patientIdTextField.getText());
                    // 다음 진단서 목록을 보여주는 메서드 호출
                    showDiagnosticListForPatient(patientId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "올바른 숫자를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel idPanel = new JPanel(); // FlowLayout을 사용하는 패널
        idPanel.add(idLabel);
        idPanel.add(patientIdTextField);

        mainPanel.add(idPanel, BorderLayout.NORTH); // 상단에 위치
        mainPanel.add(showDiagnosticButton, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createDiagnosticPanel(String[] diagnosticTypes) {
        JPanel diagnosticPanel = new JPanel(new BorderLayout());

        JList<String> diagnosticList = new JList<>(diagnosticTypes);
        diagnosticList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        diagnosticList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedDiagnostic = diagnosticList.getSelectedValue();
                    if (selectedDiagnostic != null) {
                        int diagnosticId = Integer.parseInt(selectedDiagnostic
                                .substring(selectedDiagnostic.lastIndexOf(" ") + 1));
                        showDetailsPanel(diagnosticId);
                    }
                }
            }
        });

        JButton backButton = new JButton("뒤로 가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "mainPanel");
            }
        });
        diagnosticPanel.add(backButton, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(diagnosticList);
        diagnosticPanel.add(scrollPane, BorderLayout.CENTER);

        return diagnosticPanel;
    }

    private void showDetailsPanel(int diagnosticId) {
        String diagnosticDetails = getDiagnosticDetails(diagnosticId);
        JPanel detailsPanel = createDetailsPanel(diagnosticDetails);
        cardPanel.add(detailsPanel, "detailsPanel");
        cardLayout.show(cardPanel, "detailsPanel");
    }

    private String getDiagnosticDetails(int diagnosticId) {
//        System.out.println("getDiagnosticDetails: " + diagnosticId);
        List<String> diseases = dao.getDiseaseForDiagnosisId(diagnosticId);
        List<MedicineResponse> medicines = dao.getMedicineWithDiagnosisId(diagnosticId);
        List<InjectionResponse> injections = dao.getInjectionWithDiagnosisId(diagnosticId);
        StringBuilder result = new StringBuilder();

        for (String s : diseases) {
            result.append("질병 명: ").append(s + "\n");
            for (MedicineResponse mr : medicines) {
                result.append("\n처방 받은 약: ").append(mr.getMedicineName());
                result.append("\n복용 방법: ").append(mr.getUsageMedicine());
                result.append("\n복용 횟수: 하루 ").append(mr.getDailyDosage() + "회 복용");
                result.append(", 1회 " + mr.getOnceDosage() + "정 복용");
                result.append(", 총 투약 수: " + mr.getTotalDosage() + "회\n");
            }
            for (InjectionResponse ir : injections) {
                result.append("\n처방 받은 주사제: ").append(ir.getInjectionName());
                result.append("\n복용 방법: ").append(ir.getUsageInjection());
                result.append("\n복용 횟수: 하루 ").append(ir.getDailyDosage() + "회 투여");
                result.append(", 1회 " + ir.getOnceDosage() + "회 투여");
                result.append(", 총 투여 수: " + ir.getTotalDosage() + "회\n");
            }
            result.append("\n");
        }
        return result.toString();
    }

    private static JPanel createDetailsPanel(String diagnosticDetails) {
        JPanel detailsPanel = new JPanel(new BorderLayout());

        JTextArea detailsTextArea = new JTextArea(diagnosticDetails);
        detailsTextArea.setEditable(false);

        JButton backButton = new JButton("뒤로 가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "diagnosticPanel");
            }
        });

        detailsPanel.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);
        detailsPanel.add(backButton, BorderLayout.SOUTH);

        return detailsPanel;
    }

    // 다음 진단서 목록을 보여주는 메서드
    private void showDiagnosticListForPatient(int patientId) {
        List<DiagnosisResponse> responses = dao.getDiagnosisWithPatientId(patientId);
        List<String> lists = new ArrayList<>();
        for (DiagnosisResponse r : responses){
            lists.add(String.valueOf(r.getDiagnosisId()));
        }
        String[] diagnosticTypes = lists.toArray(new String[0]);

        JPanel diagnosticPanel = createDiagnosticPanel(diagnosticTypes);

        cardPanel.add(diagnosticPanel, "diagnosticPanel");
        cardLayout.show(cardPanel, "diagnosticPanel");
    }
}
