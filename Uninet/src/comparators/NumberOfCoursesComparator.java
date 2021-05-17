package comparators;

import java.util.Comparator;

import acters.Teacher;

/** Comparator to compare teacher by number of courses they have
 */
public class NumberOfCoursesComparator implements Comparator <Teacher>{
	public int compare(Teacher c1,Teacher c2) {
		if(c1.getCoursesNumber()>c2.getCoursesNumber()) return 1;
		else if(c1.getCoursesNumber()<c2.getCoursesNumber())return -1;
		return 0;
	}
}	
