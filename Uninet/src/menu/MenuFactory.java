package menu;

import acters.User;

public class MenuFactory {
	@SuppressWarnings("incomplete-switch")
	public Menu getMenu(User user) {
		switch(user.getRole()) {
		case ADMIN:
			return new AdminMenu(user);
		case TEACHER:
			return new TeacherMenu(user);
		case MANAGER:
			return new ManagerMenu(user);
		case STUDENT:
			return new StudentMenu(user);
		case TECHSUPPORT:
			return new TechGuyMenu(user);
		}
		return null;
	}
}
