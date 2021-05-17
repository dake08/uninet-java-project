package comparators;

import java.util.Comparator;

import acters.Student;

/** Comparator to compare students by year of study
 */
public class StudentYearComparator implements Comparator <Student>{
	public int compare(Student c1,Student c2) {
		if(c1.getYear()>c2.getYear()) return 1;
		else if(c1.getYear()<c2.getYear())return -1;
		return 0;
	}
}	
