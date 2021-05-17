package resources;

import java.io.Serializable;

import enums.ScoreLetter;

/** Represents mark of student. 
 */
@SuppressWarnings("serial")
public class Mark implements Serializable{
	/** Field mark for first attestation 
	 */
	private double firstAttestation;

	/** Field mark for second attestation 
	 */
	private double secondAttestation;
 
	/** Field mark for final exam
	 */
	private double finalMark;
	/**
	 * @return the final mark
	 */
	public Mark(double first,double second,double finalMark) {
		this.finalMark = finalMark;
		this.firstAttestation = first;
		this.secondAttestation = second; 
		
	}
	public double getFinalMark() {
		return finalMark;
	}
	/** @param finalMark - new final mark 
	 */
	public void setFinalMark(double finalMark) {
		this.finalMark = finalMark;
	}
	/** @return the final mark
	 */
	public double getSecondAttestation() {
		return secondAttestation;
	}
	/** @return the final mark
	 */
	public double getFirstAttestation() {
		return firstAttestation;
	}
	
	/** 
	 * @param value - new mark for first attestation 
	 */
	public void setFirstAttestationValue(double value) {
		this.firstAttestation = value;
	}

	/**
	 * @param value - new mark for second attestation 
	 */
	public void setSecondAttestationValue(double value) {
		this.secondAttestation = value;
	}

	/**
	 * @return the total score of all semester
	 */
	public double getTotalScore() {
		return this.firstAttestation + this.secondAttestation + this.finalMark;
	}

	/**
	 * @return the score letter of mark
	 */
	public String getGradeLetter() {
		if(getTotalScore()>=95) return ScoreLetter.A.getGrade();
		else if(getTotalScore()>=90) return ScoreLetter.AMINUS.getGrade();
    	else if(getTotalScore()>=85) return ScoreLetter.BPLUS.getGrade();
        else if(getTotalScore()>=80) return ScoreLetter.B.getGrade();
        else if(getTotalScore()>=75) return ScoreLetter.BMINUS.getGrade();
        else if(getTotalScore()>=70) return ScoreLetter.CPLUS.getGrade();
        else if(getTotalScore()>=65) return ScoreLetter.C.getGrade();
        else if(getTotalScore()>=60) return ScoreLetter.CMINUS.getGrade();
        else if(getTotalScore()>=55) return ScoreLetter.DPLUS.getGrade();
        else if(getTotalScore()>=50) return ScoreLetter.D.getGrade();
        else if(getTotalScore()>=30) return ScoreLetter.FX.getGrade();
        return ScoreLetter.F.getGrade();
	}
	/**
	 * @return markInfo - all marks
	 */
	public String toString() {
		String markInfo = "";
		markInfo += "First attestation: " + this.getFirstAttestation();
		markInfo += "| Second attestation: " + this.getSecondAttestation();
		markInfo += "| Final mark: " + this.getFinalMark();
		markInfo += "| Total mark and grade letter: " + this.getTotalScore() + " " + this.getGradeLetter();
		return markInfo;
	}
	
}
