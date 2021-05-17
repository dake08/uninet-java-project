package comparators;

import java.util.Comparator;

import acters.Employee;

/** Comparator to compare employees by salary
 */
public class SalaryComparator implements Comparator <Employee>{
	public int compare(Employee c1,Employee c2) {
		if(c1.getSalary()>c2.getSalary()) return 1;
		else if(c1.getSalary()<c2.getSalary())return -1;
		return 0;
	}
}	
