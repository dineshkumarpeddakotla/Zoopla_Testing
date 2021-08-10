package com.zoopla.pages;

import com.zoopla.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AgentsPage extends BaseClass {

    @FindBy(xpath = "//div[@class='clearfix']/div[1]/h1/a")
    WebElement agentName;
    @FindBy(xpath = "//p[@class='top-half listing-results-marketed']/span")
    List<WebElement> propertiesAgentsName;

    public AgentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean checkAll_Properties_Belongs_ToThat_Agent() {
        boolean flag = true;
        System.out.println(propertiesAgentsName.size());

        for (WebElement agent: propertiesAgentsName) {
            String[] agent_name = agent.getText().split(",") ;
            System.out.println(agentName.getText());
            System.out.println("agent Name: "+agent_name[0]);
            if (!agent_name[0].equals(agentName.getText())) {
                flag = false;
                break;
            }
        }

        return flag;
    }
}
