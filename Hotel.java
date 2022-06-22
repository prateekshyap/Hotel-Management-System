//a management system for hotels
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
//import javax.mail.*;
//import javax.mail.internet.*;
import java.util.*;
import oracle.sql.*;
//import javax.activation.*;

class MainHotelFrame extends JFrame implements ActionListener, ItemListener
{
	static Container container; //the container of the frame
	static CardLayout cardLayout; //cardlayout for the main panel
	static JPanel mainPanel, topPanel, loginPanel, deptPanel, newCustPanel, queryPanel, bookPanel, billPanel, signUpPanel, recoveryPanel, roomStatusPanel; //panels
	static Color darkBlue, mediumBlue, lightBlue, darkRed, darkPink, lightGrayBlue, darkGrayBlue, darkPurple, darkGreen; //colors
	static Font largeStylishFont, mediumStylishFont, largeFont, normalFont, smallFont, midSmallFont; //fonts
	static Cursor handCursor, textCursor; //cursors
	static JLabel blueBackLabel, redBackLabel, pinkBackLabel, purpleBackLabel, darkBackLabel, greenBackLabel, pureBlueBackLabel, grayBlueBackLabel, welcomeLabel, loginBackLabel, userIcon, wrongEmail, wrongPassword, wrongPhone, wrongID, wrongDOB, signUpWarn, loginWarn, recoveryWarn, wrongCustMail, wrongCustPh, wrongInfo, bookingWarn, billingWarn; //labels
	static JToggleButton lux01, lux02, lux03, lux04, lux05, lux06, lux07, lux08, lux11, lux12, lux13, lux14, lux15, lux16, lux17, lux18, lux21, lux22, lux23, lux24, lux25, lux26, lux27, lux28, lux31, lux32, lux33, lux34, lux35, lux36, lux37, lux38, lux41, lux42, lux43, lux44, lux45, lux46, lux47, lux48, lux51, lux52, lux53, lux54, lux55, lux56, lux57, lux58, lux61, lux62, lux63, lux64, lux65, lux66, lux67, lux68, lux71, lux72, lux73, lux74, lux75, lux76, lux77, lux78, lux81, lux82, lux83, lux84, lux85, lux86, lux87, lux88, lux91, lux92, lux93, lux94, lux95, lux96, lux97, lux98, suLux01, suLux02, suLux03, suLux04, suLux11, suLux12, suLux13, suLux14, suLux21, suLux22, suLux23, suLux24, suLux31, suLux32, suLux33, suLux34, suLux41, suLux42, suLux43, suLux44, suLux51, suLux52, suLux53, suLux54, suLux61, suLux62, suLux63, suLux64, suLux71, suLux72, suLux73, suLux74, suLux81, suLux82, suLux83, suLux84, suLux91, suLux92, suLux93, suLux94, pm01, pm11, pm21, pm31, pm41, pm51, pm61, pm71, pm81, pm91, pd01, pd11, pd21, pd31, pd41, pd51, pd61, pd71, pd81, pd91, ex01, ex11, ex21, ex31, ex41, ex51, ex61, ex71, ex81, ex91; //Togglebuttons
	static JButton logout, home, login, signUp, recover, submitSignUp, goToLogin, customerRegd, newBooking, availRooms, billing, enquiry, exit, userIdOk, zero, one, two, three, four, five, six, seven, eight, nine, clear, savePassword, backToLogin, register, homeCustRegd, newRegd, checkData, homeEnq, fetchDataBook, fetchDataBill, availRoomBook, bookRoomButton, homeBook, newBook, billButton, homeBill, newBill, calcGross, calcNet, calcPending; //buttons
	static JTextField username, firstName, middleName, lastName, email, phone, userid, dob, userIdRecover, custCodeRegd, custFirstNameRegd, custMidNameRegd, custLastNameRegd, custCountryRegd, custPassportRegd, custVisaRegd, custPhoneRegd, custEmailRegd, custCodeEnq, custCodeBook, custNameBook, custPhoneBook, custEmailBook, custStatusBook, custGenderBook, bookCode, roomBook, arriveBook, personsBook, relationBook, billCode, bookCodeBill, bookedRoomBill, custCodeBill, custNameBill, personsBill, arriveBill, roomRentBill, serviceBill, grossBill, gstBill, discountBill, netBill, modeBill, cardNoBill, receivedBill, pendingBill, departBill; //text fields
	static JPasswordField password, password1, password2, newPassword, retypeNewPassword; //password fields
	static JRadioButton male, female, other, custMale, custFemale, custOther; //radio buttons
	static JTextArea sixDigitCode, custResidentRegd, custOfficeRegd, customerData, custAddrBook; //textareas
	static JComboBox custAgeRegd, custMSRegd, custStatusRegd;
	static Connection conn;
	static int digitCounter = 0, sentCode = 0, checker = 0, roomBooking = 0;
	static double grossAmt = 0.0, netAmt = 0.0, pendingAmt = 0.0;
	static String correctOldId = "";

	MainHotelFrame () //Automatic constructor
	{
		container = this.getContentPane ();
		container.setLayout (null);

		//constructing the object of the card layout
		cardLayout = new CardLayout ();

/*Defining the colors, fonts, cursors*/
		darkBlue = new Color (0,128,192);
		mediumBlue = new Color (40,109,159);
		lightBlue = new Color (223,245,252);
		darkRed = new Color (93,30,12);
		darkPink = new Color (189,32,121);
		lightGrayBlue = new Color (217,232,240);
		darkGrayBlue = new Color (39,79,101);
		darkPurple = new Color (125,50,123);
		darkGreen = new Color (99,159,64);

		largeStylishFont = new Font ("French Script MT",Font.PLAIN,70);
		mediumStylishFont = new Font ("French Script MT",Font.PLAIN,35);
		largeFont = new Font ("Eras Medium ITC",Font.PLAIN,65);
		normalFont = new Font ("Eras Medium ITC",Font.PLAIN,20);
		midSmallFont = new Font ("Eras Medium ITC",Font.PLAIN,15);
		smallFont = new Font ("Eras Medium ITC",Font.PLAIN,13);

		handCursor = new Cursor (Cursor.HAND_CURSOR);
		textCursor = new Cursor (Cursor.TEXT_CURSOR);

/*Defining the backgrounds*/
		ImageIcon blueBackImage = new ImageIcon ("BLUE_BACK.png");
		ImageIcon redBackImage = new ImageIcon ("RED_BACK.png");
		ImageIcon pinkBackImage = new ImageIcon ("PINK_BACK.png");
		ImageIcon purpleBackImage = new ImageIcon ("PURPLE_BACK.png");		
		ImageIcon darkBackImage = new ImageIcon ("DARK_BACK.png");
		ImageIcon greenBackImage = new ImageIcon ("GREEN_BACK.png");
		ImageIcon pureBlueBackImage = new ImageIcon ("PUREBLUE_BACK.png");
		ImageIcon grayBlueBackImage = new ImageIcon ("GRAYBLUE_BACK.png");

		ImageIcon loginBackImage = new ImageIcon ("LOGIN_BACK.PNG");
		ImageIcon userImage = new ImageIcon ("USER.png");

		blueBackLabel = new JLabel (blueBackImage);
		blueBackLabel.setBounds (-5,0,1375,735);
		redBackLabel = new JLabel (redBackImage);
		redBackLabel.setBounds (-5,0,1375,735);
		pinkBackLabel = new JLabel (pinkBackImage);
		pinkBackLabel.setBounds (-5,0,1375,735);
		purpleBackLabel = new JLabel (purpleBackImage);
		purpleBackLabel.setBounds (-5,0,1375,735);
		darkBackLabel = new JLabel (darkBackImage);
		darkBackLabel.setBounds (-5,0,1375,735);
		greenBackLabel = new JLabel (greenBackImage);
		greenBackLabel.setBounds (-5,0,1375,735);
		pureBlueBackLabel = new JLabel (pureBlueBackImage);
		pureBlueBackLabel.setBounds (-5,0,1375,735);
		grayBlueBackLabel = new JLabel (grayBlueBackImage);
		grayBlueBackLabel.setBounds (-5,0,1375,735);

		loginBackLabel = new JLabel (loginBackImage);
		loginBackLabel.setBounds (375,125,loginBackImage.getIconWidth (), loginBackImage.getIconHeight ());

		userIcon = new JLabel (userImage);
		userIcon.setBounds (385,160,userImage.getIconWidth (), userImage.getIconHeight ());

/*Defining all the panels*/		
		//topmost panel for the brand name
		topPanel = new JPanel ();
		topPanel.setBounds (0,0,1390,100);
		topPanel.setOpaque (false);
		topPanel.setLayout (null);

		//the panel to be added on the container of the frame
		mainPanel = new JPanel ();
		mainPanel.setBounds (0,100,1390,635);
		mainPanel.setOpaque (false);
		mainPanel.setLayout (cardLayout);

		//home panel for the users of the hotel
		loginPanel = new JPanel ();
		loginPanel.setBounds (0,0,1390,635);
		loginPanel.setOpaque (false);
		loginPanel.setLayout (null);

		//panel for selecting the operation 
		deptPanel = new JPanel ();
		deptPanel.setBounds (0,0,1390,635);
		deptPanel.setOpaque (false);
		deptPanel.setLayout (null);

		//panel for new customer registration
		newCustPanel = new JPanel ();
		newCustPanel.setBounds (0,0,1390,635);
		newCustPanel.setOpaque (false);
		newCustPanel.setLayout (null);

		//panel for booking
		bookPanel = new JPanel ();
		bookPanel.setBounds (0,0,1390,635);
		bookPanel.setOpaque (false);
		bookPanel.setLayout (null);

		//panel for enquiry
		queryPanel = new JPanel ();
		queryPanel.setBounds (0,0,1390,635);
		queryPanel.setOpaque (false);
		queryPanel.setLayout (null);

		//panel for billing
		billPanel = new JPanel ();
		billPanel.setBounds (0,0,1390,635);
		billPanel.setOpaque (false);
		billPanel.setLayout (null);

		//panel for signup of the new users
		signUpPanel = new JPanel ();
		signUpPanel.setBounds (0,0,1390,635);
		signUpPanel.setOpaque (false);
		signUpPanel.setLayout (null);

		//panel for recovery of the password if forgotten
		recoveryPanel = new JPanel ();
		recoveryPanel.setBounds (0,0,1390,635);
		recoveryPanel.setOpaque (false);
		recoveryPanel.setLayout (null);

		//room status panel
		roomStatusPanel = new JPanel ();
		roomStatusPanel.setBounds (0,0,1390,635);
		roomStatusPanel.setOpaque (false);
		roomStatusPanel.setLayout (null);

/*Defining all the components of topPanel*/
		//welcome Label
		welcomeLabel = new JLabel ("!!!Welcome to our hotel!!!");
		welcomeLabel.setFont (largeStylishFont);
		welcomeLabel.setForeground (Color.BLACK);
		welcomeLabel.setBounds (400,-20,1000,100);

		//logout button
		logout = new JButton ("Log out");
		logout.setFont (mediumStylishFont);
		logout.setForeground (Color.BLACK);
		logout.setBounds (650,65,125,35);
		logout.setContentAreaFilled (false);
		logout.setCursor (handCursor);
		logout.setOpaque (false);
		logout.setEnabled (false);
		logout.setBorder (BorderFactory.createEmptyBorder ());
		logout.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				logoutAction (ae);
			}
		});

		//home button
		home = new JButton ("Home");
		home.setFont (mediumStylishFont);
		home.setForeground (Color.BLACK);
		home.setBounds (525,65,75,35);
		home.setContentAreaFilled (false);
		home.setCursor (handCursor);
		home.setOpaque (false);
		home.setEnabled (false);
		home.setBorder (BorderFactory.createEmptyBorder ());
		home.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				homeAction (ae);
			}
		});

/*Defining all the components of loginPanel*/
		//username text field
		username = new JTextField ("Username");
		username.setBounds (700,170,225,35);
		username.setForeground (Color.GRAY);
		username.setFont (normalFont);	
		username.setCursor (textCursor);	
		username.setBackground (lightBlue);
		username.setBorder (BorderFactory.createEmptyBorder ());
		username.addFocusListener (new FocusListener ()
			{
				public void focusGained (FocusEvent fe)
				{
					usernameFocusGained (fe);
				}
				public void focusLost (FocusEvent fe)
				{
					usernameFocusLost (fe);
				}
			});

		//password field for password
		password = new JPasswordField ("Password");
		password.setBounds (700,230,225,35);
		password.setForeground (Color.GRAY);
		password.setFont (normalFont);
		password.setCursor (textCursor);
		password.setBackground (lightBlue);
		password.setBorder (BorderFactory.createEmptyBorder ());
		password.setEchoChar ((char)0);
		password.addFocusListener (new FocusListener ()
			{
				public void focusGained (FocusEvent fe)
				{
					passwordFocusGained (fe);
				}
				public void focusLost (FocusEvent fe)
				{
					passwordFocusLost (fe);
				}
			});

		//main warning label for the login page
		loginWarn = new JLabel ();
		loginWarn.setBounds (550,500,500,35);
		loginWarn.setFont (normalFont);
		loginWarn.setForeground (Color.RED);	

		//login button
		login = new JButton ("LOGIN");
		login.setFont (normalFont);
		login.setBackground (mediumBlue);
		login.setBorder (BorderFactory.createEmptyBorder ());
		login.setCursor (handCursor);
		login.setForeground (Color.WHITE);
		login.setBounds (700,290,225,35);
		login.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					loginAction (ae);
				}
			});

		//signup button
		signUp = new JButton ("CREATE NEW ACCOUNT");
		signUp.setFont (normalFont);
		signUp.setBackground (mediumBlue);
		signUp.setBorder (BorderFactory.createEmptyBorder ());
		signUp.setCursor (handCursor);
		signUp.setForeground (Color.WHITE);
		signUp.setBounds (700,350,225,35);
		signUp.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					signUpAction (ae);
				}
			});

		//recover button
		recover = new JButton ("Forgot Password! Click here.");
		recover.setFont (midSmallFont);
		recover.setForeground (Color.BLACK);
		recover.setBounds (715,385,200,35);
		recover.setContentAreaFilled (false);
		recover.setOpaque (false);
		recover.setBorder (BorderFactory.createEmptyBorder ());
		recover.setCursor (handCursor);
		recover.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					recoverAction (ae);
				}
			});

/*Defining all the components of the signup panel*/
		ButtonGroup gender=new ButtonGroup();

		//textfield for name
		firstName=new JTextField("First Name");
		firstName.setFont(midSmallFont);
		firstName.setBounds(300,100+50,100,30);
		firstName.setForeground(Color.GRAY);
		firstName.setCursor (textCursor);
		firstName.setOpaque(false);
		firstName.setBorder(BorderFactory.createEmptyBorder());
		firstName.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					firstNameFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					firstNameFocusLost(fe);
				}
			});
		firstName.addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
					firstNameTyped(ke);
				}
			});

		middleName=new JTextField("Middle Name");
		middleName.setFont(midSmallFont);
		middleName.setBounds(450,100+50,100,30);
		middleName.setForeground(Color.GRAY);
		middleName.setOpaque(false);
		middleName.setCursor (textCursor);
		middleName.setBorder(BorderFactory.createEmptyBorder());
		middleName.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					middleNameFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					middleNameFocusLost(fe);
				}
			});
		middleName.addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
					middleNameTyped(ke);
				}
			});

		lastName=new JTextField("Last Name");
		lastName.setFont(midSmallFont);
		lastName.setBounds(600,100+50,100,30);
		lastName.setForeground(Color.GRAY);
		lastName.setCursor (textCursor);
		lastName.setOpaque(false);
		lastName.setBorder(BorderFactory.createEmptyBorder());
		lastName.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					lastNameFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					lastNameFocusLost(fe);
				}
			});
		lastName.addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
					lastNameTyped(ke);
				}
			});

		//text field for email address
		email=new JTextField("Email address");
		email.setFont(midSmallFont);
		email.setBounds(300,200+50,400,30);
		email.setForeground(Color.GRAY);
		email.setCursor (textCursor);
		email.setOpaque(false);
		email.setBorder(BorderFactory.createEmptyBorder());
		email.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					emailFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					emailFocusLost(fe);
				}
			});

		//label for wrong email id warning
		wrongEmail=new JLabel();
		wrongEmail.setBounds(300,235+50-10,300,30);
		wrongEmail.setFont(midSmallFont);
		wrongEmail.setForeground(Color.RED);

		//password fields for password
		password1=new JPasswordField("Password");
		password1.setEchoChar((char)0);
		password1.setFont(midSmallFont);
		password1.setBounds(450,300+50,100,30);
		password1.setForeground(Color.GRAY);
		password1.setCursor (textCursor);
		password1.setOpaque(false);
		password1.setBorder(BorderFactory.createEmptyBorder());
		password1.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					password1FocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					password1FocusLost(fe);
				}
			});

		password2=new JPasswordField("Re-type Password");
		password2.setEchoChar((char)0);
		password2.setFont(midSmallFont);
		password2.setBounds(600,300+50,125,30);
		password2.setForeground(Color.GRAY);
		password2.setCursor (textCursor);
		password2.setOpaque(false);
		password2.setBorder(BorderFactory.createEmptyBorder());
		password2.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					password2FocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					password2FocusLost(fe);
				}
			});

		//label for wrong password warning
		wrongPassword=new JLabel();
		wrongPassword.setBounds(450,335+50-10,300,30);
		wrongPassword.setFont(midSmallFont);
		wrongPassword.setForeground(Color.RED);

		//textfield for phone
		phone=new JTextField("Phone");
		phone.setFont(smallFont);
		phone.setBounds(805,300+50,100,30);
		phone.setForeground(Color.GRAY);
		phone.setCursor (textCursor);
		phone.setOpaque(false);
		phone.setBorder(BorderFactory.createEmptyBorder());
		phone.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					phoneFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					phoneFocusLost(fe);
				}
			});

		//label for wrong phone warning
		wrongPhone=new JLabel();
		wrongPhone.setBounds(805,335+50-10,300,30);
		wrongPhone.setFont(midSmallFont);
		wrongPhone.setForeground(Color.RED);

		//textfield for userid
		userid=new JTextField("User ID");
		userid.setFont(midSmallFont);
		userid.setBounds(300,300+50,100,30);
		userid.setForeground(Color.GRAY);
		userid.setCursor (textCursor);
		userid.setOpaque(false);
		userid.setBorder(BorderFactory.createEmptyBorder());
		userid.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					useridFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					useridFocusLost(fe);
				}
			});

		//label for wrong user id warning
		wrongID=new JLabel();
		wrongID.setBounds(300,335+50-10,300,30);
		wrongID.setFont(midSmallFont);
		wrongID.setForeground(Color.RED);

		//textfield for dob
		dob=new JTextField("Date of birth (dd/mm/yyyy)");
		dob.setFont(midSmallFont);
		dob.setBounds(800,100+50,250,30);
		dob.setForeground(Color.GRAY);
		dob.setCursor (textCursor);
		dob.setOpaque(false);
		dob.setBorder(BorderFactory.createEmptyBorder());
		dob.addFocusListener(new FocusListener()
			{
				public void focusGained(FocusEvent fe)
				{
					dobFocusGained(fe);
				}
				public void focusLost(FocusEvent fe)
				{
					dobFocusLost(fe);
				}
			});

		//label for wrong dob warning
		wrongDOB=new JLabel();
		wrongDOB.setBounds(800,135+50-10,300,30);
		wrongDOB.setFont(midSmallFont);
		wrongDOB.setForeground(Color.RED);

		//radio buttons for gender
		//male
		male=new JRadioButton("Male");
		male.setBounds(800,200+50,58,25);
		male.setOpaque(false);
		male.setForeground(Color.BLACK);
		male.setFont(midSmallFont);

		//female
		female=new JRadioButton("Female");
		female.setBounds(865,200+50,73,25);
		female.setOpaque(false);
		female.setForeground(Color.BLACK);
		female.setFont(midSmallFont);

		//other
		other=new JRadioButton("Other");
		other.setBounds(950,200+50,65,25);
		other.setOpaque(false);
		other.setForeground(Color.BLACK);
		other.setFont(midSmallFont);

		//main warning label for the signup page
		signUpWarn = new JLabel ();
		signUpWarn.setBounds (450,560,500,35);
		signUpWarn.setFont (normalFont);
		signUpWarn.setForeground (Color.RED);

		//button to signup
		submitSignUp=new JButton("SIGN UP");
		submitSignUp.setBounds(450,500,100,50);
		submitSignUp.setFont(normalFont);
		submitSignUp.setForeground(Color.WHITE);
		submitSignUp.setBackground(mediumBlue);
		submitSignUp.setBorder(BorderFactory.createEmptyBorder());
		submitSignUp.setCursor(handCursor);
		submitSignUp.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					submitSignUpAction(ae);
				}
			});

		//button to login
		goToLogin=new JButton("BACK TO LOGIN");
		goToLogin.setBounds(700,500,200,50);
		goToLogin.setFont(normalFont);
		goToLogin.setForeground(Color.WHITE);
		goToLogin.setBackground(mediumBlue);
		goToLogin.setBorder(BorderFactory.createEmptyBorder());
		goToLogin.setCursor(handCursor);
		goToLogin.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					goToLoginAction(ae);
				}
			});

		//adding radio buttons to the buttongroup
		gender.add(male);
		gender.add(female);
		gender.add(other);

/*Defining all the components of Department panel*/
		//button to go to the new customer registration panel
		customerRegd = new JButton ("Customer Registration");
		customerRegd.setBounds(200+150,200-50,300,50);
		customerRegd.setFont(normalFont);
		customerRegd.setForeground(Color.WHITE);
		customerRegd.setBackground(darkRed);
		customerRegd.setBorder(BorderFactory.createEmptyBorder());
		customerRegd.setCursor(handCursor);
		customerRegd.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					customerRegdAction(ae);
				}
			});

		//button for new booking
		newBooking = new JButton ("Booking");
		newBooking.setBounds(550+150,200-50,300,50);
		newBooking.setFont(normalFont);
		newBooking.setForeground(Color.WHITE);
		newBooking.setBackground(darkRed);
		newBooking.setBorder(BorderFactory.createEmptyBorder());
		newBooking.setCursor(handCursor);
		newBooking.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					newBookingAction(ae);
				}
			});

		//button for billing
		billing = new JButton ("Billing");
		billing.setBounds(200+150,300-50,300,50);
		billing.setFont(normalFont);
		billing.setForeground(Color.WHITE);
		billing.setBackground(darkRed);
		billing.setBorder(BorderFactory.createEmptyBorder());
		billing.setCursor(handCursor);
		billing.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					billingAction(ae);
				}
			});

		//button for enquiry
		enquiry = new JButton ("Enquiry");
		enquiry.setBounds(550+150,300-50,300,50);
		enquiry.setFont(normalFont);
		enquiry.setForeground(Color.WHITE);
		enquiry.setBackground(darkRed);
		enquiry.setBorder(BorderFactory.createEmptyBorder());
		enquiry.setCursor(handCursor);
		enquiry.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					enquiryAction(ae);
				}
			});

		//button for logout
		exit = new JButton ("Logout");
		exit.setBounds(550+150,400-50,300,50);
		exit.setFont(normalFont);
		exit.setForeground(Color.WHITE);
		exit.setBackground(darkRed);
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setCursor(handCursor);
		exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					exitAction(ae);
				}
			});

		//button to check the room status
		availRooms = new JButton ("Status of rooms");
		availRooms.setBounds(200+150,400-50,300,50);
		availRooms.setFont(normalFont);
		availRooms.setForeground(Color.WHITE);
		availRooms.setBackground(darkRed);
		availRooms.setBorder(BorderFactory.createEmptyBorder());
		availRooms.setCursor(handCursor);
		availRooms.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					availRoomsAction(ae);
				}
			});

/*Defining all the components of the recovery panel*/
		//textfield to enter the user id
		userIdRecover = new JTextField ("Enter user ID");
		userIdRecover.setBounds (200,100,200,35);
		userIdRecover.setFont (normalFont);
		userIdRecover.setForeground (Color.WHITE);
		userIdRecover.setOpaque (false);
		userIdRecover.setBorder (BorderFactory.createEmptyBorder ());
		userIdRecover.setCursor (textCursor);
		userIdRecover.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				userIdRecoverFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				userIdRecoverFocusLost (fe);
			}
		});

		//button to submit the user id
		userIdOk = new JButton ("OK");
		userIdOk.setBounds(400,100,70,35);
		userIdOk.setFont(smallFont);
		userIdOk.setForeground(Color.BLACK);
		userIdOk.setBackground(Color.WHITE);
		userIdOk.setBorder(BorderFactory.createEmptyBorder());
		userIdOk.setCursor(handCursor);
		userIdOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					userIdOkAction(ae);
				}
			});

		//buttons for the digits
		one = new JButton ("1");
		one.setBounds (210,200+100,50,50);
		one.setFont (normalFont);
		one.setCursor (handCursor);
		one.setForeground (Color.WHITE);
		one.setOpaque (false);
		one.setContentAreaFilled (false);
		one.setBorder (BorderFactory.createEmptyBorder ());
		one.addActionListener (this);

		two = new JButton ("2");
		two.setBounds (310,200+100,50,50);
		two.setFont (normalFont);
		two.setCursor (handCursor);
		two.setForeground (Color.WHITE);
		two.setOpaque (false);
		two.setContentAreaFilled (false);
		two.setBorder (BorderFactory.createEmptyBorder ());
		two.addActionListener (this);

		three = new JButton ("3");
		three.setBounds (410,200+100,50,50);
		three.setFont (normalFont);
		three.setCursor (handCursor);
		three.setForeground (Color.WHITE);
		three.setOpaque (false);
		three.setContentAreaFilled (false);
		three.setBorder (BorderFactory.createEmptyBorder ());
		three.addActionListener (this);

		four = new JButton ("4");
		four.setBounds (210,300-25+100,50,50);
		four.setFont (normalFont);
		four.setCursor (handCursor);
		four.setForeground (Color.WHITE);
		four.setOpaque (false);
		four.setContentAreaFilled (false);
		four.setBorder (BorderFactory.createEmptyBorder ());
		four.addActionListener (this);

		five = new JButton ("5");
		five.setBounds (310,300-25+100,50,50);
		five.setFont (normalFont);
		five.setCursor (handCursor);
		five.setForeground (Color.WHITE);
		five.setOpaque (false);
		five.setContentAreaFilled (false);
		five.setBorder (BorderFactory.createEmptyBorder ());
		five.addActionListener (this);

		six = new JButton ("6");
		six.setBounds (410,300-25+100,50,50);
		six.setFont (normalFont);
		six.setCursor (handCursor);
		six.setForeground (Color.WHITE);
		six.setOpaque (false);
		six.setContentAreaFilled (false);
		six.setBorder (BorderFactory.createEmptyBorder ());
		six.addActionListener (this);

		seven = new JButton ("7");
		seven.setBounds (210,400-50+100,50,50);
		seven.setFont (normalFont);
		seven.setCursor (handCursor);
		seven.setForeground (Color.WHITE);
		seven.setOpaque (false);
		seven.setContentAreaFilled (false);
		seven.setBorder (BorderFactory.createEmptyBorder ());
		seven.addActionListener (this);

		eight = new JButton ("8");
		eight.setBounds (310,400-50+100,50,50);
		eight.setFont (normalFont);
		eight.setCursor (handCursor);
		eight.setForeground (Color.WHITE);
		eight.setOpaque (false);
		eight.setContentAreaFilled (false);
		eight.setBorder (BorderFactory.createEmptyBorder ());
		eight.addActionListener (this);

		nine = new JButton ("9");
		nine.setBounds (410,400-50+100,50,50);
		nine.setFont (normalFont);
		nine.setCursor (handCursor);
		nine.setForeground (Color.WHITE);
		nine.setOpaque (false);
		nine.setContentAreaFilled (false);
		nine.setBorder (BorderFactory.createEmptyBorder ());
		nine.addActionListener (this);

		zero = new JButton ("0");
		zero.setBounds (310,500-75+100,50,50);
		zero.setFont (normalFont);
		zero.setCursor (handCursor);
		zero.setForeground (Color.WHITE);
		zero.setOpaque (false);
		zero.setContentAreaFilled (false);
		zero.setBorder (BorderFactory.createEmptyBorder ());
		zero.addActionListener (this);

		clear = new JButton ("X");
		clear.setBounds (410,500-75+100,50,50);
		clear.setFont (normalFont);
		clear.setCursor (handCursor);
		clear.setForeground (Color.WHITE);
		clear.setOpaque (false);
		clear.setContentAreaFilled (false);
		clear.setBorder (BorderFactory.createEmptyBorder ());
		clear.addActionListener (new ActionListener()
			{
				public void actionPerformed (ActionEvent ae)
				{
					clearAction (ae);
				}
			});

		//textarea to display the sixdigitcode
		sixDigitCode = new JTextArea ();
		sixDigitCode.setEditable (false);
		sixDigitCode.setFont (largeFont);
		sixDigitCode.setOpaque (false);
		sixDigitCode.setBorder (BorderFactory.createEmptyBorder ());
		sixDigitCode.setForeground (Color.WHITE);
		sixDigitCode.setBounds (200,200,300,75);

		//warning for the recovery panel
		recoveryWarn = new JLabel ();
		recoveryWarn.setBounds (10,10,500,35);
		recoveryWarn.setFont (normalFont);
		recoveryWarn.setForeground (Color.RED);

		//new password
		newPassword = new JPasswordField ("New Password");
		newPassword.setBounds(800,150,500,35);
		newPassword.setFont(normalFont);
		newPassword.setForeground(Color.WHITE);
		newPassword.setOpaque (false);
		newPassword.setBorder(BorderFactory.createEmptyBorder());
		newPassword.setCursor(textCursor);
		newPassword.setEchoChar ((char)0);
		newPassword.addFocusListener (new FocusListener ()
			{
				public void focusGained (FocusEvent fe)
				{
					newPasswordFocusGained (fe);
				}
				public void focusLost (FocusEvent fe)
				{
					newPasswordFocusLost (fe);
				}
			});

		//retype password
		retypeNewPassword = new JPasswordField ("Re-type Password");
		retypeNewPassword.setBounds(800,250,500,35);
		retypeNewPassword.setFont(normalFont);
		retypeNewPassword.setForeground(Color.WHITE);
		retypeNewPassword.setOpaque (false);
		retypeNewPassword.setBorder(BorderFactory.createEmptyBorder());
		retypeNewPassword.setCursor(textCursor);
		retypeNewPassword.setEchoChar ((char)0);
		retypeNewPassword.addFocusListener (new FocusListener ()
			{
				public void focusGained (FocusEvent fe)
				{
					retypeNewPasswordFocusGained (fe);
				}
				public void focusLost (FocusEvent fe)
				{
					retypeNewPasswordFocusLost (fe);
				}
			});

		//button to save the password
		savePassword = new JButton ("Save Password");
		savePassword.setBounds(800,350,200,50);
		savePassword.setFont(normalFont);
		savePassword.setForeground(Color.BLACK);
		savePassword.setBackground(Color.WHITE);
		savePassword.setBorder(BorderFactory.createEmptyBorder());
		savePassword.setCursor(handCursor);
		savePassword.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					savePasswordAction(ae);
				}
			});

		//button to go back to the main login page
		backToLogin = new JButton ("Go to Login");
		backToLogin.setBounds(800,450,200,50);
		backToLogin.setFont(normalFont);
		backToLogin.setForeground(Color.BLACK);
		backToLogin.setBackground(Color.WHITE);
		backToLogin.setBorder(BorderFactory.createEmptyBorder());
		backToLogin.setCursor(handCursor);
		backToLogin.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					backToLoginAction(ae);
				}
			});

