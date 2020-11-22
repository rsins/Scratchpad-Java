package com.assignment.clinicalTrials;

import java.util.List;

public class ClinicalTrial {

	ClinicalTrialEligiblityCriteria clinicalTrialEligiblityCriteria;
	String description;
	
	public ClinicalTrialEligiblityCriteria getEligiblityCriteria() {
		
		return null;
	}
	
	public boolean checkEligibility(List<SurveyAnswer> surveyAnswers) {
		return false;
	}
}
