package enums;

/** Letter grades
 */
public enum ScoreLetter     {

    /** 95-100 %
     */
    A("A"),
    /** 90-94 %
     */
    AMINUS("A-"),
    /** 85-89 %
     */
    BPLUS("B+"),
    /** 80-84 %
     */
    B("B"),
    /** 75-79 %
     */
    BMINUS("B-"),
    /** 70-74 %
     */
    CPLUS("C+"),
    /** 65-69 %
     */
    C("C"),
    /** 60-64 %
     */
    CMINUS("C-"),
    /** 55-59 %
     */
    DPLUS("D+"),
    /** 50-54 %
     */
    D("D"),
    /** 25-49 %
     */
    FX("FX"),
    /** 0-24 %
     */
    F("F");


    /** The value in string
     */
    private String value;

    /** 
     */
    ScoreLetter(String newValue){
        this.value = newValue;
    }

    /** @return value - return value in String
     */ 
    public String getGrade() {
        return value;
    }


}