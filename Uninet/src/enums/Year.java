package enums;

/** Years of course
 */
public enum Year {
	/** First year of study
     */
	FIRST(1),
	/** Second year
     */
	SECOND(2),
	/** Third year
     */
	THIRD(3),
	/** Fourth year
     */
	FOURTH(4);
	
	/** Year's int value 
     */
	private final int value;
	
	/** Constructor
	 *  @param newValue value in int
     */
	Year(int newValue){
		value = newValue;
	}
	
	/** @return year of study in integer
     */
	public int getYearOfStudy() {
		return value;
	}
	/**
	 * to change the enterred integer to year of student.
	 * @param year - year of student 
	 */
	public static Year tranferFromIntToYear(int year) {
		if(year == 1) {
			return Year.FIRST;
		}
		if(year == 2) {
			return Year.SECOND;
		}
		if(year == 3) {
			return Year.THIRD;
		}
		if(year == 4) {
			return Year.FOURTH;
		}
		return null;
	}
}