/*Defining the components of the room status panel*/
		//all the togglebuttons for the rooms
		lux01 = new JToggleButton ("RDE001");
		lux01.setFont (smallFont);
		lux01.setBounds (10,550-5,50,50);
		lux01.setCursor (handCursor);
		lux01.setBackground (darkBlue);
		lux01.setForeground (Color.WHITE);
		lux01.setBorder (BorderFactory.createEmptyBorder ());
		lux01.addItemListener (this);

		lux02 = new JToggleButton ("RDE002");
		lux02.setFont (smallFont);
		lux02.setBounds (70,550-5,50,50);
		lux02.setCursor (handCursor);
		lux02.setBackground (darkBlue);
		lux02.setForeground (Color.WHITE);
		lux02.setBorder (BorderFactory.createEmptyBorder ());
		lux02.addItemListener (this);

		lux03 = new JToggleButton ("RDE003");
		lux03.setFont (smallFont);
		lux03.setBounds (130,550-5,50,50);
		lux03.setCursor (handCursor);
		lux03.setBackground (darkBlue);
		lux03.setForeground (Color.WHITE);
		lux03.setBorder (BorderFactory.createEmptyBorder ());
		lux03.addItemListener (this);

		lux04 = new JToggleButton ("RDE004");
		lux04.setFont (smallFont);
		lux04.setBounds (190,550-5,50,50);
		lux04.setCursor (handCursor);
		lux04.setBackground (darkBlue);
		lux04.setForeground (Color.WHITE);
		lux04.setBorder (BorderFactory.createEmptyBorder ());
		lux04.addItemListener (this);

		lux05 = new JToggleButton ("RDE005");
		lux05.setFont (smallFont);
		lux05.setBounds (250,550-5,50,50);
		lux05.setCursor (handCursor);
		lux05.setBackground (darkBlue);
		lux05.setForeground (Color.WHITE);
		lux05.setBorder (BorderFactory.createEmptyBorder ());
		lux05.addItemListener (this);

		lux06 = new JToggleButton ("RDE006");
		lux06.setFont (smallFont);
		lux06.setBounds (310,550-5,50,50);
		lux06.setCursor (handCursor);
		lux06.setBackground (darkBlue);
		lux06.setForeground (Color.WHITE);
		lux06.setBorder (BorderFactory.createEmptyBorder ());
		lux06.addItemListener (this);

		lux07 = new JToggleButton ("RDE007");
		lux07.setFont (smallFont);
		lux07.setBounds (370,550-5,50,50);
		lux07.setCursor (handCursor);
		lux07.setBackground (darkBlue);
		lux07.setForeground (Color.WHITE);
		lux07.setBorder (BorderFactory.createEmptyBorder ());
		lux07.addItemListener (this);

		lux08 = new JToggleButton ("RDE008");
		lux08.setFont (smallFont);
		lux08.setBounds (430,550-5,50,50);
		lux08.setCursor (handCursor);
		lux08.setBackground (darkBlue);
		lux08.setForeground (Color.WHITE);
		lux08.setBorder (BorderFactory.createEmptyBorder ());
		lux08.addItemListener (this);

		lux11 = new JToggleButton ("RDE101");
		lux11.setFont (smallFont);
		lux11.setBounds (10,490-5,50,50);
		lux11.setCursor (handCursor);
		lux11.setBackground (darkBlue);
		lux11.setForeground (Color.WHITE);
		lux11.setBorder (BorderFactory.createEmptyBorder ());
		lux11.addItemListener (this);

		lux12 = new JToggleButton ("RDE102");
		lux12.setFont (smallFont);
		lux12.setBounds (70,490-5,50,50);
		lux12.setCursor (handCursor);
		lux12.setBackground (darkBlue);
		lux12.setForeground (Color.WHITE);
		lux12.setBorder (BorderFactory.createEmptyBorder ());
		lux12.addItemListener (this);

		lux13 = new JToggleButton ("RDE103");
		lux13.setFont (smallFont);
		lux13.setBounds (130,490-5,50,50);
		lux13.setCursor (handCursor);
		lux13.setBackground (darkBlue);
		lux13.setForeground (Color.WHITE);
		lux13.setBorder (BorderFactory.createEmptyBorder ());
		lux13.addItemListener (this);

		lux14 = new JToggleButton ("RDE104");
		lux14.setFont (smallFont);
		lux14.setBounds (190,490-5,50,50);
		lux14.setCursor (handCursor);
		lux14.setBackground (darkBlue);
		lux14.setForeground (Color.WHITE);
		lux14.setBorder (BorderFactory.createEmptyBorder ());
		lux14.addItemListener (this);

		lux15 = new JToggleButton ("RDE105");
		lux15.setFont (smallFont);
		lux15.setBounds (250,490-5,50,50);
		lux15.setCursor (handCursor);
		lux15.setBackground (darkBlue);
		lux15.setForeground (Color.WHITE);
		lux15.setBorder (BorderFactory.createEmptyBorder ());
		lux15.addItemListener (this);

		lux16 = new JToggleButton ("RDE106");
		lux16.setFont (smallFont);
		lux16.setBounds (310,490-5,50,50);
		lux16.setCursor (handCursor);
		lux16.setBackground (darkBlue);
		lux16.setForeground (Color.WHITE);
		lux16.setBorder (BorderFactory.createEmptyBorder ());
		lux16.addItemListener (this);

		lux17 = new JToggleButton ("RDE107");
		lux17.setFont (smallFont);
		lux17.setBounds (370,490-5,50,50);
		lux17.setCursor (handCursor);
		lux17.setBackground (darkBlue);
		lux17.setForeground (Color.WHITE);
		lux17.setBorder (BorderFactory.createEmptyBorder ());
		lux17.addItemListener (this);

		lux18 = new JToggleButton ("RDE108");
		lux18.setFont (smallFont);
		lux18.setBounds (430,490-5,50,50);
		lux18.setCursor (handCursor);
		lux18.setBackground (darkBlue);
		lux18.setForeground (Color.WHITE);
		lux18.setBorder (BorderFactory.createEmptyBorder ());
		lux18.addItemListener (this);

		lux21 = new JToggleButton ("RDE201");
		lux21.setFont (smallFont);
		lux21.setBounds (10,430-5,50,50);
		lux21.setCursor (handCursor);
		lux21.setBackground (darkBlue);
		lux21.setForeground (Color.WHITE);
		lux21.setBorder (BorderFactory.createEmptyBorder ());
		lux21.addItemListener (this);

		lux22 = new JToggleButton ("RDE202");
		lux22.setFont (smallFont);
		lux22.setBounds (70,430-5,50,50);
		lux22.setCursor (handCursor);
		lux22.setBackground (darkBlue);
		lux22.setForeground (Color.WHITE);
		lux22.setBorder (BorderFactory.createEmptyBorder ());
		lux22.addItemListener (this);

		lux23 = new JToggleButton ("RDE203");
		lux23.setFont (smallFont);
		lux23.setBounds (130,430-5,50,50);
		lux23.setCursor (handCursor);
		lux23.setBackground (darkBlue);
		lux23.setForeground (Color.WHITE);
		lux23.setBorder (BorderFactory.createEmptyBorder ());
		lux23.addItemListener (this);

		lux24 = new JToggleButton ("RDE204");
		lux24.setFont (smallFont);
		lux24.setBounds (190,430-5,50,50);
		lux24.setCursor (handCursor);
		lux24.setBackground (darkBlue);
		lux24.setForeground (Color.WHITE);
		lux24.setBorder (BorderFactory.createEmptyBorder ());
		lux24.addItemListener (this);

		lux25 = new JToggleButton ("RDE205");
		lux25.setFont (smallFont);
		lux25.setBounds (250,430-5,50,50);
		lux25.setCursor (handCursor);
		lux25.setBackground (darkBlue);
		lux25.setForeground (Color.WHITE);
		lux25.setBorder (BorderFactory.createEmptyBorder ());
		lux25.addItemListener (this);

		lux26 = new JToggleButton ("RDE206");
		lux26.setFont (smallFont);
		lux26.setBounds (310,430-5,50,50);
		lux26.setCursor (handCursor);
		lux26.setBackground (darkBlue);
		lux26.setForeground (Color.WHITE);
		lux26.setBorder (BorderFactory.createEmptyBorder ());
		lux26.addItemListener (this);

		lux27 = new JToggleButton ("RDE207");
		lux27.setFont (smallFont);
		lux27.setBounds (370,430-5,50,50);
		lux27.setCursor (handCursor);
		lux27.setBackground (darkBlue);
		lux27.setForeground (Color.WHITE);
		lux27.setBorder (BorderFactory.createEmptyBorder ());
		lux27.addItemListener (this);

		lux28 = new JToggleButton ("RDE208");
		lux28.setFont (smallFont);
		lux28.setBounds (430,430-5,50,50);
		lux28.setCursor (handCursor);
		lux28.setBackground (darkBlue);
		lux28.setForeground (Color.WHITE);
		lux28.setBorder (BorderFactory.createEmptyBorder ());
		lux28.addItemListener (this);

		lux31 = new JToggleButton ("RDE301");
		lux31.setFont (smallFont);
		lux31.setBounds (10,370-5,50,50);
		lux31.setCursor (handCursor);
		lux31.setBackground (darkBlue);
		lux31.setForeground (Color.WHITE);
		lux31.setBorder (BorderFactory.createEmptyBorder ());
		lux31.addItemListener (this);

		lux32 = new JToggleButton ("RDE302");
		lux32.setFont (smallFont);
		lux32.setBounds (70,370-5,50,50);
		lux32.setCursor (handCursor);
		lux32.setBackground (darkBlue);
		lux32.setForeground (Color.WHITE);
		lux32.setBorder (BorderFactory.createEmptyBorder ());
		lux32.addItemListener (this);

		lux33 = new JToggleButton ("RDE303");
		lux33.setFont (smallFont);
		lux33.setBounds (130,370-5,50,50);
		lux33.setCursor (handCursor);
		lux33.setBackground (darkBlue);
		lux33.setForeground (Color.WHITE);
		lux33.setBorder (BorderFactory.createEmptyBorder ());
		lux33.addItemListener (this);

		lux34 = new JToggleButton ("RDE304");
		lux34.setFont (smallFont);
		lux34.setBounds (190,370-5,50,50);
		lux34.setCursor (handCursor);
		lux34.setBackground (darkBlue);
		lux34.setForeground (Color.WHITE);
		lux34.setBorder (BorderFactory.createEmptyBorder ());
		lux34.addItemListener (this);

		lux35 = new JToggleButton ("RDE305");
		lux35.setFont (smallFont);
		lux35.setBounds (250,370-5,50,50);
		lux35.setCursor (handCursor);
		lux35.setBackground (darkBlue);
		lux35.setForeground (Color.WHITE);
		lux35.setBorder (BorderFactory.createEmptyBorder ());
		lux35.addItemListener (this);

		lux36 = new JToggleButton ("RDE306");
		lux36.setFont (smallFont);
		lux36.setBounds (310,370-5,50,50);
		lux36.setCursor (handCursor);
		lux36.setBackground (darkBlue);
		lux36.setForeground (Color.WHITE);
		lux36.setBorder (BorderFactory.createEmptyBorder ());
		lux36.addItemListener (this);

		lux37 = new JToggleButton ("RDE307");
		lux37.setFont (smallFont);
		lux37.setBounds (370,370-5,50,50);
		lux37.setCursor (handCursor);
		lux37.setBackground (darkBlue);
		lux37.setForeground (Color.WHITE);
		lux37.setBorder (BorderFactory.createEmptyBorder ());
		lux37.addItemListener (this);

		lux38 = new JToggleButton ("RDE308");
		lux38.setFont (smallFont);
		lux38.setBounds (430,370-5,50,50);
		lux38.setCursor (handCursor);
		lux38.setBackground (darkBlue);
		lux38.setForeground (Color.WHITE);
		lux38.setBorder (BorderFactory.createEmptyBorder ());
		lux38.addItemListener (this);

		lux41 = new JToggleButton ("RDE401");
		lux41.setFont (smallFont);
		lux41.setBounds (10,310-5,50,50);
		lux41.setCursor (handCursor);
		lux41.setBackground (darkBlue);
		lux41.setForeground (Color.WHITE);
		lux41.setBorder (BorderFactory.createEmptyBorder ());
		lux41.addItemListener (this);

		lux42 = new JToggleButton ("RDE402");
		lux42.setFont (smallFont);
		lux42.setBounds (70,310-5,50,50);
		lux42.setCursor (handCursor);
		lux42.setBackground (darkBlue);
		lux42.setForeground (Color.WHITE);
		lux42.setBorder (BorderFactory.createEmptyBorder ());
		lux42.addItemListener (this);

		lux43 = new JToggleButton ("RDE403");
		lux43.setFont (smallFont);
		lux43.setBounds (130,310-5,50,50);
		lux43.setCursor (handCursor);
		lux43.setBackground (darkBlue);
		lux43.setForeground (Color.WHITE);
		lux43.setBorder (BorderFactory.createEmptyBorder ());
		lux43.addItemListener (this);

		lux44 = new JToggleButton ("RDE404");
		lux44.setFont (smallFont);
		lux44.setBounds (190,310-5,50,50);
		lux44.setCursor (handCursor);
		lux44.setBackground (darkBlue);
		lux44.setForeground (Color.WHITE);
		lux44.setBorder (BorderFactory.createEmptyBorder ());
		lux44.addItemListener (this);

		lux45 = new JToggleButton ("RDE405");
		lux45.setFont (smallFont);
		lux45.setBounds (250,310-5,50,50);
		lux45.setCursor (handCursor);
		lux45.setBackground (darkBlue);
		lux45.setForeground (Color.WHITE);
		lux45.setBorder (BorderFactory.createEmptyBorder ());
		lux45.addItemListener (this);

		lux46 = new JToggleButton ("RDE406");
		lux46.setFont (smallFont);
		lux46.setBounds (310,310-5,50,50);
		lux46.setCursor (handCursor);
		lux46.setBackground (darkBlue);
		lux46.setForeground (Color.WHITE);
		lux46.setBorder (BorderFactory.createEmptyBorder ());
		lux46.addItemListener (this);

		lux47 = new JToggleButton ("RDE407");
		lux47.setFont (smallFont);
		lux47.setBounds (370,310-5,50,50);
		lux47.setCursor (handCursor);
		lux47.setBackground (darkBlue);
		lux47.setForeground (Color.WHITE);
		lux47.setBorder (BorderFactory.createEmptyBorder ());
		lux47.addItemListener (this);

		lux48 = new JToggleButton ("RDE408");
		lux48.setFont (smallFont);
		lux48.setBounds (430,310-5,50,50);
		lux48.setCursor (handCursor);
		lux48.setBackground (darkBlue);
		lux48.setForeground (Color.WHITE);
		lux48.setBorder (BorderFactory.createEmptyBorder ());
		lux48.addItemListener (this);

		lux51 = new JToggleButton ("RDE501");
		lux51.setFont (smallFont);
		lux51.setBounds (10,250-5,50,50);
		lux51.setCursor (handCursor);
		lux51.setBackground (darkBlue);
		lux51.setForeground (Color.WHITE);
		lux51.setBorder (BorderFactory.createEmptyBorder ());
		lux51.addItemListener (this);

		lux52 = new JToggleButton ("RDE502");
		lux52.setFont (smallFont);
		lux52.setBounds (70,250-5,50,50);
		lux52.setCursor (handCursor);
		lux52.setBackground (darkBlue);
		lux52.setForeground (Color.WHITE);
		lux52.setBorder (BorderFactory.createEmptyBorder ());
		lux52.addItemListener (this);

		lux53 = new JToggleButton ("RDE503");
		lux53.setFont (smallFont);
		lux53.setBounds (130,250-5,50,50);
		lux53.setCursor (handCursor);
		lux53.setBackground (darkBlue);
		lux53.setForeground (Color.WHITE);
		lux53.setBorder (BorderFactory.createEmptyBorder ());
		lux53.addItemListener (this);

		lux54 = new JToggleButton ("RDE504");
		lux54.setFont (smallFont);
		lux54.setBounds (190,250-5,50,50);
		lux54.setCursor (handCursor);
		lux54.setBackground (darkBlue);
		lux54.setForeground (Color.WHITE);
		lux54.setBorder (BorderFactory.createEmptyBorder ());
		lux54.addItemListener (this);

		lux55 = new JToggleButton ("RDE505");
		lux55.setFont (smallFont);
		lux55.setBounds (250,250-5,50,50);
		lux55.setCursor (handCursor);
		lux55.setBackground (darkBlue);
		lux55.setForeground (Color.WHITE);
		lux55.setBorder (BorderFactory.createEmptyBorder ());
		lux55.addItemListener (this);

		lux56 = new JToggleButton ("RDE506");
		lux56.setFont (smallFont);
		lux56.setBounds (310,250-5,50,50);
		lux56.setCursor (handCursor);
		lux56.setBackground (darkBlue);
		lux56.setForeground (Color.WHITE);
		lux56.setBorder (BorderFactory.createEmptyBorder ());
		lux56.addItemListener (this);

		lux57 = new JToggleButton ("RDE507");
		lux57.setFont (smallFont);
		lux57.setBounds (370,250-5,50,50);
		lux57.setCursor (handCursor);
		lux57.setBackground (darkBlue);
		lux57.setForeground (Color.WHITE);
		lux57.setBorder (BorderFactory.createEmptyBorder ());
		lux57.addItemListener (this);

		lux58 = new JToggleButton ("RDE508");
		lux58.setFont (smallFont);
		lux58.setBounds (430,250-5,50,50);
		lux58.setCursor (handCursor);
		lux58.setBackground (darkBlue);
		lux58.setForeground (Color.WHITE);
		lux58.setBorder (BorderFactory.createEmptyBorder ());
		lux58.addItemListener (this);

		lux61 = new JToggleButton ("RDE601");
		lux61.setFont (smallFont);
		lux61.setBounds (10,190-5,50,50);
		lux61.setCursor (handCursor);
		lux61.setBackground (darkBlue);
		lux61.setForeground (Color.WHITE);
		lux61.setBorder (BorderFactory.createEmptyBorder ());
		lux61.addItemListener (this);

		lux62 = new JToggleButton ("RDE602");
		lux62.setFont (smallFont);
		lux62.setBounds (70,190-5,50,50);
		lux62.setCursor (handCursor);
		lux62.setBackground (darkBlue);
		lux62.setForeground (Color.WHITE);
		lux62.setBorder (BorderFactory.createEmptyBorder ());
		lux62.addItemListener (this);

		lux63 = new JToggleButton ("RDE603");
		lux63.setFont (smallFont);
		lux63.setBounds (130,190-5,50,50);
		lux63.setCursor (handCursor);
		lux63.setBackground (darkBlue);
		lux63.setForeground (Color.WHITE);
		lux63.setBorder (BorderFactory.createEmptyBorder ());
		lux63.addItemListener (this);

		lux64 = new JToggleButton ("RDE604");
		lux64.setFont (smallFont);
		lux64.setBounds (190,190-5,50,50);
		lux64.setCursor (handCursor);
		lux64.setBackground (darkBlue);
		lux64.setForeground (Color.WHITE);
		lux64.setBorder (BorderFactory.createEmptyBorder ());
		lux64.addItemListener (this);

		lux65 = new JToggleButton ("RDE605");
		lux65.setFont (smallFont);
		lux65.setBounds (250,190-5,50,50);
		lux65.setCursor (handCursor);
		lux65.setBackground (darkBlue);
		lux65.setForeground (Color.WHITE);
		lux65.setBorder (BorderFactory.createEmptyBorder ());
		lux65.addItemListener (this);

		lux66 = new JToggleButton ("RDE606");
		lux66.setFont (smallFont);
		lux66.setBounds (310,190-5,50,50);
		lux66.setCursor (handCursor);
		lux66.setBackground (darkBlue);
		lux66.setForeground (Color.WHITE);
		lux66.setBorder (BorderFactory.createEmptyBorder ());
		lux66.addItemListener (this);

		lux67 = new JToggleButton ("RDE607");
		lux67.setFont (smallFont);
		lux67.setBounds (370,190-5,50,50);
		lux67.setCursor (handCursor);
		lux67.setBackground (darkBlue);
		lux67.setForeground (Color.WHITE);
		lux67.setBorder (BorderFactory.createEmptyBorder ());
		lux67.addItemListener (this);

		lux68 = new JToggleButton ("RDE608");
		lux68.setFont (smallFont);
		lux68.setBounds (430,190-5,50,50);
		lux68.setCursor (handCursor);
		lux68.setBackground (darkBlue);
		lux68.setForeground (Color.WHITE);
		lux68.setBorder (BorderFactory.createEmptyBorder ());
		lux68.addItemListener (this);

		lux71 = new JToggleButton ("RDE701");
		lux71.setFont (smallFont);
		lux71.setBounds (10,130-5,50,50);
		lux71.setCursor (handCursor);
		lux71.setBackground (darkBlue);
		lux71.setForeground (Color.WHITE);
		lux71.setBorder (BorderFactory.createEmptyBorder ());
		lux71.addItemListener (this);

		lux72 = new JToggleButton ("RDE702");
		lux72.setFont (smallFont);
		lux72.setBounds (70,130-5,50,50);
		lux72.setCursor (handCursor);
		lux72.setBackground (darkBlue);
		lux72.setForeground (Color.WHITE);
		lux72.setBorder (BorderFactory.createEmptyBorder ());
		lux72.addItemListener (this);

		lux73 = new JToggleButton ("RDE703");
		lux73.setFont (smallFont);
		lux73.setBounds (130,130-5,50,50);
		lux73.setCursor (handCursor);
		lux73.setBackground (darkBlue);
		lux73.setForeground (Color.WHITE);
		lux73.setBorder (BorderFactory.createEmptyBorder ());
		lux73.addItemListener (this);

		lux74 = new JToggleButton ("RDE704");
		lux74.setFont (smallFont);
		lux74.setBounds (190,130-5,50,50);
		lux74.setCursor (handCursor);
		lux74.setBackground (darkBlue);
		lux74.setForeground (Color.WHITE);
		lux74.setBorder (BorderFactory.createEmptyBorder ());
		lux74.addItemListener (this);

		lux75 = new JToggleButton ("RDE705");
		lux75.setFont (smallFont);
		lux75.setBounds (250,130-5,50,50);
		lux75.setCursor (handCursor);
		lux75.setBackground (darkBlue);
		lux75.setForeground (Color.WHITE);
		lux75.setBorder (BorderFactory.createEmptyBorder ());
		lux75.addItemListener (this);

		lux76 = new JToggleButton ("RDE706");
		lux76.setFont (smallFont);
		lux76.setBounds (310,130-5,50,50);
		lux76.setCursor (handCursor);
		lux76.setBackground (darkBlue);
		lux76.setForeground (Color.WHITE);
		lux76.setBorder (BorderFactory.createEmptyBorder ());
		lux76.addItemListener (this);

		lux77 = new JToggleButton ("RDE707");
		lux77.setFont (smallFont);
		lux77.setBounds (370,130-5,50,50);
		lux77.setCursor (handCursor);
		lux77.setBackground (darkBlue);
		lux77.setForeground (Color.WHITE);
		lux77.setBorder (BorderFactory.createEmptyBorder ());
		lux77.addItemListener (this);

		lux78 = new JToggleButton ("RDE708");
		lux78.setFont (smallFont);
		lux78.setBounds (430,130-5,50,50);
		lux78.setCursor (handCursor);
		lux78.setBackground (darkBlue);
		lux78.setForeground (Color.WHITE);
		lux78.setBorder (BorderFactory.createEmptyBorder ());
		lux78.addItemListener (this);

		lux81 = new JToggleButton ("RDE801");
		lux81.setFont (smallFont);
		lux81.setBounds (10,70-5,50,50);
		lux81.setCursor (handCursor);
		lux81.setBackground (darkBlue);
		lux81.setForeground (Color.WHITE);
		lux81.setBorder (BorderFactory.createEmptyBorder ());
		lux81.addItemListener (this);

		lux82 = new JToggleButton ("RDE802");
		lux82.setFont (smallFont);
		lux82.setBounds (70,70-5,50,50);
		lux82.setCursor (handCursor);
		lux82.setBackground (darkBlue);
		lux82.setForeground (Color.WHITE);
		lux82.setBorder (BorderFactory.createEmptyBorder ());
		lux82.addItemListener (this);

		lux83 = new JToggleButton ("RDE803");
		lux83.setFont (smallFont);
		lux83.setBounds (130,70-5,50,50);
		lux83.setCursor (handCursor);
		lux83.setBackground (darkBlue);
		lux83.setForeground (Color.WHITE);
		lux83.setBorder (BorderFactory.createEmptyBorder ());
		lux83.addItemListener (this);

		lux84 = new JToggleButton ("RDE804");
		lux84.setFont (smallFont);
		lux84.setBounds (190,70-5,50,50);
		lux84.setCursor (handCursor);
		lux84.setBackground (darkBlue);
		lux84.setForeground (Color.WHITE);
		lux84.setBorder (BorderFactory.createEmptyBorder ());
		lux84.addItemListener (this);

		lux85 = new JToggleButton ("RDE805");
		lux85.setFont (smallFont);
		lux85.setBounds (250,70-5,50,50);
		lux85.setCursor (handCursor);
		lux85.setBackground (darkBlue);
		lux85.setForeground (Color.WHITE);
		lux85.setBorder (BorderFactory.createEmptyBorder ());
		lux85.addItemListener (this);

		lux86 = new JToggleButton ("RDE806");
		lux86.setFont (smallFont);
		lux86.setBounds (310,70-5,50,50);
		lux86.setCursor (handCursor);
		lux86.setBackground (darkBlue);
		lux86.setForeground (Color.WHITE);
		lux86.setBorder (BorderFactory.createEmptyBorder ());
		lux86.addItemListener (this);

		lux87 = new JToggleButton ("RDE807");
		lux87.setFont (smallFont);
		lux87.setBounds (370,70-5,50,50);
		lux87.setCursor (handCursor);
		lux87.setBackground (darkBlue);
		lux87.setForeground (Color.WHITE);
		lux87.setBorder (BorderFactory.createEmptyBorder ());
		lux87.addItemListener (this);

		lux88 = new JToggleButton ("RDE808");
		lux88.setFont (smallFont);
		lux88.setBounds (430,70-5,50,50);
		lux88.setCursor (handCursor);
		lux88.setBackground (darkBlue);
		lux88.setForeground (Color.WHITE);
		lux88.setBorder (BorderFactory.createEmptyBorder ());
		lux88.addItemListener (this);

		lux91 = new JToggleButton ("RDE901");
		lux91.setFont (smallFont);
		lux91.setBounds (10,10-5,50,50);
		lux91.setCursor (handCursor);
		lux91.setBackground (darkBlue);
		lux91.setForeground (Color.WHITE);
		lux91.setBorder (BorderFactory.createEmptyBorder ());
		lux91.addItemListener (this);

		lux92 = new JToggleButton ("RDE902");
		lux92.setFont (smallFont);
		lux92.setBounds (70,10-5,50,50);
		lux92.setCursor (handCursor);
		lux92.setBackground (darkBlue);
		lux92.setForeground (Color.WHITE);
		lux92.setBorder (BorderFactory.createEmptyBorder ());
		lux92.addItemListener (this);

		lux93 = new JToggleButton ("RDE903");
		lux93.setFont (smallFont);
		lux93.setBounds (130,10-5,50,50);
		lux93.setCursor (handCursor);
		lux93.setBackground (darkBlue);
		lux93.setForeground (Color.WHITE);
		lux93.setBorder (BorderFactory.createEmptyBorder ());
		lux93.addItemListener (this);

		lux94 = new JToggleButton ("RDE904");
		lux94.setFont (smallFont);
		lux94.setBounds (190,10-5,50,50);
		lux94.setCursor (handCursor);
		lux94.setBackground (darkBlue);
		lux94.setForeground (Color.WHITE);
		lux94.setBorder (BorderFactory.createEmptyBorder ());
		lux94.addItemListener (this);

		lux95 = new JToggleButton ("RDE905");
		lux95.setFont (smallFont);
		lux95.setBounds (250,10-5,50,50);
		lux95.setCursor (handCursor);
		lux95.setBackground (darkBlue);
		lux95.setForeground (Color.WHITE);
		lux95.setBorder (BorderFactory.createEmptyBorder ());
		lux95.addItemListener (this);

		lux96 = new JToggleButton ("RDE906");
		lux96.setFont (smallFont);
		lux96.setBounds (310,10-5,50,50);
		lux96.setCursor (handCursor);
		lux96.setBackground (darkBlue);
		lux96.setForeground (Color.WHITE);
		lux96.setBorder (BorderFactory.createEmptyBorder ());
		lux96.addItemListener (this);

		lux97 = new JToggleButton ("RDE907");
		lux97.setFont (smallFont);
		lux97.setBounds (370,10-5,50,50);
		lux97.setCursor (handCursor);
		lux97.setBackground (darkBlue);
		lux97.setForeground (Color.WHITE);
		lux97.setBorder (BorderFactory.createEmptyBorder ());
		lux97.addItemListener (this);

		lux98 = new JToggleButton ("RDE908");
		lux98.setFont (smallFont);
		lux98.setBounds (430,10-5,50,50);
		lux98.setCursor (handCursor);
		lux98.setBackground (darkBlue);
		lux98.setForeground (Color.WHITE);
		lux98.setBorder (BorderFactory.createEmptyBorder ());
		lux98.addItemListener (this);

		suLux01 = new JToggleButton ("RSD001");
		suLux01.setFont (smallFont);
		suLux01.setBounds (490+25,550-5,75,50);
		suLux01.setCursor (handCursor);
		suLux01.setBackground (darkBlue);
		suLux01.setForeground (Color.WHITE);
		suLux01.setBorder (BorderFactory.createEmptyBorder ());
		suLux01.addItemListener (this);

		suLux02 = new JToggleButton ("RSD002");
		suLux02.setFont (smallFont);
		suLux02.setBounds (575+25,550-5,75,50);
		suLux02.setCursor (handCursor);
		suLux02.setBackground (darkBlue);
		suLux02.setForeground (Color.WHITE);
		suLux02.setBorder (BorderFactory.createEmptyBorder ());
		suLux02.addItemListener (this);

		suLux03 = new JToggleButton ("RSD003");
		suLux03.setFont (smallFont);
		suLux03.setBounds (660+25,550-5,75,50);
		suLux03.setCursor (handCursor);
		suLux03.setBackground (darkBlue);
		suLux03.setForeground (Color.WHITE);
		suLux03.setBorder (BorderFactory.createEmptyBorder ());
		suLux03.addItemListener (this);

		suLux04 = new JToggleButton ("RSD004");
		suLux04.setFont (smallFont);
		suLux04.setBounds (745+25,550-5,75,50);
		suLux04.setCursor (handCursor);
		suLux04.setBackground (darkBlue);
		suLux04.setForeground (Color.WHITE);
		suLux04.setBorder (BorderFactory.createEmptyBorder ());
		suLux04.addItemListener (this);

		suLux11 = new JToggleButton ("RSD101");
		suLux11.setFont (smallFont);
		suLux11.setBounds (490+25,490-5,75,50);
		suLux11.setCursor (handCursor);
		suLux11.setBackground (darkBlue);
		suLux11.setForeground (Color.WHITE);
		suLux11.setBorder (BorderFactory.createEmptyBorder ());
		suLux11.addItemListener (this);

		suLux12 = new JToggleButton ("RSD102");
		suLux12.setFont (smallFont);
		suLux12.setBounds (575+25,490-5,75,50);
		suLux12.setCursor (handCursor);
		suLux12.setBackground (darkBlue);
		suLux12.setForeground (Color.WHITE);
		suLux12.setBorder (BorderFactory.createEmptyBorder ());
		suLux12.addItemListener (this);

		suLux13 = new JToggleButton ("RSD103");
		suLux13.setFont (smallFont);
		suLux13.setBounds (660+25,490-5,75,50);
		suLux13.setCursor (handCursor);
		suLux13.setBackground (darkBlue);
		suLux13.setForeground (Color.WHITE);
		suLux13.setBorder (BorderFactory.createEmptyBorder ());
		suLux13.addItemListener (this);

		suLux14 = new JToggleButton ("RSD104");
		suLux14.setFont (smallFont);
		suLux14.setBounds (745+25,490-5,75,50);
		suLux14.setCursor (handCursor);
		suLux14.setBackground (darkBlue);
		suLux14.setForeground (Color.WHITE);
		suLux14.setBorder (BorderFactory.createEmptyBorder ());
		suLux14.addItemListener (this);

		suLux21 = new JToggleButton ("RSD201");
		suLux21.setFont (smallFont);
		suLux21.setBounds (490+25,430-5,75,50);
		suLux21.setCursor (handCursor);
		suLux21.setBackground (darkBlue);
		suLux21.setForeground (Color.WHITE);
		suLux21.setBorder (BorderFactory.createEmptyBorder ());
		suLux21.addItemListener (this);

		suLux22 = new JToggleButton ("RSD202");
		suLux22.setFont (smallFont);
		suLux22.setBounds (575+25,430-5,75,50);
		suLux22.setCursor (handCursor);
		suLux22.setBackground (darkBlue);
		suLux22.setForeground (Color.WHITE);
		suLux22.setBorder (BorderFactory.createEmptyBorder ());
		suLux22.addItemListener (this);

		suLux23 = new JToggleButton ("RSD203");
		suLux23.setFont (smallFont);
		suLux23.setBounds (660+25,430-5,75,50);
		suLux23.setCursor (handCursor);
		suLux23.setBackground (darkBlue);
		suLux23.setForeground (Color.WHITE);
		suLux23.setBorder (BorderFactory.createEmptyBorder ());
		suLux23.addItemListener (this);

		suLux24 = new JToggleButton ("RSD204");
		suLux24.setFont (smallFont);
		suLux24.setBounds (745+25,430-5,75,50);
		suLux24.setCursor (handCursor);
		suLux24.setBackground (darkBlue);
		suLux24.setForeground (Color.WHITE);
		suLux24.setBorder (BorderFactory.createEmptyBorder ());
		suLux24.addItemListener (this);

		suLux31 = new JToggleButton ("RSD301");
		suLux31.setFont (smallFont);
		suLux31.setBounds (490+25,370-5,75,50);
		suLux31.setCursor (handCursor);
		suLux31.setBackground (darkBlue);
		suLux31.setForeground (Color.WHITE);
		suLux31.setBorder (BorderFactory.createEmptyBorder ());
		suLux31.addItemListener (this);

		suLux32 = new JToggleButton ("RSD302");
		suLux32.setFont (smallFont);
		suLux32.setBounds (575+25,370-5,75,50);
		suLux32.setCursor (handCursor);
		suLux32.setBackground (darkBlue);
		suLux32.setForeground (Color.WHITE);
		suLux32.setBorder (BorderFactory.createEmptyBorder ());
		suLux32.addItemListener (this);

		suLux33 = new JToggleButton ("RSD303");
		suLux33.setFont (smallFont);
		suLux33.setBounds (660+25,370-5,75,50);
		suLux33.setCursor (handCursor);
		suLux33.setBackground (darkBlue);
		suLux33.setForeground (Color.WHITE);
		suLux33.setBorder (BorderFactory.createEmptyBorder ());
		suLux33.addItemListener (this);

		suLux34 = new JToggleButton ("RSD304");
		suLux34.setFont (smallFont);
		suLux34.setBounds (745+25,370-5,75,50);
		suLux34.setCursor (handCursor);
		suLux34.setBackground (darkBlue);
		suLux34.setForeground (Color.WHITE);
		suLux34.setBorder (BorderFactory.createEmptyBorder ());
		suLux34.addItemListener (this);

		suLux41 = new JToggleButton ("RSD401");
		suLux41.setFont (smallFont);
		suLux41.setBounds (490+25,310-5,75,50);
		suLux41.setCursor (handCursor);
		suLux41.setBackground (darkBlue);
		suLux41.setForeground (Color.WHITE);
		suLux41.setBorder (BorderFactory.createEmptyBorder ());
		suLux41.addItemListener (this);

		suLux42 = new JToggleButton ("RSD402");
		suLux42.setFont (smallFont);
		suLux42.setBounds (575+25,310-5,75,50);
		suLux42.setCursor (handCursor);
		suLux42.setBackground (darkBlue);
		suLux42.setForeground (Color.WHITE);
		suLux42.setBorder (BorderFactory.createEmptyBorder ());
		suLux42.addItemListener (this);

		suLux43 = new JToggleButton ("RSD403");
		suLux43.setFont (smallFont);
		suLux43.setBounds (660+25,310-5,75,50);
		suLux43.setCursor (handCursor);
		suLux43.setBackground (darkBlue);
		suLux43.setForeground (Color.WHITE);
		suLux43.setBorder (BorderFactory.createEmptyBorder ());
		suLux43.addItemListener (this);

		suLux44 = new JToggleButton ("RSD404");
		suLux44.setFont (smallFont);
		suLux44.setBounds (745+25,310-5,75,50);
		suLux44.setCursor (handCursor);
		suLux44.setBackground (darkBlue);
		suLux44.setForeground (Color.WHITE);
		suLux44.setBorder (BorderFactory.createEmptyBorder ());
		suLux44.addItemListener (this);

		suLux51 = new JToggleButton ("RSD501");
		suLux51.setFont (smallFont);
		suLux51.setBounds (490+25,250-5,75,50);
		suLux51.setCursor (handCursor);
		suLux51.setBackground (darkBlue);
		suLux51.setForeground (Color.WHITE);
		suLux51.setBorder (BorderFactory.createEmptyBorder ());
		suLux51.addItemListener (this);

		suLux52 = new JToggleButton ("RSD502");
		suLux52.setFont (smallFont);
		suLux52.setBounds (575+25,250-5,75,50);
		suLux52.setCursor (handCursor);
		suLux52.setBackground (darkBlue);
		suLux52.setForeground (Color.WHITE);
		suLux52.setBorder (BorderFactory.createEmptyBorder ());
		suLux52.addItemListener (this);

		suLux53 = new JToggleButton ("RSD503");
		suLux53.setFont (smallFont);
		suLux53.setBounds (660+25,250-5,75,50);
		suLux53.setCursor (handCursor);
		suLux53.setBackground (darkBlue);
		suLux53.setForeground (Color.WHITE);
		suLux53.setBorder (BorderFactory.createEmptyBorder ());
		suLux53.addItemListener (this);

		suLux54 = new JToggleButton ("RSD504");
		suLux54.setFont (smallFont);
		suLux54.setBounds (745+25,250-5,75,50);
		suLux54.setCursor (handCursor);
		suLux54.setBackground (darkBlue);
		suLux54.setForeground (Color.WHITE);
		suLux54.setBorder (BorderFactory.createEmptyBorder ());
		suLux54.addItemListener (this);

		suLux61 = new JToggleButton ("RSD601");
		suLux61.setFont (smallFont);
		suLux61.setBounds (490+25,190-5,75,50);
		suLux61.setCursor (handCursor);
		suLux61.setBackground (darkBlue);
		suLux61.setForeground (Color.WHITE);
		suLux61.setBorder (BorderFactory.createEmptyBorder ());
		suLux61.addItemListener (this);

		suLux62 = new JToggleButton ("RSD602");
		suLux62.setFont (smallFont);
		suLux62.setBounds (575+25,190-5,75,50);
		suLux62.setCursor (handCursor);
		suLux62.setBackground (darkBlue);
		suLux62.setForeground (Color.WHITE);
		suLux62.setBorder (BorderFactory.createEmptyBorder ());
		suLux62.addItemListener (this);

		suLux63 = new JToggleButton ("RSD603");
		suLux63.setFont (smallFont);
		suLux63.setBounds (660+25,190-5,75,50);
		suLux63.setCursor (handCursor);
		suLux63.setBackground (darkBlue);
		suLux63.setForeground (Color.WHITE);
		suLux63.setBorder (BorderFactory.createEmptyBorder ());
		suLux63.addItemListener (this);

		suLux64 = new JToggleButton ("RSD604");
		suLux64.setFont (smallFont);
		suLux64.setBounds (745+25,190-5,75,50);
		suLux64.setCursor (handCursor);
		suLux64.setBackground (darkBlue);
		suLux64.setForeground (Color.WHITE);
		suLux64.setBorder (BorderFactory.createEmptyBorder ());
		suLux64.addItemListener (this);

		suLux71 = new JToggleButton ("RSD701");
		suLux71.setFont (smallFont);
		suLux71.setBounds (490+25,130-5,75,50);
		suLux71.setCursor (handCursor);
		suLux71.setBackground (darkBlue);
		suLux71.setForeground (Color.WHITE);
		suLux71.setBorder (BorderFactory.createEmptyBorder ());
		suLux71.addItemListener (this);

		suLux72 = new JToggleButton ("RSD702");
		suLux72.setFont (smallFont);
		suLux72.setBounds (575+25,130-5,75,50);
		suLux72.setCursor (handCursor);
		suLux72.setBackground (darkBlue);
		suLux72.setForeground (Color.WHITE);
		suLux72.setBorder (BorderFactory.createEmptyBorder ());
		suLux72.addItemListener (this);

		suLux73 = new JToggleButton ("RSD703");
		suLux73.setFont (smallFont);
		suLux73.setBounds (660+25,130-5,75,50);
		suLux73.setCursor (handCursor);
		suLux73.setBackground (darkBlue);
		suLux73.setForeground (Color.WHITE);
		suLux73.setBorder (BorderFactory.createEmptyBorder ());
		suLux73.addItemListener (this);

		suLux74 = new JToggleButton ("RSD704");
		suLux74.setFont (smallFont);
		suLux74.setBounds (745+25,130-5,75,50);
		suLux74.setCursor (handCursor);
		suLux74.setBackground (darkBlue);
		suLux74.setForeground (Color.WHITE);
		suLux74.setBorder (BorderFactory.createEmptyBorder ());
		suLux74.addItemListener (this);

		suLux81 = new JToggleButton ("RSD801");
		suLux81.setFont (smallFont);
		suLux81.setBounds (490+25,70-5,75,50);
		suLux81.setCursor (handCursor);
		suLux81.setBackground (darkBlue);
		suLux81.setForeground (Color.WHITE);
		suLux81.setBorder (BorderFactory.createEmptyBorder ());
		suLux81.addItemListener (this);

		suLux82 = new JToggleButton ("RSD802");
		suLux82.setFont (smallFont);
		suLux82.setBounds (575+25,70-5,75,50);
		suLux82.setCursor (handCursor);
		suLux82.setBackground (darkBlue);
		suLux82.setForeground (Color.WHITE);
		suLux82.setBorder (BorderFactory.createEmptyBorder ());
		suLux82.addItemListener (this);

		suLux83 = new JToggleButton ("RSD803");
		suLux83.setFont (smallFont);
		suLux83.setBounds (660+25,70-5,75,50);
		suLux83.setCursor (handCursor);
		suLux83.setBackground (darkBlue);
		suLux83.setForeground (Color.WHITE);
		suLux83.setBorder (BorderFactory.createEmptyBorder ());
		suLux83.addItemListener (this);

		suLux84 = new JToggleButton ("RSD804");
		suLux84.setFont (smallFont);
		suLux84.setBounds (745+25,70-5,75,50);
		suLux84.setCursor (handCursor);
		suLux84.setBackground (darkBlue);
		suLux84.setForeground (Color.WHITE);
		suLux84.setBorder (BorderFactory.createEmptyBorder ());
		suLux84.addItemListener (this);

		suLux91 = new JToggleButton ("RSD901");
		suLux91.setFont (smallFont);
		suLux91.setBounds (490+25,10-5,75,50);
		suLux91.setCursor (handCursor);
		suLux91.setBackground (darkBlue);
		suLux91.setForeground (Color.WHITE);
		suLux91.setBorder (BorderFactory.createEmptyBorder ());
		suLux91.addItemListener (this);

		suLux92 = new JToggleButton ("RSD902");
		suLux92.setFont (smallFont);
		suLux92.setBounds (575+25,10-5,75,50);
		suLux92.setCursor (handCursor);
		suLux92.setBackground (darkBlue);
		suLux92.setForeground (Color.WHITE);
		suLux92.setBorder (BorderFactory.createEmptyBorder ());
		suLux92.addItemListener (this);

		suLux93 = new JToggleButton ("RSD903");
		suLux93.setFont (smallFont);
		suLux93.setBounds (660+25,10-5,75,50);
		suLux93.setCursor (handCursor);
		suLux93.setBackground (darkBlue);
		suLux93.setForeground (Color.WHITE);
		suLux93.setBorder (BorderFactory.createEmptyBorder ());
		suLux93.addItemListener (this);

		suLux94 = new JToggleButton ("RSD904");
		suLux94.setFont (smallFont);
		suLux94.setBounds (745+25,10-5,75,50);
		suLux94.setCursor (handCursor);
		suLux94.setBackground (darkBlue);
		suLux94.setForeground (Color.WHITE);
		suLux94.setBorder (BorderFactory.createEmptyBorder ());
		suLux94.addItemListener (this);

		pm01 = new JToggleButton ("SPM001");
		pm01.setFont (smallFont);
		pm01.setBounds (830+50,550-5,100,50);
		pm01.setCursor (handCursor);
		pm01.setBackground (darkBlue);
		pm01.setForeground (Color.WHITE);
		pm01.setBorder (BorderFactory.createEmptyBorder ());
		pm01.addItemListener (this);

		pm11 = new JToggleButton ("SPM101");
		pm11.setFont (smallFont);
		pm11.setBounds (830+50,490-5,100,50);
		pm11.setCursor (handCursor);
		pm11.setBackground (darkBlue);
		pm11.setForeground (Color.WHITE);
		pm11.setBorder (BorderFactory.createEmptyBorder ());
		pm11.addItemListener (this);

		pm21 = new JToggleButton ("SPM201");
		pm21.setFont (smallFont);
		pm21.setBounds (830+50,430-5,100,50);
		pm21.setCursor (handCursor);
		pm21.setBackground (darkBlue);
		pm21.setForeground (Color.WHITE);
		pm21.setBorder (BorderFactory.createEmptyBorder ());
		pm21.addItemListener (this);

		pm31 = new JToggleButton ("SPM301");
		pm31.setFont (smallFont);
		pm31.setBounds (830+50,370-5,100,50);
		pm31.setCursor (handCursor);
		pm31.setBackground (darkBlue);
		pm31.setForeground (Color.WHITE);
		pm31.setBorder (BorderFactory.createEmptyBorder ());
		pm31.addItemListener (this);

		pm41 = new JToggleButton ("SPM401");
		pm41.setFont (smallFont);
		pm41.setBounds (830+50,310-5,100,50);
		pm41.setCursor (handCursor);
		pm41.setBackground (darkBlue);
		pm41.setForeground (Color.WHITE);
		pm41.setBorder (BorderFactory.createEmptyBorder ());
		pm41.addItemListener (this);

		pm51 = new JToggleButton ("SPM501");
		pm51.setFont (smallFont);
		pm51.setBounds (830+50,250-5,100,50);
		pm51.setCursor (handCursor);
		pm51.setBackground (darkBlue);
		pm51.setForeground (Color.WHITE);
		pm51.setBorder (BorderFactory.createEmptyBorder ());
		pm51.addItemListener (this);

		pm61 = new JToggleButton ("SPM601");
		pm61.setFont (smallFont);
		pm61.setBounds (830+50,190-5,100,50);
		pm61.setCursor (handCursor);
		pm61.setBackground (darkBlue);
		pm61.setForeground (Color.WHITE);
		pm61.setBorder (BorderFactory.createEmptyBorder ());
		pm61.addItemListener (this);

		pm71 = new JToggleButton ("SPM701");
		pm71.setFont (smallFont);
		pm71.setBounds (830+50,130-5,100,50);
		pm71.setCursor (handCursor);
		pm71.setBackground (darkBlue);
		pm71.setForeground (Color.WHITE);
		pm71.setBorder (BorderFactory.createEmptyBorder ());
		pm71.addItemListener (this);

		pm81 = new JToggleButton ("SPM801");
		pm81.setFont (smallFont);
		pm81.setBounds (830+50,70-5,100,50);
		pm81.setCursor (handCursor);
		pm81.setBackground (darkBlue);
		pm81.setForeground (Color.WHITE);
		pm81.setBorder (BorderFactory.createEmptyBorder ());
		pm81.addItemListener (this);

		pm91 = new JToggleButton ("SPM901");
		pm91.setFont (smallFont);
		pm91.setBounds (830+50,10-5,100,50);
		pm91.setCursor (handCursor);
		pm91.setBackground (darkBlue);
		pm91.setForeground (Color.WHITE);
		pm91.setBorder (BorderFactory.createEmptyBorder ());
		pm91.addItemListener (this);

		pd01 = new JToggleButton ("SPD001>");
		pd01.setFont (smallFont);
		pd01.setBounds (940+75,550-5,100,50);
		pd01.setCursor (handCursor);
		pd01.setBackground (darkBlue);
		pd01.setForeground (Color.WHITE);
		pd01.setBorder (BorderFactory.createEmptyBorder ());
		pd01.addItemListener (this);

		pd11 = new JToggleButton ("SPD101");
		pd11.setFont (smallFont);
		pd11.setBounds (940+75,490-5,100,50);
		pd11.setCursor (handCursor);
		pd11.setBackground (darkBlue);
		pd11.setForeground (Color.WHITE);
		pd11.setBorder (BorderFactory.createEmptyBorder ());
		pd11.addItemListener (this);

		pd21 = new JToggleButton ("SPD201");
		pd21.setFont (smallFont);
		pd21.setBounds (940+75,430-5,100,50);
		pd21.setCursor (handCursor);
		pd21.setBackground (darkBlue);
		pd21.setForeground (Color.WHITE);
		pd21.setBorder (BorderFactory.createEmptyBorder ());
		pd21.addItemListener (this);

		pd31 = new JToggleButton ("SPD301");
		pd31.setFont (smallFont);
		pd31.setBounds (940+75,370-5,100,50);
		pd31.setCursor (handCursor);
		pd31.setBackground (darkBlue);
		pd31.setForeground (Color.WHITE);
		pd31.setBorder (BorderFactory.createEmptyBorder ());
		pd31.addItemListener (this);

		pd41 = new JToggleButton ("SPD401");
		pd41.setFont (smallFont);
		pd41.setBounds (940+75,310-5,100,50);
		pd41.setCursor (handCursor);
		pd41.setBackground (darkBlue);
		pd41.setForeground (Color.WHITE);
		pd41.setBorder (BorderFactory.createEmptyBorder ());
		pd41.addItemListener (this);

		pd51 = new JToggleButton ("SPD501");
		pd51.setFont (smallFont);
		pd51.setBounds (940+75,250-5,100,50);
		pd51.setCursor (handCursor);
		pd51.setBackground (darkBlue);
		pd51.setForeground (Color.WHITE);
		pd51.setBorder (BorderFactory.createEmptyBorder ());
		pd51.addItemListener (this);

		pd61 = new JToggleButton ("SPD601");
		pd61.setFont (smallFont);
		pd61.setBounds (940+75,190-5,100,50);
		pd61.setCursor (handCursor);
		pd61.setBackground (darkBlue);
		pd61.setForeground (Color.WHITE);
		pd61.setBorder (BorderFactory.createEmptyBorder ());
		pd61.addItemListener (this);

		pd71 = new JToggleButton ("SPD701");
		pd71.setFont (smallFont);
		pd71.setBounds (940+75,130-5,100,50);
		pd71.setCursor (handCursor);
		pd71.setBackground (darkBlue);
		pd71.setForeground (Color.WHITE);
		pd71.setBorder (BorderFactory.createEmptyBorder ());
		pd71.addItemListener (this);

		pd81 = new JToggleButton ("SPD801");
		pd81.setFont (smallFont);
		pd81.setBounds (940+75,70-5,100,50);
		pd81.setCursor (handCursor);
		pd81.setBackground (darkBlue);
		pd81.setForeground (Color.WHITE);
		pd81.setBorder (BorderFactory.createEmptyBorder ());
		pd81.addItemListener (this);

		pd91 = new JToggleButton ("SPD901");
		pd91.setFont (smallFont);
		pd91.setBounds (940+75,10-5,100,50);
		pd91.setCursor (handCursor);
		pd91.setBackground (darkBlue);
		pd91.setForeground (Color.WHITE);
		pd91.setBorder (BorderFactory.createEmptyBorder ());
		pd91.addItemListener (this);

		ex01 = new JToggleButton ("SEX001");
		ex01.setFont (smallFont);
		ex01.setBounds (1050+100,550-5,100,50);
		ex01.setCursor (handCursor);
		ex01.setBackground (darkBlue);
		ex01.setForeground (Color.WHITE);
		ex01.setBorder (BorderFactory.createEmptyBorder ());
		ex01.addItemListener (this);

		ex11 = new JToggleButton ("SEX101");
		ex11.setFont (smallFont);
		ex11.setBounds (1050+100,490-5,100,50);
		ex11.setCursor (handCursor);
		ex11.setBackground (darkBlue);
		ex11.setForeground (Color.WHITE);
		ex11.setBorder (BorderFactory.createEmptyBorder ());
		ex11.addItemListener (this);

		ex21 = new JToggleButton ("SEX201");
		ex21.setFont (smallFont);
		ex21.setBounds (1050+100,430-5,100,50);
		ex21.setCursor (handCursor);
		ex21.setBackground (darkBlue);
		ex21.setForeground (Color.WHITE);
		ex21.setBorder (BorderFactory.createEmptyBorder ());
		ex21.addItemListener (this);

		ex31 = new JToggleButton ("SEX301");
		ex31.setFont (smallFont);
		ex31.setBounds (1050+100,370-5,100,50);
		ex31.setCursor (handCursor);
		ex31.setBackground (darkBlue);
		ex31.setForeground (Color.WHITE);
		ex31.setBorder (BorderFactory.createEmptyBorder ());
		ex31.addItemListener (this);

		ex41 = new JToggleButton ("SEX401");
		ex41.setFont (smallFont);
		ex41.setBounds (1050+100,310-5,100,50);
		ex41.setCursor (handCursor);
		ex41.setBackground (darkBlue);
		ex41.setForeground (Color.WHITE);
		ex41.setBorder (BorderFactory.createEmptyBorder ());
		ex41.addItemListener (this);

		ex51 = new JToggleButton ("SEX501");
		ex51.setFont (smallFont);
		ex51.setBounds (1050+100,250-5,100,50);
		ex51.setCursor (handCursor);
		ex51.setBackground (darkBlue);
		ex51.setForeground (Color.WHITE);
		ex51.setBorder (BorderFactory.createEmptyBorder ());
		ex51.addItemListener (this);

		ex61 = new JToggleButton ("SEX601");
		ex61.setFont (smallFont);
		ex61.setBounds (1050+100,190-5,100,50);
		ex61.setCursor (handCursor);
		ex61.setBackground (darkBlue);
		ex61.setForeground (Color.WHITE);
		ex61.setBorder (BorderFactory.createEmptyBorder ());
		ex61.addItemListener (this);

		ex71 = new JToggleButton ("SEX701");
		ex71.setFont (smallFont);
		ex71.setBounds (1050+100,130-5,100,50);
		ex71.setCursor (handCursor);
		ex71.setBackground (darkBlue);
		ex71.setForeground (Color.WHITE);
		ex71.setBorder (BorderFactory.createEmptyBorder ());
		ex71.addItemListener (this);

		ex81 = new JToggleButton ("SEX801");
		ex81.setFont (smallFont);
		ex81.setBounds (1050+100,70-5,100,50);
		ex81.setCursor (handCursor);
		ex81.setBackground (darkBlue);
		ex81.setForeground (Color.WHITE);
		ex81.setBorder (BorderFactory.createEmptyBorder ());
		ex81.addItemListener (this);

		ex91 = new JToggleButton ("SEX901");
		ex91.setFont (smallFont);
		ex91.setBounds (1050+100,10-5,100,50);
		ex91.setCursor (handCursor);
		ex91.setBackground (darkBlue);
		ex91.setForeground (Color.WHITE);
		ex91.setBorder (BorderFactory.createEmptyBorder ());
		ex91.addItemListener (this);

