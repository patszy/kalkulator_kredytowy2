package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class Calc {
	private String value;
	private String year;
	private String percent;
	private Double result;
	private Double cost;

	@Inject
	FacesContext ctx;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	} 

	public boolean doTheMath() {
		try {
			double value = Double.parseDouble(this.value);
			double percent = Double.parseDouble(this.percent);
			double year = Double.parseDouble(this.year);

			cost = value+(value*(percent/100)*year);
			result = value+(value*(percent/100)*year)/(year*12);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Udało sie.", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ziom, ale bez takich manewrów.", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) return "showresult";
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Koszt: " + cost, null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
