package com.actions;

import org.openqa.selenium.support.PageFactory;

import com.pages.DashboardPageLocator;
import com.utilities.HelperClass;

public class DashboardPageAction {
  DashboardPageLocator dpl=null;

  public DashboardPageAction() {
	this.dpl = new DashboardPageLocator();
	PageFactory.initElements(HelperClass.getDriver(), dpl);
  }
  public String getDashboardText() {
	  return dpl.dashboard.getText();
  }
}
