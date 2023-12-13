import java.beans.PropertyChangeEvent;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.ReservationDAO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import response.ReservationResponse;
import response.SelectDoctorResponse;

public class ReservationUI extends JFrame {
	private JPanel contentPane;
	private int departmentNum = 10; //부서 숫자
    private ReservationDAO dao = new ReservationDAO();
	private int doctorNum =10; //의사숫자
	private JTextField textField;
	private JTextField textField_1;
	private String hour[] = {"08", "09", "10","11","12", "14","15","16","17","18"};
	private String min[] = {"00","30"};

    private int doctorIdToReserve = 0;
    private int patientIdToReserve = 0;
	
    public ReservationUI(){
    	setTitle("DataBaseTeam2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("1.진료과 선택");
        lblNewLabel.setBounds(56, 27, 80, 15);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("2.의료진 선택");
        lblNewLabel_1.setBounds(206, 27, 80, 15);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("3.예약 날짜/시간 선택");
        lblNewLabel_2.setBounds(397, 27, 118, 15);
        contentPane.add(lblNewLabel_2);
        JRadioButton[] doctorBtn = new JRadioButton[doctorNum];
        
        JPanel selectedDepartment = new JPanel();
        selectedDepartment.setBounds(22, 57, 144, 385);
        contentPane.add(selectedDepartment);
        selectedDepartment.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        ButtonGroup bg1 = new ButtonGroup();
        JRadioButton[] departmentBtn = new JRadioButton[departmentNum];
        
        JPanel selectedDoctor = new JPanel();
        selectedDoctor.setBounds(176, 59, 144, 305);
        contentPane.add(selectedDoctor);
        selectedDoctor.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        List<String> departments = dao.getDepartments();
        //진료과 버튼 생성
        for(int i = 0; i < departments.size(); i++) {
        	departmentBtn[i] = new JRadioButton(departments.get(i));
        	departmentBtn[i].setFont(new Font("굴림", Font.BOLD, 20));
        	selectedDepartment.add(departmentBtn[i]);
        	bg1.add(departmentBtn[i]);

        	//진료과 라디오 버튼 클릭시 효과
        	departmentBtn[i].addActionListener(e -> {
                ButtonGroup bg2 = new ButtonGroup();
                selectedDoctor.removeAll();
                String s = "\"" + e.getActionCommand() + "\"";

                List<SelectDoctorResponse> doctors = dao.selectDoctor(dao.getDeptIdForName(s));
                doctorNum = doctors.size();

                for(int i1 = 0; i1 < doctors.size(); i1++) {
                    doctorBtn[i1] = new JRadioButton(doctors.get(i1).getDoctorName());
                    doctorBtn[i1].setFont(new Font("굴림", Font.BOLD, 20));
                    selectedDoctor.add(doctorBtn[i1]);
                    bg2.add(doctorBtn[i1]);

                    doctorBtn[i1].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = "\"" + e.getActionCommand() + "\"";
                            doctorIdToReserve = dao.getDoctorIdForDoctorName(name);
                            System.out.println("doctor ID: " + doctorIdToReserve);
                            List<ReservationResponse> reservations = dao.isCanReservation(doctorIdToReserve);

                            for (ReservationResponse r : reservations){
                                System.out.println("doctor ID: " + r.getDoctorName() +
                                        " can Reserve at " + r.getSchedule());
                            }
                        }
                    });
                }
                repaint();
                setVisible(true);
            });
        }
        
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        JPanel selectedDate = new JPanel();
        selectedDate.setBounds(357, 57, 209, 335);
        contentPane.add(selectedDate);
        selectedDate.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

        selectedDate.add(datePicker);
        
        JLabel lblNewLabel_5 = new JLabel("예약시간");
        selectedDate.add(lblNewLabel_5);
        
        JComboBox comboBox = new JComboBox(hour);
        selectedDate.add(comboBox);
        
        JComboBox comboBox_1 = new JComboBox(min);
        selectedDate.add(comboBox_1);
        
        JLabel lblNewLabel_3 = new JLabel("예약자 이름");
        selectedDate.add(lblNewLabel_3);
        
        textField = new JTextField();
        selectedDate.add(textField);
        textField.setColumns(20);
        
        JLabel lblNewLabel_4 = new JLabel("연락처");
        selectedDate.add(lblNewLabel_4);
        
        textField_1 = new JTextField();
        selectedDate.add(textField_1);
        textField_1.setColumns(20);
        
        JButton reservationBtn = new JButton("예약하기");
        selectedDate.add(reservationBtn);
        reservationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = model.getYear() + "-" +
                        (model.getMonth() + 1) + "-" +
                        model.getDay() + " " +
                        comboBox.getSelectedItem() + ":" +
                        comboBox_1.getSelectedItem() + ":00";
                String patientName = "\"" + textField.getText() + "\"";
                patientIdToReserve = dao.getPatientIdForPatientName(patientName);
                if (patientIdToReserve == 0) {
                    JOptionPane.showMessageDialog(null,
                            "존재하지 않는 ID입니다.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(dao.insertSchedule(doctorIdToReserve, Timestamp.valueOf(s)))
                        dao.insertReservation(doctorIdToReserve, patientIdToReserve, Timestamp.valueOf(s));
                }
            }
        });
    }
    /*
    public static void main(String[] args) {
    	ReservationUI reservation = new ReservationUI();
    	reservation.show();
    }
    */
}
