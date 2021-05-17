package comparators;

import java.util.Comparator;

import acters.Student;

/** Comparator to compare students by total gpa
 */
public class GpaComparator implements Comparator <Student>{
	public int compare(Student c1,Student c2) {
		if(c1.getTotalGpa()>c2.getTotalGpa()) return 1;
		else if(c1.getTotalGpa()<c2.getTotalGpa())return -1;
		return 0;
	}
}	