/*Defining the components of the customer registration panel*/
		//customer code
		custCodeRegd = new JTextField ("Code");
		custCodeRegd.setBounds (750,10,100,35);
		custCodeRegd.setFont (midSmallFont);
		custCodeRegd.setCursor (textCursor);
		custCodeRegd.setForeground (Color.GRAY);
		custCodeRegd.setOpaque (false);
		custCodeRegd.setBorder (BorderFactory.createEmptyBorder ());
		custCodeRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custCodeRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custCodeRegdFocusLost (fe);
			}
		});

/*Defining the components of New Customer registration panel*/
		//name
		custFirstNameRegd = new JTextField ("First Name");
		custFirstNameRegd.setBounds (400,60,150,35);
		custFirstNameRegd.setFont (midSmallFont);
		custFirstNameRegd.setOpaque (false);
		custFirstNameRegd.setCursor (textCursor);
		custFirstNameRegd.setForeground (Color.GRAY);
		custFirstNameRegd.setBorder (BorderFactory.createEmptyBorder ());
		custFirstNameRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custFirstNameRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custFirstNameRegdFocusLost (fe);
			}
		});

		custMidNameRegd = new JTextField ("Middle Name");
		custMidNameRegd.setBounds (575,60,150,35);
		custMidNameRegd.setFont (midSmallFont);
		custMidNameRegd.setCursor (textCursor);
		custMidNameRegd.setForeground (Color.GRAY);
		custMidNameRegd.setOpaque (false);
		custMidNameRegd.setBorder (BorderFactory.createEmptyBorder ());
		custMidNameRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custMidNameRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custMidNameRegdFocusLost (fe);
			}
		});

		custLastNameRegd = new JTextField ("Last Name");
		custLastNameRegd.setBounds (750,60,150,35);
		custLastNameRegd.setFont (midSmallFont);
		custLastNameRegd.setCursor (textCursor);
		custLastNameRegd.setForeground (Color.GRAY);
		custLastNameRegd.setOpaque (false);
		custLastNameRegd.setBorder (BorderFactory.createEmptyBorder ());
		custLastNameRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custLastNameRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custLastNameRegdFocusLost (fe);
			}
		});

		//age
		custAgeRegd = new JComboBox ();
		custAgeRegd.addItem ("Age");
		for (int i = 0 ; i < 150 ; i++)
			custAgeRegd.addItem (Integer.toString (i+1));
		custAgeRegd.setBounds (650,260,200,35);
		custAgeRegd.setFont (midSmallFont);

		//marital status
		custMSRegd = new JComboBox ();
		custMSRegd.addItem ("Marital Status");
		custMSRegd.addItem ("Single");
		custMSRegd.addItem ("Married");
		custMSRegd.addItem ("Widowed");
		custMSRegd.addItem ("Divorced");
		custMSRegd.addItem ("Other");
		custMSRegd.setBounds (400,110,200,35);
		custMSRegd.setFont (midSmallFont);

		custCountryRegd = new JTextField ("Country");
		custCountryRegd.setBounds (650,110,250,35);
		custCountryRegd.setFont (midSmallFont);
		custCountryRegd.setCursor (textCursor);
		custCountryRegd.setForeground (Color.GRAY);
		custCountryRegd.setOpaque (false);
		custCountryRegd.setBorder (BorderFactory.createEmptyBorder ());
		custCountryRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custCountryRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custCountryRegdFocusLost (fe);
			}
		});

		custPassportRegd = new JTextField ("Passport Number");
		custPassportRegd.setBounds (400,160,250,35);
		custPassportRegd.setFont (midSmallFont);
		custPassportRegd.setCursor (textCursor);
		custPassportRegd.setForeground (Color.GRAY);
		custPassportRegd.setOpaque (false);
		custPassportRegd.setBorder (BorderFactory.createEmptyBorder ());
		custPassportRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custPassportRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custPassportRegdFocusLost (fe);
			}
		});

		custVisaRegd = new JTextField ("Visa Number");
		custVisaRegd.setBounds (650,160,250,35);
		custVisaRegd.setFont (midSmallFont);
		custVisaRegd.setCursor (textCursor);
		custVisaRegd.setForeground (Color.GRAY);
		custVisaRegd.setOpaque (false);
		custVisaRegd.setBorder (BorderFactory.createEmptyBorder ());
		custVisaRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custVisaRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custVisaRegdFocusLost (fe);
			}
		});

		custResidentRegd = new JTextArea ("Residential Address");
		custResidentRegd.setBounds (400,360,225,100);
		custResidentRegd.setForeground (Color.GRAY);
		custResidentRegd.setFont (midSmallFont);
		custResidentRegd.setCursor (textCursor);
		custResidentRegd.setLineWrap(true);
		custResidentRegd.setWrapStyleWord(true);
		custResidentRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custResidentRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custResidentRegdFocusLost (fe);
			}
		});

		custOfficeRegd = new JTextArea ("Office Address (if any)");
		custOfficeRegd.setBounds (650,360,225,100);
		custOfficeRegd.setForeground (Color.GRAY);
		custOfficeRegd.setFont (midSmallFont);
		custOfficeRegd.setCursor (textCursor);
		custOfficeRegd.setLineWrap(true);
		custOfficeRegd.setWrapStyleWord(true);
		custOfficeRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custOfficeRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custOfficeRegdFocusLost (fe);
			}
		});

		custPhoneRegd = new JTextField ("Phone");
		custPhoneRegd.setBounds (650,210,150,35);
		custPhoneRegd.setFont (midSmallFont);
		custPhoneRegd.setCursor (textCursor);
		custPhoneRegd.setForeground (Color.GRAY);
		custPhoneRegd.setOpaque (false);
		custPhoneRegd.setBorder (BorderFactory.createEmptyBorder ());
		custPhoneRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custPhoneRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custPhoneRegdFocusLost (fe);
			}
		});

		custEmailRegd = new JTextField ("Email");
		custEmailRegd.setBounds (400,210,250,35);
		custEmailRegd.setFont (midSmallFont);
		custEmailRegd.setCursor (textCursor);
		custEmailRegd.setForeground (Color.GRAY);
		custEmailRegd.setOpaque (false);
		custEmailRegd.setBorder (BorderFactory.createEmptyBorder ());
		custEmailRegd.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custEmailRegdFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custEmailRegdFocusLost (fe);
			}
		});

		custStatusRegd = new JComboBox ();
		custStatusRegd.addItem ("Customer Status");
		custStatusRegd.addItem ("Regular");
		custStatusRegd.addItem ("Occasional");
		custStatusRegd.addItem ("Other");
		custStatusRegd.setBounds (400,260,200,35);
		custStatusRegd.setFont (midSmallFont);

		custMale = new JRadioButton ("Male");
		custMale.setBounds (400,310,100,35);
		custMale.setFont (midSmallFont);
		custMale.setOpaque (false);

		custFemale = new JRadioButton ("Female");
		custFemale.setBounds (575,310,150,35);
		custFemale.setFont (midSmallFont);
		custFemale.setOpaque (false);

		custOther = new JRadioButton ("Other");
		custOther.setBounds (750,310,100,35);
		custOther.setFont (midSmallFont);
		custOther.setOpaque (false);

		gender.add (custMale);
		gender.add (custFemale);
		gender.add (custOther);

		//button for registration
		register = new JButton ("Register");
		register.setBounds(250+50,475,200,50);
		register.setFont(normalFont);
		register.setForeground(Color.WHITE);
		register.setBackground(darkPink);
		register.setBorder(BorderFactory.createEmptyBorder());
		register.setCursor(handCursor);
		register.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					registerAction(ae);
				}
			});

		//button to refresh the page
		newRegd = new JButton ("New");
		newRegd.setBounds(475+50,475,200,50);
		newRegd.setFont(normalFont);
		newRegd.setForeground(Color.WHITE);
		newRegd.setBackground(darkPink);
		newRegd.setBorder(BorderFactory.createEmptyBorder());
		newRegd.setCursor(handCursor);
		newRegd.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					newRegdAction(ae);
				}
			});

		//home button in registration panel
		homeCustRegd = new JButton ("Back To Home");
		homeCustRegd.setBounds(700+50,475,200,50);
		homeCustRegd.setFont(normalFont);
		homeCustRegd.setForeground(Color.WHITE);
		homeCustRegd.setBackground(darkPink);
		homeCustRegd.setBorder(BorderFactory.createEmptyBorder());
		homeCustRegd.setCursor(handCursor);
		homeCustRegd.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					homeCustRegdAction(ae);
				}
			});

		//warning labels
		wrongInfo=new JLabel();
		wrongInfo.setBounds(400,550,500,35);
		wrongInfo.setFont(normalFont);
		wrongInfo.setForeground(Color.RED);

		wrongCustMail=new JLabel();
		wrongCustMail.setBounds(900,210,500,35);
		wrongCustMail.setFont(normalFont);
		wrongCustMail.setForeground(Color.RED);

		wrongCustPh=new JLabel();
		wrongCustPh.setBounds(900,210,500,35);
		wrongCustPh.setFont(normalFont);
		wrongCustPh.setForeground(Color.RED);

/*Defining all the components of booking panel*/
		//textfield to enter the customer code
		custCodeBook = new JTextField ("Enter Customer Code");
		custCodeBook.setBounds (400-250,10+100,150,35);
		custCodeBook.setFont (midSmallFont);
		custCodeBook.setCursor (textCursor);
		custCodeBook.setForeground (Color.GRAY);
		custCodeBook.setOpaque (false);
		custCodeBook.setBorder (BorderFactory.createEmptyBorder ());
		custCodeBook.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				custCodeBookFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				custCodeBookFocusLost (fe);
			}
		});

		//name of the customer
		custNameBook = new JTextField ("Name");
		custNameBook.setBounds (400-250,60+100,300,35);
		custNameBook.setFont (midSmallFont);
		custNameBook.setCursor (textCursor);
		custNameBook.setForeground (Color.GRAY);
		custNameBook.setOpaque (false);
		custNameBook.setBorder (BorderFactory.createEmptyBorder ());
		custNameBook.setEditable (false);

		//address
		custAddrBook = new JTextArea ("Address");
		custAddrBook.setBounds (400-250,220+100,250,125);
		custAddrBook.setFont (midSmallFont);
		custAddrBook.setCursor (textCursor);
		custAddrBook.setForeground (Color.GRAY);
		custAddrBook.setOpaque (false);
		custAddrBook.setBorder (BorderFactory.createEmptyBorder ());
		custAddrBook.setEditable (false);

		//phone
		custPhoneBook = new JTextField ("Phone");
		custPhoneBook.setBounds (400-250,110+100,125,35);
		custPhoneBook.setFont (midSmallFont);
		custPhoneBook.setCursor (textCursor);
		custPhoneBook.setForeground (Color.GRAY);
		custPhoneBook.setOpaque (false);
		custPhoneBook.setBorder (BorderFactory.createEmptyBorder ());
		custPhoneBook.setEditable (false);

		//email address
		custEmailBook = new JTextField ("Email Address");
		custEmailBook.setBounds (600-250,110+100,250,35);
		custEmailBook.setFont (midSmallFont);
		custEmailBook.setCursor (textCursor);
		custEmailBook.setForeground (Color.GRAY);
		custEmailBook.setOpaque (false);
		custEmailBook.setBorder (BorderFactory.createEmptyBorder ());
		custEmailBook.setEditable (false);

		//status of the customer
		custStatusBook = new JTextField ("Status");
		custStatusBook.setBounds (400-250,160+100,125,35);
		custStatusBook.setFont (midSmallFont);
		custStatusBook.setCursor (textCursor);
		custStatusBook.setForeground (Color.GRAY);
		custStatusBook.setOpaque (false);
		custStatusBook.setBorder (BorderFactory.createEmptyBorder ());
		custStatusBook.setEditable (false);

		//gender
		custGenderBook = new JTextField ("Gender");
		custGenderBook.setBounds (600-250,160+100,125,35);
		custGenderBook.setFont (midSmallFont);
		custGenderBook.setCursor (textCursor);
		custGenderBook.setForeground (Color.GRAY);
		custGenderBook.setOpaque (false);
		custGenderBook.setBorder (BorderFactory.createEmptyBorder ());
		custGenderBook.setEditable (false);

		//booking code
		bookCode = new JTextField ("Booking Code");
		bookCode.setBounds (400+250+50,350+50-300+10,250,35);
		bookCode.setFont (midSmallFont);
		bookCode.setCursor (textCursor);
		bookCode.setForeground (Color.GRAY);
		bookCode.setOpaque (false);
		bookCode.setBorder (BorderFactory.createEmptyBorder ());
		bookCode.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				bookCodeFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				bookCodeFocusLost (fe);
			}
		});

		//details of the booked room
		roomBook = new JTextField ("Room Details");
		roomBook.setBounds (400+250+50,400-300+50+10,250,35);
		roomBook.setFont (midSmallFont);
		roomBook.setCursor (textCursor);
		roomBook.setForeground (Color.GRAY);
		roomBook.setOpaque (false);
		roomBook.setBorder (BorderFactory.createEmptyBorder ());
		roomBook.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				roomBookFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				roomBookFocusLost (fe);
			}
		});

		//date of arrival
		arriveBook = new JTextField ("Date of Arrival (dd/mm/yyyy)");
		arriveBook.setBounds (400+250+50,450-300+50+10,300,35);
		arriveBook.setFont (midSmallFont);
		arriveBook.setCursor (textCursor);
		arriveBook.setForeground (Color.GRAY);
		arriveBook.setOpaque (false);
		arriveBook.setBorder (BorderFactory.createEmptyBorder ());
		arriveBook.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				arriveBookFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				arriveBookFocusLost (fe);
			}
		});

		//number of persons
		personsBook = new JTextField ("Number of Persons");
		personsBook.setBounds (400+250+50,500-300+50+10,150,35);
		personsBook.setFont (midSmallFont);
		personsBook.setCursor (textCursor);
		personsBook.setForeground (Color.GRAY);
		personsBook.setOpaque (false);
		personsBook.setBorder (BorderFactory.createEmptyBorder ());
		personsBook.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				personsBookFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				personsBookFocusLost (fe);
			}
		});

		//relationship
		relationBook = new JTextField ("Relation");
		relationBook.setBounds (400+250+50,550-300+50+10,125,35);
		relationBook.setFont (midSmallFont);
		relationBook.setCursor (textCursor);
		relationBook.setForeground (Color.GRAY);
		relationBook.setOpaque (false);
		relationBook.setBorder (BorderFactory.createEmptyBorder ());
		relationBook.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				relationBookFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				relationBookFocusLost (fe);
			}
		});

		//buttons
		//button to fetch the customer details
		fetchDataBook = new JButton ("OK");
		fetchDataBook.setBounds(575-250+25,10+100,70,35);
		fetchDataBook.setFont(midSmallFont);
		fetchDataBook.setForeground(Color.WHITE);
		fetchDataBook.setBackground(darkPurple);
		fetchDataBook.setBorder(BorderFactory.createEmptyBorder());
		fetchDataBook.setCursor(handCursor);
		fetchDataBook.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					fetchDataBookAction(ae);
				}
			});

		//button to go to the room booking page
		availRoomBook = new JButton ("Select Room");
		availRoomBook.setBounds(950,400-300+50+10,100,35);
		availRoomBook.setFont(midSmallFont);
		availRoomBook.setForeground(Color.WHITE);
		availRoomBook.setBackground(darkPurple);
		availRoomBook.setBorder(BorderFactory.createEmptyBorder());
		availRoomBook.setCursor(handCursor);
		availRoomBook.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					availRoomBookAction(ae);
				}
			});

		//button to book
		bookRoomButton = new JButton ("Save and Book");
		bookRoomButton.setBounds(225,450,200,50);
		bookRoomButton.setFont(normalFont);
		bookRoomButton.setForeground(Color.WHITE);
		bookRoomButton.setBackground(darkPurple);
		bookRoomButton.setBorder(BorderFactory.createEmptyBorder());
		bookRoomButton.setCursor(handCursor);
		bookRoomButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					bookRoomButtonAction(ae);
				}
			});

		//button to go back to home page
		homeBook = new JButton ("Back To Home");
		homeBook.setBounds(725,450,200,50);
		homeBook.setFont(normalFont);
		homeBook.setForeground(Color.WHITE);
		homeBook.setBackground(darkPurple);
		homeBook.setBorder(BorderFactory.createEmptyBorder());
		homeBook.setCursor(handCursor);
		homeBook.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					homeBookAction(ae);
				}
			});

		//button to reset the data of booking panel
		newBook = new JButton ("New Booking");
		newBook.setBounds(475,450,200,50);
		newBook.setFont(normalFont);
		newBook.setForeground(Color.WHITE);
		newBook.setBackground(darkPurple);
		newBook.setBorder(BorderFactory.createEmptyBorder());
		newBook.setCursor(handCursor);
		newBook.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					newBookAction(ae);
				}
			});

		//main warning label for the booking panel
		bookingWarn = new JLabel ();
		bookingWarn.setBounds (650,525,500,35);
		bookingWarn.setFont (normalFont);
		bookingWarn.setForeground (Color.RED);

/*Defining all the components of billing panel*/
		//booking code
		bookCodeBill = new JTextField ("Enter Booking Code");
		bookCodeBill.setBounds (400-250,10+75,150,35);
		bookCodeBill.setFont (midSmallFont);
		bookCodeBill.setCursor (textCursor);
		bookCodeBill.setForeground (Color.GRAY);
		bookCodeBill.setOpaque (false);
		bookCodeBill.setBorder (BorderFactory.createEmptyBorder ());
		bookCodeBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				bookCodeBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				bookCodeBillFocusLost (fe);
			}
		});

		//customer code
		custCodeBill = new JTextField ("Customer Code");
		custCodeBill.setEditable (false);
		custCodeBill.setBounds (150,60+75,250,35);
		custCodeBill.setFont (midSmallFont);
		custCodeBill.setCursor (textCursor);
		custCodeBill.setForeground (Color.GRAY);
		custCodeBill.setOpaque (false);
		custCodeBill.setBorder (BorderFactory.createEmptyBorder ());

		//customer name
		custNameBill = new JTextField ("Customer Name");
		custNameBill.setEditable (false);
		custNameBill.setBounds (400-250,110+75,300,35);
		custNameBill.setFont (midSmallFont);
		custNameBill.setCursor (textCursor);
		custNameBill.setForeground (Color.GRAY);
		custNameBill.setOpaque (false);
		custNameBill.setBorder (BorderFactory.createEmptyBorder ());

		//booked room number
		bookedRoomBill = new JTextField ("Room Number");
		bookedRoomBill.setEditable (false);
		bookedRoomBill.setBounds (400-250,160+75,125,35);
		bookedRoomBill.setFont (midSmallFont);
		bookedRoomBill.setCursor (textCursor);
		bookedRoomBill.setForeground (Color.GRAY);
		bookedRoomBill.setOpaque (false);
		bookedRoomBill.setBorder (BorderFactory.createEmptyBorder ());

		//no of persons
		personsBill = new JTextField ("Number of Persons");
		personsBill.setEditable (false);
		personsBill.setBounds (400-250,210+75,250,35);
		personsBill.setFont (midSmallFont);
		personsBill.setCursor (textCursor);
		personsBill.setForeground (Color.GRAY);
		personsBill.setOpaque (false);
		personsBill.setBorder (BorderFactory.createEmptyBorder ());

		//date of arrival
		arriveBill = new JTextField ("Date of Arrival");
		arriveBill.setEditable (false);
		arriveBill.setBounds (400-250,260+75,125,35);
		arriveBill.setFont (midSmallFont);
		arriveBill.setCursor (textCursor);
		arriveBill.setForeground (Color.GRAY);
		arriveBill.setOpaque (false);
		arriveBill.setBorder (BorderFactory.createEmptyBorder ());

		//billing code
		billCode = new JTextField ("Billing Code");
		billCode.setBounds (550,10+75,250,35);
		billCode.setFont (midSmallFont);
		billCode.setCursor (textCursor);
		billCode.setForeground (Color.GRAY);
		billCode.setOpaque (false);
		billCode.setBorder (BorderFactory.createEmptyBorder ());
		billCode.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				billCodeFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				billCodeFocusLost (fe);
			}
		});

		//date of departure
		departBill = new JTextField ("Date of Departure (dd/mm/yyyy)");
		departBill.setBounds (550,60+75,250,35);
		departBill.setFont (midSmallFont);
		departBill.setCursor (textCursor);
		departBill.setForeground (Color.GRAY);
		departBill.setOpaque (false);
		departBill.setBorder (BorderFactory.createEmptyBorder ());
		departBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				departBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				departBillFocusLost (fe);
			}
		});

		//room rent
		roomRentBill = new JTextField ("Room Rent");
		roomRentBill.setEditable (false);
		roomRentBill.setBounds (550,110+75,150,35);
		roomRentBill.setFont (midSmallFont);
		roomRentBill.setCursor (textCursor);
		roomRentBill.setForeground (Color.GRAY);
		roomRentBill.setOpaque (false);
		roomRentBill.setBorder (BorderFactory.createEmptyBorder ());

		//service charges
		serviceBill = new JTextField ("Service Charges");
		serviceBill.setBounds (550,160+75,150,35);
		serviceBill.setFont (midSmallFont);
		serviceBill.setCursor (textCursor);
		serviceBill.setForeground (Color.GRAY);
		serviceBill.setOpaque (false);
		serviceBill.setBorder (BorderFactory.createEmptyBorder ());
		serviceBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				serviceBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				serviceBillFocusLost (fe);
			}
		});

		//gross billing amount
		grossBill = new JTextField ("Gross Amount");
		grossBill.setBounds (550,260+75,150,35);
		grossBill.setEditable (false);
		grossBill.setFont (midSmallFont);
		grossBill.setCursor (textCursor);
		grossBill.setForeground (Color.GRAY);
		grossBill.setOpaque (false);
		grossBill.setBorder (BorderFactory.createEmptyBorder ());

		//gst
		gstBill = new JTextField ("GST");
		gstBill.setBounds (750,110+75,150,35);
		gstBill.setFont (midSmallFont);
		gstBill.setCursor (textCursor);
		gstBill.setForeground (Color.GRAY);
		gstBill.setOpaque (false);
		gstBill.setBorder (BorderFactory.createEmptyBorder ());
		gstBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				gstBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				gstBillFocusLost (fe);
			}
		});

		//discount
		discountBill = new JTextField ("Discount (%)");
		discountBill.setBounds (750,160+75,150,35);
		discountBill.setFont (midSmallFont);
		discountBill.setCursor (textCursor);
		discountBill.setForeground (Color.GRAY);
		discountBill.setOpaque (false);
		discountBill.setBorder (BorderFactory.createEmptyBorder ());
		discountBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				discountBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				discountBillFocusLost (fe);
			}
		});

		//total amount
		netBill = new JTextField ("Net Amount");
		netBill.setBounds (750,260+75,150,35);
		netBill.setEditable (false);
		netBill.setFont (midSmallFont);
		netBill.setCursor (textCursor);
		netBill.setForeground (Color.GRAY);
		netBill.setOpaque (false);
		netBill.setBorder (BorderFactory.createEmptyBorder ());

		//payment mode
		modeBill = new JTextField ("Payment Mode");
		modeBill.setBounds (950,60+75,150,35);
		modeBill.setFont (midSmallFont);
		modeBill.setCursor (textCursor);
		modeBill.setForeground (Color.GRAY);
		modeBill.setOpaque (false);
		modeBill.setBorder (BorderFactory.createEmptyBorder ());
		modeBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				modeBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				modeBillFocusLost (fe);
			}
		});

		//card number
		cardNoBill = new JTextField ("Card Number");
		cardNoBill.setBounds (950,110+75,200,35);
		cardNoBill.setFont (midSmallFont);
		cardNoBill.setCursor (textCursor);
		cardNoBill.setForeground (Color.GRAY);
		cardNoBill.setOpaque (false);
		cardNoBill.setBorder (BorderFactory.createEmptyBorder ());
		cardNoBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				cardNoBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				cardNoBillFocusLost (fe);
			}
		});

		//received amount
		receivedBill = new JTextField ("Received Amount");
		receivedBill.setBounds (950,160+75,150,35);
		receivedBill.setFont (midSmallFont);
		receivedBill.setCursor (textCursor);
		receivedBill.setForeground (Color.GRAY);
		receivedBill.setOpaque (false);
		receivedBill.setBorder (BorderFactory.createEmptyBorder ());
		receivedBill.addFocusListener (new FocusListener ()
		{
			public void focusGained (FocusEvent fe)
			{
				receivedBillFocusGained (fe);
			}
			public void focusLost (FocusEvent fe)
			{
				receivedBillFocusLost (fe);
			}
		});

		//pending amount
		pendingBill = new JTextField ("Pending Amount");
		pendingBill.setBounds (950,260+75,150,35);
		pendingBill.setEditable (false);
		pendingBill.setFont (midSmallFont);
		pendingBill.setCursor (textCursor);
		pendingBill.setForeground (Color.GRAY);
		pendingBill.setOpaque (false);
		pendingBill.setBorder (BorderFactory.createEmptyBorder ());

		//buttons
		//button to fetch the customer details
		fetchDataBill = new JButton ("OK");
		fetchDataBill.setBounds(575-250+25,10+75,70,35);
		fetchDataBill.setFont(midSmallFont);
		fetchDataBill.setForeground(Color.WHITE);
		fetchDataBill.setBackground(darkGreen);
		fetchDataBill.setBorder(BorderFactory.createEmptyBorder());
		fetchDataBill.setCursor(handCursor);
		fetchDataBill.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					fetchDataBillAction(ae);
				}
			});

		//button to calculate the gross amount
		calcGross = new JButton ("Gross Amount");
		calcGross.setBounds(550,210+75,150,35);
		calcGross.setFont(midSmallFont);
		calcGross.setForeground(Color.WHITE);
		calcGross.setBackground(darkGreen);
		calcGross.setBorder(BorderFactory.createEmptyBorder());
		calcGross.setCursor(handCursor);
		calcGross.setEnabled (false);
		calcGross.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					calcGrossAction(ae);
				}
			});

		//button to calculate the net amount
		calcNet = new JButton ("Net Amount");
		calcNet.setBounds(750,210+75,150,35);
		calcNet.setFont(midSmallFont);
		calcNet.setForeground(Color.WHITE);
		calcNet.setBackground(darkGreen);
		calcNet.setBorder(BorderFactory.createEmptyBorder());
		calcNet.setCursor(handCursor);
		calcNet.setEnabled (false);
		calcNet.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					calcNetAction(ae);
				}
			});

		//button to calculate the pending amount
		calcPending = new JButton ("Pending Amount");
		calcPending.setBounds(950,210+75,150,35);
		calcPending.setFont(midSmallFont);
		calcPending.setForeground(Color.WHITE);
		calcPending.setBackground(darkGreen);
		calcPending.setBorder(BorderFactory.createEmptyBorder());
		calcPending.setCursor(handCursor);
		calcPending.setEnabled (false);
		calcPending.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					calcPendingAction(ae);
				}
			});

		//bill button
		billButton = new JButton ("Bill and Save");
		billButton.setBounds(225,450,200,50);
		billButton.setFont(normalFont);
		billButton.setForeground(Color.WHITE);
		billButton.setBackground(darkGreen);
		billButton.setBorder(BorderFactory.createEmptyBorder());
		billButton.setCursor(handCursor);
		billButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					billButtonAction(ae);
				}
			});

		//home button
		homeBill = new JButton ("Back To Home");
		homeBill.setBounds(725,450,200,50);
		homeBill.setFont(normalFont);
		homeBill.setForeground(Color.WHITE);
		homeBill.setBackground(darkGreen);
		homeBill.setBorder(BorderFactory.createEmptyBorder());
		homeBill.setCursor(handCursor);
		homeBill.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					homeBillAction(ae);
				}
			});

		//button to refresh the page
		newBill = new JButton ("New");
		newBill.setBounds(475,450,200,50);
		newBill.setFont(normalFont);
		newBill.setForeground(Color.WHITE);
		newBill.setBackground(darkGreen);
		newBill.setBorder(BorderFactory.createEmptyBorder());
		newBill.setCursor(handCursor);
		newBill.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					newBillAction(ae);
				}
			});

		//main warning label for the billing panel
		billingWarn = new JLabel ();
		billingWarn.setBounds (650,525,500,35);
		billingWarn.setFont (normalFont);
		billingWarn.setForeground (Color.RED);

