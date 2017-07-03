package group787.utor.flylocity.users;

import java.io.Serializable;

/**
 * A representation of a user with administrative privileges. 
 * @author Arjun
 *
 */
public class Admin extends Client implements Serializable {

	/**
	 * Create an instance of Admin with all fields.
	 * @param first_name string of the first name of the Admin
	 * @param last_name string of the last name of the Admin
	 * @param email the string email of this Admin
	 * @param address string address of the Admin
	 * @param cc_number credit card number of this Admin
	 * @param cc_expiry security code of the credit card info of this Admin
	 */
	public Admin(String last_name,String first_name, String email, 
			String address, String cc_number, String cc_expiry) {
		super(last_name, first_name, email, address, cc_number, cc_expiry);
	}
}
