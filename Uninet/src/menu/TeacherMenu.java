package menu;

import java.io.*;

import java.util.Iterator;
import acters.Student;
import acters.Teacher;
import acters.User;
import resources.*;

/**
 * Represents teacher's menu.
 */
public class TeacherMenu implements Menu {
	/**
	 * Current user teacher in wsp
	 */
	private Teacher teacher;

	/**
	 * @param user - current teacher
	 */
	public TeacherMenu(User user) {
		this.teacher = (Teacher) user;
	}

	/**
	 * Show all courses in Wsp
	 * 
	 * @return list of all courses
	 */
	public String showAllCourses() {
		String allCourses = "";
		int i = 1;
		for (Course course : Wsp.getInstance().getCourses()) {
			allCourses += "\n " + i + ". " + course.getName();
			i++;
		}
		return allCourses;
	}

	/**
	 * Show all courses of this teacher
	 * 
	 * @return the name of courses
	 */
	public String showCourses() {
		int i = 1;
		String teacherCourses = "";
		for (Course course : teacher.getCourses()) {
			teacherCourses += "\n" + i + ". " + course.getName();
			i++;
		}
		return teacherCourses;
	}

	/**
	 * Shows all course files of this course
	 * 
	 * @return the course files list
	 */
	public String showCourseFiles(int choice) {
		int i = 1;
		String courseFiles = "";
		for (Course course : teacher.getCourses()) {
			if (i == choice) {
				int j = 1;
				for (CourseFile file : teacher.getCourseFiles(course)) {
					courseFiles += "\n" + j + ". " + file.getNameOfFile();
					j++;
				}
			}
			i++;
		}
		return courseFiles;
	}

	/**
	 * Show a students list for a given course.
	 * 
	 * @return the name, surname and mark of students
	 */
	public String showCourseStudents(int courseNumber) {
		int i = 1;
		String courseStudents = "";
		for (Course course : teacher.getCourses()) {
			if (i == courseNumber) {
				int j = 1;
				for (Student student : teacher.getListOfStudents(course)) {
					courseStudents += "\n" + j + ". " + student.getName() + " " + student.getSurname() + "\n"
							+ student.viewCourseMarks(course);
					j++;
				}
			}
			i++;
		}
		return courseStudents;
	}

	/**
	 * This method is using to add new course file for the course.
	 * 
	 * @param courseNumber number of course
	 * @param nameOfFile   file name
	 */
	public void addCourseFile(int courseNumber, String nameOfFile) {
		int i = 1;
		for (Course course : teacher.getCourses()) {
			if (i == courseNumber) {
				course.addFile(new CourseFile(nameOfFile));
				System.out.println("File: " + nameOfFile + " successfully added to course: " + course.getName());
			}
			i++;
		}
	}

	/**
	 * This method is using to delete course file in the course.
	 * 
	 * @param courseNumber number of course
	 * @param nameOfFile   file name
	 */
	public void deleteCourseFile(int courseNumber, int fileNumber) {
		int i = 1;
		for (Iterator<Course> iterator = teacher.getCourses().iterator(); iterator.hasNext();) {
			Course course = iterator.next();
			if (i == courseNumber) {
				int j = 1;
				for (Iterator<CourseFile> fileIt = course.getCourseFiles().iterator(); fileIt.hasNext();) {
					CourseFile file = fileIt.next();
					if (j == fileNumber) {
						System.out.println("File: " + file + " successfully deleted from course: " + course.getName());
						fileIt.remove();
					}
					j++;
				}
				System.out.println("New course files list of course: " + course.getName() + ": "
						+ this.showCourseFiles(courseNumber));
			}
			i++;
		}

	}