/*Defining all the components of enquiry panel*/
		custCodeEnq = new JTextField ("Enter Customer Code");
		custCodeEnq.setBounds (500,10,400,35);
		custCodeEnq.setForeground (Color.GRAY);
		custCodeEnq.setFont (midSmallFont);	
		custCodeEnq.setCursor (textCursor);	
		custCodeEnq.setBackground (lightGrayBlue);
		custCodeEnq.setBorder (BorderFactory.createEmptyBorder ());
		custCodeEnq.addFocusListener (new FocusListener ()
			{
				public void focusGained (FocusEvent fe)
				{
					custCodeEnqFocusGained (fe);
				}
				public void focusLost (FocusEvent fe)
				{
					custCodeEnqFocusLost (fe);
				}
			});

		//button to fetch the details
		checkData = new JButton ("Fetch Data");
		checkData.setBounds(600,50,200,50);
		checkData.setFont(normalFont);
		checkData.setForeground(Color.WHITE);
		checkData.setBackground(darkGrayBlue);
		checkData.setBorder(BorderFactory.createEmptyBorder());
		checkData.setCursor(handCursor);
		checkData.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					checkDataAction(ae);
				}
			});

		//button to go back to home
		homeEnq = new JButton ("Back To Home");
		homeEnq.setBounds(600,500,200,50);
		homeEnq.setFont(normalFont);
		homeEnq.setForeground(Color.WHITE);
		homeEnq.setBackground(darkGrayBlue);
		homeEnq.setBorder(BorderFactory.createEmptyBorder());
		homeEnq.setCursor(handCursor);
		homeEnq.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					homeEnqAction(ae);
				}
			});

		//textarea for displaying the data
		customerData = new JTextArea ();
		customerData.setBounds (400,125,650,375);
		customerData.setFont (midSmallFont);
		customerData.setCursor (textCursor);
		customerData.setEditable (false);
		customerData.setLineWrap(true);
		customerData.setWrapStyleWord(true);

