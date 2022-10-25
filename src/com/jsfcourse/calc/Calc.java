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
	private Integer value;
	private Integer year;
	private Double percent;
	private Double result;
	private Double cost;

	@Inject
	FacesContext ctx;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
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
			cost = (Math.round((this.value+(this.value*(this.percent/100)*this.year)) * 100))/100.0;
			result = (Math.round((this.value+(this.value*(this.percent/100)*this.year)/(this.year*12)) * 100))/100.0;
			

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
