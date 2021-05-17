package enums;

/** Roles of users to login
 */
public enum Role {
    /** Teacher's role
     */
    TEACHER,

    /** Admin's role
     */
    ADMIN,

    /** Manager's role
     */
    MANAGER,

    /** Student's role
     */
    STUDENT,

    /** TechSupport's role
     */
    TECHSUPPORT,
    UNDEFINED;
	
	public static String getTypeOfUsers() {
		String types = "";
		int i = 1;
		for(Role r : Role.values()) {
			if(r.equals(Role.UNDEFINED))
				continue;
			types += "\n" + i + ". " + r.toString();
			i++;
		}
		return types;
	}
	public static Role getEnumOfString (String type) {
		String types = "";
		int i = 1;
		for(Role r : Role.values()) {
			if(r.toString().equals(type))
				return r;
		}
		return Role.UNDEFINED;
	}
	public static Role chooseRole(int roleNumber) {
		int i = 1;
		for(Role r : Role.values()) {
			if(r.equals(Role.UNDEFINED))
				continue;
			if(i == roleNumber) {
				return r;
			}
			i++;
		}
		return Role.UNDEFINED;
	}
}