/*Adding the components to the respective panels*/
		//adding the base panels to the container
		container.add (topPanel);
		container.add (mainPanel);
		container.add (blueBackLabel);

		//adding the components of the top panel
		topPanel.add (welcomeLabel);
		topPanel.add (logout);
		topPanel.add (home);

		//adding all the panels to the main panel
		mainPanel.add (loginPanel, "First");
		mainPanel.add (deptPanel, "Dept");
		mainPanel.add (newCustPanel, "New Registration");
		mainPanel.add (bookPanel, "Booking");
		mainPanel.add (queryPanel, "Enquiry");
		mainPanel.add (billPanel, "Billing");
		mainPanel.add (signUpPanel, "SignUp");
		mainPanel.add (recoveryPanel, "Recover");
		mainPanel.add (roomStatusPanel, "Room Status");

		//adding the components to the login panel
		loginPanel.add (username);
		loginPanel.add (password);
		loginPanel.add (userIcon);
		loginPanel.add (login);
		loginPanel.add (signUp);
		loginPanel.add (recover);
		loginPanel.add (loginWarn);
		loginPanel.add (loginBackLabel);

		//adding the components to the signup panel
		signUpPanel.add(firstName);
		signUpPanel.add(middleName);
		signUpPanel.add(lastName);
		signUpPanel.add(email);
		signUpPanel.add(password1);
		signUpPanel.add(password2);
		signUpPanel.add(userid);
		signUpPanel.add(phone);
		signUpPanel.add(male);
		signUpPanel.add(female);
		signUpPanel.add(other);
		signUpPanel.add(dob);
		signUpPanel.add(goToLogin);
		signUpPanel.add(submitSignUp);
		signUpPanel.add(wrongID);
		signUpPanel.add(wrongPassword);
		signUpPanel.add(wrongDOB);
		signUpPanel.add(wrongPhone);
		signUpPanel.add(wrongEmail);
		signUpPanel.add (signUpWarn);

		//adding the components of the department panel
		deptPanel.add (customerRegd);
		deptPanel.add (newBooking);
		deptPanel.add (billing);
		deptPanel.add (enquiry);
		deptPanel.add (exit);
		deptPanel.add (availRooms);

		//adding the components of the recovery panel
		recoveryPanel.add (userIdRecover);
		recoveryPanel.add (userIdOk);
		recoveryPanel.add (recoveryWarn);

		//adding the components to the room status panel
		roomStatusPanel.add (lux01);
		roomStatusPanel.add (lux02);
		roomStatusPanel.add (lux03);
		roomStatusPanel.add (lux04);
		roomStatusPanel.add (lux05);
		roomStatusPanel.add (lux06);
		roomStatusPanel.add (lux07);
		roomStatusPanel.add (lux08);
		roomStatusPanel.add (lux11);
		roomStatusPanel.add (lux12);
		roomStatusPanel.add (lux13);
		roomStatusPanel.add (lux14);
		roomStatusPanel.add (lux15);
		roomStatusPanel.add (lux16);
		roomStatusPanel.add (lux17);
		roomStatusPanel.add (lux18);
		roomStatusPanel.add (lux21);
		roomStatusPanel.add (lux22);
		roomStatusPanel.add (lux23);
		roomStatusPanel.add (lux24);
		roomStatusPanel.add (lux25);
		roomStatusPanel.add (lux26);
		roomStatusPanel.add (lux27);
		roomStatusPanel.add (lux28);
		roomStatusPanel.add (lux31);
		roomStatusPanel.add (lux32);
		roomStatusPanel.add (lux33);
		roomStatusPanel.add (lux34);
		roomStatusPanel.add (lux35);
		roomStatusPanel.add (lux36);
		roomStatusPanel.add (lux37);
		roomStatusPanel.add (lux38);
		roomStatusPanel.add (lux41);
		roomStatusPanel.add (lux42);
		roomStatusPanel.add (lux43);
		roomStatusPanel.add (lux44);
		roomStatusPanel.add (lux45);
		roomStatusPanel.add (lux46);
		roomStatusPanel.add (lux47);
		roomStatusPanel.add (lux48);
		roomStatusPanel.add (lux51);
		roomStatusPanel.add (lux52);
		roomStatusPanel.add (lux53);
		roomStatusPanel.add (lux54);
		roomStatusPanel.add (lux55);
		roomStatusPanel.add (lux56);
		roomStatusPanel.add (lux57);
		roomStatusPanel.add (lux58);
		roomStatusPanel.add (lux61);
		roomStatusPanel.add (lux62);
		roomStatusPanel.add (lux63);
		roomStatusPanel.add (lux64);
		roomStatusPanel.add (lux65);
		roomStatusPanel.add (lux66);
		roomStatusPanel.add (lux67);
		roomStatusPanel.add (lux68);
		roomStatusPanel.add (lux71);
		roomStatusPanel.add (lux72);
		roomStatusPanel.add (lux73);
		roomStatusPanel.add (lux74);
		roomStatusPanel.add (lux75);
		roomStatusPanel.add (lux76);
		roomStatusPanel.add (lux77);
		roomStatusPanel.add (lux78);
		roomStatusPanel.add (lux81);
		roomStatusPanel.add (lux82);
		roomStatusPanel.add (lux83);
		roomStatusPanel.add (lux84);
		roomStatusPanel.add (lux85);
		roomStatusPanel.add (lux86);
		roomStatusPanel.add (lux87);
		roomStatusPanel.add (lux88);
		roomStatusPanel.add (lux91);
		roomStatusPanel.add (lux92);
		roomStatusPanel.add (lux93);
		roomStatusPanel.add (lux94);
		roomStatusPanel.add (lux95);
		roomStatusPanel.add (lux96);
		roomStatusPanel.add (lux97);
		roomStatusPanel.add (lux98);
		roomStatusPanel.add (suLux01);
		roomStatusPanel.add (suLux02);
		roomStatusPanel.add (suLux03);
		roomStatusPanel.add (suLux04);
		roomStatusPanel.add (suLux11);
		roomStatusPanel.add (suLux12);
		roomStatusPanel.add (suLux13);
		roomStatusPanel.add (suLux14);
		roomStatusPanel.add (suLux21);
		roomStatusPanel.add (suLux22);
		roomStatusPanel.add (suLux23);
		roomStatusPanel.add (suLux24);
		roomStatusPanel.add (suLux31);
		roomStatusPanel.add (suLux32);
		roomStatusPanel.add (suLux33);
		roomStatusPanel.add (suLux34);
		roomStatusPanel.add (suLux41);
		roomStatusPanel.add (suLux42);
		roomStatusPanel.add (suLux43);
		roomStatusPanel.add (suLux44);
		roomStatusPanel.add (suLux51);
		roomStatusPanel.add (suLux52);
		roomStatusPanel.add (suLux53);
		roomStatusPanel.add (suLux54);
		roomStatusPanel.add (suLux61);
		roomStatusPanel.add (suLux62);
		roomStatusPanel.add (suLux63);
		roomStatusPanel.add (suLux64);
		roomStatusPanel.add (suLux71);
		roomStatusPanel.add (suLux72);
		roomStatusPanel.add (suLux73);
		roomStatusPanel.add (suLux74);
		roomStatusPanel.add (suLux81);
		roomStatusPanel.add (suLux82);
		roomStatusPanel.add (suLux83);
		roomStatusPanel.add (suLux84);
		roomStatusPanel.add (suLux91);
		roomStatusPanel.add (suLux92);
		roomStatusPanel.add (suLux93);
		roomStatusPanel.add (suLux94);
		roomStatusPanel.add (pm01);
		roomStatusPanel.add (pm11);
		roomStatusPanel.add (pm21);
		roomStatusPanel.add (pm31);
		roomStatusPanel.add (pm41);
		roomStatusPanel.add (pm51);
		roomStatusPanel.add (pm61);
		roomStatusPanel.add (pm71);
		roomStatusPanel.add (pm81);
		roomStatusPanel.add (pm91);
		roomStatusPanel.add (pd01);
		roomStatusPanel.add (pd11);
		roomStatusPanel.add (pd21);
		roomStatusPanel.add (pd31);
		roomStatusPanel.add (pd41);
		roomStatusPanel.add (pd51);
		roomStatusPanel.add (pd61);
		roomStatusPanel.add (pd71);
		roomStatusPanel.add (pd81);
		roomStatusPanel.add (pd91);
		roomStatusPanel.add (ex01);
		roomStatusPanel.add (ex11);
		roomStatusPanel.add (ex21);
		roomStatusPanel.add (ex31);
		roomStatusPanel.add (ex41);
		roomStatusPanel.add (ex51);
		roomStatusPanel.add (ex61);
		roomStatusPanel.add (ex71);
		roomStatusPanel.add (ex81);
		roomStatusPanel.add (ex91);

		//adding the components of the customer registration panel
		newCustPanel.add (custCodeRegd);
		newCustPanel.add (custFirstNameRegd);
		newCustPanel.add (custMidNameRegd);
		newCustPanel.add (custLastNameRegd);
		newCustPanel.add (custAgeRegd);
		newCustPanel.add (custMSRegd);
		newCustPanel.add (custCountryRegd);
		newCustPanel.add (custPassportRegd);
		newCustPanel.add (custVisaRegd);
		newCustPanel.add (custResidentRegd);
		newCustPanel.add (custOfficeRegd);
		newCustPanel.add (custPhoneRegd);
		newCustPanel.add (custEmailRegd);
		newCustPanel.add (custStatusRegd);
		newCustPanel.add (custMale);
		newCustPanel.add (custFemale);
		newCustPanel.add (custOther);
		newCustPanel.add (register);
		newCustPanel.add (homeCustRegd);
		newCustPanel.add (wrongInfo);
		newCustPanel.add (wrongCustMail);
		newCustPanel.add (wrongCustPh);
		newCustPanel.add (newRegd);

		//adding the components of booking panel
		bookPanel.add (custCodeBook);
		bookPanel.add (custNameBook);
		bookPanel.add (custAddrBook);
		bookPanel.add (custPhoneBook);
		bookPanel.add (custEmailBook);
		bookPanel.add (custStatusBook);
		bookPanel.add (custGenderBook);
		bookPanel.add (bookCode);
		bookPanel.add (roomBook);
		bookPanel.add (arriveBook);
		bookPanel.add (personsBook);
		bookPanel.add (relationBook);
		bookPanel.add (fetchDataBook);
		bookPanel.add (availRoomBook);
		bookPanel.add (bookRoomButton);
		bookPanel.add (homeBook);
		bookPanel.add (newBook);
		bookPanel.add (bookingWarn);

		//adding the components of billing panel
		billPanel.add (bookCodeBill);
		billPanel.add (custCodeBill);
		billPanel.add (custNameBill);
		billPanel.add (bookedRoomBill);
		billPanel.add (personsBill);
		billPanel.add (arriveBill);
		billPanel.add (billCode);
		billPanel.add (departBill);
		billPanel.add (roomRentBill);
		billPanel.add (serviceBill);
		billPanel.add (grossBill);
		billPanel.add (gstBill);
		billPanel.add (discountBill);
		billPanel.add (netBill);
		billPanel.add (modeBill);
		billPanel.add (cardNoBill);
		billPanel.add (receivedBill);
		billPanel.add (pendingBill);
		billPanel.add (billButton);
		billPanel.add (homeBill);
		billPanel.add (billingWarn);
		billPanel.add (newBill);
		billPanel.add (fetchDataBill);
		billPanel.add (calcGross);
		billPanel.add (calcNet);
		billPanel.add (calcPending);

		//adding the components of enquirypanel
		queryPanel.add (custCodeEnq);
		queryPanel.add (checkData);
		queryPanel.add (homeEnq);
		queryPanel.add (customerData);

		//connecting to the database
		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:xe","prateekshyap","Soni1999");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	//focuslisteners for user name and pass word fields of the login page
	public static void usernameFocusGained (FocusEvent fe)
	{
		if (username.getText ().equals ("Username"))
		{
			username.setText ("");
			username.setForeground (Color.BLACK);
		}
	}

	public static void usernameFocusLost (FocusEvent fe)
	{
		if (username.getText ().equals (""))
		{
			username.setText ("Username");
			username.setForeground (Color.GRAY);
		}
	}

	public static void passwordFocusGained (FocusEvent fe)
	{
		if (password.getText ().equals ("Password"))
		{
			password.setText ("");
			password.setForeground (Color.BLACK);
			password.setEchoChar ('*');
		}
	}

	public static void passwordFocusLost (FocusEvent fe)
	{
		if (password.getText ().equals (""))
		{
			password.setText ("Password");
			password.setForeground (Color.GRAY);
			password.setEchoChar ((char)0);
		}
	}

	//focuslisteners and keylisteners for the textfields of the signup panel
	public void firstNameFocusGained(FocusEvent fe)
	{
		if(firstName.getText().equals("First Name"))
		{
			firstName.setText("");
			firstName.setForeground (Color.BLACK);
		}
	}

	public void firstNameFocusLost(FocusEvent fe)
	{
		if(firstName.getText().equals(""))
		{
			firstName.setText("First Name");
			firstName.setForeground (Color.GRAY);
		}
	}

	public void firstNameTyped(KeyEvent ke)
	{
		if(firstName.getText().equals(""))
		{
			char kc=ke.getKeyChar();
			if(Character.isLowerCase(kc))
				ke.setKeyChar(Character.toUpperCase(kc));
		}
	}

	public void middleNameFocusGained(FocusEvent fe)
	{
		if(middleName.getText().equals("Middle Name"))
		{
			middleName.setForeground (Color.BLACK);
			middleName.setText("");
		}
	}

	public void middleNameFocusLost(FocusEvent fe)
	{
		if(middleName.getText().equals(""))
		{
			middleName.setForeground (Color.GRAY);
			middleName.setText("Middle Name");
		}
	}

	public void middleNameTyped(KeyEvent ke)
	{
		if(middleName.getText().equals(""))
		{
			char kc=ke.getKeyChar();
			if(Character.isLowerCase(kc))
				ke.setKeyChar(Character.toUpperCase(kc));
		}
	}

	public void lastNameFocusGained(FocusEvent fe)
	{
		if(lastName.getText().equals("Last Name"))
		{
			lastName.setForeground (Color.BLACK);
			lastName.setText("");
		}
	}

	public void lastNameFocusLost(FocusEvent fe)
	{
		if(lastName.getText().equals(""))
		{
			lastName.setForeground (Color.GRAY);
			lastName.setText("Last Name");
		}
	}

	public void lastNameTyped(KeyEvent ke)
	{
		if(lastName.getText().equals(""))
		{
			char kc=ke.getKeyChar();
			if(Character.isLowerCase(kc))
				ke.setKeyChar(Character.toUpperCase(kc));
		}
	}

	public void emailFocusGained(FocusEvent fe)
	{
		if(email.getText().equals("Email address"))
		{
			email.setForeground (Color.BLACK);
			email.setText("");
		}
	}

	public void emailFocusLost(FocusEvent fe)
	{
		if(email.getText().equals(""))
		{
			email.setForeground (Color.GRAY);
			email.setText("Email address");
		}
	}

	public void password1FocusGained(FocusEvent fe)
	{
		if(password1.getText().equals("Password"))
		{
			password1.setForeground (Color.BLACK);
			password1.setText("");
			password1.setEchoChar('*');
		}
	}

	public void password1FocusLost(FocusEvent fe)
	{
		if(password1.getText().equals(""))
		{
			password1.setForeground (Color.GRAY);
			password1.setText("Password");
			password1.setEchoChar((char)0);
		}
	}

	public void password2FocusGained(FocusEvent fe)
	{
		if(password2.getText().equals("Re-type Password"))
		{
			password2.setForeground (Color.BLACK);
			password2.setText("");
			password2.setEchoChar('*');
		}
	}

	public void password2FocusLost(FocusEvent fe)
	{
		if(password2.getText().equals(""))
		{
			password2.setForeground (Color.GRAY);
			password2.setText("Re-type Password");
			password2.setEchoChar((char)0);
		}
	}

	public void phoneFocusGained(FocusEvent fe)
	{
		if(phone.getText().equals("Phone"))
		{
			phone.setForeground (Color.BLACK);
			phone.setText("");
		}
	}

	public void phoneFocusLost(FocusEvent fe)
	{
		if(phone.getText().equals(""))
		{
			phone.setForeground (Color.GRAY);
			phone.setText("Phone");
		}
	}

	public void useridFocusGained(FocusEvent fe)
	{
		if(userid.getText().equals("User ID"))
		{
			userid.setForeground (Color.BLACK);
			userid.setText("");
		}
	}

	public void useridFocusLost(FocusEvent fe)
	{
		if(userid.getText().equals(""))
		{
			userid.setForeground (Color.GRAY);
			userid.setText("User ID");
		}
	}

	public void dobFocusGained(FocusEvent fe)
	{
		if(dob.getText().equals("Date of birth (dd/mm/yyyy)"))
		{
			dob.setForeground (Color.BLACK);
			dob.setText("");
		}
	}

	public void dobFocusLost(FocusEvent fe)
	{
		if(dob.getText().equals(""))
		{
			dob.setForeground (Color.GRAY);
			dob.setText("Date of birth (dd/mm/yyyy)");
		}
	}

	//focuslistener for userid of recovery panel
	public static void userIdRecoverFocusGained (FocusEvent fe)
	{
		if (userIdRecover.getText ().equals ("Enter user ID"))
			userIdRecover.setText ("");
	}

	public static void userIdRecoverFocusLost (FocusEvent fe)
	{
		if (userIdRecover.getText ().equals (""))
			userIdRecover.setText ("Enter user ID");
	}

	//focuslisteners for the password fields of the recovery panel
	public static void newPasswordFocusGained (FocusEvent fe)
	{
		if (newPassword.getText ().equals ("New Password"))
		{
			newPassword.setText ("");
			newPassword.setEchoChar ('*');
		}
	}

	public static void newPasswordFocusLost (FocusEvent fe)
	{
		if (newPassword.getText ().equals (""))
		{
			newPassword.setText ("New Password");
			newPassword.setEchoChar ((char)0);
		}
	}

	public static void retypeNewPasswordFocusGained (FocusEvent fe)
	{
		if (retypeNewPassword.getText ().equals ("Re-type Password"))
		{
			retypeNewPassword.setText ("");
			retypeNewPassword.setEchoChar ('*');
		}
	}

	public static void retypeNewPasswordFocusLost (FocusEvent fe)
	{
		if (retypeNewPassword.getText ().equals (""))
		{
			retypeNewPassword.setText ("Re-type Password");
			retypeNewPassword.setEchoChar ((char)0);
		}
	}

	//focuslisteners for the new customer registration page
	public static void custCodeRegdFocusGained (FocusEvent fe)
	{
			if (custCodeRegd.getText ().equals ("Code"))
			{
				custCodeRegd.setText ("");
				custCodeRegd.setForeground (Color.BLACK);
			}
	}

	public static void custCodeRegdFocusLost (FocusEvent fe)
	{
			if (custCodeRegd.getText ().equals (""))
			{
				custCodeRegd.setText ("Code");
				custCodeRegd.setForeground (Color.GRAY);
			}
	}

	public static void custFirstNameRegdFocusGained (FocusEvent fe)
	{
			if (custFirstNameRegd.getText ().equals ("First Name"))
			{
				custFirstNameRegd.setText ("");
				custFirstNameRegd.setForeground (Color.BLACK);
			}
	}

	public static void custFirstNameRegdFocusLost (FocusEvent fe)
	{
			if (custFirstNameRegd.getText ().equals (""))
			{
				custFirstNameRegd.setText ("First Name");
				custFirstNameRegd.setForeground (Color.GRAY);
			}
	}

	public static void custMidNameRegdFocusGained (FocusEvent fe)
	{
			if (custMidNameRegd.getText ().equals ("Middle Name"))
			{
				custMidNameRegd.setText ("");
				custMidNameRegd.setForeground (Color.BLACK);
			}
	}

	public static void custMidNameRegdFocusLost (FocusEvent fe)
	{
			if (custMidNameRegd.getText ().equals (""))
			{
				custMidNameRegd.setText ("Middle Name");
				custMidNameRegd.setForeground (Color.GRAY);
			}
	}

	public static void custLastNameRegdFocusGained (FocusEvent fe)
	{
			if (custLastNameRegd.getText ().equals ("Last Name"))
			{
				custLastNameRegd.setText ("");
				custLastNameRegd.setForeground (Color.BLACK);
			}
	}

	public static void custLastNameRegdFocusLost (FocusEvent fe)
	{
			if (custLastNameRegd.getText ().equals (""))
			{
				custLastNameRegd.setText ("Last Name");
				custLastNameRegd.setForeground (Color.GRAY);
			}
	}

	public static void custCountryRegdFocusGained (FocusEvent fe)
	{
			if (custCountryRegd.getText ().equals ("Country"))
			{
				custCountryRegd.setText ("");
				custCountryRegd.setForeground (Color.BLACK);
			}
	}

	public static void custCountryRegdFocusLost (FocusEvent fe)
	{
			if (custCountryRegd.getText ().equals (""))
			{
				custCountryRegd.setText ("Country");
				custCountryRegd.setForeground (Color.GRAY);
			}
	}

	public static void custPassportRegdFocusGained (FocusEvent fe)
	{
			if (custPassportRegd.getText ().equals ("Passport Number"))
			{
				custPassportRegd.setText ("");
				custPassportRegd.setForeground (Color.BLACK);
			}
	}

	public static void custPassportRegdFocusLost (FocusEvent fe)
	{
			if (custPassportRegd.getText ().equals (""))
			{
				custPassportRegd.setText ("Passport Number");
				custPassportRegd.setForeground (Color.GRAY);
			}
	}

	public static void custVisaRegdFocusGained (FocusEvent fe)
	{
			if (custVisaRegd.getText ().equals ("Visa Number"))
			{
				custVisaRegd.setText ("");
				custVisaRegd.setForeground (Color.BLACK);
			}
	}

	public static void custVisaRegdFocusLost (FocusEvent fe)
	{
			if (custVisaRegd.getText ().equals (""))
			{
				custVisaRegd.setText ("Visa Number");
				custVisaRegd.setForeground (Color.GRAY);
			}
	}

	public static void custResidentRegdFocusGained (FocusEvent fe)
	{
		if (custResidentRegd.getText ().equals ("Residential Address"))
		{
			custResidentRegd.setText ("");
			custResidentRegd.setForeground (Color.BLACK);
		}
	}

	public static void custResidentRegdFocusLost (FocusEvent fe)
	{
		if (custResidentRegd.getText ().equals (""))
		{
			custResidentRegd.setText ("Residential Address");
			custResidentRegd.setForeground (Color.GRAY);
		}
	}

	public static void custOfficeRegdFocusGained (FocusEvent fe)
	{
		if (custOfficeRegd.getText ().equals ("Office Address (if any)"))
		{
			custOfficeRegd.setText ("");
			custOfficeRegd.setForeground (Color.BLACK);
		}
	}

	public static void custOfficeRegdFocusLost (FocusEvent fe)
	{
		if (custOfficeRegd.getText ().equals (""))
		{
			custOfficeRegd.setText ("Office Address (if any)");
			custOfficeRegd.setForeground (Color.GRAY);
		}
	}


	public static void custPhoneRegdFocusGained (FocusEvent fe)
	{
			if (custPhoneRegd.getText ().equals ("Phone"))
			{
				custPhoneRegd.setText ("");
				custPhoneRegd.setForeground (Color.BLACK);
			}
	}

	public static void custPhoneRegdFocusLost (FocusEvent fe)
	{
			if (custPhoneRegd.getText ().equals (""))
			{
				custPhoneRegd.setText ("Phone");
				custPhoneRegd.setForeground (Color.GRAY);
			}
	}

	public static void custEmailRegdFocusGained (FocusEvent fe)
	{
			if (custEmailRegd.getText ().equals ("Email"))
			{
				custEmailRegd.setText ("");
				custEmailRegd.setForeground (Color.BLACK);
			}
	}

	public static void custEmailRegdFocusLost (FocusEvent fe)
	{
			if (custEmailRegd.getText ().equals (""))
			{
				custEmailRegd.setText ("Email");
				custEmailRegd.setForeground (Color.GRAY);
			}
	}

	
	//actionlistener for the signup and gotologin button
	public void goToLoginAction(ActionEvent ae)
	{
		username.setText ("Username");
		username.setForeground (Color.GRAY);
		password.setText ("Password");
		password.setEchoChar ((char)0);
		password.setForeground (Color.GRAY);
		cardLayout.show (mainPanel, "First");
	}

	public void submitSignUpAction(ActionEvent ae)
	{
		try{
		String fn=firstName.getText();
		String mn=middleName.getText();
		String ln=lastName.getText();
		if(fn.equals("First Name"))
			fn="";
		if(mn.equals("Middle Name"))
			mn="";
		if(ln.equals("Last Name"))
			ln="";
		String e=email.getText();
		if(e.equals("Email address"))
			e="";
		int flag=0;
		boolean eStr=false;
		for(int i=0;i<e.length();i++)
		{
			char ch=e.charAt(i);
			if(ch=='@')
			{
				flag++;
				break;
			}
		}
		if(flag==1)
			eStr=true;
		else
			eStr=false;
		String pw1=password1.getText();
		String pw2=password2.getText();
		boolean phStr=true;
		long pho=0;
		try{
		pho=Long.parseLong(phone.getText());
		}catch(Exception ee)
		{
			phStr=false;
		}
		String ph=Long.toString(pho);
		if(ph.length()>10||ph.length()<10)
			phStr=false;
		else
		{
			for(int i=0;i<ph.length();i++)
			{
				char ch=ph.charAt(i);
				if(ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9')
					phStr=true;
				else
					phStr=false;
			}
		}
		String id=userid.getText();
		if(id.equals("Choose unique ID"))
			id="";
		String d=dob.getText();
		if(d.equals("Date of birth (dd/mm/yyyy)"))
			d="";
		boolean dobStr=true;
		if(d.length()>10||d.length()<10)
			dobStr=false;
		else
		{
			String y=d.substring(6,10);
			int iy=Integer.parseInt(y);
			String m=d.substring(3,5);
			String dt=d.substring(0,2);
			int i=iy%4;
			if(i==0)
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30")))
					dobStr=false;
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					dobStr=false;
			}
			else
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30"))||(m.equals("02")&&dt.equals("29")))
					dobStr=false;
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					dobStr=false;
			}
		}
		String gen="";
		if(male.isSelected())
			gen="M";
		else if(female.isSelected())
			gen="F";
		else if(other.isSelected())
			gen="O";
		if(fn.equals("")||ln.equals("")||e.equals("")||pw1.equals("")||pw2.equals("")||ph.equals("")||id.equals("")||d.equals("")||gen.equals(""))
		{
			signUpWarn.setText("Information is not complete!");
			wrongDOB.setText("");
			wrongEmail.setText("");
			wrongPhone.setText("");
			wrongPassword.setText("");
		}
		else if(pw1.equals(pw2)==false)
		{
			signUpWarn.setText("");
			wrongPassword.setText("Passwords are not matching!");
			wrongDOB.setText("");
			wrongEmail.setText("");
			wrongPhone.setText("");
		}
		else if(eStr==false)
		{
			signUpWarn.setText("");
			wrongPassword.setText("");
			wrongEmail.setText("Please enter a valid email address!");
			wrongDOB.setText("");
			wrongPhone.setText("");
		}
		else if(phStr==false)
		{
			signUpWarn.setText("");
			wrongPassword.setText("");
			wrongEmail.setText("");
			wrongPhone.setText("Please enter a valid phone number!");
			wrongDOB.setText("");
		}
		else if(dobStr==false)
		{
			signUpWarn.setText("");
			wrongPassword.setText("");
			wrongEmail.setText("");
			wrongPhone.setText("");
			wrongDOB.setText("Please enter a valid date!");
		}
		else
		{
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("select user_id from Admin_Details");
			int flg=0;
			while(rs.next())
			{
				String ss=rs.getString(1);
				if(ss.equals(id))
				{
					wrongID.setText("This ID is already taken!");
					flg++;
					break;
				}
			}
			if(flg==0)
			{
				int sl=0;
				PreparedStatement ps=conn.prepareStatement("insert into Admin_Details values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1,fn);
				ps.setString(2,mn);
				ps.setString(3,ln);
				ps.setString(4,e);
				ps.setString(5,gen);
				ps.setString(6,id);
				ps.setString(7,pw1);
				ps.setLong(8,pho);
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date ud=sdf.parse(d);
				java.sql.Date sqd=new java.sql.Date(ud.getTime());
				ps.setDate(9,sqd);
				int status=ps.executeUpdate();
				if(status>0)
				{
					signUpWarn.setText("");
					wrongPassword.setText("");
					wrongEmail.setText("");
					wrongPhone.setText("");
					wrongDOB.setText("");
					signUpWarn.setText("Signed up successfully. Go to Login page.");
				}
				else
				{
					signUpWarn.setText("Error!");
				}
			}
		}
		}catch(Exception ee)
		{
			login.setText("Error!");
		}
	}

	//actionlistener for logout button
	public static void logoutAction (ActionEvent ae)
	{
		roomBooking = 0;
		username.setText ("Username");
		username.setForeground (Color.GRAY);
		password.setText ("Password");
		password.setForeground (Color.GRAY);
		password.setEchoChar ((char)0);
		container.remove (pinkBackLabel);
		container.remove (purpleBackLabel);
		container.remove (greenBackLabel);
		container.remove (pureBlueBackLabel);
		container.remove (grayBlueBackLabel);
		container.remove (redBackLabel);
		container.add (blueBackLabel);
		home.setEnabled (false);
		logout.setEnabled (false);
		cardLayout.show (mainPanel, "First");
		welcomeLabel.setText ("!!!Welcome to our hotel!!!");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for home button
	public static void homeAction (ActionEvent ae)
	{
		container.remove (pinkBackLabel);
		container.remove (purpleBackLabel);
		container.remove (greenBackLabel);
		container.remove (pureBlueBackLabel);
		container.remove (grayBlueBackLabel);
		container.remove (blueBackLabel);
		container.add (redBackLabel);
		home.setEnabled (false);
		cardLayout.show (mainPanel, "Dept");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for login button
	public static void loginAction (ActionEvent ae)
	{
		loginWarn.setText ("");
		String typedUserId = username.getText ();
		String typedPassword = password.getText ();
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select user_id, password from Admin_Details");
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				if (rs.getString(1).equals(typedUserId)&&rs.getString(2).equals(typedPassword))
				{
					container.remove (blueBackLabel);
					container.add (redBackLabel);
					logout.setEnabled (true);
					welcomeLabel.setText ("Hotel Management System");
					cardLayout.show (mainPanel, "Dept");
				}
				else
					loginWarn.setText ("Invalid user ID or password!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select room_no, status from Room_Details");
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				String roomNumber = rs.getString (1);
				String status= rs.getString (2);
				if (roomNumber.equals ("RDE001"))
				{
					if (status.equals ("Available"))
						lux01.setSelected (false);
					else
						lux01.setSelected (true);
				}
				else if (roomNumber.equals ("RDE002"))
				{
					if (status.equals ("Available"))
						lux02.setSelected (false);
					else
						lux02.setSelected (true);
				}
				else if (roomNumber.equals ("RDE003"))
				{
					if (status.equals ("Available"))
						lux03.setSelected (false);
					else
						lux03.setSelected (true);
				}
				else if (roomNumber.equals ("RDE004"))
				{
					if (status.equals ("Available"))
						lux04.setSelected (false);
					else
						lux04.setSelected (true);
				}
				else if (roomNumber.equals ("RDE005"))
				{
					if (status.equals ("Available"))
						lux05.setSelected (false);
					else
						lux05.setSelected (true);
				}
				else if (roomNumber.equals ("RDE006"))
				{
					if (status.equals ("Available"))
						lux06.setSelected (false);
					else
						lux06.setSelected (true);
				}
				else if (roomNumber.equals ("RDE007"))
				{
					if (status.equals ("Available"))
						lux07.setSelected (false);
					else
						lux07.setSelected (true);
				}
				else if (roomNumber.equals ("RDE008"))
				{
					if (status.equals ("Available"))
						lux08.setSelected (false);
					else
						lux08.setSelected (true);
				}
				else if (roomNumber.equals ("RDE101"))
				{
					if (status.equals ("Available"))
						lux11.setSelected (false);
					else
						lux11.setSelected (true);
				}
				else if (roomNumber.equals ("RDE102"))
				{
					if (status.equals ("Available"))
						lux12.setSelected (false);
					else
						lux12.setSelected (true);
				}
				else if (roomNumber.equals ("RDE103"))
				{
					if (status.equals ("Available"))
						lux13.setSelected (false);
					else
						lux13.setSelected (true);
				}
				else if (roomNumber.equals ("RDE104"))
				{
					if (status.equals ("Available"))
						lux14.setSelected (false);
					else
						lux14.setSelected (true);
				}
				else if (roomNumber.equals ("RDE105"))
				{
					if (status.equals ("Available"))
						lux15.setSelected (false);
					else
						lux15.setSelected (true);
				}
				else if (roomNumber.equals ("RDE106"))
				{
					if (status.equals ("Available"))
						lux16.setSelected (false);
					else
						lux16.setSelected (true);
				}
				else if (roomNumber.equals ("RDE107"))
				{
					if (status.equals ("Available"))
						lux17.setSelected (false);
					else
						lux17.setSelected (true);
				}
				else if (roomNumber.equals ("RDE108"))
				{
					if (status.equals ("Available"))
						lux18.setSelected (false);
					else
						lux18.setSelected (true);
				}
				else if (roomNumber.equals ("RDE201"))
				{
					if (status.equals ("Available"))
						lux21.setSelected (false);
					else
						lux21.setSelected (true);
				}
				else if (roomNumber.equals ("RDE202"))
				{
					if (status.equals ("Available"))
						lux22.setSelected (false);
					else
						lux22.setSelected (true);
				}
				else if (roomNumber.equals ("RDE203"))
				{
					if (status.equals ("Available"))
						lux23.setSelected (false);
					else
						lux23.setSelected (true);
				}
				else if (roomNumber.equals ("RDE204"))
				{
					if (status.equals ("Available"))
						lux24.setSelected (false);
					else
						lux24.setSelected (true);
				}
				else if (roomNumber.equals ("RDE205"))
				{
					if (status.equals ("Available"))
						lux25.setSelected (false);
					else
						lux25.setSelected (true);
				}
				else if (roomNumber.equals ("RDE206"))
				{
					if (status.equals ("Available"))
						lux26.setSelected (false);
					else
						lux26.setSelected (true);
				}
				else if (roomNumber.equals ("RDE207"))
				{
					if (status.equals ("Available"))
						lux27.setSelected (false);
					else
						lux27.setSelected (true);
				}
				else if (roomNumber.equals ("RDE208"))
				{
					if (status.equals ("Available"))
						lux28.setSelected (false);
					else
						lux28.setSelected (true);
				}
				else if (roomNumber.equals ("RDE301"))
				{
					if (status.equals ("Available"))
						lux31.setSelected (false);
					else
						lux31.setSelected (true);
				}
				else if (roomNumber.equals ("RDE302"))
				{
					if (status.equals ("Available"))
						lux32.setSelected (false);
					else
						lux32.setSelected (true);
				}
				else if (roomNumber.equals ("RDE303"))
				{
					if (status.equals ("Available"))
						lux33.setSelected (false);
					else
						lux33.setSelected (true);
				}
				else if (roomNumber.equals ("RDE304"))
				{
					if (status.equals ("Available"))
						lux34.setSelected (false);
					else
						lux34.setSelected (true);
				}
				else if (roomNumber.equals ("RDE305"))
				{
					if (status.equals ("Available"))
						lux35.setSelected (false);
					else
						lux35.setSelected (true);
				}
				else if (roomNumber.equals ("RDE306"))
				{
					if (status.equals ("Available"))
						lux36.setSelected (false);
					else
						lux36.setSelected (true);
				}
				else if (roomNumber.equals ("RDE307"))
				{
					if (status.equals ("Available"))
						lux37.setSelected (false);
					else
						lux37.setSelected (true);
				}
				else if (roomNumber.equals ("RDE308"))
				{
					if (status.equals ("Available"))
						lux38.setSelected (false);
					else
						lux38.setSelected (true);
				}
				else if (roomNumber.equals ("RDE401"))
				{
					if (status.equals ("Available"))
						lux41.setSelected (false);
					else
						lux41.setSelected (true);
				}
				else if (roomNumber.equals ("RDE402"))
				{
					if (status.equals ("Available"))
						lux42.setSelected (false);
					else
						lux42.setSelected (true);
				}
				else if (roomNumber.equals ("RDE403"))
				{
					if (status.equals ("Available"))
						lux43.setSelected (false);
					else
						lux43.setSelected (true);
				}
				else if (roomNumber.equals ("RDE404"))
				{
					if (status.equals ("Available"))
						lux44.setSelected (false);
					else
						lux44.setSelected (true);
				}
				else if (roomNumber.equals ("RDE405"))
				{
					if (status.equals ("Available"))
						lux45.setSelected (false);
					else
						lux45.setSelected (true);
				}
				else if (roomNumber.equals ("RDE406"))
				{
					if (status.equals ("Available"))
						lux46.setSelected (false);
					else
						lux46.setSelected (true);
				}
				else if (roomNumber.equals ("RDE407"))
				{
					if (status.equals ("Available"))
						lux47.setSelected (false);
					else
						lux47.setSelected (true);
				}
				else if (roomNumber.equals ("RDE408"))
				{
					if (status.equals ("Available"))
						lux48.setSelected (false);
					else
						lux48.setSelected (true);
				}
				else if (roomNumber.equals ("RDE501"))
				{
					if (status.equals ("Available"))
						lux51.setSelected (false);
					else
						lux51.setSelected (true);
				}
				else if (roomNumber.equals ("RDE502"))
				{
					if (status.equals ("Available"))
						lux52.setSelected (false);
					else
						lux52.setSelected (true);
				}
				else if (roomNumber.equals ("RDE503"))
				{
					if (status.equals ("Available"))
						lux53.setSelected (false);
					else
						lux53.setSelected (true);
				}
				else if (roomNumber.equals ("RDE504"))
				{
					if (status.equals ("Available"))
						lux54.setSelected (false);
					else
						lux54.setSelected (true);
				}
				else if (roomNumber.equals ("RDE505"))
				{
					if (status.equals ("Available"))
						lux55.setSelected (false);
					else
						lux55.setSelected (true);
				}
				else if (roomNumber.equals ("RDE506"))
				{
					if (status.equals ("Available"))
						lux56.setSelected (false);
					else
						lux56.setSelected (true);
				}
				else if (roomNumber.equals ("RDE507"))
				{
					if (status.equals ("Available"))
						lux57.setSelected (false);
					else
						lux57.setSelected (true);
				}
				else if (roomNumber.equals ("RDE508"))
				{
					if (status.equals ("Available"))
						lux58.setSelected (false);
					else
						lux58.setSelected (true);
				}
				else if (roomNumber.equals ("RDE601"))
				{
					if (status.equals ("Available"))
						lux61.setSelected (false);
					else
						lux61.setSelected (true);
				}
				else if (roomNumber.equals ("RDE602"))
				{
					if (status.equals ("Available"))
						lux62.setSelected (false);
					else
						lux62.setSelected (true);
				}
				else if (roomNumber.equals ("RDE603"))
				{
					if (status.equals ("Available"))
						lux63.setSelected (false);
					else
						lux63.setSelected (true);
				}
				else if (roomNumber.equals ("RDE604"))
				{
					if (status.equals ("Available"))
						lux64.setSelected (false);
					else
						lux64.setSelected (true);
				}
				else if (roomNumber.equals ("RDE605"))
				{
					if (status.equals ("Available"))
						lux65.setSelected (false);
					else
						lux65.setSelected (true);
				}
				else if (roomNumber.equals ("RDE606"))
				{
					if (status.equals ("Available"))
						lux66.setSelected (false);
					else
						lux66.setSelected (true);
				}
				else if (roomNumber.equals ("RDE607"))
				{
					if (status.equals ("Available"))
						lux67.setSelected (false);
					else
						lux67.setSelected (true);
				}
				else if (roomNumber.equals ("RDE608"))
				{
					if (status.equals ("Available"))
						lux68.setSelected (false);
					else
						lux68.setSelected (true);
				}
				else if (roomNumber.equals ("RDE701"))
				{
					if (status.equals ("Available"))
						lux71.setSelected (false);
					else
						lux71.setSelected (true);
				}
				else if (roomNumber.equals ("RDE702"))
				{
					if (status.equals ("Available"))
						lux72.setSelected (false);
					else
						lux72.setSelected (true);
				}
				else if (roomNumber.equals ("RDE703"))
				{
					if (status.equals ("Available"))
						lux73.setSelected (false);
					else
						lux73.setSelected (true);
				}
				else if (roomNumber.equals ("RDE704"))
				{
					if (status.equals ("Available"))
						lux74.setSelected (false);
					else
						lux74.setSelected (true);
				}
				else if (roomNumber.equals ("RDE705"))
				{
					if (status.equals ("Available"))
						lux75.setSelected (false);
					else
						lux75.setSelected (true);
				}
				else if (roomNumber.equals ("RDE706"))
				{
					if (status.equals ("Available"))
						lux76.setSelected (false);
					else
						lux76.setSelected (true);
				}
				else if (roomNumber.equals ("RDE707"))
				{
					if (status.equals ("Available"))
						lux77.setSelected (false);
					else
						lux77.setSelected (true);
				}
				else if (roomNumber.equals ("RDE708"))
				{
					if (status.equals ("Available"))
						lux78.setSelected (false);
					else
						lux78.setSelected (true);
				}
				else if (roomNumber.equals ("RDE801"))
				{
					if (status.equals ("Available"))
						lux81.setSelected (false);
					else
						lux81.setSelected (true);
				}
				else if (roomNumber.equals ("RDE802"))
				{
					if (status.equals ("Available"))
						lux82.setSelected (false);
					else
						lux82.setSelected (true);
				}
				else if (roomNumber.equals ("RDE803"))
				{
					if (status.equals ("Available"))
						lux83.setSelected (false);
					else
						lux83.setSelected (true);
				}
				else if (roomNumber.equals ("RDE804"))
				{
					if (status.equals ("Available"))
						lux84.setSelected (false);
					else
						lux84.setSelected (true);
				}
				else if (roomNumber.equals ("RDE805"))
				{
					if (status.equals ("Available"))
						lux85.setSelected (false);
					else
						lux85.setSelected (true);
				}
				else if (roomNumber.equals ("RDE806"))
				{
					if (status.equals ("Available"))
						lux86.setSelected (false);
					else
						lux86.setSelected (true);
				}
				else if (roomNumber.equals ("RDE807"))
				{
					if (status.equals ("Available"))
						lux87.setSelected (false);
					else
						lux87.setSelected (true);
				}
				else if (roomNumber.equals ("RDE808"))
				{
					if (status.equals ("Available"))
						lux88.setSelected (false);
					else
						lux88.setSelected (true);
				}
				else if (roomNumber.equals ("RDE901"))
				{
					if (status.equals ("Available"))
						lux91.setSelected (false);
					else
						lux91.setSelected (true);
				}
				else if (roomNumber.equals ("RDE902"))
				{
					if (status.equals ("Available"))
						lux92.setSelected (false);
					else
						lux92.setSelected (true);
				}
				else if (roomNumber.equals ("RDE903"))
				{
					if (status.equals ("Available"))
						lux93.setSelected (false);
					else
						lux93.setSelected (true);
				}
				else if (roomNumber.equals ("RDE904"))
				{
					if (status.equals ("Available"))
						lux94.setSelected (false);
					else
						lux94.setSelected (true);
				}
				else if (roomNumber.equals ("RDE905"))
				{
					if (status.equals ("Available"))
						lux95.setSelected (false);
					else
						lux95.setSelected (true);
				}
				else if (roomNumber.equals ("RDE906"))
				{
					if (status.equals ("Available"))
						lux96.setSelected (false);
					else
						lux96.setSelected (true);
				}
				else if (roomNumber.equals ("RDE907"))
				{
					if (status.equals ("Available"))
						lux97.setSelected (false);
					else
						lux97.setSelected (true);
				}
				else if (roomNumber.equals ("RDE908"))
				{
					if (status.equals ("Available"))
						lux98.setSelected (false);
					else
						lux98.setSelected (true);
				}
				else if (roomNumber.equals ("RSD001"))
				{
					if (status.equals ("Available"))
						suLux01.setSelected (false);
					else
						suLux01.setSelected (true);
				}
				else if (roomNumber.equals ("RSD002"))
				{
					if (status.equals ("Available"))
						suLux02.setSelected (false);
					else
						suLux02.setSelected (true);
				}
				else if (roomNumber.equals ("RSD003"))
				{
					if (status.equals ("Available"))
						suLux03.setSelected (false);
					else
						suLux03.setSelected (true);
				}
				else if (roomNumber.equals ("RSD004"))
				{
					if (status.equals ("Available"))
						suLux04.setSelected (false);
					else
						suLux04.setSelected (true);
				}
				else if (roomNumber.equals ("RSD101"))
				{
					if (status.equals ("Available"))
						suLux11.setSelected (false);
					else
						suLux11.setSelected (true);
				}
				else if (roomNumber.equals ("RSD102"))
				{
					if (status.equals ("Available"))
						suLux12.setSelected (false);
					else
						suLux12.setSelected (true);
				}
				else if (roomNumber.equals ("RSD103"))
				{
					if (status.equals ("Available"))
						suLux13.setSelected (false);
					else
						suLux13.setSelected (true);
				}
				else if (roomNumber.equals ("RSD104"))
				{
					if (status.equals ("Available"))
						suLux14.setSelected (false);
					else
						suLux14.setSelected (true);
				}
				else if (roomNumber.equals ("RSD201"))
				{
					if (status.equals ("Available"))
						suLux21.setSelected (false);
					else
						suLux21.setSelected (true);
				}
				else if (roomNumber.equals ("RSD202"))
				{
					if (status.equals ("Available"))
						suLux22.setSelected (false);
					else
						suLux22.setSelected (true);
				}
				else if (roomNumber.equals ("RSD203"))
				{
					if (status.equals ("Available"))
						suLux23.setSelected (false);
					else
						suLux23.setSelected (true);
				}
				else if (roomNumber.equals ("RSD204"))
				{
					if (status.equals ("Available"))
						suLux24.setSelected (false);
					else
						suLux24.setSelected (true);
				}
				else if (roomNumber.equals ("RSD301"))
				{
					if (status.equals ("Available"))
						suLux31.setSelected (false);
					else
						suLux31.setSelected (true);
				}
				else if (roomNumber.equals ("RSD302"))
				{
					if (status.equals ("Available"))
						suLux32.setSelected (false);
					else
						suLux32.setSelected (true);
				}
				else if (roomNumber.equals ("RSD303"))
				{
					if (status.equals ("Available"))
						suLux33.setSelected (false);
					else
						suLux33.setSelected (true);
				}
				else if (roomNumber.equals ("RSD304"))
				{
					if (status.equals ("Available"))
						suLux34.setSelected (false);
					else
						suLux34.setSelected (true);
				}
				else if (roomNumber.equals ("RSD401"))
				{
					if (status.equals ("Available"))
						suLux41.setSelected (false);
					else
						suLux41.setSelected (true);
				}
				else if (roomNumber.equals ("RSD402"))
				{
					if (status.equals ("Available"))
						suLux42.setSelected (false);
					else
						suLux42.setSelected (true);
				}
				else if (roomNumber.equals ("RSD403"))
				{
					if (status.equals ("Available"))
						suLux43.setSelected (false);
					else
						suLux43.setSelected (true);
				}
				else if (roomNumber.equals ("RSD404"))
				{
					if (status.equals ("Available"))
						suLux44.setSelected (false);
					else
						suLux44.setSelected (true);
				}
				else if (roomNumber.equals ("RSD501"))
				{
					if (status.equals ("Available"))
						suLux51.setSelected (false);
					else
						suLux51.setSelected (true);
				}
				else if (roomNumber.equals ("RSD502"))
				{
					if (status.equals ("Available"))
						suLux52.setSelected (false);
					else
						suLux52.setSelected (true);
				}
				else if (roomNumber.equals ("RSD503"))
				{
					if (status.equals ("Available"))
						suLux53.setSelected (false);
					else
						suLux53.setSelected (true);
				}
				else if (roomNumber.equals ("RSD504"))
				{
					if (status.equals ("Available"))
						suLux54.setSelected (false);
					else
						suLux54.setSelected (true);
				}
				else if (roomNumber.equals ("RSD601"))
				{
					if (status.equals ("Available"))
						suLux61.setSelected (false);
					else
						suLux61.setSelected (true);
				}
				else if (roomNumber.equals ("RSD602"))
				{
					if (status.equals ("Available"))
						suLux62.setSelected (false);
					else
						suLux62.setSelected (true);
				}
				else if (roomNumber.equals ("RSD603"))
				{
					if (status.equals ("Available"))
						suLux63.setSelected (false);
					else
						suLux63.setSelected (true);
				}
				else if (roomNumber.equals ("RSD604"))
				{
					if (status.equals ("Available"))
						suLux64.setSelected (false);
					else
						suLux64.setSelected (true);
				}
				else if (roomNumber.equals ("RSD701"))
				{
					if (status.equals ("Available"))
						suLux71.setSelected (false);
					else
						suLux71.setSelected (true);
				}
				else if (roomNumber.equals ("RSD702"))
				{
					if (status.equals ("Available"))
						suLux72.setSelected (false);
					else
						suLux72.setSelected (true);
				}
				else if (roomNumber.equals ("RSD703"))
				{
					if (status.equals ("Available"))
						suLux73.setSelected (false);
					else
						suLux73.setSelected (true);
				}
				else if (roomNumber.equals ("RSD704"))
				{
					if (status.equals ("Available"))
						suLux74.setSelected (false);
					else
						suLux74.setSelected (true);
				}
				else if (roomNumber.equals ("RSD801"))
				{
					if (status.equals ("Available"))
						suLux81.setSelected (false);
					else
						suLux81.setSelected (true);
				}
				else if (roomNumber.equals ("RSD802"))
				{
					if (status.equals ("Available"))
						suLux82.setSelected (false);
					else
						suLux82.setSelected (true);
				}
				else if (roomNumber.equals ("RSD803"))
				{
					if (status.equals ("Available"))
						suLux83.setSelected (false);
					else
						suLux83.setSelected (true);
				}
				else if (roomNumber.equals ("RSD804"))
				{
					if (status.equals ("Available"))
						suLux84.setSelected (false);
					else
						suLux84.setSelected (true);
				}
				else if (roomNumber.equals ("RSD901"))
				{
					if (status.equals ("Available"))
						suLux91.setSelected (false);
					else
						suLux91.setSelected (true);
				}
				else if (roomNumber.equals ("RSD902"))
				{
					if (status.equals ("Available"))
						suLux92.setSelected (false);
					else
						suLux92.setSelected (true);
				}
				else if (roomNumber.equals ("RSD903"))
				{
					if (status.equals ("Available"))
						suLux93.setSelected (false);
					else
						suLux93.setSelected (true);
				}
				else if (roomNumber.equals ("RSD904"))
				{
					if (status.equals ("Available"))
						suLux94.setSelected (false);
					else
						suLux94.setSelected (true);
				}
				else if (roomNumber.equals ("SPM001"))
				{
					if (status.equals ("Available"))
						pm01.setSelected (false);
					else
						pm01.setSelected (true);
				}
				else if (roomNumber.equals ("SPM101"))
				{
					if (status.equals ("Available"))
						pm11.setSelected (false);
					else
						pm11.setSelected (true);
				}
				else if (roomNumber.equals ("SPM201"))
				{
					if (status.equals ("Available"))
						pm21.setSelected (false);
					else
						pm21.setSelected (true);
				}
				else if (roomNumber.equals ("SPM301"))
				{
					if (status.equals ("Available"))
						pm31.setSelected (false);
					else
						pm31.setSelected (true);
				}
				else if (roomNumber.equals ("SPM401"))
				{
					if (status.equals ("Available"))
						pm41.setSelected (false);
					else
						pm41.setSelected (true);
				}
				else if (roomNumber.equals ("SPM501"))
				{
					if (status.equals ("Available"))
						pm51.setSelected (false);
					else
						pm51.setSelected (true);
				}
				else if (roomNumber.equals ("SPM601"))
				{
					if (status.equals ("Available"))
						pm61.setSelected (false);
					else
						pm61.setSelected (true);
				}
				else if (roomNumber.equals ("SPM701"))
				{
					if (status.equals ("Available"))
						pm71.setSelected (false);
					else
						pm71.setSelected (true);
				}
				else if (roomNumber.equals ("SPM801"))
				{
					if (status.equals ("Available"))
						pm81.setSelected (false);
					else
						pm81.setSelected (true);
				}
				else if (roomNumber.equals ("SPM901"))
				{
					if (status.equals ("Available"))
						pm91.setSelected (false);
					else
						pm91.setSelected (true);
				}
				else if (roomNumber.equals ("SPD001"))
				{
					if (status.equals ("Available"))
						pd01.setSelected (false);
					else
						pd01.setSelected (true);
				}
				else if (roomNumber.equals ("SPD101"))
				{
					if (status.equals ("Available"))
						pd11.setSelected (false);
					else
						pd11.setSelected (true);
				}
				else if (roomNumber.equals ("SPD201"))
				{
					if (status.equals ("Available"))
						pd21.setSelected (false);
					else
						pd21.setSelected (true);
				}
				else if (roomNumber.equals ("SPD301"))
				{
					if (status.equals ("Available"))
						pd31.setSelected (false);
					else
						pd31.setSelected (true);
				}
				else if (roomNumber.equals ("SPD401"))
				{
					if (status.equals ("Available"))
						pd41.setSelected (false);
					else
						pd41.setSelected (true);
				}
				else if (roomNumber.equals ("SPD501"))
				{
					if (status.equals ("Available"))
						pd51.setSelected (false);
					else
						pd51.setSelected (true);
				}
				else if (roomNumber.equals ("SPD601"))
				{
					if (status.equals ("Available"))
						pd61.setSelected (false);
					else
						pd61.setSelected (true);
				}
				else if (roomNumber.equals ("SPD701"))
				{
					if (status.equals ("Available"))
						pd71.setSelected (false);
					else
						pd71.setSelected (true);
				}
				else if (roomNumber.equals ("SPD801"))
				{
					if (status.equals ("Available"))
						pd81.setSelected (false);
					else
						pd81.setSelected (true);
				}
				else if (roomNumber.equals ("SPD901"))
				{
					if (status.equals ("Available"))
						pd91.setSelected (false);
					else
						pd91.setSelected (true);
				}
				else if (roomNumber.equals ("SEX001"))
				{
					if (status.equals ("Available"))
						ex01.setSelected (false);
					else
						ex01.setSelected (true);
				}
				else if (roomNumber.equals ("SEX101"))
				{
					if (status.equals ("Available"))
						ex11.setSelected (false);
					else
						ex11.setSelected (true);
				}
				else if (roomNumber.equals ("SEX201"))
				{
					if (status.equals ("Available"))
						ex21.setSelected (false);
					else
						ex21.setSelected (true);
				}
				else if (roomNumber.equals ("SEX301"))
				{
					if (status.equals ("Available"))
						ex31.setSelected (false);
					else
						ex31.setSelected (true);
				}
				else if (roomNumber.equals ("SEX401"))
				{
					if (status.equals ("Available"))
						ex41.setSelected (false);
					else
						ex41.setSelected (true);
				}
				else if (roomNumber.equals ("SEX501"))
				{
					if (status.equals ("Available"))
						ex51.setSelected (false);
					else
						ex51.setSelected (true);
				}
				else if (roomNumber.equals ("SEX601"))
				{
					if (status.equals ("Available"))
						ex61.setSelected (false);
					else
						ex61.setSelected (true);
				}
				else if (roomNumber.equals ("SEX701"))
				{
					if (status.equals ("Available"))
						ex71.setSelected (false);
					else
						ex71.setSelected (true);
				}
				else if (roomNumber.equals ("SEX801"))
				{
					if (status.equals ("Available"))
						ex81.setSelected (false);
					else
						ex81.setSelected (true);
				}
				else if (roomNumber.equals ("SEX901"))
				{
					if (status.equals ("Available"))
						ex91.setSelected (false);
					else
						ex91.setSelected (true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		container.revalidate();
		container.repaint();
	}

	//actionlistener for signup button
	public static void signUpAction (ActionEvent ae)
	{
		wrongEmail.setText ("");
		wrongPassword.setText ("");
		wrongPhone.setText ("");
		wrongID.setText ("");
		wrongDOB.setText ("");
		signUpWarn.setText ("");
		loginWarn.setText ("");
		firstName.setText ("First Name");
		firstName.setForeground(Color.GRAY);
		middleName.setText ("Middle Name");
		middleName.setForeground(Color.GRAY);
		lastName.setText ("Last Name");
		lastName.setForeground(Color.GRAY);
		email.setText ("Email address");
		email.setForeground(Color.GRAY);
		password1.setText ("Password");
		password1.setEchoChar((char)0);
		password1.setForeground(Color.GRAY);
		password2.setText ("Re-type Password");
		password2.setEchoChar((char)0);
		password2.setForeground(Color.GRAY);
		phone.setText ("Phone");
		phone.setForeground(Color.GRAY);
		userid.setText ("User ID");
		userid.setForeground(Color.GRAY);
		dob.setText ("Date of birth (dd/mm/yyyy)");
		dob.setForeground(Color.GRAY);
		cardLayout.show (mainPanel, "SignUp");
	}

	//actionlistener for recover button
	public static void recoverAction (ActionEvent ae)
	{
		loginWarn.setText ("");
		container.remove (blueBackLabel);
		container.add (darkBackLabel);
		cardLayout.show (mainPanel, "Recover");
		container.revalidate();
		container.repaint();
	}

/*action listeners for the departmental buttons*/
	//actionlistener for new customer registration
	public static void customerRegdAction (ActionEvent ae)
	{
		custCodeRegd.setText("Code");
		custCodeRegd.setForeground(Color.GRAY);
		custFirstNameRegd.setText("First Name");
		custFirstNameRegd.setForeground(Color.GRAY);
		custMidNameRegd.setText("Middle Name");
		custMidNameRegd.setForeground(Color.GRAY);
		custLastNameRegd.setText("Last Name");
		custLastNameRegd.setForeground(Color.GRAY);
		custCountryRegd.setText("Country");
		custCountryRegd.setForeground(Color.GRAY);
		custPassportRegd.setText("Passport Number");
		custPassportRegd.setForeground(Color.GRAY);
		custVisaRegd.setText("Visa Number");
		custVisaRegd.setForeground(Color.GRAY);
		custResidentRegd.setText("Residential Address");
		custResidentRegd.setForeground(Color.GRAY);
		custOfficeRegd.setText("Office Address (if any)");
		custOfficeRegd.setForeground(Color.GRAY);
		custPhoneRegd.setText("Phone");
		custPhoneRegd.setForeground(Color.GRAY);
		custEmailRegd.setText("Email");
		custEmailRegd.setForeground(Color.GRAY);
		custAgeRegd.setSelectedItem ("Age");
		custMSRegd.setSelectedItem ("Marital Status");
		custStatusRegd.setSelectedItem ("Customer Status");
		wrongInfo.setText ("");
		wrongCustMail.setText ("");
		wrongCustPh.setText ("");
		newCustPanel.repaint ();
		newCustPanel.revalidate ();
		container.remove (redBackLabel);
		container.add (pinkBackLabel);
		home.setEnabled (true);
		cardLayout.show (mainPanel, "New Registration");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for new booking
	public static void newBookingAction (ActionEvent ae)
	{
		custCodeBook.setEditable (true);
		custCodeBook.setText ("Enter Customer Code");
		custCodeBook.setForeground (Color.GRAY);
		custNameBook.setText ("Name");
		custNameBook.setForeground (Color.GRAY);
		custAddrBook.setText ("Address");
		custAddrBook.setForeground (Color.GRAY);
		custPhoneBook.setText ("Phone");
		custPhoneBook.setForeground (Color.GRAY);
		custEmailBook.setText ("Email Address");
		custEmailBook.setForeground (Color.GRAY);
		custGenderBook.setText ("Gender");
		custGenderBook.setForeground (Color.GRAY);
		custStatusBook.setText ("Status");
		custStatusBook.setForeground (Color.GRAY);
		bookCode.setText ("Booking Code");
		bookCode.setForeground (Color.GRAY);
		roomBook.setText ("Room Details");
		roomBook.setForeground (Color.GRAY);
		arriveBook.setText ("Date of Arrival (dd/mm/yyyy)");
		arriveBook.setForeground (Color.GRAY);
		personsBook.setText ("Number of Persons");
		personsBook.setForeground (Color.GRAY);
		relationBook.setText ("Relation");
		relationBook.setForeground (Color.GRAY);
		bookingWarn.setText ("");
		container.remove (redBackLabel);
		container.add (purpleBackLabel);
		home.setEnabled (true);
		cardLayout.show (mainPanel, "Booking");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for billing
	public static void billingAction (ActionEvent ae)
	{
		bookCodeBill.setText ("Enter Booking Code");
		bookCodeBill.setForeground (Color.GRAY);
		bookCodeBill.setEditable (true);
		custCodeBill.setText ("Customer Code");
		custCodeBill.setForeground (Color.GRAY);
		custNameBill.setText ("Customer Name");
		custNameBill.setForeground (Color.GRAY);
		bookedRoomBill.setText ("Room Number");
		bookedRoomBill.setForeground (Color.GRAY);
		personsBill.setText ("Number of Persons");
		personsBill.setForeground (Color.GRAY);
		arriveBill.setText ("Date of Arrival");
		arriveBill.setForeground (Color.GRAY);
		billCode.setText ("Billing Code");
		billCode.setForeground (Color.GRAY);
		billCode.setEditable (true);
		departBill.setText ("Date of Departure (dd/mm/yyyy)");
		departBill.setForeground (Color.GRAY);
		departBill.setEditable (true);
		roomRentBill.setText ("Room Rent");
		roomRentBill.setForeground (Color.GRAY);
		serviceBill.setText ("Service Charges");
		serviceBill.setForeground (Color.GRAY);
		serviceBill.setEditable (true);
		grossBill.setText ("Gross Amount");
		grossBill.setForeground (Color.GRAY);
		gstBill.setText ("GST");
		gstBill.setForeground (Color.GRAY);
		gstBill.setEditable (true);
		discountBill.setText ("Discount (%)");
		discountBill.setForeground (Color.GRAY);
		discountBill.setEditable (true);
		netBill.setText ("Net Amount");
		netBill.setForeground (Color.GRAY);
		modeBill.setText ("Payment Mode");
		modeBill.setForeground (Color.GRAY);
		cardNoBill.setText ("Card Number");
		cardNoBill.setForeground (Color.GRAY);
		receivedBill.setText ("Received Amount");
		receivedBill.setForeground (Color.GRAY);
		receivedBill.setEditable (true);
		pendingBill.setText ("Pending Amount");
		pendingBill.setForeground (Color.GRAY);
		container.remove (redBackLabel);
		container.add (greenBackLabel);
		home.setEnabled (true);
		cardLayout.show (mainPanel, "Billing");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for enquiry
	public static void enquiryAction (ActionEvent ae)
	{
		custCodeEnq.setText ("Enter Customer Code");
		customerData.setText ("");
		container.remove (redBackLabel);
		container.add (grayBlueBackLabel);
		home.setEnabled (true);
		cardLayout.show (mainPanel, "Enquiry");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for logout or exit
	public static void exitAction (ActionEvent ae)
	{
		username.setText ("Username");
		username.setForeground (Color.GRAY);
		password.setText ("Password");
		password.setForeground (Color.GRAY);
		password.setEchoChar ((char)0);
		welcomeLabel.setText ("!!!Welcome to our hotel!!!");
		container.remove (redBackLabel);
		container.add (blueBackLabel);
		logout.setEnabled (false);
		cardLayout.first (mainPanel);
		container.revalidate();
		container.repaint();
	}

	//actionlistener for availrooms button
	public static void availRoomsAction (ActionEvent ae)
	{
		container.remove (redBackLabel);
		container.add (pureBlueBackLabel);
		home.setEnabled (true);
		cardLayout.show (mainPanel, "Room Status");
		container.revalidate();
		container.repaint();
	}

	//actionlistener for the ok button for the user id of the recovery page
	public static void userIdOkAction (ActionEvent ae) 
	{
		String enteredUserId = userIdRecover.getText ();
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select user_id from Admin_Details");
			ResultSet rs = ps.executeQuery ();
			int flag = 0;
			while (rs.next ())
			{
				if (rs.getString (1).equals (enteredUserId))
				{
					flag++;
					break;
				}
			}
			if (flag == 1)
			{
				//found the id
				recoveryWarn.setText ("");
				recoveryPanel.remove (userIdRecover);
				recoveryPanel.remove (userIdOk);
				recoveryPanel.add (sixDigitCode);
				recoveryPanel.add (zero);
				recoveryPanel.add (one);
				recoveryPanel.add (two);
				recoveryPanel.add (three);
				recoveryPanel.add (four);
				recoveryPanel.add (five);
				recoveryPanel.add (six);
				recoveryPanel.add (seven);
				recoveryPanel.add (eight);
				recoveryPanel.add (nine);
				recoveryPanel.add (clear);
				recoveryPanel.revalidate ();
				recoveryPanel.repaint ();
				correctOldId = enteredUserId;
				sentCode = (int) ( Math.random() * ( (999999-1) + 1 ) ) + 1;
				System.out.println (sentCode);
				//send (sentCode);
			}
			else if (flag == 0)
			{
				//didn't found the id
				recoveryWarn.setText ("User ID doesn't exist!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public void actionPerformed (ActionEvent ae)
	{
		JButton buttonPressed = (JButton) ae.getSource();
		String buttonPressedStr = buttonPressed.getText();
		sixDigitCode.append (buttonPressedStr+" ");
		++digitCounter;
		int typedCode = 0;
		if (digitCounter == 6)
		{
			String typedCodeStr = sixDigitCode.getText ();
			String[] tokens = typedCodeStr.split(" ");
			int[] codeArr = new int [tokens.length];
			for (int i=0; i<tokens.length; i++)
				codeArr [i] = Integer.parseInt (tokens [i]);
			for (int i=0;i<codeArr.length;i++)
				typedCode = (typedCode*10) + codeArr [i];
			if (sentCode == typedCode)
			{
				//matched
				recoveryWarn.setText ("");
				recoveryPanel.remove (sixDigitCode);
				recoveryPanel.remove (zero);
				recoveryPanel.remove (one);
				recoveryPanel.remove (two);
				recoveryPanel.remove (three);
				recoveryPanel.remove (four);
				recoveryPanel.remove (five);
				recoveryPanel.remove (six);
				recoveryPanel.remove (seven);
				recoveryPanel.remove (eight);
				recoveryPanel.remove (nine);
				recoveryPanel.remove (clear);
				recoveryPanel.add (newPassword);
				recoveryPanel.add (retypeNewPassword);
				recoveryPanel.add (savePassword);
				recoveryPanel.revalidate ();
				recoveryPanel.repaint ();
			}
			else
			{
				//didn't match
				recoveryWarn.setText ("Wrong OTP!");
			}
		}
	}

	//actions for clear button
	public static void clearAction (ActionEvent ae)
	{
		char[] text=sixDigitCode.getText().toCharArray();
		String str="";
		for(int i=0;i<text.length-2;i++)
			str+=text[i];
		sixDigitCode.setText(str);
		--digitCounter;
	}

	//actions for the save password button
	public static void savePasswordAction (ActionEvent ae)
	{
		String newPasswordStr = newPassword.getText ();
		String retypeNewPasswordStr = retypeNewPassword.getText ();
		if (newPasswordStr.equals(retypeNewPasswordStr))
		{
			//save the password to the database and activate the backtologin button
			recoveryWarn.setText ("");
			try
			{
				PreparedStatement ps = conn.prepareStatement ("update Admin_Details set password= ? where user_id= ?");
				ps.setString (1,newPasswordStr);
				ps.setString (2,correctOldId);
				int status = ps.executeUpdate ();
				if (status != 0)
				{
					recoveryWarn.setText ("");
					recoveryPanel.remove (newPassword);
					recoveryPanel.remove (retypeNewPassword);
					recoveryPanel.remove (savePassword);
					recoveryPanel.add (backToLogin);
					recoveryPanel.revalidate ();
					recoveryPanel.repaint ();
				}
				else
					recoveryWarn.setText ("Error in saving the password!");
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
		else
		{
			//passwords should be same
			recoveryWarn.setText ("Passwords are not matching!");
		}
	}

	//actions for the back to login button in the recover page
	public static void backToLoginAction (ActionEvent ae)
	{
		username.setText ("Username");
		username.setForeground (Color.GRAY);
		password.setText ("Password");
		password.setForeground (Color.GRAY);
		password.setEchoChar ((char)0);
		welcomeLabel.setText ("!!!Welcome to our hotel!!!");
		container.remove (darkBackLabel);
		container.add (blueBackLabel);
		logout.setEnabled (false);
		cardLayout.first (mainPanel);
		container.revalidate();
		container.repaint();
	}

	//java mail api to send the code through gmail server
	/*public static void send (int sentCode)
	{
		String firstNameStr = "", emailStr= "";
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select first_name, email_address from Admin_Details where user_id= ?");
			ps.setString (1,correctOldId);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
			{
				firstNameStr = rs.getString (1);
				emailStr = rs.getString (2);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		String from= "adityamishra3333333333@gmail.com";
		String pswd= "Soni19994$";
		String to= emailStr;
		String host= "smtp.gmail.com";
		Properties properties= new Properties ();
		properties.put ("mail.smtp.host",host);
		properties.put ("mail.smtp.auth",true);
		properties.put ("mail.smtp.socketFactory.port","465");
		properties.put ("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put ("mail.smtp.port","465");
		Session session= Session.getDefaultInstance (properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,pswd);
			}
		});
		try {
			MimeMessage msg = new MimeMessage (session);
			msg.setFrom (new InternetAddress (from));
			msg.addRecipient (Message.RecipientType.TO, new InternetAddress (to));
			msg.setSubject ("Hotel Admin Account Recovery");
			msg.setText ("Hi "+firstNameStr+",\n\tA request was generated for an OTP to recover the password of your account. The OTP to recover your account is: "+sentCode+".\n\nIf you have not done this, please let us know.\n\nThanking you\nHotel Management Team.");
			Transport.send (msg);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}*/

	//itemlistener for the togglebuttons of the rooms page
	public void itemStateChanged (ItemEvent ie)
	{
		JToggleButton tb = (JToggleButton)ie.getSource ();
		String roomNumber = tb.getText ();
		if (tb.isSelected ())
		{
			try
			{
				PreparedStatement ps = conn.prepareStatement ("update Room_Details set status=? where room_no=?");
				ps.setString (1,"Not Available");
				ps.setString (2,roomNumber);
				int status = ps.executeUpdate ();
				if (status != 0)
					{
						if (roomBooking == 1)
						{
							roomBook.setText (roomNumber);
							roomBook.setForeground (Color.BLACK);
							roomBooking = 0;
							container.remove (pureBlueBackLabel);
							container.add (purpleBackLabel);
							cardLayout.show (mainPanel, "Booking");
							container.revalidate();
							container.repaint();
						}
					}
				else
					System.out.println ("Error");
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
			try
		{
			PreparedStatement ps = conn.prepareStatement ("select room_no, status from Room_Details");
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				roomNumber = rs.getString (1);
				String status= rs.getString (2);
				if (roomNumber.equals ("RDE001"))
				{
					if (status.equals ("Available"))
						lux01.setSelected (false);
					else
						lux01.setSelected (true);
				}
				else if (roomNumber.equals ("RDE002"))
				{
					if (status.equals ("Available"))
						lux02.setSelected (false);
					else
						lux02.setSelected (true);
				}
				else if (roomNumber.equals ("RDE003"))
				{
					if (status.equals ("Available"))
						lux03.setSelected (false);
					else
						lux03.setSelected (true);
				}
				else if (roomNumber.equals ("RDE004"))
				{
					if (status.equals ("Available"))
						lux04.setSelected (false);
					else
						lux04.setSelected (true);
				}
				else if (roomNumber.equals ("RDE005"))
				{
					if (status.equals ("Available"))
						lux05.setSelected (false);
					else
						lux05.setSelected (true);
				}
				else if (roomNumber.equals ("RDE006"))
				{
					if (status.equals ("Available"))
						lux06.setSelected (false);
					else
						lux06.setSelected (true);
				}
				else if (roomNumber.equals ("RDE007"))
				{
					if (status.equals ("Available"))
						lux07.setSelected (false);
					else
						lux07.setSelected (true);
				}
				else if (roomNumber.equals ("RDE008"))
				{
					if (status.equals ("Available"))
						lux08.setSelected (false);
					else
						lux08.setSelected (true);
				}
				else if (roomNumber.equals ("RDE101"))
				{
					if (status.equals ("Available"))
						lux11.setSelected (false);
					else
						lux11.setSelected (true);
				}
				else if (roomNumber.equals ("RDE102"))
				{
					if (status.equals ("Available"))
						lux12.setSelected (false);
					else
						lux12.setSelected (true);
				}
				else if (roomNumber.equals ("RDE103"))
				{
					if (status.equals ("Available"))
						lux13.setSelected (false);
					else
						lux13.setSelected (true);
				}
				else if (roomNumber.equals ("RDE104"))
				{
					if (status.equals ("Available"))
						lux14.setSelected (false);
					else
						lux14.setSelected (true);
				}
				else if (roomNumber.equals ("RDE105"))
				{
					if (status.equals ("Available"))
						lux15.setSelected (false);
					else
						lux15.setSelected (true);
				}
				else if (roomNumber.equals ("RDE106"))
				{
					if (status.equals ("Available"))
						lux16.setSelected (false);
					else
						lux16.setSelected (true);
				}
				else if (roomNumber.equals ("RDE107"))
				{
					if (status.equals ("Available"))
						lux17.setSelected (false);
					else
						lux17.setSelected (true);
				}
				else if (roomNumber.equals ("RDE108"))
				{
					if (status.equals ("Available"))
						lux18.setSelected (false);
					else
						lux18.setSelected (true);
				}
				else if (roomNumber.equals ("RDE201"))
				{
					if (status.equals ("Available"))
						lux21.setSelected (false);
					else
						lux21.setSelected (true);
				}
				else if (roomNumber.equals ("RDE202"))
				{
					if (status.equals ("Available"))
						lux22.setSelected (false);
					else
						lux22.setSelected (true);
				}
				else if (roomNumber.equals ("RDE203"))
				{
					if (status.equals ("Available"))
						lux23.setSelected (false);
					else
						lux23.setSelected (true);
				}
				else if (roomNumber.equals ("RDE204"))
				{
					if (status.equals ("Available"))
						lux24.setSelected (false);
					else
						lux24.setSelected (true);
				}
				else if (roomNumber.equals ("RDE205"))
				{
					if (status.equals ("Available"))
						lux25.setSelected (false);
					else
						lux25.setSelected (true);
				}
				else if (roomNumber.equals ("RDE206"))
				{
					if (status.equals ("Available"))
						lux26.setSelected (false);
					else
						lux26.setSelected (true);
				}
				else if (roomNumber.equals ("RDE207"))
				{
					if (status.equals ("Available"))
						lux27.setSelected (false);
					else
						lux27.setSelected (true);
				}
				else if (roomNumber.equals ("RDE208"))
				{
					if (status.equals ("Available"))
						lux28.setSelected (false);
					else
						lux28.setSelected (true);
				}
				else if (roomNumber.equals ("RDE301"))
				{
					if (status.equals ("Available"))
						lux31.setSelected (false);
					else
						lux31.setSelected (true);
				}
				else if (roomNumber.equals ("RDE302"))
				{
					if (status.equals ("Available"))
						lux32.setSelected (false);
					else
						lux32.setSelected (true);
				}
				else if (roomNumber.equals ("RDE303"))
				{
					if (status.equals ("Available"))
						lux33.setSelected (false);
					else
						lux33.setSelected (true);
				}
				else if (roomNumber.equals ("RDE304"))
				{
					if (status.equals ("Available"))
						lux34.setSelected (false);
					else
						lux34.setSelected (true);
				}
				else if (roomNumber.equals ("RDE305"))
				{
					if (status.equals ("Available"))
						lux35.setSelected (false);
					else
						lux35.setSelected (true);
				}
				else if (roomNumber.equals ("RDE306"))
				{
					if (status.equals ("Available"))
						lux36.setSelected (false);
					else
						lux36.setSelected (true);
				}
				else if (roomNumber.equals ("RDE307"))
				{
					if (status.equals ("Available"))
						lux37.setSelected (false);
					else
						lux37.setSelected (true);
				}
				else if (roomNumber.equals ("RDE308"))
				{
					if (status.equals ("Available"))
						lux38.setSelected (false);
					else
						lux38.setSelected (true);
				}
				else if (roomNumber.equals ("RDE401"))
				{
					if (status.equals ("Available"))
						lux41.setSelected (false);
					else
						lux41.setSelected (true);
				}
				else if (roomNumber.equals ("RDE402"))
				{
					if (status.equals ("Available"))
						lux42.setSelected (false);
					else
						lux42.setSelected (true);
				}
				else if (roomNumber.equals ("RDE403"))
				{
					if (status.equals ("Available"))
						lux43.setSelected (false);
					else
						lux43.setSelected (true);
				}
				else if (roomNumber.equals ("RDE404"))
				{
					if (status.equals ("Available"))
						lux44.setSelected (false);
					else
						lux44.setSelected (true);
				}
				else if (roomNumber.equals ("RDE405"))
				{
					if (status.equals ("Available"))
						lux45.setSelected (false);
					else
						lux45.setSelected (true);
				}
				else if (roomNumber.equals ("RDE406"))
				{
					if (status.equals ("Available"))
						lux46.setSelected (false);
					else
						lux46.setSelected (true);
				}
				else if (roomNumber.equals ("RDE407"))
				{
					if (status.equals ("Available"))
						lux47.setSelected (false);
					else
						lux47.setSelected (true);
				}
				else if (roomNumber.equals ("RDE408"))
				{
					if (status.equals ("Available"))
						lux48.setSelected (false);
					else
						lux48.setSelected (true);
				}
				else if (roomNumber.equals ("RDE501"))
				{
					if (status.equals ("Available"))
						lux51.setSelected (false);
					else
						lux51.setSelected (true);
				}
				else if (roomNumber.equals ("RDE502"))
				{
					if (status.equals ("Available"))
						lux52.setSelected (false);
					else
						lux52.setSelected (true);
				}
				else if (roomNumber.equals ("RDE503"))
				{
					if (status.equals ("Available"))
						lux53.setSelected (false);
					else
						lux53.setSelected (true);
				}
				else if (roomNumber.equals ("RDE504"))
				{
					if (status.equals ("Available"))
						lux54.setSelected (false);
					else
						lux54.setSelected (true);
				}
				else if (roomNumber.equals ("RDE505"))
				{
					if (status.equals ("Available"))
						lux55.setSelected (false);
					else
						lux55.setSelected (true);
				}
				else if (roomNumber.equals ("RDE506"))
				{
					if (status.equals ("Available"))
						lux56.setSelected (false);
					else
						lux56.setSelected (true);
				}
				else if (roomNumber.equals ("RDE507"))
				{
					if (status.equals ("Available"))
						lux57.setSelected (false);
					else
						lux57.setSelected (true);
				}
				else if (roomNumber.equals ("RDE508"))
				{
					if (status.equals ("Available"))
						lux58.setSelected (false);
					else
						lux58.setSelected (true);
				}
				else if (roomNumber.equals ("RDE601"))
				{
					if (status.equals ("Available"))
						lux61.setSelected (false);
					else
						lux61.setSelected (true);
				}
				else if (roomNumber.equals ("RDE602"))
				{
					if (status.equals ("Available"))
						lux62.setSelected (false);
					else
						lux62.setSelected (true);
				}
				else if (roomNumber.equals ("RDE603"))
				{
					if (status.equals ("Available"))
						lux63.setSelected (false);
					else
						lux63.setSelected (true);
				}
				else if (roomNumber.equals ("RDE604"))
				{
					if (status.equals ("Available"))
						lux64.setSelected (false);
					else
						lux64.setSelected (true);
				}
				else if (roomNumber.equals ("RDE605"))
				{
					if (status.equals ("Available"))
						lux65.setSelected (false);
					else
						lux65.setSelected (true);
				}
				else if (roomNumber.equals ("RDE606"))
				{
					if (status.equals ("Available"))
						lux66.setSelected (false);
					else
						lux66.setSelected (true);
				}
				else if (roomNumber.equals ("RDE607"))
				{
					if (status.equals ("Available"))
						lux67.setSelected (false);
					else
						lux67.setSelected (true);
				}
				else if (roomNumber.equals ("RDE608"))
				{
					if (status.equals ("Available"))
						lux68.setSelected (false);
					else
						lux68.setSelected (true);
				}
				else if (roomNumber.equals ("RDE701"))
				{
					if (status.equals ("Available"))
						lux71.setSelected (false);
					else
						lux71.setSelected (true);
				}
				else if (roomNumber.equals ("RDE702"))
				{
					if (status.equals ("Available"))
						lux72.setSelected (false);
					else
						lux72.setSelected (true);
				}
				else if (roomNumber.equals ("RDE703"))
				{
					if (status.equals ("Available"))
						lux73.setSelected (false);
					else
						lux73.setSelected (true);
				}
				else if (roomNumber.equals ("RDE704"))
				{
					if (status.equals ("Available"))
						lux74.setSelected (false);
					else
						lux74.setSelected (true);
				}
				else if (roomNumber.equals ("RDE705"))
				{
					if (status.equals ("Available"))
						lux75.setSelected (false);
					else
						lux75.setSelected (true);
				}
				else if (roomNumber.equals ("RDE706"))
				{
					if (status.equals ("Available"))
						lux76.setSelected (false);
					else
						lux76.setSelected (true);
				}
				else if (roomNumber.equals ("RDE707"))
				{
					if (status.equals ("Available"))
						lux77.setSelected (false);
					else
						lux77.setSelected (true);
				}
				else if (roomNumber.equals ("RDE708"))
				{
					if (status.equals ("Available"))
						lux78.setSelected (false);
					else
						lux78.setSelected (true);
				}
				else if (roomNumber.equals ("RDE801"))
				{
					if (status.equals ("Available"))
						lux81.setSelected (false);
					else
						lux81.setSelected (true);
				}
				else if (roomNumber.equals ("RDE802"))
				{
					if (status.equals ("Available"))
						lux82.setSelected (false);
					else
						lux82.setSelected (true);
				}
				else if (roomNumber.equals ("RDE803"))
				{
					if (status.equals ("Available"))
						lux83.setSelected (false);
					else
						lux83.setSelected (true);
				}
				else if (roomNumber.equals ("RDE804"))
				{
					if (status.equals ("Available"))
						lux84.setSelected (false);
					else
						lux84.setSelected (true);
				}
				else if (roomNumber.equals ("RDE805"))
				{
					if (status.equals ("Available"))
						lux85.setSelected (false);
					else
						lux85.setSelected (true);
				}
				else if (roomNumber.equals ("RDE806"))
				{
					if (status.equals ("Available"))
						lux86.setSelected (false);
					else
						lux86.setSelected (true);
				}
				else if (roomNumber.equals ("RDE807"))
				{
					if (status.equals ("Available"))
						lux87.setSelected (false);
					else
						lux87.setSelected (true);
				}
				else if (roomNumber.equals ("RDE808"))
				{
					if (status.equals ("Available"))
						lux88.setSelected (false);
					else
						lux88.setSelected (true);
				}
				else if (roomNumber.equals ("RDE901"))
				{
					if (status.equals ("Available"))
						lux91.setSelected (false);
					else
						lux91.setSelected (true);
				}
				else if (roomNumber.equals ("RDE902"))
				{
					if (status.equals ("Available"))
						lux92.setSelected (false);
					else
						lux92.setSelected (true);
				}
				else if (roomNumber.equals ("RDE903"))
				{
					if (status.equals ("Available"))
						lux93.setSelected (false);
					else
						lux93.setSelected (true);
				}
				else if (roomNumber.equals ("RDE904"))
				{
					if (status.equals ("Available"))
						lux94.setSelected (false);
					else
						lux94.setSelected (true);
				}
				else if (roomNumber.equals ("RDE905"))
				{
					if (status.equals ("Available"))
						lux95.setSelected (false);
					else
						lux95.setSelected (true);
				}
				else if (roomNumber.equals ("RDE906"))
				{
					if (status.equals ("Available"))
						lux96.setSelected (false);
					else
						lux96.setSelected (true);
				}
				else if (roomNumber.equals ("RDE907"))
				{
					if (status.equals ("Available"))
						lux97.setSelected (false);
					else
						lux97.setSelected (true);
				}
				else if (roomNumber.equals ("RDE908"))
				{
					if (status.equals ("Available"))
						lux98.setSelected (false);
					else
						lux98.setSelected (true);
				}
				else if (roomNumber.equals ("RSD001"))
				{
					if (status.equals ("Available"))
						suLux01.setSelected (false);
					else
						suLux01.setSelected (true);
				}
				else if (roomNumber.equals ("RSD002"))
				{
					if (status.equals ("Available"))
						suLux02.setSelected (false);
					else
						suLux02.setSelected (true);
				}
				else if (roomNumber.equals ("RSD003"))
				{
					if (status.equals ("Available"))
						suLux03.setSelected (false);
					else
						suLux03.setSelected (true);
				}
				else if (roomNumber.equals ("RSD004"))
				{
					if (status.equals ("Available"))
						suLux04.setSelected (false);
					else
						suLux04.setSelected (true);
				}
				else if (roomNumber.equals ("RSD101"))
				{
					if (status.equals ("Available"))
						suLux11.setSelected (false);
					else
						suLux11.setSelected (true);
				}
				else if (roomNumber.equals ("RSD102"))
				{
					if (status.equals ("Available"))
						suLux12.setSelected (false);
					else
						suLux12.setSelected (true);
				}
				else if (roomNumber.equals ("RSD103"))
				{
					if (status.equals ("Available"))
						suLux13.setSelected (false);
					else
						suLux13.setSelected (true);
				}
				else if (roomNumber.equals ("RSD104"))
				{
					if (status.equals ("Available"))
						suLux14.setSelected (false);
					else
						suLux14.setSelected (true);
				}
				else if (roomNumber.equals ("RSD201"))
				{
					if (status.equals ("Available"))
						suLux21.setSelected (false);
					else
						suLux21.setSelected (true);
				}
				else if (roomNumber.equals ("RSD202"))
				{
					if (status.equals ("Available"))
						suLux22.setSelected (false);
					else
						suLux22.setSelected (true);
				}
				else if (roomNumber.equals ("RSD203"))
				{
					if (status.equals ("Available"))
						suLux23.setSelected (false);
					else
						suLux23.setSelected (true);
				}
				else if (roomNumber.equals ("RSD204"))
				{
					if (status.equals ("Available"))
						suLux24.setSelected (false);
					else
						suLux24.setSelected (true);
				}
				else if (roomNumber.equals ("RSD301"))
				{
					if (status.equals ("Available"))
						suLux31.setSelected (false);
					else
						suLux31.setSelected (true);
				}
				else if (roomNumber.equals ("RSD302"))
				{
					if (status.equals ("Available"))
						suLux32.setSelected (false);
					else
						suLux32.setSelected (true);
				}
				else if (roomNumber.equals ("RSD303"))
				{
					if (status.equals ("Available"))
						suLux33.setSelected (false);
					else
						suLux33.setSelected (true);
				}
				else if (roomNumber.equals ("RSD304"))
				{
					if (status.equals ("Available"))
						suLux34.setSelected (false);
					else
						suLux34.setSelected (true);
				}
				else if (roomNumber.equals ("RSD401"))
				{
					if (status.equals ("Available"))
						suLux41.setSelected (false);
					else
						suLux41.setSelected (true);
				}
				else if (roomNumber.equals ("RSD402"))
				{
					if (status.equals ("Available"))
						suLux42.setSelected (false);
					else
						suLux42.setSelected (true);
				}
				else if (roomNumber.equals ("RSD403"))
				{
					if (status.equals ("Available"))
						suLux43.setSelected (false);
					else
						suLux43.setSelected (true);
				}
				else if (roomNumber.equals ("RSD404"))
				{
					if (status.equals ("Available"))
						suLux44.setSelected (false);
					else
						suLux44.setSelected (true);
				}
				else if (roomNumber.equals ("RSD501"))
				{
					if (status.equals ("Available"))
						suLux51.setSelected (false);
					else
						suLux51.setSelected (true);
				}
				else if (roomNumber.equals ("RSD502"))
				{
					if (status.equals ("Available"))
						suLux52.setSelected (false);
					else
						suLux52.setSelected (true);
				}
				else if (roomNumber.equals ("RSD503"))
				{
					if (status.equals ("Available"))
						suLux53.setSelected (false);
					else
						suLux53.setSelected (true);
				}
				else if (roomNumber.equals ("RSD504"))
				{
					if (status.equals ("Available"))
						suLux54.setSelected (false);
					else
						suLux54.setSelected (true);
				}
				else if (roomNumber.equals ("RSD601"))
				{
					if (status.equals ("Available"))
						suLux61.setSelected (false);
					else
						suLux61.setSelected (true);
				}
				else if (roomNumber.equals ("RSD602"))
				{
					if (status.equals ("Available"))
						suLux62.setSelected (false);
					else
						suLux62.setSelected (true);
				}
				else if (roomNumber.equals ("RSD603"))
				{
					if (status.equals ("Available"))
						suLux63.setSelected (false);
					else
						suLux63.setSelected (true);
				}
				else if (roomNumber.equals ("RSD604"))
				{
					if (status.equals ("Available"))
						suLux64.setSelected (false);
					else
						suLux64.setSelected (true);
				}
				else if (roomNumber.equals ("RSD701"))
				{
					if (status.equals ("Available"))
						suLux71.setSelected (false);
					else
						suLux71.setSelected (true);
				}
				else if (roomNumber.equals ("RSD702"))
				{
					if (status.equals ("Available"))
						suLux72.setSelected (false);
					else
						suLux72.setSelected (true);
				}
				else if (roomNumber.equals ("RSD703"))
				{
					if (status.equals ("Available"))
						suLux73.setSelected (false);
					else
						suLux73.setSelected (true);
				}
				else if (roomNumber.equals ("RSD704"))
				{
					if (status.equals ("Available"))
						suLux74.setSelected (false);
					else
						suLux74.setSelected (true);
				}
				else if (roomNumber.equals ("RSD801"))
				{
					if (status.equals ("Available"))
						suLux81.setSelected (false);
					else
						suLux81.setSelected (true);
				}
				else if (roomNumber.equals ("RSD802"))
				{
					if (status.equals ("Available"))
						suLux82.setSelected (false);
					else
						suLux82.setSelected (true);
				}
				else if (roomNumber.equals ("RSD803"))
				{
					if (status.equals ("Available"))
						suLux83.setSelected (false);
					else
						suLux83.setSelected (true);
				}
				else if (roomNumber.equals ("RSD804"))
				{
					if (status.equals ("Available"))
						suLux84.setSelected (false);
					else
						suLux84.setSelected (true);
				}
				else if (roomNumber.equals ("RSD901"))
				{
					if (status.equals ("Available"))
						suLux91.setSelected (false);
					else
						suLux91.setSelected (true);
				}
				else if (roomNumber.equals ("RSD902"))
				{
					if (status.equals ("Available"))
						suLux92.setSelected (false);
					else
						suLux92.setSelected (true);
				}
				else if (roomNumber.equals ("RSD903"))
				{
					if (status.equals ("Available"))
						suLux93.setSelected (false);
					else
						suLux93.setSelected (true);
				}
				else if (roomNumber.equals ("RSD904"))
				{
					if (status.equals ("Available"))
						suLux94.setSelected (false);
					else
						suLux94.setSelected (true);
				}
				else if (roomNumber.equals ("SPM001"))
				{
					if (status.equals ("Available"))
						pm01.setSelected (false);
					else
						pm01.setSelected (true);
				}
				else if (roomNumber.equals ("SPM101"))
				{
					if (status.equals ("Available"))
						pm11.setSelected (false);
					else
						pm11.setSelected (true);
				}
				else if (roomNumber.equals ("SPM201"))
				{
					if (status.equals ("Available"))
						pm21.setSelected (false);
					else
						pm21.setSelected (true);
				}
				else if (roomNumber.equals ("SPM301"))
				{
					if (status.equals ("Available"))
						pm31.setSelected (false);
					else
						pm31.setSelected (true);
				}
				else if (roomNumber.equals ("SPM401"))
				{
					if (status.equals ("Available"))
						pm41.setSelected (false);
					else
						pm41.setSelected (true);
				}
				else if (roomNumber.equals ("SPM501"))
				{
					if (status.equals ("Available"))
						pm51.setSelected (false);
					else
						pm51.setSelected (true);
				}
				else if (roomNumber.equals ("SPM601"))
				{
					if (status.equals ("Available"))
						pm61.setSelected (false);
					else
						pm61.setSelected (true);
				}
				else if (roomNumber.equals ("SPM701"))
				{
					if (status.equals ("Available"))
						pm71.setSelected (false);
					else
						pm71.setSelected (true);
				}
				else if (roomNumber.equals ("SPM801"))
				{
					if (status.equals ("Available"))
						pm81.setSelected (false);
					else
						pm81.setSelected (true);
				}
				else if (roomNumber.equals ("SPM901"))
				{
					if (status.equals ("Available"))
						pm91.setSelected (false);
					else
						pm91.setSelected (true);
				}
				else if (roomNumber.equals ("SPD001"))
				{
					if (status.equals ("Available"))
						pd01.setSelected (false);
					else
						pd01.setSelected (true);
				}
				else if (roomNumber.equals ("SPD101"))
				{
					if (status.equals ("Available"))
						pd11.setSelected (false);
					else
						pd11.setSelected (true);
				}
				else if (roomNumber.equals ("SPD201"))
				{
					if (status.equals ("Available"))
						pd21.setSelected (false);
					else
						pd21.setSelected (true);
				}
				else if (roomNumber.equals ("SPD301"))
				{
					if (status.equals ("Available"))
						pd31.setSelected (false);
					else
						pd31.setSelected (true);
				}
				else if (roomNumber.equals ("SPD401"))
				{
					if (status.equals ("Available"))
						pd41.setSelected (false);
					else
						pd41.setSelected (true);
				}
				else if (roomNumber.equals ("SPD501"))
				{
					if (status.equals ("Available"))
						pd51.setSelected (false);
					else
						pd51.setSelected (true);
				}
				else if (roomNumber.equals ("SPD601"))
				{
					if (status.equals ("Available"))
						pd61.setSelected (false);
					else
						pd61.setSelected (true);
				}
				else if (roomNumber.equals ("SPD701"))
				{
					if (status.equals ("Available"))
						pd71.setSelected (false);
					else
						pd71.setSelected (true);
				}
				else if (roomNumber.equals ("SPD801"))
				{
					if (status.equals ("Available"))
						pd81.setSelected (false);
					else
						pd81.setSelected (true);
				}
				else if (roomNumber.equals ("SPD901"))
				{
					if (status.equals ("Available"))
						pd91.setSelected (false);
					else
						pd91.setSelected (true);
				}
				else if (roomNumber.equals ("SEX001"))
				{
					if (status.equals ("Available"))
						ex01.setSelected (false);
					else
						ex01.setSelected (true);
				}
				else if (roomNumber.equals ("SEX101"))
				{
					if (status.equals ("Available"))
						ex11.setSelected (false);
					else
						ex11.setSelected (true);
				}
				else if (roomNumber.equals ("SEX201"))
				{
					if (status.equals ("Available"))
						ex21.setSelected (false);
					else
						ex21.setSelected (true);
				}
				else if (roomNumber.equals ("SEX301"))
				{
					if (status.equals ("Available"))
						ex31.setSelected (false);
					else
						ex31.setSelected (true);
				}
				else if (roomNumber.equals ("SEX401"))
				{
					if (status.equals ("Available"))
						ex41.setSelected (false);
					else
						ex41.setSelected (true);
				}
				else if (roomNumber.equals ("SEX501"))
				{
					if (status.equals ("Available"))
						ex51.setSelected (false);
					else
						ex51.setSelected (true);
				}
				else if (roomNumber.equals ("SEX601"))
				{
					if (status.equals ("Available"))
						ex61.setSelected (false);
					else
						ex61.setSelected (true);
				}
				else if (roomNumber.equals ("SEX701"))
				{
					if (status.equals ("Available"))
						ex71.setSelected (false);
					else
						ex71.setSelected (true);
				}
				else if (roomNumber.equals ("SEX801"))
				{
					if (status.equals ("Available"))
						ex81.setSelected (false);
					else
						ex81.setSelected (true);
				}
				else if (roomNumber.equals ("SEX901"))
				{
					if (status.equals ("Available"))
						ex91.setSelected (false);
					else
						ex91.setSelected (true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		container.revalidate();
		container.repaint();
		}
		else
		{
			try
			{
				PreparedStatement ps = conn.prepareStatement ("update Room_Details set status=? where room_no=?");
				ps.setString (1,"Available");
				ps.setString (2,roomNumber);
				int status = ps.executeUpdate ();
				if (status != 0)
				{}
				else
					System.out.println ("Error");
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select room_no, status from Room_Details");
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				roomNumber = rs.getString (1);
				String status= rs.getString (2);
				if (roomNumber.equals ("RDE001"))
				{
					if (status.equals ("Available"))
						lux01.setSelected (false);
					else
						lux01.setSelected (true);
				}
				else if (roomNumber.equals ("RDE002"))
				{
					if (status.equals ("Available"))
						lux02.setSelected (false);
					else
						lux02.setSelected (true);
				}
				else if (roomNumber.equals ("RDE003"))
				{
					if (status.equals ("Available"))
						lux03.setSelected (false);
					else
						lux03.setSelected (true);
				}
				else if (roomNumber.equals ("RDE004"))
				{
					if (status.equals ("Available"))
						lux04.setSelected (false);
					else
						lux04.setSelected (true);
				}
				else if (roomNumber.equals ("RDE005"))
				{
					if (status.equals ("Available"))
						lux05.setSelected (false);
					else
						lux05.setSelected (true);
				}
				else if (roomNumber.equals ("RDE006"))
				{
					if (status.equals ("Available"))
						lux06.setSelected (false);
					else
						lux06.setSelected (true);
				}
				else if (roomNumber.equals ("RDE007"))
				{
					if (status.equals ("Available"))
						lux07.setSelected (false);
					else
						lux07.setSelected (true);
				}
				else if (roomNumber.equals ("RDE008"))
				{
					if (status.equals ("Available"))
						lux08.setSelected (false);
					else
						lux08.setSelected (true);
				}
				else if (roomNumber.equals ("RDE101"))
				{
					if (status.equals ("Available"))
						lux11.setSelected (false);
					else
						lux11.setSelected (true);
				}
				else if (roomNumber.equals ("RDE102"))
				{
					if (status.equals ("Available"))
						lux12.setSelected (false);
					else
						lux12.setSelected (true);
				}
				else if (roomNumber.equals ("RDE103"))
				{
					if (status.equals ("Available"))
						lux13.setSelected (false);
					else
						lux13.setSelected (true);
				}
				else if (roomNumber.equals ("RDE104"))
				{
					if (status.equals ("Available"))
						lux14.setSelected (false);
					else
						lux14.setSelected (true);
				}
				else if (roomNumber.equals ("RDE105"))
				{
					if (status.equals ("Available"))
						lux15.setSelected (false);
					else
						lux15.setSelected (true);
				}
				else if (roomNumber.equals ("RDE106"))
				{
					if (status.equals ("Available"))
						lux16.setSelected (false);
					else
						lux16.setSelected (true);
				}
				else if (roomNumber.equals ("RDE107"))
				{
					if (status.equals ("Available"))
						lux17.setSelected (false);
					else
						lux17.setSelected (true);
				}
				else if (roomNumber.equals ("RDE108"))
				{
					if (status.equals ("Available"))
						lux18.setSelected (false);
					else
						lux18.setSelected (true);
				}
				else if (roomNumber.equals ("RDE201"))
				{
					if (status.equals ("Available"))
						lux21.setSelected (false);
					else
						lux21.setSelected (true);
				}
				else if (roomNumber.equals ("RDE202"))
				{
					if (status.equals ("Available"))
						lux22.setSelected (false);
					else
						lux22.setSelected (true);
				}
				else if (roomNumber.equals ("RDE203"))
				{
					if (status.equals ("Available"))
						lux23.setSelected (false);
					else
						lux23.setSelected (true);
				}
				else if (roomNumber.equals ("RDE204"))
				{
					if (status.equals ("Available"))
						lux24.setSelected (false);
					else
						lux24.setSelected (true);
				}
				else if (roomNumber.equals ("RDE205"))
				{
					if (status.equals ("Available"))
						lux25.setSelected (false);
					else
						lux25.setSelected (true);
				}
				else if (roomNumber.equals ("RDE206"))
				{
					if (status.equals ("Available"))
						lux26.setSelected (false);
					else
						lux26.setSelected (true);
				}
				else if (roomNumber.equals ("RDE207"))
				{
					if (status.equals ("Available"))
						lux27.setSelected (false);
					else
						lux27.setSelected (true);
				}
				else if (roomNumber.equals ("RDE208"))
				{
					if (status.equals ("Available"))
						lux28.setSelected (false);
					else
						lux28.setSelected (true);
				}
				else if (roomNumber.equals ("RDE301"))
				{
					if (status.equals ("Available"))
						lux31.setSelected (false);
					else
						lux31.setSelected (true);
				}
				else if (roomNumber.equals ("RDE302"))
				{
					if (status.equals ("Available"))
						lux32.setSelected (false);
					else
						lux32.setSelected (true);
				}
				else if (roomNumber.equals ("RDE303"))
				{
					if (status.equals ("Available"))
						lux33.setSelected (false);
					else
						lux33.setSelected (true);
				}
				else if (roomNumber.equals ("RDE304"))
				{
					if (status.equals ("Available"))
						lux34.setSelected (false);
					else
						lux34.setSelected (true);
				}
				else if (roomNumber.equals ("RDE305"))
				{
					if (status.equals ("Available"))
						lux35.setSelected (false);
					else
						lux35.setSelected (true);
				}
				else if (roomNumber.equals ("RDE306"))
				{
					if (status.equals ("Available"))
						lux36.setSelected (false);
					else
						lux36.setSelected (true);
				}
				else if (roomNumber.equals ("RDE307"))
				{
					if (status.equals ("Available"))
						lux37.setSelected (false);
					else
						lux37.setSelected (true);
				}
				else if (roomNumber.equals ("RDE308"))
				{
					if (status.equals ("Available"))
						lux38.setSelected (false);
					else
						lux38.setSelected (true);
				}
				else if (roomNumber.equals ("RDE401"))
				{
					if (status.equals ("Available"))
						lux41.setSelected (false);
					else
						lux41.setSelected (true);
				}
				else if (roomNumber.equals ("RDE402"))
				{
					if (status.equals ("Available"))
						lux42.setSelected (false);
					else
						lux42.setSelected (true);
				}
				else if (roomNumber.equals ("RDE403"))
				{
					if (status.equals ("Available"))
						lux43.setSelected (false);
					else
						lux43.setSelected (true);
				}
				else if (roomNumber.equals ("RDE404"))
				{
					if (status.equals ("Available"))
						lux44.setSelected (false);
					else
						lux44.setSelected (true);
				}
				else if (roomNumber.equals ("RDE405"))
				{
					if (status.equals ("Available"))
						lux45.setSelected (false);
					else
						lux45.setSelected (true);
				}
				else if (roomNumber.equals ("RDE406"))
				{
					if (status.equals ("Available"))
						lux46.setSelected (false);
					else
						lux46.setSelected (true);
				}
				else if (roomNumber.equals ("RDE407"))
				{
					if (status.equals ("Available"))
						lux47.setSelected (false);
					else
						lux47.setSelected (true);
				}
				else if (roomNumber.equals ("RDE408"))
				{
					if (status.equals ("Available"))
						lux48.setSelected (false);
					else
						lux48.setSelected (true);
				}
				else if (roomNumber.equals ("RDE501"))
				{
					if (status.equals ("Available"))
						lux51.setSelected (false);
					else
						lux51.setSelected (true);
				}
				else if (roomNumber.equals ("RDE502"))
				{
					if (status.equals ("Available"))
						lux52.setSelected (false);
					else
						lux52.setSelected (true);
				}
				else if (roomNumber.equals ("RDE503"))
				{
					if (status.equals ("Available"))
						lux53.setSelected (false);
					else
						lux53.setSelected (true);
				}
				else if (roomNumber.equals ("RDE504"))
				{
					if (status.equals ("Available"))
						lux54.setSelected (false);
					else
						lux54.setSelected (true);
				}
				else if (roomNumber.equals ("RDE505"))
				{
					if (status.equals ("Available"))
						lux55.setSelected (false);
					else
						lux55.setSelected (true);
				}
				else if (roomNumber.equals ("RDE506"))
				{
					if (status.equals ("Available"))
						lux56.setSelected (false);
					else
						lux56.setSelected (true);
				}
				else if (roomNumber.equals ("RDE507"))
				{
					if (status.equals ("Available"))
						lux57.setSelected (false);
					else
						lux57.setSelected (true);
				}
				else if (roomNumber.equals ("RDE508"))
				{
					if (status.equals ("Available"))
						lux58.setSelected (false);
					else
						lux58.setSelected (true);
				}
				else if (roomNumber.equals ("RDE601"))
				{
					if (status.equals ("Available"))
						lux61.setSelected (false);
					else
						lux61.setSelected (true);
				}
				else if (roomNumber.equals ("RDE602"))
				{
					if (status.equals ("Available"))
						lux62.setSelected (false);
					else
						lux62.setSelected (true);
				}
				else if (roomNumber.equals ("RDE603"))
				{
					if (status.equals ("Available"))
						lux63.setSelected (false);
					else
						lux63.setSelected (true);
				}
				else if (roomNumber.equals ("RDE604"))
				{
					if (status.equals ("Available"))
						lux64.setSelected (false);
					else
						lux64.setSelected (true);
				}
				else if (roomNumber.equals ("RDE605"))
				{
					if (status.equals ("Available"))
						lux65.setSelected (false);
					else
						lux65.setSelected (true);
				}
				else if (roomNumber.equals ("RDE606"))
				{
					if (status.equals ("Available"))
						lux66.setSelected (false);
					else
						lux66.setSelected (true);
				}
				else if (roomNumber.equals ("RDE607"))
				{
					if (status.equals ("Available"))
						lux67.setSelected (false);
					else
						lux67.setSelected (true);
				}
				else if (roomNumber.equals ("RDE608"))
				{
					if (status.equals ("Available"))
						lux68.setSelected (false);
					else
						lux68.setSelected (true);
				}
				else if (roomNumber.equals ("RDE701"))
				{
					if (status.equals ("Available"))
						lux71.setSelected (false);
					else
						lux71.setSelected (true);
				}
				else if (roomNumber.equals ("RDE702"))
				{
					if (status.equals ("Available"))
						lux72.setSelected (false);
					else
						lux72.setSelected (true);
				}
				else if (roomNumber.equals ("RDE703"))
				{
					if (status.equals ("Available"))
						lux73.setSelected (false);
					else
						lux73.setSelected (true);
				}
				else if (roomNumber.equals ("RDE704"))
				{
					if (status.equals ("Available"))
						lux74.setSelected (false);
					else
						lux74.setSelected (true);
				}
				else if (roomNumber.equals ("RDE705"))
				{
					if (status.equals ("Available"))
						lux75.setSelected (false);
					else
						lux75.setSelected (true);
				}
				else if (roomNumber.equals ("RDE706"))
				{
					if (status.equals ("Available"))
						lux76.setSelected (false);
					else
						lux76.setSelected (true);
				}
				else if (roomNumber.equals ("RDE707"))
				{
					if (status.equals ("Available"))
						lux77.setSelected (false);
					else
						lux77.setSelected (true);
				}
				else if (roomNumber.equals ("RDE708"))
				{
					if (status.equals ("Available"))
						lux78.setSelected (false);
					else
						lux78.setSelected (true);
				}
				else if (roomNumber.equals ("RDE801"))
				{
					if (status.equals ("Available"))
						lux81.setSelected (false);
					else
						lux81.setSelected (true);
				}
				else if (roomNumber.equals ("RDE802"))
				{
					if (status.equals ("Available"))
						lux82.setSelected (false);
					else
						lux82.setSelected (true);
				}
				else if (roomNumber.equals ("RDE803"))
				{
					if (status.equals ("Available"))
						lux83.setSelected (false);
					else
						lux83.setSelected (true);
				}
				else if (roomNumber.equals ("RDE804"))
				{
					if (status.equals ("Available"))
						lux84.setSelected (false);
					else
						lux84.setSelected (true);
				}
				else if (roomNumber.equals ("RDE805"))
				{
					if (status.equals ("Available"))
						lux85.setSelected (false);
					else
						lux85.setSelected (true);
				}
				else if (roomNumber.equals ("RDE806"))
				{
					if (status.equals ("Available"))
						lux86.setSelected (false);
					else
						lux86.setSelected (true);
				}
				else if (roomNumber.equals ("RDE807"))
				{
					if (status.equals ("Available"))
						lux87.setSelected (false);
					else
						lux87.setSelected (true);
				}
				else if (roomNumber.equals ("RDE808"))
				{
					if (status.equals ("Available"))
						lux88.setSelected (false);
					else
						lux88.setSelected (true);
				}
				else if (roomNumber.equals ("RDE901"))
				{
					if (status.equals ("Available"))
						lux91.setSelected (false);
					else
						lux91.setSelected (true);
				}
				else if (roomNumber.equals ("RDE902"))
				{
					if (status.equals ("Available"))
						lux92.setSelected (false);
					else
						lux92.setSelected (true);
				}
				else if (roomNumber.equals ("RDE903"))
				{
					if (status.equals ("Available"))
						lux93.setSelected (false);
					else
						lux93.setSelected (true);
				}
				else if (roomNumber.equals ("RDE904"))
				{
					if (status.equals ("Available"))
						lux94.setSelected (false);
					else
						lux94.setSelected (true);
				}
				else if (roomNumber.equals ("RDE905"))
				{
					if (status.equals ("Available"))
						lux95.setSelected (false);
					else
						lux95.setSelected (true);
				}
				else if (roomNumber.equals ("RDE906"))
				{
					if (status.equals ("Available"))
						lux96.setSelected (false);
					else
						lux96.setSelected (true);
				}
				else if (roomNumber.equals ("RDE907"))
				{
					if (status.equals ("Available"))
						lux97.setSelected (false);
					else
						lux97.setSelected (true);
				}
				else if (roomNumber.equals ("RDE908"))
				{
					if (status.equals ("Available"))
						lux98.setSelected (false);
					else
						lux98.setSelected (true);
				}
				else if (roomNumber.equals ("RSD001"))
				{
					if (status.equals ("Available"))
						suLux01.setSelected (false);
					else
						suLux01.setSelected (true);
				}
				else if (roomNumber.equals ("RSD002"))
				{
					if (status.equals ("Available"))
						suLux02.setSelected (false);
					else
						suLux02.setSelected (true);
				}
				else if (roomNumber.equals ("RSD003"))
				{
					if (status.equals ("Available"))
						suLux03.setSelected (false);
					else
						suLux03.setSelected (true);
				}
				else if (roomNumber.equals ("RSD004"))
				{
					if (status.equals ("Available"))
						suLux04.setSelected (false);
					else
						suLux04.setSelected (true);
				}
				else if (roomNumber.equals ("RSD101"))
				{
					if (status.equals ("Available"))
						suLux11.setSelected (false);
					else
						suLux11.setSelected (true);
				}
				else if (roomNumber.equals ("RSD102"))
				{
					if (status.equals ("Available"))
						suLux12.setSelected (false);
					else
						suLux12.setSelected (true);
				}
				else if (roomNumber.equals ("RSD103"))
				{
					if (status.equals ("Available"))
						suLux13.setSelected (false);
					else
						suLux13.setSelected (true);
				}
				else if (roomNumber.equals ("RSD104"))
				{
					if (status.equals ("Available"))
						suLux14.setSelected (false);
					else
						suLux14.setSelected (true);
				}
				else if (roomNumber.equals ("RSD201"))
				{
					if (status.equals ("Available"))
						suLux21.setSelected (false);
					else
						suLux21.setSelected (true);
				}
				else if (roomNumber.equals ("RSD202"))
				{
					if (status.equals ("Available"))
						suLux22.setSelected (false);
					else
						suLux22.setSelected (true);
				}
				else if (roomNumber.equals ("RSD203"))
				{
					if (status.equals ("Available"))
						suLux23.setSelected (false);
					else
						suLux23.setSelected (true);
				}
				else if (roomNumber.equals ("RSD204"))
				{
					if (status.equals ("Available"))
						suLux24.setSelected (false);
					else
						suLux24.setSelected (true);
				}
				else if (roomNumber.equals ("RSD301"))
				{
					if (status.equals ("Available"))
						suLux31.setSelected (false);
					else
						suLux31.setSelected (true);
				}
				else if (roomNumber.equals ("RSD302"))
				{
					if (status.equals ("Available"))
						suLux32.setSelected (false);
					else
						suLux32.setSelected (true);
				}
				else if (roomNumber.equals ("RSD303"))
				{
					if (status.equals ("Available"))
						suLux33.setSelected (false);
					else
						suLux33.setSelected (true);
				}
				else if (roomNumber.equals ("RSD304"))
				{
					if (status.equals ("Available"))
						suLux34.setSelected (false);
					else
						suLux34.setSelected (true);
				}
				else if (roomNumber.equals ("RSD401"))
				{
					if (status.equals ("Available"))
						suLux41.setSelected (false);
					else
						suLux41.setSelected (true);
				}
				else if (roomNumber.equals ("RSD402"))
				{
					if (status.equals ("Available"))
						suLux42.setSelected (false);
					else
						suLux42.setSelected (true);
				}
				else if (roomNumber.equals ("RSD403"))
				{
					if (status.equals ("Available"))
						suLux43.setSelected (false);
					else
						suLux43.setSelected (true);
				}
				else if (roomNumber.equals ("RSD404"))
				{
					if (status.equals ("Available"))
						suLux44.setSelected (false);
					else
						suLux44.setSelected (true);
				}
				else if (roomNumber.equals ("RSD501"))
				{
					if (status.equals ("Available"))
						suLux51.setSelected (false);
					else
						suLux51.setSelected (true);
				}
				else if (roomNumber.equals ("RSD502"))
				{
					if (status.equals ("Available"))
						suLux52.setSelected (false);
					else
						suLux52.setSelected (true);
				}
				else if (roomNumber.equals ("RSD503"))
				{
					if (status.equals ("Available"))
						suLux53.setSelected (false);
					else
						suLux53.setSelected (true);
				}
				else if (roomNumber.equals ("RSD504"))
				{
					if (status.equals ("Available"))
						suLux54.setSelected (false);
					else
						suLux54.setSelected (true);
				}
				else if (roomNumber.equals ("RSD601"))
				{
					if (status.equals ("Available"))
						suLux61.setSelected (false);
					else
						suLux61.setSelected (true);
				}
				else if (roomNumber.equals ("RSD602"))
				{
					if (status.equals ("Available"))
						suLux62.setSelected (false);
					else
						suLux62.setSelected (true);
				}
				else if (roomNumber.equals ("RSD603"))
				{
					if (status.equals ("Available"))
						suLux63.setSelected (false);
					else
						suLux63.setSelected (true);
				}
				else if (roomNumber.equals ("RSD604"))
				{
					if (status.equals ("Available"))
						suLux64.setSelected (false);
					else
						suLux64.setSelected (true);
				}
				else if (roomNumber.equals ("RSD701"))
				{
					if (status.equals ("Available"))
						suLux71.setSelected (false);
					else
						suLux71.setSelected (true);
				}
				else if (roomNumber.equals ("RSD702"))
				{
					if (status.equals ("Available"))
						suLux72.setSelected (false);
					else
						suLux72.setSelected (true);
				}
				else if (roomNumber.equals ("RSD703"))
				{
					if (status.equals ("Available"))
						suLux73.setSelected (false);
					else
						suLux73.setSelected (true);
				}
				else if (roomNumber.equals ("RSD704"))
				{
					if (status.equals ("Available"))
						suLux74.setSelected (false);
					else
						suLux74.setSelected (true);
				}
				else if (roomNumber.equals ("RSD801"))
				{
					if (status.equals ("Available"))
						suLux81.setSelected (false);
					else
						suLux81.setSelected (true);
				}
				else if (roomNumber.equals ("RSD802"))
				{
					if (status.equals ("Available"))
						suLux82.setSelected (false);
					else
						suLux82.setSelected (true);
				}
				else if (roomNumber.equals ("RSD803"))
				{
					if (status.equals ("Available"))
						suLux83.setSelected (false);
					else
						suLux83.setSelected (true);
				}
				else if (roomNumber.equals ("RSD804"))
				{
					if (status.equals ("Available"))
						suLux84.setSelected (false);
					else
						suLux84.setSelected (true);
				}
				else if (roomNumber.equals ("RSD901"))
				{
					if (status.equals ("Available"))
						suLux91.setSelected (false);
					else
						suLux91.setSelected (true);
				}
				else if (roomNumber.equals ("RSD902"))
				{
					if (status.equals ("Available"))
						suLux92.setSelected (false);
					else
						suLux92.setSelected (true);
				}
				else if (roomNumber.equals ("RSD903"))
				{
					if (status.equals ("Available"))
						suLux93.setSelected (false);
					else
						suLux93.setSelected (true);
				}
				else if (roomNumber.equals ("RSD904"))
				{
					if (status.equals ("Available"))
						suLux94.setSelected (false);
					else
						suLux94.setSelected (true);
				}
				else if (roomNumber.equals ("SPM001"))
				{
					if (status.equals ("Available"))
						pm01.setSelected (false);
					else
						pm01.setSelected (true);
				}
				else if (roomNumber.equals ("SPM101"))
				{
					if (status.equals ("Available"))
						pm11.setSelected (false);
					else
						pm11.setSelected (true);
				}
				else if (roomNumber.equals ("SPM201"))
				{
					if (status.equals ("Available"))
						pm21.setSelected (false);
					else
						pm21.setSelected (true);
				}
				else if (roomNumber.equals ("SPM301"))
				{
					if (status.equals ("Available"))
						pm31.setSelected (false);
					else
						pm31.setSelected (true);
				}
				else if (roomNumber.equals ("SPM401"))
				{
					if (status.equals ("Available"))
						pm41.setSelected (false);
					else
						pm41.setSelected (true);
				}
				else if (roomNumber.equals ("SPM501"))
				{
					if (status.equals ("Available"))
						pm51.setSelected (false);
					else
						pm51.setSelected (true);
				}
				else if (roomNumber.equals ("SPM601"))
				{
					if (status.equals ("Available"))
						pm61.setSelected (false);
					else
						pm61.setSelected (true);
				}
				else if (roomNumber.equals ("SPM701"))
				{
					if (status.equals ("Available"))
						pm71.setSelected (false);
					else
						pm71.setSelected (true);
				}
				else if (roomNumber.equals ("SPM801"))
				{
					if (status.equals ("Available"))
						pm81.setSelected (false);
					else
						pm81.setSelected (true);
				}
				else if (roomNumber.equals ("SPM901"))
				{
					if (status.equals ("Available"))
						pm91.setSelected (false);
					else
						pm91.setSelected (true);
				}
				else if (roomNumber.equals ("SPD001"))
				{
					if (status.equals ("Available"))
						pd01.setSelected (false);
					else
						pd01.setSelected (true);
				}
				else if (roomNumber.equals ("SPD101"))
				{
					if (status.equals ("Available"))
						pd11.setSelected (false);
					else
						pd11.setSelected (true);
				}
				else if (roomNumber.equals ("SPD201"))
				{
					if (status.equals ("Available"))
						pd21.setSelected (false);
					else
						pd21.setSelected (true);
				}
				else if (roomNumber.equals ("SPD301"))
				{
					if (status.equals ("Available"))
						pd31.setSelected (false);
					else
						pd31.setSelected (true);
				}
				else if (roomNumber.equals ("SPD401"))
				{
					if (status.equals ("Available"))
						pd41.setSelected (false);
					else
						pd41.setSelected (true);
				}
				else if (roomNumber.equals ("SPD501"))
				{
					if (status.equals ("Available"))
						pd51.setSelected (false);
					else
						pd51.setSelected (true);
				}
				else if (roomNumber.equals ("SPD601"))
				{
					if (status.equals ("Available"))
						pd61.setSelected (false);
					else
						pd61.setSelected (true);
				}
				else if (roomNumber.equals ("SPD701"))
				{
					if (status.equals ("Available"))
						pd71.setSelected (false);
					else
						pd71.setSelected (true);
				}
				else if (roomNumber.equals ("SPD801"))
				{
					if (status.equals ("Available"))
						pd81.setSelected (false);
					else
						pd81.setSelected (true);
				}
				else if (roomNumber.equals ("SPD901"))
				{
					if (status.equals ("Available"))
						pd91.setSelected (false);
					else
						pd91.setSelected (true);
				}
				else if (roomNumber.equals ("SEX001"))
				{
					if (status.equals ("Available"))
						ex01.setSelected (false);
					else
						ex01.setSelected (true);
				}
				else if (roomNumber.equals ("SEX101"))
				{
					if (status.equals ("Available"))
						ex11.setSelected (false);
					else
						ex11.setSelected (true);
				}
				else if (roomNumber.equals ("SEX201"))
				{
					if (status.equals ("Available"))
						ex21.setSelected (false);
					else
						ex21.setSelected (true);
				}
				else if (roomNumber.equals ("SEX301"))
				{
					if (status.equals ("Available"))
						ex31.setSelected (false);
					else
						ex31.setSelected (true);
				}
				else if (roomNumber.equals ("SEX401"))
				{
					if (status.equals ("Available"))
						ex41.setSelected (false);
					else
						ex41.setSelected (true);
				}
				else if (roomNumber.equals ("SEX501"))
				{
					if (status.equals ("Available"))
						ex51.setSelected (false);
					else
						ex51.setSelected (true);
				}
				else if (roomNumber.equals ("SEX601"))
				{
					if (status.equals ("Available"))
						ex61.setSelected (false);
					else
						ex61.setSelected (true);
				}
				else if (roomNumber.equals ("SEX701"))
				{
					if (status.equals ("Available"))
						ex71.setSelected (false);
					else
						ex71.setSelected (true);
				}
				else if (roomNumber.equals ("SEX801"))
				{
					if (status.equals ("Available"))
						ex81.setSelected (false);
					else
						ex81.setSelected (true);
				}
				else if (roomNumber.equals ("SEX901"))
				{
					if (status.equals ("Available"))
						ex91.setSelected (false);
					else
						ex91.setSelected (true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		container.revalidate();
		container.repaint();
		if (checker == 1)
		{
			checker = 0;
			cardLayout.show (mainPanel, "Booking");
		}
	}

	//actionlistener for the register button
	public static void registerAction (ActionEvent ae)
	{
		try{
		String fn=custFirstNameRegd.getText();
		String mn=custMidNameRegd.getText();
		String ln=custLastNameRegd.getText();
		if(fn.equals("First Name"))
			fn="";
		if(mn.equals("Middle Name"))
			mn="";
		if(ln.equals("Last Name"))
			ln="";
		String cd = custCodeRegd.getText ();
		if (cd.equals ("Code"))
			cd = "";
		int age = 0;
		try{
		age = Integer.parseInt ((String) custAgeRegd.getSelectedItem ());
		}catch (Exception e)
		{
			age = 0;
		}
		String ms = (String) custMSRegd.getSelectedItem ();
		if (ms.equals ("Marital Status"))
			ms = "";
		String cnt = custCountryRegd.getText ();
		if (cnt.equals ("Country"))
			cnt = "";
		int pp = 0;
		try {
		pp = Integer.parseInt(custPassportRegd.getText ());
		}catch (Exception e)
		{
			pp = 0;
		}
		int visa = 0;
		try {
			visa = Integer.parseInt (custVisaRegd.getText ());
		}catch (Exception e)
		{
			visa = 0;
		}
		String e=custEmailRegd.getText();
		if(e.equals("Email"))
			e="";
		int flag=0;
		boolean eStr=false;
		for(int i=0;i<e.length();i++)
		{
			char ch=e.charAt(i);
			if(ch=='@')
			{
				flag++;
				break;
			}
		}
		if(flag==1)
			eStr=true;
		else
			eStr=false;
		boolean phStr=true;
		long pho=0;
		try{
		pho=Long.parseLong(custPhoneRegd.getText());
		}catch(Exception ee)
		{
			phStr=false;
		}
		String ph=Long.toString(pho);
		if(ph.length()>10||ph.length()<10)
			phStr=false;
		else
		{
			for(int i=0;i<ph.length();i++)
			{
				char ch=ph.charAt(i);
				if(ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9')
					phStr=true;
				else
					phStr=false;
			}
		}
		String gen="";
		if(custMale.isSelected())
			gen="M";
		else if(custFemale.isSelected())
			gen="F";
		else if(custOther.isSelected())
			gen="O";
		String stat = (String) custStatusRegd.getSelectedItem ();
		if (stat.equals ("Customer Status"))
			stat = "";
		String res = custResidentRegd.getText ();
		if (res.equals ("Residential Address"))
			res = "";
		String ofc = custOfficeRegd.getText ();
		if (ofc.equals ("Office Address (if any)"))
			ofc = "";
		if(cd.equals("")||age==0||ms.equals("")||cnt.equals("")||pho==0||fn.equals("")||stat.equals("")||ln.equals("")||e.equals("")||ph.equals("")||gen.equals("")||res.equals(""))
		{
			wrongInfo.setText("Information is not complete!");
			wrongCustMail.setText("");
			wrongCustPh.setText("");
		}
		else if(eStr==false)
		{
			wrongInfo.setText("");
			wrongCustMail.setText("Please enter a valid email address!");
			wrongCustPh.setText("");
		}
		else if(phStr==false)
		{
			wrongInfo.setText("");
			wrongCustMail.setText("");
			wrongCustPh.setText("Please enter a valid phone number!");
		}
		else
		{
				PreparedStatement ps=conn.prepareStatement("insert into Customer_Details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,cd);
				ps.setString(2,fn);
				ps.setString(3,mn);
				ps.setString(4,ln);
				ps.setString(5,gen);
				ps.setInt(6,age);
				ps.setString(7,ms);
				ps.setString(8,cnt);
				ps.setInt(9,pp);
				ps.setInt(10,visa);
				ps.setString(11,res);
				ps.setString(12,ofc);
				ps.setLong(13,pho);
				ps.setString(14,e);
				ps.setString(15,stat);
				int status=ps.executeUpdate();
				if(status!=0)
				{
					wrongInfo.setText("Registered successfully!");
					wrongCustMail.setText("");
					wrongCustPh.setText("");
				}
				else
				{
					wrongInfo.setText("Error!");
					wrongCustMail.setText("");
					wrongCustPh.setText("");
				}
			
		}
		}catch(Exception ee)
		{
			ee.printStackTrace();
			//wrongInfo.setText("Error!!");
		}
	}

	//actionlistener for new registration button of registration panel
	public static void newRegdAction (ActionEvent ae)
	{
		custCodeRegd.setText("Code");
		custCodeRegd.setForeground(Color.GRAY);
		custFirstNameRegd.setText("First Name");
		custFirstNameRegd.setForeground(Color.GRAY);
		custMidNameRegd.setText("Middle Name");
		custMidNameRegd.setForeground(Color.GRAY);
		custLastNameRegd.setText("Last Name");
		custLastNameRegd.setForeground(Color.GRAY);
		custCountryRegd.setText("Country");
		custCountryRegd.setForeground(Color.GRAY);
		custPassportRegd.setText("Passport Number");
		custPassportRegd.setForeground(Color.GRAY);
		custVisaRegd.setText("Visa Number");
		custVisaRegd.setForeground(Color.GRAY);
		custResidentRegd.setText("Residential Address");
		custResidentRegd.setForeground(Color.GRAY);
		custOfficeRegd.setText("Office Address (if any)");
		custOfficeRegd.setForeground(Color.GRAY);
		custPhoneRegd.setText("Phone");
		custPhoneRegd.setForeground(Color.GRAY);
		custEmailRegd.setText("Email");
		custEmailRegd.setForeground(Color.GRAY);
		custAgeRegd.setSelectedItem ("Age");
		custMSRegd.setSelectedItem ("Marital Status");
		custStatusRegd.setSelectedItem ("Customer Status");
		wrongInfo.setText ("");
		wrongCustMail.setText ("");
		wrongCustPh.setText ("");
		newCustPanel.repaint ();
		newCustPanel.revalidate ();
	}

	//actionlistener for the home button of registration panel
	public static void homeCustRegdAction (ActionEvent ae)
	{
		container.remove (pinkBackLabel);
		container.add (redBackLabel);
		home.setEnabled (false);
		cardLayout.show (mainPanel, "Dept");
		container.revalidate();
		container.repaint();
	}

	//focuslistener for customer code field of enquiry panel
	public static void custCodeEnqFocusGained (FocusEvent fe)
	{
		if (custCodeEnq.getText ().equals ("Enter Customer Code"))
		{
			custCodeEnq.setText ("");
			custCodeEnq.setForeground (Color.BLACK);
		}
	}

	public static void custCodeEnqFocusLost (FocusEvent fe)
	{
		if (custCodeEnq.getText ().equals (""))
		{
			custCodeEnq.setText ("Enter Customer Code");
			custCodeEnq.setForeground (Color.GRAY);
		}
	}

	//actionlistener for check button in enquiry panel
	public static void checkDataAction (ActionEvent ae)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select * from Customer_Details where customer_code=?");
			String custCode = custCodeEnq.getText ();
			String gend = "", mn = "", ppno="", vno="";
			ps.setString (1,custCode);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
			{
				if (rs.getString ("gender").equals ("M"))
					gend = "Male";
				else if (rs.getString ("gender").equals ("F"))
					gend = "Female";
				else if (rs.getString ("gender").equals ("O"))
					gend = "Other";
				if (rs.getString ("midde_name")==null)
					mn = "";
				else
					mn = rs.getString ("midde_name");
				if (rs.getInt ("passport_number")==0)
					ppno = "";
				else
					ppno = Integer.toString (rs.getInt ("passport_number"));
				if (rs.getInt ("visa_no")==0)
					vno = "";
				else
					vno = Integer.toString (rs.getInt ("visa_no"));
				if ((gend.equals ("Other")||gend.equals ("Male"))&&rs.getString ("status").equals ("Other"))
					customerData.setText ("NAME: "+rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name")+"\nSTATUS: "+rs.getString ("status")+"\t\tGENDER: "+gend+"\t\tAGE: "+rs.getInt ("age")+"\nMARITAL STATUS: "+rs.getString ("marital_status")+"\tPHONE: "+rs.getLong ("phone")+"\tCOUNTRY: "+rs.getString ("country")+"\nE-MAIL ADDRESS:"+rs.getString ("email_address")+"\nPASSPORT NUMBER: "+ppno+"\nVISA NUMBER: "+vno+"\nRESIDENTIAL ADDRESS: "+rs.getString ("residential_address")+"\nOFFICE ADDRESS: "+rs.getString ("official_address"));
				else if (gend.equals ("Other")||gend.equals ("Male"))
					customerData.setText ("NAME: "+rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name")+"\nSTATUS: "+rs.getString ("status")+"\tGENDER: "+gend+"\t\tAGE: "+rs.getInt ("age")+"\nMARITAL STATUS: "+rs.getString ("marital_status")+"\tPHONE: "+rs.getLong ("phone")+"\tCOUNTRY: "+rs.getString ("country")+"\nE-MAIL ADDRESS:"+rs.getString ("email_address")+"\nPASSPORT NUMBER: "+ppno+"\nVISA NUMBER: "+vno+"\nRESIDENTIAL ADDRESS: "+rs.getString ("residential_address")+"\nOFFICE ADDRESS: "+rs.getString ("official_address"));
				else if (rs.getString ("status").equals ("Other"))
					customerData.setText ("NAME: "+rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name")+"\nSTATUS: "+rs.getString ("status")+"\t\tGENDER: "+gend+"\tAGE: "+rs.getInt ("age")+"\nMARITAL STATUS: "+rs.getString ("marital_status")+"\tPHONE: "+rs.getLong ("phone")+"\tCOUNTRY: "+rs.getString ("country")+"\nE-MAIL ADDRESS:"+rs.getString ("email_address")+"\nPASSPORT NUMBER: "+ppno+"\nVISA NUMBER: "+vno+"\nRESIDENTIAL ADDRESS: "+rs.getString ("residential_address")+"\nOFFICE ADDRESS: "+rs.getString ("official_address"));
				else
					customerData.setText ("NAME: "+rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name")+"\nSTATUS: "+rs.getString ("status")+"\tGENDER: "+gend+"\tAGE: "+rs.getInt ("age")+"\nMARITAL STATUS: "+rs.getString ("marital_status")+"\tPHONE: "+rs.getLong ("phone")+"\tCOUNTRY: "+rs.getString ("country")+"\nE-MAIL ADDRESS:"+rs.getString ("email_address")+"\nPASSPORT NUMBER: "+ppno+"\nVISA NUMBER: "+vno+"\nRESIDENTIAL ADDRESS: "+rs.getString ("residential_address")+"\nOFFICE ADDRESS: "+rs.getString ("official_address"));
				//customerData.setText ("NAME:\t\t"+rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name")+"\nSTATUS:\t\t"+rs.getString ("status")+"\nGENDER:\t\t"+gend+"\nAGE:\t\t"+rs.getInt ("age")+"\nMARITAL STATUS:\t"+rs.getString ("marital_status")+"\nCOUNTRY:\t\t"+rs.getString ("country")+"\nPHONE:\t\t"+rs.getLong ("phone")+"\nE-MAIL ADDRESS:\t"+rs.getString ("email_address")+"\nPASSPORT NUMBER:\t"+ppno+"\nVISA NUMBER:\t\t"+vno+"\nRESIDENTIAL ADDRESS:\t"+rs.getString ("residential_address")+"\nOFFICE ADDRESS:\t"+rs.getString ("official_address"));
			}
			else
				customerData.setText ("Error!");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	//actionlistener for back to home button in enquiry panel
	public static void homeEnqAction (ActionEvent ae)
	{
		container.remove (grayBlueBackLabel);
		container.add (redBackLabel);
		home.setEnabled (false);
		cardLayout.show (mainPanel, "Dept");
		container.revalidate();
		container.repaint();
	}

	//listeners for booking panel
	//focuslisteners for the textfields
	public static void custCodeBookFocusGained (FocusEvent fe)
	{
		if (custCodeBook.getText (). equals ("Enter Customer Code"))
		{
			custCodeBook.setText ("");
			custCodeBook.setForeground (Color.BLACK);
		}
	}

	public static void custCodeBookFocusLost (FocusEvent fe)
	{
		if (custCodeBook.getText (). equals (""))
		{
			custCodeBook.setText ("Enter Customer Code");
			custCodeBook.setForeground (Color.GRAY);
		}
	}

	public static void bookCodeFocusGained (FocusEvent fe)
	{
		if (bookCode.getText (). equals ("Booking Code"))
		{
			bookCode.setText ("");
			bookCode.setForeground (Color.BLACK);
		}
	}

	public static void bookCodeFocusLost (FocusEvent fe)
	{
		if (bookCode.getText (). equals (""))
		{
			bookCode.setText ("Booking Code");
			bookCode.setForeground (Color.GRAY);
		}
	}

	public static void roomBookFocusGained (FocusEvent fe)
	{
		if (roomBook.getText (). equals ("Room Details"))
		{
			roomBook.setText ("");
			roomBook.setForeground (Color.BLACK);
		}
	}

	public static void roomBookFocusLost (FocusEvent fe)
	{
		if (roomBook.getText (). equals (""))
		{
			roomBook.setText ("Room Details");
			roomBook.setForeground (Color.GRAY);
		}
	}

	public static void arriveBookFocusGained (FocusEvent fe)
	{
		if (arriveBook.getText (). equals ("Date of Arrival (dd/mm/yyyy)"))
		{
			arriveBook.setText ("");
			arriveBook.setForeground (Color.BLACK);
		}
	}

	public static void arriveBookFocusLost (FocusEvent fe)
	{
		if (arriveBook.getText (). equals (""))
		{
			arriveBook.setText ("Date of Arrival (dd/mm/yyyy)");
			arriveBook.setForeground (Color.GRAY);
		}
	}

	public static void personsBookFocusGained (FocusEvent fe)
	{
		if (personsBook.getText (). equals ("Number of Persons"))
		{
			personsBook.setText ("");
			personsBook.setForeground (Color.BLACK);
		}
	}

	public static void personsBookFocusLost (FocusEvent fe)
	{
		if (personsBook.getText (). equals (""))
		{
			personsBook.setText ("Number of Persons");
			personsBook.setForeground (Color.GRAY);
		}
	}

	public static void relationBookFocusGained (FocusEvent fe)
	{
		if (relationBook.getText (). equals ("Relation"))
		{
			relationBook.setText ("");
			relationBook.setForeground (Color.BLACK);
		}
	}

	public static void relationBookFocusLost (FocusEvent fe)
	{
		if (relationBook.getText (). equals (""))
		{
			relationBook.setText ("Relation");
			relationBook.setForeground (Color.GRAY);
		}
	}

	//actionlisteners for the buttons of the booking panel
	public static void fetchDataBookAction (ActionEvent ae)
	{
		String custCodeStr = custCodeBook.getText ();
		if (custCodeStr.equals ("Enter Customer Code"))
			bookingWarn.setText ("Please enter a valid Customer Code!");
		else
		{
			try
			{
				//fetch and display the data
				PreparedStatement ps = conn.prepareStatement ("select first_name,midde_name,last_name,phone,email_address,status,gender,residential_address from Customer_Details where customer_code=?");
				ps.setString (1,custCodeStr);
				ResultSet rs = ps.executeQuery ();
				String mn = "", gend= "";
				if (rs.next ())
				{
					custCodeBook.setEditable (false);
					if (rs.getString ("midde_name") == null)
						mn = "";
					else
						mn = rs.getString ("midde_name");
					if (rs.getString ("gender").equals ("M"))
						gend = "Male";
					else if (rs.getString ("gender").equals ("F"))
						gend = "Female";
					else if (rs.getString ("gender").equals ("O"))
						gend = "Other";
					custNameBook.setText (rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name"));
					custNameBook.setForeground (Color.BLACK);
					custPhoneBook.setText (Long.toString (rs.getLong ("phone")));
					custPhoneBook.setForeground (Color.BLACK);
					custEmailBook.setText (rs.getString ("email_address"));
					custEmailBook.setForeground (Color.BLACK);
					custStatusBook.setText (rs.getString ("status"));
					custStatusBook.setForeground (Color.BLACK);
					custGenderBook.setText (gend);
					custGenderBook.setForeground (Color.BLACK);
					custAddrBook.setText (rs.getString ("residential_address"));
					custAddrBook.setForeground (Color.BLACK);
					bookingWarn.setText ("");
				}
				else
					bookingWarn.setText ("Wrong Customer Code!");
			}
			catch (Exception e)
			{
				e.printStackTrace ();
				//bookingWarn.setText ("Error!!");
			}
		}
	}

	//button to reset the booking panel
	public static void newBookAction (ActionEvent ae)
	{
		custCodeBook.setEditable (true);
		custCodeBook.setText ("Enter Customer Code");
		custCodeBook.setForeground (Color.GRAY);
		custNameBook.setText ("Name");
		custNameBook.setForeground (Color.GRAY);
		custAddrBook.setText ("Address");
		custAddrBook.setForeground (Color.GRAY);
		custPhoneBook.setText ("Phone");
		custPhoneBook.setForeground (Color.GRAY);
		custEmailBook.setText ("Email Address");
		custEmailBook.setForeground (Color.GRAY);
		custGenderBook.setText ("Gender");
		custGenderBook.setForeground (Color.GRAY);
		custStatusBook.setText ("Status");
		custStatusBook.setForeground (Color.GRAY);
		bookCode.setText ("Booking Code");
		bookCode.setForeground (Color.GRAY);
		roomBook.setText ("Room Details");
		roomBook.setForeground (Color.GRAY);
		arriveBook.setText ("Date of Arrival (dd/mm/yyyy)");
		arriveBook.setForeground (Color.GRAY);
		personsBook.setText ("Number of Persons");
		personsBook.setForeground (Color.GRAY);
		relationBook.setText ("Relation");
		relationBook.setForeground (Color.GRAY);
		bookingWarn.setText ("");
	}

	public static void availRoomBookAction (ActionEvent ae)
	{
		roomBooking = 1;
		container.remove (purpleBackLabel);
		container.add (pureBlueBackLabel);
		cardLayout.show (mainPanel, "Room Status");
		container.revalidate();
		container.repaint();
	}

	public static void bookRoomButtonAction (ActionEvent ae)
	{
		String cc = custCodeBook.getText ();
		String bc = bookCode.getText ();
		String rn = roomBook.getText ();
		String d = arriveBook.getText ();
		String np = personsBook.getText ();
		String r = relationBook.getText ();
		if (cc.equals ("Enter Customer Code") || cc.equals (""))
			bookingWarn.setText ("Invalid Customer Code!");
		else if (bc.equals ("Booking Code"))
			bookingWarn.setText ("Please enter a valid booking code!");
		else if (d.equals ("Date of Arrival (dd/mm/yyyy)"))
			bookingWarn.setText ("Invalid Date!");
		else if (np.equals ("Number of Persons"))
			bookingWarn.setText ("Invalid number!");
		else if (r.equals ("Relation"))
			bookingWarn.setText ("Invalid Relation!");
		else
		{
			int npNum = 0;
			try
			{
				npNum = Integer.parseInt (np);
			}
			catch (Exception e)
			{
				bookingWarn.setText ("Invalid number!");
			}
			if(d.equals("Date of birth (dd/mm/yyyy)"))
				d="";
			boolean dobStr=true;
			if(d.length()>10||d.length()<10)
				dobStr=false;
			else
			{
				String y=d.substring(6,10);
				int iy=Integer.parseInt(y);
				String m=d.substring(3,5);
				String dt=d.substring(0,2);
				int i=iy%4;
				if(i==0)
				{
					if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30")))
						dobStr=false;
					if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
						dobStr=false;
				}
				else
				{
					if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30"))||(m.equals("02")&&dt.equals("29")))
						dobStr=false;
					if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
						dobStr=false;
				}
			}
			if (dobStr == false)
				bookingWarn.setText ("Invalid Date!");
			else
			{
				try
				{
					PreparedStatement ps = conn.prepareStatement ("insert into Booking_Details values (?,?,?,?,?,?)");
					ps.setString (1,bc);
					ps.setString (2,cc);
					ps.setString (3,rn);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date ud = sdf.parse(d);
					java.sql.Date sqd = new java.sql.Date(ud.getTime());
					ps.setDate (4,sqd);
					ps.setInt (5,npNum);
					ps.setString (6,r);
					int status = ps.executeUpdate ();
					if (status != 0)
						bookingWarn.setText ("Saved successfully!");
					else
						bookingWarn.setText ("Error!");
				}
				catch (Exception e)
				{
					e.printStackTrace ();
				}
			}
		}
	}

	public static void homeBookAction (ActionEvent ae)
	{
		custCodeBook.setEditable (true);
		custCodeBook.setText ("Enter Customer Code");
		custCodeBook.setForeground (Color.GRAY);
		custNameBook.setText ("Name");
		custNameBook.setForeground (Color.GRAY);
		custAddrBook.setText ("Address");
		custAddrBook.setForeground (Color.GRAY);
		custPhoneBook.setText ("Phone");
		custPhoneBook.setForeground (Color.GRAY);
		custEmailBook.setText ("Email Address");
		custEmailBook.setForeground (Color.GRAY);
		custGenderBook.setText ("Gender");
		custGenderBook.setForeground (Color.GRAY);
		custStatusBook.setText ("Status");
		custStatusBook.setForeground (Color.GRAY);
		container.remove (purpleBackLabel);
		container.add (redBackLabel);
		home.setEnabled (false);
		cardLayout.show (mainPanel, "Dept");
		container.revalidate();
		container.repaint();
		bookingWarn.setText ("");
	}

	//focuslisteners for billing panel
	public static void bookCodeBillFocusGained (FocusEvent fe)
	{
		if (bookCodeBill.getText ().equals ("Enter Booking Code"))
		{
			bookCodeBill.setText ("");
			bookCodeBill.setForeground (Color.BLACK);
		}
	}

	public static void bookCodeBillFocusLost (FocusEvent fe)
	{
		if (bookCodeBill.getText ().equals (""))
		{
			bookCodeBill.setText ("Enter Booking Code");
			bookCodeBill.setForeground (Color.GRAY);
		}
	}

	public static void billCodeFocusGained (FocusEvent fe)
	{
		if (billCode.getText ().equals ("Billing Code"))
		{
			billCode.setText ("");
			billCode.setForeground (Color.BLACK);
		}
	}

	public static void billCodeFocusLost (FocusEvent fe)
	{
		if (billCode.getText ().equals (""))
		{
			billCode.setText ("Billing Code");
			billCode.setForeground (Color.GRAY);
		}
	}

	public static void departBillFocusGained (FocusEvent fe)
	{
		if (departBill.getText ().equals ("Date of Departure (dd/mm/yyyy)"))
		{
			departBill.setText ("");
			departBill.setForeground (Color.BLACK);
		}
	}

	public static void departBillFocusLost (FocusEvent fe)
	{
		if (departBill.getText ().equals (""))
		{
			departBill.setText ("Date of Departure (dd/mm/yyyy)");
			departBill.setForeground (Color.GRAY);
		}
	}

	public static void serviceBillFocusGained (FocusEvent fe)
	{
		if (serviceBill.getText ().equals ("Service Charges"))
		{
			serviceBill.setText ("");
			serviceBill.setForeground (Color.BLACK);
		}
	}

	public static void serviceBillFocusLost (FocusEvent fe)
	{
		if (serviceBill.getText ().equals (""))
		{
			serviceBill.setText ("Service Charges");
			serviceBill.setForeground (Color.GRAY);
		}
	}

	public static void gstBillFocusGained (FocusEvent fe)
	{
		if (gstBill.getText ().equals ("GST"))
		{
			gstBill.setText ("");
			gstBill.setForeground (Color.BLACK);
		}
	}

	public static void gstBillFocusLost (FocusEvent fe)
	{
		if (gstBill.getText ().equals (""))
		{
			gstBill.setText ("GST");
			gstBill.setForeground (Color.GRAY);
		}
	}

	public static void discountBillFocusGained (FocusEvent fe)
	{
		if (discountBill.getText ().equals ("Discount (%)"))
		{
			discountBill.setText ("");
			discountBill.setForeground (Color.BLACK);
		}
	}

	public static void discountBillFocusLost (FocusEvent fe)
	{
		if (discountBill.getText ().equals (""))
		{
			discountBill.setText ("Discount (%)");
			discountBill.setForeground (Color.GRAY);
		}
	}

	public static void modeBillFocusGained (FocusEvent fe)
	{
		if (modeBill.getText ().equals ("Payment Mode"))
		{
			modeBill.setText ("");
			modeBill.setForeground (Color.BLACK);
		}
	}

	public static void modeBillFocusLost (FocusEvent fe)
	{
		if (modeBill.getText ().equals (""))
		{
			modeBill.setText ("Payment Mode");
			modeBill.setForeground (Color.GRAY);
		}
	}

	public static void cardNoBillFocusGained (FocusEvent fe)
	{
		if (cardNoBill.getText ().equals ("Card Number"))
		{
			cardNoBill.setText ("");
			cardNoBill.setForeground (Color.BLACK);
		}
	}

	public static void cardNoBillFocusLost (FocusEvent fe)
	{
		if (cardNoBill.getText ().equals (""))
		{
			cardNoBill.setText ("Card Number");
			cardNoBill.setForeground (Color.GRAY);
		}
	}

	public static void receivedBillFocusGained (FocusEvent fe)
	{
		if (receivedBill.getText ().equals ("Received Amount"))
		{
			receivedBill.setText ("");
			receivedBill.setForeground (Color.BLACK);
		}
	}

	public static void receivedBillFocusLost (FocusEvent fe)
	{
		if (receivedBill.getText ().equals (""))
		{
			receivedBill.setText ("Received Amount");
			receivedBill.setForeground (Color.GRAY);
		}
	}

	//actionlisteners for billing panel
	public static void fetchDataBillAction (ActionEvent ae)
	{
		String bookCodeStr = bookCodeBill.getText ();
		if (bookCodeStr.equals ("Enter Booking Code"))
			billingWarn.setText ("Please enter a valid Booking Code!");
		else
		{
			try
			{
				//fetch and display the data
				PreparedStatement ps = conn.prepareStatement ("select c.customer_code, c.first_name, c.midde_name, c.last_name, b.room_no, b.no_of_persons, b.date_of_arrival, r.room_rent from Customer_Details c, Booking_Details b, Room_Details r where c.customer_code = b.customer_code and b.room_no = r.room_no and b.booking_code=?");
				ps.setString (1,bookCodeStr);
				ResultSet rs = ps.executeQuery ();
				String mn = "";
				if (rs.next ())
				{
					bookCodeBill.setEditable (false);
					if (rs.getString ("midde_name") == null)
						mn = "";
					else
						mn = rs.getString ("midde_name");
					custNameBill.setText (rs.getString ("first_name")+" "+mn+" "+rs.getString ("last_name"));
					custNameBill.setForeground (Color.BLACK);
					custCodeBill.setText (rs.getString ("customer_code"));
					custCodeBill.setForeground (Color.BLACK);
					bookedRoomBill.setText (rs.getString ("room_no"));
					bookedRoomBill.setForeground (Color.BLACK);
					personsBill.setText (rs.getString ("no_of_persons"));
					personsBill.setForeground (Color.BLACK);
					SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
					java.sql.Date sqd = rs.getDate ("date_of_arrival");
					java.util.Date ud = (java.util.Date)sqd;
					String d = sdf.format (ud);
					arriveBill.setText (d);
					arriveBill.setForeground (Color.BLACK);
					roomRentBill.setText (rs.getString ("room_rent"));
					roomRentBill.setForeground (Color.BLACK);
					billingWarn.setText ("");
					calcGross.setEnabled (true);
				}
				else
					billingWarn.setText ("Wrong Booking Code!");
			}
			catch (Exception e)
			{
				e.printStackTrace ();
				//billingWarn.setText ("Error!!");
			}
		}
	}

	public static void calcGrossAction (ActionEvent ae)
	{
		try
		{
			double rentAmt = Double.parseDouble (roomRentBill.getText ());
			double serviceAmt = Double.parseDouble (serviceBill.getText ());
			grossBill.setText (Double.toString (rentAmt+serviceAmt));
			grossBill.setForeground (Color.BLACK);
			serviceBill.setEditable (false);
			grossAmt = Double.parseDouble (grossBill.getText ());
			billingWarn.setText ("");
			calcGross.setEnabled (false);
			calcNet.setEnabled (true);
		}
		catch (Exception e)
		{
			billingWarn.setText ("Invalid amounts!");
		}
	}

	public static void calcNetAction (ActionEvent ae)
	{
		try
		{
			double gstAmt = Double.parseDouble (gstBill.getText ());
			int discountPercentage = Integer.parseInt (discountBill.getText ());
			if (discountPercentage < 0 && discountPercentage > 100)
				billingWarn.setText ("Please enter a valid discount!");
			else
			{
				double discountAmt = (grossAmt+gstAmt)*discountPercentage/100;
				netBill.setText (Double.toString(grossAmt+gstAmt-discountAmt));
				netBill.setForeground (Color.BLACK);
				gstBill.setEditable (false);
				discountBill.setEditable (false);
				billingWarn.setText ("");
				netAmt = Double.parseDouble (netBill.getText ());
				calcNet.setEnabled (false);
				calcPending.setEnabled (true);
			}
		}
		catch (Exception e)
		{
			billingWarn.setText ("Invalild numbers!");
		}
	}

	public static void calcPendingAction (ActionEvent ae)
	{
		try
		{
			double paidAmt = Double.parseDouble (receivedBill.getText ());
			pendingBill.setText (Double.toString (netAmt - paidAmt));
			pendingBill.setForeground (Color.BLACK);
			receivedBill.setEditable (false);
			calcPending.setEnabled (false);
		}
		catch (Exception e)
		{
			billingWarn.setText ("Invalild numbers!");
		}
	}

	public static void billButtonAction (ActionEvent ae)
	{
		String billCodeStr = billCode.getText ();
		String bookingCodeStr = bookCodeBill.getText ();
		double roomRent = 0.0, serviceCharges = 0.0, grossAmount = 0.0, gstAmount = 0.0, discountAmount = 0.0, netAmount = 0.0;
		try
		{
			roomRent = Double.parseDouble (roomRentBill.getText ());
			serviceCharges = Double.parseDouble (serviceBill.getText ());
			grossAmount = Double.parseDouble (grossBill.getText ());
			gstAmount = Double.parseDouble (gstBill.getText ());
			discountAmount = Double.parseDouble (discountBill.getText ());
			netAmount = Double.parseDouble (netBill.getText ());
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		String billMode = modeBill.getText ();
		String cardNumberStr = cardNoBill.getText ();
		long cardNumber = 0;
		try
		{
			cardNumber = Long.parseLong (cardNumberStr);
			cardNumberStr = "nil";
		}
		catch (Exception e)
		{
			cardNumberStr = "";
		}
		double receivedAmount = Double.parseDouble (receivedBill.getText ());
		double pendingAmount = Double.parseDouble (pendingBill.getText ());
		String d = departBill.getText ();
		if(d.equals("Date of Departure (dd/mm/yyyy)"))
			d="";
		boolean dobStr=true;
		if(d.length()>10||d.length()<10)
			dobStr=false;
		else
		{
			String y=d.substring(6,10);
			int iy=Integer.parseInt(y);
			String m=d.substring(3,5);
			String dt=d.substring(0,2);
			int i=iy%4;
			if(i==0)
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30")))
					dobStr=false;
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					dobStr=false;
			}
			else
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30"))||(m.equals("02")&&dt.equals("29")))
					dobStr=false;
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					dobStr=false;
			}
		}
		if (billCodeStr.equals ("")||modeBill.equals ("")||cardNumberStr.equals (""))
			billingWarn.setText ("Incomplete Information!");
		else if (dobStr==false)
			billingWarn.setText ("Please enter a valid date!");
		else
		{
			try
			{
				PreparedStatement ps = conn.prepareStatement ("insert into Billing_Details values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString (1,billCodeStr);
				ps.setString (2,bookingCodeStr);
				ps.setDouble (3,roomRent);
				ps.setDouble (4,serviceCharges);
				ps.setDouble (5,grossAmount);
				ps.setDouble (6,gstAmount);
				ps.setDouble (7,discountAmount);
				ps.setDouble (8,netAmount);
				ps.setString (9,billMode);
				ps.setLong (10,cardNumber);
				ps.setDouble (11,receivedAmount);
				ps.setDouble (12,pendingAmount);
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.util.Date ud = sdf.parse (d);
				java.sql.Date sqd = new java.sql.Date (ud.getTime ());
				ps.setDate (13,sqd);
				int status = ps.executeUpdate ();
				if (status != 0)
					billingWarn.setText ("Submitted Successfully!");
				else
					billingWarn.setText ("Error!");
				ps = conn.prepareStatement ("update room_details set status = ? where room_no = ?");
				ps.setString (1,"Available");
				ps.setString (2,bookedRoomBill.getText ());
				status = ps.executeUpdate ();
				if (status != 0)
					billingWarn.setText ("Successful!");
			}
			catch (Exception e)
			{
				e.printStackTrace ();
				//billingWarn.setText ("Error!!");
			}
			try
		{
			PreparedStatement ps = conn.prepareStatement ("select room_no, status from Room_Details");
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				String roomNumber = rs.getString (1);
				String status= rs.getString (2);
				if (roomNumber.equals ("RDE001"))
				{
					if (status.equals ("Available"))
						lux01.setSelected (false);
					else
						lux01.setSelected (true);
				}
				else if (roomNumber.equals ("RDE002"))
				{
					if (status.equals ("Available"))
						lux02.setSelected (false);
					else
						lux02.setSelected (true);
				}
				else if (roomNumber.equals ("RDE003"))
				{
					if (status.equals ("Available"))
						lux03.setSelected (false);
					else
						lux03.setSelected (true);
				}
				else if (roomNumber.equals ("RDE004"))
				{
					if (status.equals ("Available"))
						lux04.setSelected (false);
					else
						lux04.setSelected (true);
				}
				else if (roomNumber.equals ("RDE005"))
				{
					if (status.equals ("Available"))
						lux05.setSelected (false);
					else
						lux05.setSelected (true);
				}
				else if (roomNumber.equals ("RDE006"))
				{
					if (status.equals ("Available"))
						lux06.setSelected (false);
					else
						lux06.setSelected (true);
				}
				else if (roomNumber.equals ("RDE007"))
				{
					if (status.equals ("Available"))
						lux07.setSelected (false);
					else
						lux07.setSelected (true);
				}
				else if (roomNumber.equals ("RDE008"))
				{
					if (status.equals ("Available"))
						lux08.setSelected (false);
					else
						lux08.setSelected (true);
				}
				else if (roomNumber.equals ("RDE101"))
				{
					if (status.equals ("Available"))
						lux11.setSelected (false);
					else
						lux11.setSelected (true);
				}
				else if (roomNumber.equals ("RDE102"))
				{
					if (status.equals ("Available"))
						lux12.setSelected (false);
					else
						lux12.setSelected (true);
				}
				else if (roomNumber.equals ("RDE103"))
				{
					if (status.equals ("Available"))
						lux13.setSelected (false);
					else
						lux13.setSelected (true);
				}
				else if (roomNumber.equals ("RDE104"))
				{
					if (status.equals ("Available"))
						lux14.setSelected (false);
					else
						lux14.setSelected (true);
				}
				else if (roomNumber.equals ("RDE105"))
				{
					if (status.equals ("Available"))
						lux15.setSelected (false);
					else
						lux15.setSelected (true);
				}
				else if (roomNumber.equals ("RDE106"))
				{
					if (status.equals ("Available"))
						lux16.setSelected (false);
					else
						lux16.setSelected (true);
				}
				else if (roomNumber.equals ("RDE107"))
				{
					if (status.equals ("Available"))
						lux17.setSelected (false);
					else
						lux17.setSelected (true);
				}
				else if (roomNumber.equals ("RDE108"))
				{
					if (status.equals ("Available"))
						lux18.setSelected (false);
					else
						lux18.setSelected (true);
				}
				else if (roomNumber.equals ("RDE201"))
				{
					if (status.equals ("Available"))
						lux21.setSelected (false);
					else
						lux21.setSelected (true);
				}
				else if (roomNumber.equals ("RDE202"))
				{
					if (status.equals ("Available"))
						lux22.setSelected (false);
					else
						lux22.setSelected (true);
				}
				else if (roomNumber.equals ("RDE203"))
				{
					if (status.equals ("Available"))
						lux23.setSelected (false);
					else
						lux23.setSelected (true);
				}
				else if (roomNumber.equals ("RDE204"))
				{
					if (status.equals ("Available"))
						lux24.setSelected (false);
					else
						lux24.setSelected (true);
				}
				else if (roomNumber.equals ("RDE205"))
				{
					if (status.equals ("Available"))
						lux25.setSelected (false);
					else
						lux25.setSelected (true);
				}
				else if (roomNumber.equals ("RDE206"))
				{
					if (status.equals ("Available"))
						lux26.setSelected (false);
					else
						lux26.setSelected (true);
				}
				else if (roomNumber.equals ("RDE207"))
				{
					if (status.equals ("Available"))
						lux27.setSelected (false);
					else
						lux27.setSelected (true);
				}
				else if (roomNumber.equals ("RDE208"))
				{
					if (status.equals ("Available"))
						lux28.setSelected (false);
					else
						lux28.setSelected (true);
				}
				else if (roomNumber.equals ("RDE301"))
				{
					if (status.equals ("Available"))
						lux31.setSelected (false);
					else
						lux31.setSelected (true);
				}
				else if (roomNumber.equals ("RDE302"))
				{
					if (status.equals ("Available"))
						lux32.setSelected (false);
					else
						lux32.setSelected (true);
				}
				else if (roomNumber.equals ("RDE303"))
				{
					if (status.equals ("Available"))
						lux33.setSelected (false);
					else
						lux33.setSelected (true);
				}
				else if (roomNumber.equals ("RDE304"))
				{
					if (status.equals ("Available"))
						lux34.setSelected (false);
					else
						lux34.setSelected (true);
				}
				else if (roomNumber.equals ("RDE305"))
				{
					if (status.equals ("Available"))
						lux35.setSelected (false);
					else
						lux35.setSelected (true);
				}
				else if (roomNumber.equals ("RDE306"))
				{
					if (status.equals ("Available"))
						lux36.setSelected (false);
					else
						lux36.setSelected (true);
				}
				else if (roomNumber.equals ("RDE307"))
				{
					if (status.equals ("Available"))
						lux37.setSelected (false);
					else
						lux37.setSelected (true);
				}
				else if (roomNumber.equals ("RDE308"))
				{
					if (status.equals ("Available"))
						lux38.setSelected (false);
					else
						lux38.setSelected (true);
				}
				else if (roomNumber.equals ("RDE401"))
				{
					if (status.equals ("Available"))
						lux41.setSelected (false);
					else
						lux41.setSelected (true);
				}
				else if (roomNumber.equals ("RDE402"))
				{
					if (status.equals ("Available"))
						lux42.setSelected (false);
					else
						lux42.setSelected (true);
				}
				else if (roomNumber.equals ("RDE403"))
				{
					if (status.equals ("Available"))
						lux43.setSelected (false);
					else
						lux43.setSelected (true);
				}
				else if (roomNumber.equals ("RDE404"))
				{
					if (status.equals ("Available"))
						lux44.setSelected (false);
					else
						lux44.setSelected (true);
				}
				else if (roomNumber.equals ("RDE405"))
				{
					if (status.equals ("Available"))
						lux45.setSelected (false);
					else
						lux45.setSelected (true);
				}
				else if (roomNumber.equals ("RDE406"))
				{
					if (status.equals ("Available"))
						lux46.setSelected (false);
					else
						lux46.setSelected (true);
				}
				else if (roomNumber.equals ("RDE407"))
				{
					if (status.equals ("Available"))
						lux47.setSelected (false);
					else
						lux47.setSelected (true);
				}
				else if (roomNumber.equals ("RDE408"))
				{
					if (status.equals ("Available"))
						lux48.setSelected (false);
					else
						lux48.setSelected (true);
				}
				else if (roomNumber.equals ("RDE501"))
				{
					if (status.equals ("Available"))
						lux51.setSelected (false);
					else
						lux51.setSelected (true);
				}
				else if (roomNumber.equals ("RDE502"))
				{
					if (status.equals ("Available"))
						lux52.setSelected (false);
					else
						lux52.setSelected (true);
				}
				else if (roomNumber.equals ("RDE503"))
				{
					if (status.equals ("Available"))
						lux53.setSelected (false);
					else
						lux53.setSelected (true);
				}
				else if (roomNumber.equals ("RDE504"))
				{
					if (status.equals ("Available"))
						lux54.setSelected (false);
					else
						lux54.setSelected (true);
				}
				else if (roomNumber.equals ("RDE505"))
				{
					if (status.equals ("Available"))
						lux55.setSelected (false);
					else
						lux55.setSelected (true);
				}
				else if (roomNumber.equals ("RDE506"))
				{
					if (status.equals ("Available"))
						lux56.setSelected (false);
					else
						lux56.setSelected (true);
				}
				else if (roomNumber.equals ("RDE507"))
				{
					if (status.equals ("Available"))
						lux57.setSelected (false);
					else
						lux57.setSelected (true);
				}
				else if (roomNumber.equals ("RDE508"))
				{
					if (status.equals ("Available"))
						lux58.setSelected (false);
					else
						lux58.setSelected (true);
				}
				else if (roomNumber.equals ("RDE601"))
				{
					if (status.equals ("Available"))
						lux61.setSelected (false);
					else
						lux61.setSelected (true);
				}
				else if (roomNumber.equals ("RDE602"))
				{
					if (status.equals ("Available"))
						lux62.setSelected (false);
					else
						lux62.setSelected (true);
				}
				else if (roomNumber.equals ("RDE603"))
				{
					if (status.equals ("Available"))
						lux63.setSelected (false);
					else
						lux63.setSelected (true);
				}
				else if (roomNumber.equals ("RDE604"))
				{
					if (status.equals ("Available"))
						lux64.setSelected (false);
					else
						lux64.setSelected (true);
				}
				else if (roomNumber.equals ("RDE605"))
				{
					if (status.equals ("Available"))
						lux65.setSelected (false);
					else
						lux65.setSelected (true);
				}
				else if (roomNumber.equals ("RDE606"))
				{
					if (status.equals ("Available"))
						lux66.setSelected (false);
					else
						lux66.setSelected (true);
				}
				else if (roomNumber.equals ("RDE607"))
				{
					if (status.equals ("Available"))
						lux67.setSelected (false);
					else
						lux67.setSelected (true);
				}
				else if (roomNumber.equals ("RDE608"))
				{
					if (status.equals ("Available"))
						lux68.setSelected (false);
					else
						lux68.setSelected (true);
				}
				else if (roomNumber.equals ("RDE701"))
				{
					if (status.equals ("Available"))
						lux71.setSelected (false);
					else
						lux71.setSelected (true);
				}
				else if (roomNumber.equals ("RDE702"))
				{
					if (status.equals ("Available"))
						lux72.setSelected (false);
					else
						lux72.setSelected (true);
				}
				else if (roomNumber.equals ("RDE703"))
				{
					if (status.equals ("Available"))
						lux73.setSelected (false);
					else
						lux73.setSelected (true);
				}
				else if (roomNumber.equals ("RDE704"))
				{
					if (status.equals ("Available"))
						lux74.setSelected (false);
					else
						lux74.setSelected (true);
				}
				else if (roomNumber.equals ("RDE705"))
				{
					if (status.equals ("Available"))
						lux75.setSelected (false);
					else
						lux75.setSelected (true);
				}
				else if (roomNumber.equals ("RDE706"))
				{
					if (status.equals ("Available"))
						lux76.setSelected (false);
					else
						lux76.setSelected (true);
				}
				else if (roomNumber.equals ("RDE707"))
				{
					if (status.equals ("Available"))
						lux77.setSelected (false);
					else
						lux77.setSelected (true);
				}
				else if (roomNumber.equals ("RDE708"))
				{
					if (status.equals ("Available"))
						lux78.setSelected (false);
					else
						lux78.setSelected (true);
				}
				else if (roomNumber.equals ("RDE801"))
				{
					if (status.equals ("Available"))
						lux81.setSelected (false);
					else
						lux81.setSelected (true);
				}
				else if (roomNumber.equals ("RDE802"))
				{
					if (status.equals ("Available"))
						lux82.setSelected (false);
					else
						lux82.setSelected (true);
				}
				else if (roomNumber.equals ("RDE803"))
				{
					if (status.equals ("Available"))
						lux83.setSelected (false);
					else
						lux83.setSelected (true);
				}
				else if (roomNumber.equals ("RDE804"))
				{
					if (status.equals ("Available"))
						lux84.setSelected (false);
					else
						lux84.setSelected (true);
				}
				else if (roomNumber.equals ("RDE805"))
				{
					if (status.equals ("Available"))
						lux85.setSelected (false);
					else
						lux85.setSelected (true);
				}
				else if (roomNumber.equals ("RDE806"))
				{
					if (status.equals ("Available"))
						lux86.setSelected (false);
					else
						lux86.setSelected (true);
				}
				else if (roomNumber.equals ("RDE807"))
				{
					if (status.equals ("Available"))
						lux87.setSelected (false);
					else
						lux87.setSelected (true);
				}
				else if (roomNumber.equals ("RDE808"))
				{
					if (status.equals ("Available"))
						lux88.setSelected (false);
					else
						lux88.setSelected (true);
				}
				else if (roomNumber.equals ("RDE901"))
				{
					if (status.equals ("Available"))
						lux91.setSelected (false);
					else
						lux91.setSelected (true);
				}
				else if (roomNumber.equals ("RDE902"))
				{
					if (status.equals ("Available"))
						lux92.setSelected (false);
					else
						lux92.setSelected (true);
				}
				else if (roomNumber.equals ("RDE903"))
				{
					if (status.equals ("Available"))
						lux93.setSelected (false);
					else
						lux93.setSelected (true);
				}
				else if (roomNumber.equals ("RDE904"))
				{
					if (status.equals ("Available"))
						lux94.setSelected (false);
					else
						lux94.setSelected (true);
				}
				else if (roomNumber.equals ("RDE905"))
				{
					if (status.equals ("Available"))
						lux95.setSelected (false);
					else
						lux95.setSelected (true);
				}
				else if (roomNumber.equals ("RDE906"))
				{
					if (status.equals ("Available"))
						lux96.setSelected (false);
					else
						lux96.setSelected (true);
				}
				else if (roomNumber.equals ("RDE907"))
				{
					if (status.equals ("Available"))
						lux97.setSelected (false);
					else
						lux97.setSelected (true);
				}
				else if (roomNumber.equals ("RDE908"))
				{
					if (status.equals ("Available"))
						lux98.setSelected (false);
					else
						lux98.setSelected (true);
				}
				else if (roomNumber.equals ("RSD001"))
				{
					if (status.equals ("Available"))
						suLux01.setSelected (false);
					else
						suLux01.setSelected (true);
				}
				else if (roomNumber.equals ("RSD002"))
				{
					if (status.equals ("Available"))
						suLux02.setSelected (false);
					else
						suLux02.setSelected (true);
				}
				else if (roomNumber.equals ("RSD003"))
				{
					if (status.equals ("Available"))
						suLux03.setSelected (false);
					else
						suLux03.setSelected (true);
				}
				else if (roomNumber.equals ("RSD004"))
				{
					if (status.equals ("Available"))
						suLux04.setSelected (false);
					else
						suLux04.setSelected (true);
				}
				else if (roomNumber.equals ("RSD101"))
				{
					if (status.equals ("Available"))
						suLux11.setSelected (false);
					else
						suLux11.setSelected (true);
				}
				else if (roomNumber.equals ("RSD102"))
				{
					if (status.equals ("Available"))
						suLux12.setSelected (false);
					else
						suLux12.setSelected (true);
				}
				else if (roomNumber.equals ("RSD103"))
				{
					if (status.equals ("Available"))
						suLux13.setSelected (false);
					else
						suLux13.setSelected (true);
				}
				else if (roomNumber.equals ("RSD104"))
				{
					if (status.equals ("Available"))
						suLux14.setSelected (false);
					else
						suLux14.setSelected (true);
				}
				else if (roomNumber.equals ("RSD201"))
				{
					if (status.equals ("Available"))
						suLux21.setSelected (false);
					else
						suLux21.setSelected (true);
				}
				else if (roomNumber.equals ("RSD202"))
				{
					if (status.equals ("Available"))
						suLux22.setSelected (false);
					else
						suLux22.setSelected (true);
				}
				else if (roomNumber.equals ("RSD203"))
				{
					if (status.equals ("Available"))
						suLux23.setSelected (false);
					else
						suLux23.setSelected (true);
				}
				else if (roomNumber.equals ("RSD204"))
				{
					if (status.equals ("Available"))
						suLux24.setSelected (false);
					else
						suLux24.setSelected (true);
				}
				else if (roomNumber.equals ("RSD301"))
				{
					if (status.equals ("Available"))
						suLux31.setSelected (false);
					else
						suLux31.setSelected (true);
				}
				else if (roomNumber.equals ("RSD302"))
				{
					if (status.equals ("Available"))
						suLux32.setSelected (false);
					else
						suLux32.setSelected (true);
				}
				else if (roomNumber.equals ("RSD303"))
				{
					if (status.equals ("Available"))
						suLux33.setSelected (false);
					else
						suLux33.setSelected (true);
				}
				else if (roomNumber.equals ("RSD304"))
				{
					if (status.equals ("Available"))
						suLux34.setSelected (false);
					else
						suLux34.setSelected (true);
				}
				else if (roomNumber.equals ("RSD401"))
				{
					if (status.equals ("Available"))
						suLux41.setSelected (false);
					else
						suLux41.setSelected (true);
				}
				else if (roomNumber.equals ("RSD402"))
				{
					if (status.equals ("Available"))
						suLux42.setSelected (false);
					else
						suLux42.setSelected (true);
				}
				else if (roomNumber.equals ("RSD403"))
				{
					if (status.equals ("Available"))
						suLux43.setSelected (false);
					else
						suLux43.setSelected (true);
				}
				else if (roomNumber.equals ("RSD404"))
				{
					if (status.equals ("Available"))
						suLux44.setSelected (false);
					else
						suLux44.setSelected (true);
				}
				else if (roomNumber.equals ("RSD501"))
				{
					if (status.equals ("Available"))
						suLux51.setSelected (false);
					else
						suLux51.setSelected (true);
				}
				else if (roomNumber.equals ("RSD502"))
				{
					if (status.equals ("Available"))
						suLux52.setSelected (false);
					else
						suLux52.setSelected (true);
				}
				else if (roomNumber.equals ("RSD503"))
				{
					if (status.equals ("Available"))
						suLux53.setSelected (false);
					else
						suLux53.setSelected (true);
				}
				else if (roomNumber.equals ("RSD504"))
				{
					if (status.equals ("Available"))
						suLux54.setSelected (false);
					else
						suLux54.setSelected (true);
				}
				else if (roomNumber.equals ("RSD601"))
				{
					if (status.equals ("Available"))
						suLux61.setSelected (false);
					else
						suLux61.setSelected (true);
				}
				else if (roomNumber.equals ("RSD602"))
				{
					if (status.equals ("Available"))
						suLux62.setSelected (false);
					else
						suLux62.setSelected (true);
				}
				else if (roomNumber.equals ("RSD603"))
				{
					if (status.equals ("Available"))
						suLux63.setSelected (false);
					else
						suLux63.setSelected (true);
				}
				else if (roomNumber.equals ("RSD604"))
				{
					if (status.equals ("Available"))
						suLux64.setSelected (false);
					else
						suLux64.setSelected (true);
				}
				else if (roomNumber.equals ("RSD701"))
				{
					if (status.equals ("Available"))
						suLux71.setSelected (false);
					else
						suLux71.setSelected (true);
				}
				else if (roomNumber.equals ("RSD702"))
				{
					if (status.equals ("Available"))
						suLux72.setSelected (false);
					else
						suLux72.setSelected (true);
				}
				else if (roomNumber.equals ("RSD703"))
				{
					if (status.equals ("Available"))
						suLux73.setSelected (false);
					else
						suLux73.setSelected (true);
				}
				else if (roomNumber.equals ("RSD704"))
				{
					if (status.equals ("Available"))
						suLux74.setSelected (false);
					else
						suLux74.setSelected (true);
				}
				else if (roomNumber.equals ("RSD801"))
				{
					if (status.equals ("Available"))
						suLux81.setSelected (false);
					else
						suLux81.setSelected (true);
				}
				else if (roomNumber.equals ("RSD802"))
				{
					if (status.equals ("Available"))
						suLux82.setSelected (false);
					else
						suLux82.setSelected (true);
				}
				else if (roomNumber.equals ("RSD803"))
				{
					if (status.equals ("Available"))
						suLux83.setSelected (false);
					else
						suLux83.setSelected (true);
				}
				else if (roomNumber.equals ("RSD804"))
				{
					if (status.equals ("Available"))
						suLux84.setSelected (false);
					else
						suLux84.setSelected (true);
				}
				else if (roomNumber.equals ("RSD901"))
				{
					if (status.equals ("Available"))
						suLux91.setSelected (false);
					else
						suLux91.setSelected (true);
				}
				else if (roomNumber.equals ("RSD902"))
				{
					if (status.equals ("Available"))
						suLux92.setSelected (false);
					else
						suLux92.setSelected (true);
				}
				else if (roomNumber.equals ("RSD903"))
				{
					if (status.equals ("Available"))
						suLux93.setSelected (false);
					else
						suLux93.setSelected (true);
				}
				else if (roomNumber.equals ("RSD904"))
				{
					if (status.equals ("Available"))
						suLux94.setSelected (false);
					else
						suLux94.setSelected (true);
				}
				else if (roomNumber.equals ("SPM001"))
				{
					if (status.equals ("Available"))
						pm01.setSelected (false);
					else
						pm01.setSelected (true);
				}
				else if (roomNumber.equals ("SPM101"))
				{
					if (status.equals ("Available"))
						pm11.setSelected (false);
					else
						pm11.setSelected (true);
				}
				else if (roomNumber.equals ("SPM201"))
				{
					if (status.equals ("Available"))
						pm21.setSelected (false);
					else
						pm21.setSelected (true);
				}
				else if (roomNumber.equals ("SPM301"))
				{
					if (status.equals ("Available"))
						pm31.setSelected (false);
					else
						pm31.setSelected (true);
				}
				else if (roomNumber.equals ("SPM401"))
				{
					if (status.equals ("Available"))
						pm41.setSelected (false);
					else
						pm41.setSelected (true);
				}
				else if (roomNumber.equals ("SPM501"))
				{
					if (status.equals ("Available"))
						pm51.setSelected (false);
					else
						pm51.setSelected (true);
				}
				else if (roomNumber.equals ("SPM601"))
				{
					if (status.equals ("Available"))
						pm61.setSelected (false);
					else
						pm61.setSelected (true);
				}
				else if (roomNumber.equals ("SPM701"))
				{
					if (status.equals ("Available"))
						pm71.setSelected (false);
					else
						pm71.setSelected (true);
				}
				else if (roomNumber.equals ("SPM801"))
				{
					if (status.equals ("Available"))
						pm81.setSelected (false);
					else
						pm81.setSelected (true);
				}
				else if (roomNumber.equals ("SPM901"))
				{
					if (status.equals ("Available"))
						pm91.setSelected (false);
					else
						pm91.setSelected (true);
				}
				else if (roomNumber.equals ("SPD001"))
				{
					if (status.equals ("Available"))
						pd01.setSelected (false);
					else
						pd01.setSelected (true);
				}
				else if (roomNumber.equals ("SPD101"))
				{
					if (status.equals ("Available"))
						pd11.setSelected (false);
					else
						pd11.setSelected (true);
				}
				else if (roomNumber.equals ("SPD201"))
				{
					if (status.equals ("Available"))
						pd21.setSelected (false);
					else
						pd21.setSelected (true);
				}
				else if (roomNumber.equals ("SPD301"))
				{
					if (status.equals ("Available"))
						pd31.setSelected (false);
					else
						pd31.setSelected (true);
				}
				else if (roomNumber.equals ("SPD401"))
				{
					if (status.equals ("Available"))
						pd41.setSelected (false);
					else
						pd41.setSelected (true);
				}
				else if (roomNumber.equals ("SPD501"))
				{
					if (status.equals ("Available"))
						pd51.setSelected (false);
					else
						pd51.setSelected (true);
				}
				else if (roomNumber.equals ("SPD601"))
				{
					if (status.equals ("Available"))
						pd61.setSelected (false);
					else
						pd61.setSelected (true);
				}
				else if (roomNumber.equals ("SPD701"))
				{
					if (status.equals ("Available"))
						pd71.setSelected (false);
					else
						pd71.setSelected (true);
				}
				else if (roomNumber.equals ("SPD801"))
				{
					if (status.equals ("Available"))
						pd81.setSelected (false);
					else
						pd81.setSelected (true);
				}
				else if (roomNumber.equals ("SPD901"))
				{
					if (status.equals ("Available"))
						pd91.setSelected (false);
					else
						pd91.setSelected (true);
				}
				else if (roomNumber.equals ("SEX001"))
				{
					if (status.equals ("Available"))
						ex01.setSelected (false);
					else
						ex01.setSelected (true);
				}
				else if (roomNumber.equals ("SEX101"))
				{
					if (status.equals ("Available"))
						ex11.setSelected (false);
					else
						ex11.setSelected (true);
				}
				else if (roomNumber.equals ("SEX201"))
				{
					if (status.equals ("Available"))
						ex21.setSelected (false);
					else
						ex21.setSelected (true);
				}
				else if (roomNumber.equals ("SEX301"))
				{
					if (status.equals ("Available"))
						ex31.setSelected (false);
					else
						ex31.setSelected (true);
				}
				else if (roomNumber.equals ("SEX401"))
				{
					if (status.equals ("Available"))
						ex41.setSelected (false);
					else
						ex41.setSelected (true);
				}
				else if (roomNumber.equals ("SEX501"))
				{
					if (status.equals ("Available"))
						ex51.setSelected (false);
					else
						ex51.setSelected (true);
				}
				else if (roomNumber.equals ("SEX601"))
				{
					if (status.equals ("Available"))
						ex61.setSelected (false);
					else
						ex61.setSelected (true);
				}
				else if (roomNumber.equals ("SEX701"))
				{
					if (status.equals ("Available"))
						ex71.setSelected (false);
					else
						ex71.setSelected (true);
				}
				else if (roomNumber.equals ("SEX801"))
				{
					if (status.equals ("Available"))
						ex81.setSelected (false);
					else
						ex81.setSelected (true);
				}
				else if (roomNumber.equals ("SEX901"))
				{
					if (status.equals ("Available"))
						ex91.setSelected (false);
					else
						ex91.setSelected (true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		container.revalidate();
		container.repaint();
		}
	}

	public static void homeBillAction (ActionEvent ae)
	{
		bookCodeBill.setText ("Enter Booking Code");
		bookCodeBill.setForeground (Color.GRAY);
		bookCodeBill.setEditable (true);
		custCodeBill.setText ("Customer Code");
		custCodeBill.setForeground (Color.GRAY);
		custNameBill.setText ("Customer Name");
		custNameBill.setForeground (Color.GRAY);
		bookedRoomBill.setText ("Room Number");
		bookedRoomBill.setForeground (Color.GRAY);
		personsBill.setText ("Number of Persons");
		personsBill.setForeground (Color.GRAY);
		arriveBill.setText ("Date of Arrival");
		arriveBill.setForeground (Color.GRAY);
		billCode.setText ("Billing Code");
		billCode.setForeground (Color.GRAY);
		billCode.setEditable (true);
		departBill.setText ("Date of Departure (dd/mm/yyyy)");
		departBill.setForeground (Color.GRAY);
		departBill.setEditable (true);
		roomRentBill.setText ("Room Rent");
		roomRentBill.setForeground (Color.GRAY);
		serviceBill.setText ("Service Charges");
		serviceBill.setForeground (Color.GRAY);
		serviceBill.setEditable (true);
		grossBill.setText ("Gross Amount");
		grossBill.setForeground (Color.GRAY);
		gstBill.setText ("GST");
		gstBill.setForeground (Color.GRAY);
		gstBill.setEditable (true);
		discountBill.setText ("Discount (%)");
		discountBill.setForeground (Color.GRAY);
		discountBill.setEditable (true);
		netBill.setText ("Net Amount");
		netBill.setForeground (Color.GRAY);
		modeBill.setText ("Payment Mode");
		modeBill.setForeground (Color.GRAY);
		cardNoBill.setText ("Card Number");
		cardNoBill.setForeground (Color.GRAY);
		receivedBill.setText ("Received Amount");
		receivedBill.setForeground (Color.GRAY);
		receivedBill.setEditable (true);
		pendingBill.setText ("Pending Amount");
		pendingBill.setForeground (Color.GRAY);
		container.remove (greenBackLabel);
		container.add (redBackLabel);
		home.setEnabled (false);
		cardLayout.show (mainPanel, "Dept");
		container.revalidate();
		container.repaint();
	}

	public static void newBillAction (ActionEvent ae)
	{
		bookCodeBill.setText ("Enter Booking Code");
		bookCodeBill.setForeground (Color.GRAY);
		bookCodeBill.setEditable (true);
		custCodeBill.setText ("Customer Code");
		custCodeBill.setForeground (Color.GRAY);
		custNameBill.setText ("Customer Name");
		custNameBill.setForeground (Color.GRAY);
		bookedRoomBill.setText ("Room Number");
		bookedRoomBill.setForeground (Color.GRAY);
		personsBill.setText ("Number of Persons");
		personsBill.setForeground (Color.GRAY);
		arriveBill.setText ("Date of Arrival");
		arriveBill.setForeground (Color.GRAY);
		billCode.setText ("Billing Code");
		billCode.setForeground (Color.GRAY);
		billCode.setEditable (true);
		departBill.setText ("Date of Departure (dd/mm/yyyy)");
		departBill.setForeground (Color.GRAY);
		departBill.setEditable (true);
		roomRentBill.setText ("Room Rent");
		roomRentBill.setForeground (Color.GRAY);
		serviceBill.setText ("Service Charges");
		serviceBill.setForeground (Color.GRAY);
		serviceBill.setEditable (true);
		grossBill.setText ("Gross Amount");
		grossBill.setForeground (Color.GRAY);
		gstBill.setText ("GST");
		gstBill.setForeground (Color.GRAY);
		gstBill.setEditable (true);
		discountBill.setText ("Discount (%)");
		discountBill.setForeground (Color.GRAY);
		discountBill.setEditable (true);
		netBill.setText ("Net Amount");
		netBill.setForeground (Color.GRAY);
		modeBill.setText ("Payment Mode");
		modeBill.setForeground (Color.GRAY);
		cardNoBill.setText ("Card Number");
		cardNoBill.setForeground (Color.GRAY);
		receivedBill.setText ("Received Amount");
		receivedBill.setForeground (Color.GRAY);
		receivedBill.setEditable (true);
		pendingBill.setText ("Pending Amount");
		pendingBill.setForeground (Color.GRAY);
	}
}

public class Hotel 
{
	public static void main (String[] args) 
	{
		MainHotelFrame mainFrame = new MainHotelFrame ();
		mainFrame.setVisible (true);
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds (-5,0,1390,735);
	}
}