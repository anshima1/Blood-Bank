import java.util.*;
import java.sql.*;


public class Admin { //code starts
	
	

	public static void main(String[] args) throws Exception {
		String dburl="jdbc:mysql://localhost:3306/blood_bank_management_system";
		String uname="root";
		String password="swapan";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(dburl,uname,password);
		Scanner input=new Scanner(System.in);
		System.out.println("Welcome to BLOOD BANK MANAGEMENT SYSTEM\n");
		String ques1,ques2;
		String field;
		int enter;
		do{                       //first do
			System.out.println("Are you faculty of the blood bank or a visitor? Press \n1. For Faculty \n2. For visitor");
			ques1=input.next();
			switch(ques1){
			case "1":
				do{
					System.out.println("Enter whether you are an Administrator or a Receptionist?");
					ques2=input.next();
					switch(ques2){
					case "Administrator":
						System.out.println("Which dept do you work in? Internal or External?");
						ques2=input.next();
						switch(ques2){
						case "Internal":
							System.out.println("Choose one of the following options (Enter Choice No.): "
									+ "\n1.Retrieve Blood bank Data"
									+ "\n2. Retrieve Blood Inventory details"
									+ "\n3.Manage Patient Requests"
									+ "\n4.Manage Donor Applications");
							ques2=input.next();
							String query="";
							switch(ques2){
							case "1":
								query="select * from Blood_bank, Administrator where blood_bank.blood_bank_id=Administrator.bank_id";
								Statement st = con.createStatement();
								ResultSet rs = st.executeQuery(query);
								ResultSetMetaData rsmd=rs.getMetaData();
								int c=rsmd.getColumnCount();
								int i=c;
								while(c!=0){
									c--;
									System.out.format("%16s",rsmd.getColumnName(i-c));  
								}
								System.out.println();
								while(rs.next()){
									c=i;
									while(c!=0){
										c--;
										System.out.format("%16s",rs.getString(i-c));
									}
									System.out.println();
								}
								break;
							case "2":
								System.out.println("Enter whether you want to see "
										+"\n 'All' blood types"
										+"\n 'One' Blood Type"
										+"\n 'Misc' for One or more blood types");
								ques2=input.next();
								switch(ques2){
								case "All":
									query="select * from Blood_bank bb, Administrator a, Blood b where bb.blood_bank_id=a.bank_id and bb.blood_bank_id=blood.bb_id ";
									st = con.createStatement();
									rs = st.executeQuery(query);
									rsmd=rs.getMetaData();
									c=rsmd.getColumnCount();
									i=c;
									while(c!=0){
										c--;
										System.out.format("%16s",rsmd.getColumnName(i-c));  
									}
									System.out.println();
									while(rs.next()){
										c=i;
										while(c!=0){
											c--;
											System.out.format("%16s",rs.getString(i-c));
										}
										System.out.println();
									}
									break;
								case "One":
									System.out.println("Enter Blood type");
									String btype;
									btype=input.next();
									query="select * from blood_bank bb, Administrator a, Blood b where bb.Blood_bank-id=a.bank_id and bb.Blood_bank_id=b.bb_id and Blood_type=?";
									do{
										System.out.println("Enter Choice No. \n1. All Details \n2.Quantity \n3.Platelet Count\n4.Expiry Date");
										ques2=input.next();
										switch(ques2){
										case "1":
										query="select * from blood where blood_type=?";
										break;
										case "2":
											query="select blood_units from blood where blood_type=?";           ////////check
											System.out.println(btype + "has units ");
											break;
										case "3":
											query="select Platelet_count from blood where blood_type=?";
											System.out.println(btype + "has platelet count");
											break;
										case "4":
											query="select Expiry Date from blood where lood_type=?";
											break;
										}
										PreparedStatement st1 = con.prepareStatement(query);
										st1.setString(1,btype);
										ResultSet rs1 = st1.executeQuery();
										ResultSetMetaData rsmd1=rs1.getMetaData();
										int c1=rsmd1.getColumnCount();
										int i1=c1;
										while(c1!=0){
											c1--;
											System.out.format("%16s",rsmd1.getColumnName(i1-c1));  
										}
										System.out.println();
										while(rs1.next()){
											c1=i1;
											while(c1!=0){
												c1--;
												System.out.format("%16s",rs1.getString(i1-c1));
											}
											System.out.println();
										}
										System.out.println("\nDo you want to continue viewing information for blood type " + btype + "?(Yes/No)");
										ques2=input.next();
										}while(ques2.equals("Yes"));
										break;
								case "Misc":
									System.out.println("Enter your query in MYSQL");
									String temp=input.nextLine();
									query=input.nextLine();
									Statement st2 = con.createStatement();
									ResultSet rs2 = st2.executeQuery(query);
									ResultSetMetaData rsmd2=rs2.getMetaData();
									int c2=rsmd2.getColumnCount();
									int i2=c2;
									while(c2!=0){
										c2--;
										System.out.format("%16s",rsmd2.getColumnName(i2-c2));  
									}
									System.out.println();
									while(rs2.next()){
										c2=i2;
										while(c2!=0){
											c2--;
											System.out.format("%16s",rs2.getString(i2-c2));
										}
										System.out.println();
									}
									break;
								}
							System.out.println("\nDo you want to continue viewing Blood Type information?(Yes/No)");
							field=input.next();
						
						}		
		/////////////////////////////// 
								
							case "3":
								System.out.println("Do you want to see "
										+ "\n1.'All' Patient Records? "
										+ "\n2. Approve Patient Request "
										+ "\n3. Misc");
								ques2=input.next();
								switch(ques2){
								do{
								case "1":
									query="select * from Recipient";
									Statement st = con.createStatement();
									ResultSet rs = st.executeQuery(query);
									ResultSetMetaData rsmd=rs.getMetaData();
									int c=rsmd.getColumnCount();
									int i=c;
									while(c!=0){
										c--;
										System.out.format("%16s",rsmd.getColumnName(i-c));  
									}
									System.out.println();
									while(rs.next()){
										c=i;
										while(c!=0){
											c--;
											System.out.format("%16s",rs.getString(i-c));
										}
										System.out.println();
									}
									break;
								case "2":
									System.out.println("Enter the id of the patient you want to update requests of");
									int id;
									enter=input.nextInt();
									query="update table Recipient set approved=TRUE where recipient_id=?";
									String query2="update table blood set blood_units=blood_units-?";
									/////////////////dooooooo
									PreparedStatement st1 = con.prepareStatement(query);
									st1.setInt(1,id);
									ResultSet rs1 = st1.executeQuery();
									ResultSetMetaData rsmd1=rs1.getMetaData();
									int c1=rsmd1.getColumnCount();
									int i1=c1;
									while(c1!=0){
										c1--;
										System.out.format("%16s",rsmd1.getColumnName(i1-c1));  
									}
									System.out.println();
									while(rs1.next()){
										c1=i1;
										while(c1!=0){
											c1--;
											System.out.format("%16s",rs1.getString(i1-c1));
										}
										System.out.println();
									}
									break;
									
								case "3":
									System.out.println("Enter your query in MYSQL");
									String temp=input.nextLine();
									query=input.nextLine();
									Statement st2 = con.createStatement();
									ResultSet rs2 = st2.executeQuery(query);
									ResultSetMetaData rsmd2=rs2.getMetaData();
									int c2=rsmd2.getColumnCount();
									int i2=c2;
									while(c2!=0){
										c2--;
										System.out.format("%16s",rsmd2.getColumnName(i2-c2));  
									}
									System.out.println();

									while(rs2.next()){
										c2=i2;
										while(c2!=0){
											c2--;
											System.out.format("%16s",rs2.getString(i2-c2));
										}
										System.out.println();
									}
									break;
								
				//some case
						case "External":
							System.out.println("Choose one of the following options (Enter Choice No.): "
									+ "\n1.View Hospital list"
									+ "\n2.Manage hospitals Request"
									+ "\n3.Manage Blood Donation drive");
							ques2=input.next();
							
							switch(ques2){
							case "1":
								
								query="select * from Hospital";
								st = con.createStatement();
								rs = st.executeQuery(query);
								rsmd=rs.getMetaData();
								c=rsmd.getColumnCount();
								i=c;
								while(c!=0){
									c--;
									System.out.format("%16s",rsmd.getColumnName(i-c));  
								}
								System.out.println();
								while(rs.next()){
									c=i;
									while(c!=0){
										c--;
										System.out.format("%16s",rs.getString(i-c));
									}
									System.out.println();
								}
								break;
							case "2":
								System.out.println("Enter ID of the hospital you want to approve the request of");
								enter=input.nextInt();
									String query2;
									query="update Hospital set status='1' where hospital_id=?";
									query2="update Blood set units= blood_units-? where blood_type='unitS_requested"; 
									st = con.createStatement();
									rs = st.executeQuery(query);
									rsmd=rs.getMetaData();
									c=rsmd.getColumnCount();
									i=c;
									while(c!=0){
										c--;
										System.out.format("%16s",rsmd.getColumnName(i-c));  
									}
									System.out.println();
									while(rs.next()){
										c=i;
										while(c!=0){
											c--;
											System.out.format("%16s",rs.getString(i-c));
										}
										System.out.println();
									}
									break;
									
							case "3":
								System.out.println("Enter 1 to view the list of existing drives "
										+ "\n 2 To create an event for blood "
										+ "\n 3 To edit existing details");
								ques2=input.next();
								switch(ques2){
								case "1":
									query="select * from Blood_donation_drive";
									st = con.createStatement();
									rs = st.executeQuery(query);
									rsmd=rs.getMetaData();
									c=rsmd.getColumnCount();
									i=c;
									while(c!=0){
										c--;
										System.out.format("%16s",rsmd.getColumnName(i-c));  
									}
									System.out.println();
									while(rs.next()){
										c=i;
										while(c!=0){
											c--;
											System.out.format("%16s",rs.getString(i-c));
										}
										System.out.println();
									}
									break;
								case "2":
									do{
										query2 = "insert into Blood_donation_drive values(?,?,?,?)";
										PreparedStatement st4 = con.prepareStatement(query2);
										System.out.println("Enter Location");
										int temp1;
										String temp2;
										temp2=input.next();
										st4.setString(1, temp2);
										System.out.println("Enter Number of Donors registered yet");
										temp1=input.nextInt();
										st4.setInt(2, temp1);
										System.out.println("Enter Date(YYYY-MM-DD) of blood donation drive ");
										temp2=input.next();
										st4.setString(3, temp2);
										System.out.println("Enter Blood_bank_id of your bank");
										temp1=input.nextInt();
										st4.setInt(4, temp1);
										int count2 = st4.executeUpdate();
										if(count2==1)
											System.out.println("Successful insertion");
										System.out.println("\nDo you want to continue creating new drives?(Yes/No)");
										field=input.next();
									}while(field.equals("Yes"));
									break;
								case "3":
									do{
										System.out.println("Enter the id of the drive you want to edit details of ");
										int id=input.nextInt();
										System.out.println("Enter 1. to edit Location "
												+ "\n2. to update registered number of donors"
												+ "\n3. to change date");
										enter=input.nextInt();
										switch(enter){
										case 1:
											query2="update blood_donation_drive set location=?";
											System.out.println("Enter new location");
											PreparedStatement st5 = con.prepareStatement(query2);
											String temp2=input.next();
											st5.setString(1, temp2);
											int count2 = st5.executeUpdate();
											if(count2==1)
												System.out.println("Successful updation");
											break;
											
											
											//////////// while loop lgana hai?
										case 2:
											query2="update blood_donation_drive set registered_donors=?";
											System.out.println("Enter updated number of registered donors");
											PreparedStatement st6 = con.prepareStatement(query2);
											int temp1=input.nextInt();
											st6.setInt(1, temp1);
											int count1 = st6.executeUpdate();
											if(count1==1)
												System.out.println("Successful updation");
											break;
										case 3:
											query=query2="update blood_donation_drive set registered_donors=?";
											System.out.println("Enter Changed Date (YYYY-MM-DD)");
											st6 = con.prepareStatement(query2);
											temp2=input.next();
											st6.setString(1, temp2);         
											count1 = st6.executeUpdate();
											if(count1==1)
												System.out.println("Successful updation");
											break;
											
											
										}System.out.println("Do you want to continue adding, viewing and deleting donation drives? ");
										field=input.next();
										
									}while(field.equals("Yes"));
									break;
								} // donation drives ka switch case ends ---- break lagana hai?
								
										
								
							
							
							
							
								
								System.out.println("\nDo you want to continue viewing information?(Yes/No)");
								field=input.next();
							}while(field.equals("Yes"));
							break;
							
				case "Receptionist":
					System.out.println("Enter your id");
					int id=input.nextInt();
					System.out.println("Enter"
							+ "\n1. For viewing Patients' List"
							+ "\n2. For viewing Donors' List"
							+ "\n3. For registering Donors"
							+ "\n4. For registering Recipients");
					enter=input.nextInt();
					do{
					switch(enter){

					case 1:
						query="show view receptionist_donor";  
						break;
						/////////// view wali cheez?
						
					case 2:
						query="show view receptionist_recipients";
						break;
						
					case 3:
						do{
						System.out.println("Enter the id of donor you want to register");
						enter=input.nextInt();
						query="update donor set registered_by=? where donor_id=?";
						PreparedStatement st4 = con.prepareStatement(query);
						st4.setInt(1, id);
						st4.setInt(2, enter);
						int count1 = st4.executeUpdate();
						if(count1==1)
							System.out.println("Successful updation");
						
						System.out.println("Do you want to continuing registering donors?");
						field=input.next();
						
						}         //// break yahan bhi?
						while(field.equals("Yes"));
						break;
					case 4:
						do{
						System.out.println("Enter the id of recipient you want to register");
						enter=input.nextInt();
						query="update recipient set registered_by=? where recipient_id=?";
						PreparedStatement st4 = con.prepareStatement(query);
						st4.setInt(1, id);
						st4.setInt(2, enter);
						int count1 = st4.executeUpdate();
						if(count1==1)
							System.out.println("Successful updation");
						
						System.out.println("Do you want to continuing registering recipients?");
						field=input.next();
						
						}         //// break yahan bhi?
						while(field.equals("Yes"));
						break;
						
						
					}System.out.println("Do you want to continuing working?");
					field=input.next();
					
					} while(field.equals("Yes"));
					break;  // switch for Reception ends here
						
							
							
		  case "2":  // visitor
			System.out.println("Enter whether you want to donate or recieive blood"
					+"\n press D for Donor "
					+"\n press R for Recipient");
			ques2=input.next();
			
			switch(ques2){
			case "D":
				System.out.println("Answer the following questions and submit valid proofs of the same:"+
						"\n Q1. Is your weight above 47kg? ");
				String a1=input.next();
				System.out.println("Q2. Are you HIV positive or negative? Enter P or N");
				String a2=input.next();
				System.out.println("Q2. Is your blood devoid of hard-drugs/alchohol? ");
				String a3=input.next();
				if(a1.equals("Yes") && a2.equals("N") && a3.equals("Yes")){
					String query1="insert into Visitor values(?,?,?,?)";
					PreparedStatement st4 = con.prepareStatement(query1);
					System.out.println("Enter Aadhar_id");
					int temp1;
					String temp2;
					temp1=input.nextInt();
					st4.setInt(1, temp1);
					System.out.println("Enter Name");
					temp2=input.next();
					st4.setString(2, temp2);
					System.out.println("Enter Location");
					temp2=input.next();
					st4.setString(3, temp2);
					System.out.println("Enter Date of Birth(YYYY-MM-DD)");
					temp2=input.next();
					st4.setString(4, temp2);
					int count2 = st4.executeUpdate();
					
					///////////TRIGGGERRRRRRR
					
					String query2="insert into Donor values(?,?)";
				    PreparedStatement st5 = con.prepareStatement(query2);
					System.out.println("Enter Donor_id");
					temp1=input.nextInt();
					st5.setInt(1, temp1);
					System.out.println("Enter Blood type you want to donate");
					temp2=input.next();
					st5.setString(2, temp2);
					count2 += st5.executeUpdate();
					
					if(count2==2)
						System.out.println("Successful insertion");
				}
				else{
					System.out.println("Sorry, you can't donate blood");
				}
				break;
			case "R":
				String query1="insert into Visitor values(?,?,?,?)";
				PreparedStatement st4 = con.prepareStatement(query1);
				System.out.println("Enter Aadhar_id");
				int temp1;
				String temp2;
				temp1=input.nextInt();
				st4.setInt(1, temp1);
				System.out.println("Enter Name");
				temp2=input.next();
				st4.setString(2, temp2);
				System.out.println("Enter Location");
				temp2=input.next();
				st4.setString(3, temp2);
				System.out.println("Enter Date of Birth(YYYY-MM-DD)");
				temp2=input.next();
				st4.setString(4, temp2);
				int count3 = st4.executeUpdate();
				
				String query2="insert into Donor values(?,?,?)";
			    PreparedStatement st5 = con.prepareStatement(query2);
				System.out.println("Enter Recipient_id");
				temp1=input.nextInt();
				st5.setInt(1, temp1);
				System.out.println("Enter Blood type you want to recieve");
				temp2=input.next();
				st5.setString(2, temp2);
				count3 += st5.executeUpdate();
				
				if(count3==2){
					System.out.println("Successful insertion");}
			
				else{
					System.out.println("Sorry, Please Try again");}
				break;				
				
				
			}               
			
			
								}
						}
					}
				
		
			}
			
			System.out.println("Do you want to continue working in blood bank management system? Yes or No");
			ques1=input.next();
			}while(ques1.equals("Yes"));            // first do ends
		System.out.println("ThankYou!");            //main ka hi part
		input.close();
		con.close();
	
	} //main ends
}  // Code Ends
