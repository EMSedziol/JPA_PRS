package ui;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;

import business.Console;
import business.Product;
import business.User;
import business.UserDB;
import business.Vendor;
import business.ProductDB;
import business.VendorDB;

public class jpa_prs {

	public static void main(String[] args) {
		System.out.println("JPA Demo");
		String choice = "";
		while (!choice.equalsIgnoreCase("e")) {
			choice = optionMenu();
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
				try {
					UserDB.addUsersFromTabfile();
				} catch (FileNotFoundException e) {
					System.out.println("Could not upload User data");
					e.printStackTrace();
				}
			if (choice.equalsIgnoreCase("10"))
				try {
					UserDB.addUsersFromFlatfile();
				} catch (FileNotFoundException e) {
					System.out.println("Could not upload User data from flat file");
					e.printStackTrace();
				}
		}
		System.out.println("Bye Bye");

	}

	public static String optionMenu() {
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
		if (u != null) {
			System.out.println("user '" + u.getUserName() + "' successfully login");
		} else
			System.out.println("Login failed");
	}

}
