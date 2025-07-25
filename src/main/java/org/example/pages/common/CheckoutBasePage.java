package org.example.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutBasePage extends BasePage {

    @FindBy(id = "first-name")
    private ExtendedWebElement firstNameField;

    @FindBy(id = "last-name")
    private ExtendedWebElement lastNameField;

    @FindBy(id = "postal-code")
    private ExtendedWebElement postalCodeField;

    @FindBy(id = "continue")
    private ExtendedWebElement continueButton;

    @FindBy(id = "finish")
    private ExtendedWebElement finishButton;

    @FindBy(className = "complete-header")
    private ExtendedWebElement confirmationMessage;

    public CheckoutBasePage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String firstName, String lastName, String postalCode) {
        firstNameField.type(firstName);
        lastNameField.type(lastName);
        postalCodeField.type(postalCode);
        continueButton.click();
    }

    public void finishOrder() {
        finishButton.click();
    }

    public boolean isConfirmationDisplayed() {
        return confirmationMessage.isElementPresent();
    }
}