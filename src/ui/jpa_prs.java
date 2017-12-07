package ui;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import business.*;


public class jpa_prs {
	static UserDB userDB = null;
	static User validatedUser = null;
	static ProductDB productDB = null;

	public static void main(String[] args) {
		System.out.println();
		System.out.println("JPA Demo");
		System.out.println();
		System.out.println("before method validateLoginUser");
		validateLoginUser();
		System.out.println("try to print name");
		System.out.println(validatedUser.getFirstName() + " " + validatedUser.getLastName());
		// the above works
		ArrayList<Status> status = StatusDB.getAllStatus();
		System.out.println(StatusDB.getAllStatus().toString());  // this needs to be put into a hashMap
//		HashMap statusHashMap = fillStatusHashMap();
		String choice = "";
		while (!choice.equalsIgnoreCase("e")) {
			choice = optionDisplayMenu();
			System.out.println("choice " + choice);
			if (choice.equalsIgnoreCase("1"))
				getUserInfo(); // for 1 user
			if (choice.equalsIgnoreCase("2"))
				getAllUsers();
			if (choice.equalsIgnoreCase("3"))
				addUser();
			if (choice.equalsIgnoreCase("4"))
				deleteUser();
			if (choice.equalsIgnoreCase("5"))
				updateUser();
			if (choice.equalsIgnoreCase("6"))
				getVendorInfo(); // for 1 vendor
			if (choice.equalsIgnoreCase("7"))
				getProductInfo(); // for 1 product
			if (choice.equalsIgnoreCase("8"))
				validateLoginUser();
			if (choice.equalsIgnoreCase("9"))
					UserDB.addUsersFromTabfile();
			if (choice.equalsIgnoreCase("10"))
					UserDB.addUsersFromFlatfile();
			if (choice.equalsIgnoreCase("11"))
				addPurchaseRequest();
		}
		System.out.println("Bye Bye");

	}

	public static String optionDisplayMenu() {
		String choice = "";
		System.out.println("1 - Get User Information ");
		System.out.println("2 - List all users: ");
		System.out.println("3 - Add User ");
		System.out.println("4 - Delete User ");
		System.out.println("5 - Update User ");
		System.out.println("6 - Get Vendor Information ");
		System.out.println("7 - Get Product Information ");
		System.out.println("8 - Validate User Login ");
		System.out.println("9 - Upload tab delimited User data ");
		System.out.println("10 - upload flat file User Data ");
		System.out.println("11 - Enter purchase request ");
		System.out.println("e - exit");
		choice = Console.getString("Enter option ");
		return choice;
	}

	public static void getUserInfo() {
		int userId = Console.getInt("Enter userId to retrieve: ");
		User u = UserDB.getUserByInt(userId);
		System.out.println("User details: " + u);
	}

	public static void getVendorInfo() {
		System.out.println();
		int vendorId = Console.getInt("Enter vendor id ");
		Vendor v = VendorDB.getVendorByInt(vendorId);
		System.out.println("Vendor " + v);
	}

	public static void getProductInfo() {
		int productId = Console.getInt("Enter product id to retrieve");
		Product p = ProductDB.getProductByInt(productId);
		System.out.println("Product id: " + p);
	}

	private static void getAllUsers() {
		ArrayList<User> users = UserDB.getAllUsers();
		System.out.println("List of Users");
		for (User u : users) {
			System.out.println(u);
		}
	}
	public static HashMap fillStatusHashMap() {
		ArrayList<Status> status = StatusDB.getAllStatus();
		HashMap<Integer, String>  statusHashMap = new HashMap<Integer, String>();
		for (Status s : status) {
			statusHashMap.put(s.getId(), s.getDescription());
		}
		return statusHashMap;
	}
/*	
	private static String getStatusDesc(int id, HashMap statusHashMap) {
		String statusDesc = "";
		statusDesc = statusHashMap.get(id);
		return statusDesc
		
	}*/

	private static void deleteUser() {
		int userId = Console.getInt("Enter userId to delete ");
		User u = UserDB.getUserByInt(userId);
		if (UserDB.deleteUser(u)) {
			System.out.println("user '" + u.getUserName() + "' successfully deleted");
		}
	}

	private static void updateUser() {
		int userId = Console.getInt("Enter userId to update ");
		User u = UserDB.getUserByInt(userId);
		String firstName = Console.getString("enter new first name ");
		u.setFirstName(firstName);
		if (UserDB.updateUser(u)) {
			System.out.println("user '" + u.getUserName() + "' successfully deleted");
		}
	}

	public static void addUser() {
		String userName = Console.getString("username ");
		String passWord = Console.getString("password ");
		String firstName = Console.getString("name ");
		String lastName = Console.getString("last ");
		String phone = Console.getString("phone ");
		String email = Console.getString("email ");
		boolean reviewer = Console.getBoolean("reviewer ");
		boolean admin = Console.getBoolean("admin ");
		boolean active = Console.getBoolean("active ");

		User u2 = new User(userName, passWord, firstName, lastName, phone, email, reviewer, admin, active);
		u2.setDateCreated(new Timestamp(System.currentTimeMillis()));

		if (UserDB.addUser(u2)) {
			System.out.println("User added");
		}
	}

	public static void getProductByInt() {
		System.out.println();
		int productId = Console.getInt("Enter product id to retrieve");
		Product p = ProductDB.getProductByInt(productId);
		System.out.println("Product id: " + p);
		System.out.println();
	}

	public static void validateLoginUser() {
		System.out.println();
		String userName = Console.getString("Enter User Name: ");
		String password = Console.getString("Password: ");
		User u = UserDB.validateUser(userName, password);
		System.out.println("out of validate user in UserDB");
		if (u != null) {
			System.out.println("user '" + u.getUserName() + "' successfully login");
			validatedUser = u;
		} else
			System.out.println("Login failed");
	}
	
	public static void addPurchaseRequest() {
		System.out.println("");
		String userName = validatedUser.getUserName();
		String description = Console.getString("Description: ", 100);
		String justification = Console.getString("Justification: ", 225);
		LocalDate dateNeeded = Console.getLocalDate("Date Needed: ");
		String deliveryMode = Console.getString("Delivery method: ");
		int statusId = 1; // new request
		double total = 0.0;
		LocalDate submittedDate = LocalDate.now();
		String reasonForRejection = "";
		boolean isActive = true;
		
		boolean enterLineItem = true;
		boolean cancel = false;
		boolean submit = false;
		while (enterLineItem) {
			String lineItem = Console.getString("1 - enter line item, 2 - view summary/submit, 3 - cancel");
			if (lineItem.equalsIgnoreCase("1")) {
				ArrayList<Product> products = ProductDB.getAllProducts();
				System.out.println("List of Users");
				for (Product prd : products) {
					System.out.println(prd.getProductId() + " " + prd.getName() + " " + prd.getPrice());
				}
			}
			if (lineItem.equalsIgnoreCase("2")) {
				submit = true;
				enterLineItem = false;
			}
			if (lineItem.equalsIgnoreCase("3")) {
				cancel = true;
				enterLineItem = false;
			}
		}
		
		
	}


}