	/**
	 * Start the menu of teacher.
	 */
	public void startMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("Which option do you choose?");
				System.out.println(
						"\n 1)Get personal data \n 2.View my courses \n 3.Add new course \n 4.View/Manage Course Files \n 5.View/Manage list of students \n 6.Send order to techGuy \n 7.Check mail \n 8.Exit");
				int choice = Integer.parseInt(reader.readLine());
				if (choice == 1) {
					if (teacher.viewInfo() == null) {
						System.out.println("bac");
					}
					System.out.println(teacher.viewInfo());
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if (choice == 1)
						continue menu;
					if (choice == 2) {
						System.out.println("Goodbye!!! Saving data...");
						Wsp.getInstance().save();
						break menu;
					}
					break;
				} else if (choice == 2) {
					System.out.println(this.showCourses());
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if (choice == 1)
						continue menu;
					if (choice == 2) {
						System.out.println("Goodbye!!! Saving data...");
						Wsp.getInstance().save();
						break menu;
					}
					break;
				} else if (choice == 3) {
					registrForCourse: while (true) {
						System.out.println("Choose course to add: ");
						System.out.println(this.showAllCourses());
						choice = Integer.parseInt(reader.readLine());
						int i = 1;
						for (Course course : Wsp.getInstance().getCourses()) {
							if (i == choice) {
								teacher.addCourse(course.getName());
								System.out.println("You successfully added course: " + course.getName());
							}
							i++;
						}
						System.out.println("\n 1) Add another course  \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1)
							continue registrForCourse;
						if (choice == 2)
							continue menu;
						if (choice == 3) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break;
					}
				} else if (choice == 4) {
					int courseNumber;
					manageCourseFiles: while (true) {
						System.out.println("Choose which course files to view: ");
						System.out.println(this.showCourses());
						courseNumber = Integer.parseInt(reader.readLine());
						System.out.println(this.showCourseFiles(courseNumber));
						System.out
								.println("\n 1) Add course file \n 2) Delete course file \n 3) Return back \n 4) Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1) {
							addFiles: while (true) {
								System.out.println("Enter name of file to add");
								String nameOfFile = reader.readLine();
								this.addCourseFile(courseNumber, nameOfFile);
								System.out.println(
										"\n 1) Add another course file \n 2) Return back \n 3) Return back to menu \n 4) Exit");
								choice = Integer.parseInt(reader.readLine());
								if (choice == 1)
									continue addFiles;
								if (choice == 2)
									continue manageCourseFiles;
								if (choice == 3)
									continue menu;
								if (choice == 4) {
									System.out.println("Goodbye!!! Saving data...");
									Wsp.getInstance().save();
									break menu;
								}
								break addFiles;
							}
						}
						if (choice == 2) {
							deleteFile: while (true) {
								System.out.println("Choose file to delete");
								System.out.println(this.showCourseFiles(courseNumber));
								int fileNumber = Integer.parseInt(reader.readLine());
								this.deleteCourseFile(courseNumber, fileNumber);
								System.out.println(
										"\n 1) Delete another course file \n 2) Return back \n 3) Return back to menu \n 4) Exit");
								choice = Integer.parseInt(reader.readLine());
								if (choice == 1)
									continue deleteFile;
								if (choice == 2)
									continue manageCourseFiles;
								if (choice == 3)
									continue menu;
								if (choice == 4) {
									System.out.println("Goodbye!!! Saving data...");
									Wsp.getInstance().save();
									break menu;
								}
								break deleteFile;
							}
						}
						if (choice == 3)
							continue menu;
						if (choice == 4) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break manageCourseFiles;
					}
				} else if (choice == 5) {
					manageCourseStudents: while (true) {
						System.out.println("Choose course to view students");
						System.out.println(this.showCourses());
						int number = Integer.parseInt(reader.readLine());
						System.out.println(this.showCourseStudents(number));
						System.out.println("\n 1) Put or change marks of student \n 2) View mark statistics about students \n 3) Return back to menu\n 4) Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1) {
							putMark: while (true) {
								System.out.println("Choose student to put marks");
								int studentToPutMark = Integer.parseInt(reader.readLine());
								int i = 1;
								for (Course course : teacher.getCourses()) {
									if (i == number) {
										int j = 1;
										for (Student student : teacher.getListOfStudents(course)) {
											if (j == studentToPutMark) {
												putMarks: while (true) {
													System.out.println("\n 1.Put first attestation \n 2. Put second attestation\n 3. Put final \n 4. Choose another student \n 5. Return back to course list \n 6. Return back to menu \n 7. Exit");
													int choose = Integer.parseInt(reader.readLine());
													if (choose == 1) {
														System.out.println(
																"Enter mark to put first attestation (0 - 30)");
														double value = Double.parseDouble(reader.readLine());
														teacher.putFirstAttestationMark(student, course, value);
														System.out.println("Student : " + student.getName()+ "'s first attestation mark changed to " + value);
														continue putMarks;
													}
													if (choose == 2) {
														System.out.println(
																"Enter mark to put second attestation (0 - 30)");
														double value = Double.parseDouble(reader.readLine());
														teacher.putSecondAttestationMark(student, course, value);
														System.out.println("Student : " + student.getName()
																+ "'s second attestation mark changed to " + value);
														continue putMarks;
														
													}
													if (choose == 3) {
														System.out.println("Enter mark to put final (0 - 40)");
														double value = Double.parseDouble(reader.readLine());
														teacher.putFinalMark(student, course, value);
														System.out.println("Student : " + student.getName()
																+ "'s final mark changed to " + value);
														continue putMarks;
													}
													if (choose == 4)
														continue putMark;
													if (choose == 5)
														continue manageCourseStudents;
													if (choose == 6)
														continue menu;
													if (choose == 7) {
														System.out.println("Goodbye!!! Saving data...");
														Wsp.getInstance().save();
														break menu;
													}
													j++;
													break putMarks;
												}
											}
										}
										i++;
									}

								}
								break putMark;
							}
						}
						if (choice == 2) {
							int i = 1;
							for(Course course : teacher.getCourses()) {
								if(i == number) {
									System.out.println(teacher.viewStatistics(course.getName()));
								}
								i++;
							}
							
						}
						if (choice == 3)
							continue menu;
						if (choice == 4) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break manageCourseStudents;
					}
				} else if (choice == 6) {
					sendOrder: while (true) {
						System.out.println("Enter order text to send order to TechSupport");
						String orderText = reader.readLine();
						teacher.sendOrderToIT(orderText);
						System.out.println("\n 1) Send another order \n 2) Return back to menu \n 3) Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1)
							continue sendOrder;
						if (choice == 2)
							continue menu;
						if (choice == 3) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break sendOrder;
					}
				} else if (choice == 7) {
					System.out.println(teacher.viewMessages());
					System.out.println("\n 1) Return back to menu \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if (choice == 1)
						continue menu;
					if (choice == 2) {
						System.out.println("Goodbye!!! Saving data...");
						Wsp.getInstance().save();
						break menu;
					}
				} else if (choice == 8) {
					System.out.println("Bye!!!");
					Wsp.getInstance().save();
					break menu;
				}
			}
		}catch(

	Exception e)
	{
		System.out.println("Oh, something wrong happened..");
		e.printStackTrace();
		Wsp.getInstance().save();
	}
}}
