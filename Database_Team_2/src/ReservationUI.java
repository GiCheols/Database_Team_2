import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReservationUI extends JFrame {
    private JPanel contentPane;
    public ReservationUI(){
        setTitle("DataBaseTeam2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel SelectDepartment = new JPanel();
        SelectDepartment.setBounds(10, 10, 130, 453);
        contentPane.add(SelectDepartment);
        SelectDepartment.setLayout(new BoxLayout(SelectDepartment, BoxLayout.Y_AXIS));

        ButtonGroup bg = new ButtonGroup();

        JRadioButton Radio = new JRadioButton("이비인후과");
        Radio.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(Radio);
        bg.add(Radio);
        JRadioButton Radio1 = new JRadioButton("피부과");
        Radio1.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(Radio1);
        bg.add(Radio1);
        JRadioButton Radio2 = new JRadioButton("산부인과");
        Radio2.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(Radio2);
        bg.add(Radio2);
        JRadioButton Radio3 = new JRadioButton("신경과");
        Radio3.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(Radio3);
        bg.add(Radio3);
        JRadioButton Radio4 = new JRadioButton("성형외과");
        Radio4.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(Radio4);
        bg.add(Radio4);
        JRadioButton rdbtnNewRadioButton = new JRadioButton("내과");
        rdbtnNewRadioButton.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(rdbtnNewRadioButton);
        bg.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("신경외과");
        rdbtnNewRadioButton_1.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(rdbtnNewRadioButton_1);
        bg.add(rdbtnNewRadioButton_1);

        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("흉부외과");
        rdbtnNewRadioButton_2.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(rdbtnNewRadioButton_2);
        bg.add(rdbtnNewRadioButton_2);

        JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("비뇨기과");
        rdbtnNewRadioButton_3.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(rdbtnNewRadioButton_3);
        bg.add(rdbtnNewRadioButton_3);

        JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("치과");
        rdbtnNewRadioButton_4.setFont(new Font("굴림", Font.BOLD, 20));
        SelectDepartment.add(rdbtnNewRadioButton_4);
        bg.add(rdbtnNewRadioButton_4);

        JPanel SelectDoctor = new JPanel();
        SelectDoctor.setBounds(144, 94, 113, 161);
        contentPane.add(SelectDoctor);
        SelectDoctor.setLayout(new BoxLayout(SelectDoctor, BoxLayout.Y_AXIS));
//		JScrollPane scrollPane = new JScrollPane(SelectDoctor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		SelectDoctor.add(scrollPane);


        JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_5);

        JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_6);

        JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_7);

        JRadioButton rdbtnNewRadioButton_8 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_8);

        JRadioButton rdbtnNewRadioButton_9 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_9);

        JRadioButton rdbtnNewRadioButton_10 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_10);

        JRadioButton rdbtnNewRadioButton_11 = new JRadioButton("New radio button");
        SelectDoctor.add(rdbtnNewRadioButton_11);

        JPanel SelectDate = new JPanel();
        SelectDate.setBounds(262, 170, 10, 10);
        contentPane.add(SelectDate);
    }
}
