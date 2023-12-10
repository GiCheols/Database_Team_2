import java.beans.PropertyChangeEvent;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ReservationUI extends JFrame {
	private JPanel contentPane;
	private int departmentNum = 5; //부서 숫자
	private int doctorNum =10; //의사숫자
	private JTextField textField;
	private JTextField textField_1;
	private String hour[] = {"8", "9", "10","11","12", "14","15","16","17","18"};
	private String min[] = {"0","5","10","15","20","25","30","35","40","45","50","55"};
	
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
        ButtonGroup bg2 = new ButtonGroup();
        JRadioButton[] doctorBtn = new JRadioButton[doctorNum];
        /*
        JRadioButton btn1 = new JRadioButton("이비인후과");
        btn1.setFont(new Font("굴림", Font.BOLD, 20));
        panel.add(btn1);
        bg1.add(btn1);
        
        JRadioButton btn2 = new JRadioButton("피부과");
        btn2.setFont(new Font("굴림", Font.BOLD, 20));
        panel.add(btn2);
        bg1.add(btn2);
        
        JRadioButton btn3 = new JRadioButton("산부인과");
        btn3.setFont(new Font("굴림", Font.BOLD, 20));
        panel.add(btn3);
        bg1.add(btn3);
        */
        
        //진료과 버튼 생성
        for(int i = 0;i<departmentBtn.length;i++) {
        	departmentBtn[i] = new JRadioButton(i + "번 진료과");
        	departmentBtn[i].setFont(new Font("굴림", Font.BOLD, 20));
        	selectedDepartment.add(departmentBtn[i]);
        	bg1.add(departmentBtn[i]);
        	//진료과 라디오 버튼 클릭시 효과
        	departmentBtn[i].addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		selectedDoctor.removeAll();
            		doctorNum = (int)(Math.random()*10)+1; //의사 수 랜덤
            		for(int i = 0;i<doctorNum;i++) {
                    	doctorBtn[i] = new JRadioButton(i + "번 의사");
                    	doctorBtn[i].setFont(new Font("굴림", Font.BOLD, 20));
                    	selectedDoctor.add(doctorBtn[i]);
                    	bg2.add(doctorBtn[i]);
                    }
            		setVisible(true);
            	}
            	
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
        //콘솔에 날짜 값 출력
        model.addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent evt) {
        		if ("value".equals(evt.getPropertyName())) {
        			System.out.println(model.getValue());
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
