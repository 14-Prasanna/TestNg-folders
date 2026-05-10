package com.demoblaze.pages;

import com.demoblaze.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(id = "navi")
    private WebElement navigationBar;

    @FindBy(xpath = "//a[@class='nav-link' and text()='Home ']")
    private WebElement homeNavLink;

    @FindBy(xpath = "//a[@class='nav-link' and text()='Contact']")
    private WebElement contactNavLink;

    @FindBy(xpath = "//a[@class='nav-link' and text()='About us']")
    private WebElement aboutUsNavLink;

    @FindBy(id = "cartur")
    private WebElement cartNavLink;

    @FindBy(id = "logout2")
    private WebElement logoutLink;

    @FindBy(id = "nameofuser")
    private WebElement welcomeText;

    @FindBy(xpath = "//a[text()='Phones']")
    private WebElement phonesCategory;

    @FindBy(xpath = "//a[text()='Laptops']")
    private WebElement laptopsCategory;

    @FindBy(xpath = "//a[text()='Monitors']")
    private WebElement monitorsCategory;

    @FindBy(id = "carouselExampleIndicators")
    private WebElement carousel;

    @FindBy(id = "next")
    private WebElement carouselNextButton;

    @FindBy(xpath = "//div[@id='tbodyid']//div[contains(@class,'card')]")
    private List<WebElement> productCards;

    @FindBy(xpath = "//div[@id='tbodyid']//h4[@class='card-title']/a")
    private List<WebElement> productTitles;

    @FindBy(id = "exampleModal")
    private WebElement contactModal;

    @FindBy(id = "recipient-email")
    private WebElement contactEmailField;

    @FindBy(id = "recipient-name")
    private WebElement contactNameField;

    @FindBy(id = "message-text")
    private WebElement contactMessageField;

    @FindBy(id = "videoModal")
    private WebElement aboutUsModal;

    @FindBy(xpath = "//div[@id='videoModal']//video")
    private WebElement videoPlayer;

    private final By contactModalLocator = By.id("exampleModal");
    private final By aboutUsModalLocator = By.id("videoModal");

    public void clickHomeNav() {
        log.info("Clicking Home nav link");
        click(homeNavLink);
    }

    public void clickContactNav() {
        log.info("Clicking Contact nav link");
        click(contactNavLink);
        WaitUtil.shortPause(800);
    }

    public void clickAboutUsNav() {
        log.info("Clicking About Us nav link");
        click(aboutUsNavLink);
        WaitUtil.shortPause(800);
    }

    public void clickCartNav() {
        log.info("Clicking Cart nav link");
        click(cartNavLink);
    }

    public void clickLogout() {
        log.info("Clicking Logout");
        click(logoutLink);
        WaitUtil.shortPause(1000);
    }

    public void clickNavLinkByText(String linkText) {
        log.info("Clicking nav link: '{}'", linkText);
        By linkLocator = By.xpath("//a[normalize-space(text())='" + linkText + "' or @id='cartur']");
        switch (linkText.trim()) {
            case "Home":
                click(homeNavLink);
                break;
            case "Contact":
                click(contactNavLink);
                break;
            case "About us":
                click(aboutUsNavLink);
                break;
            case "Cart":
                click(cartNavLink);
                break;
            case "Log out":
                click(logoutLink);
                break;
            default:
                log.warn("Unknown nav link: {}", linkText);
        }
        WaitUtil.shortPause(800);
    }

    public boolean isNavBarContaining(String text) {
        try {
            String navText = navigationBar.getText();
            boolean contains = navText.contains(text);
            log.info("Nav bar contains '{}': {}", text, contains);
            return contains;
        } catch (Exception e) {

            By el = By.xpath("//*[@id='navbarSupportedContent']//*[contains(text(),'" + text + "')]");
            return isDisplayed(el);
        }
    }

    public String getWelcomeText() {
        try {
            return welcomeText.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isPhonesVisible() {
        return isDisplayed(phonesCategory);
    }

    public boolean isLaptopsVisible() {
        return isDisplayed(laptopsCategory);
    }

    public boolean isMonitorsVisible() {
        return isDisplayed(monitorsCategory);
    }

    public boolean isCategoryVisible(String category) {
        By categoryLocator = By.xpath("//a[text()='" + category + "']");
        return isDisplayed(categoryLocator);
    }

    public void clickCategory(String category) {
        log.info("Clicking category: '{}'", category);
        By categoryLocator = By.xpath("//a[text()='" + category + "']");
        click(categoryLocator);
        WaitUtil.shortPause(2000); // Wait for product list refresh
    }

    public int getProductCount() {
        WaitUtil.shortPause(1500);
        int count = productCards.size();
        log.info("Product count: {}", count);
        return count;
    }

    public void clickFirstProduct() {
        log.info("Clicking first product in list");
        WaitUtil.shortPause(1500);
        if (!productTitles.isEmpty()) {
            click(productTitles.get(0));
        }
    }

    public boolean isProductVisible(String productName) {
        By productLocator = By.xpath("//*[contains(text(),'" + productName + "')]");
        return isDisplayed(productLocator);
    }

    public boolean isCarouselDisplayed() {
        return isDisplayed(carousel);
    }

    public boolean isCarouselNextButtonClickable() {
        return isDisplayed(carouselNextButton);
    }

    public void clickCarouselNext() {
        log.info("Clicking carousel next button");
        click(carouselNextButton);
        WaitUtil.shortPause(1500);
    }

    public boolean isContactModalDisplayed() {
        try {
            WaitUtil.waitForVisibility(driver, contactModal);
            return contactModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContactEmailFieldVisible() {
        return isDisplayed(contactEmailField);
    }

    public boolean isContactNameFieldVisible() {
        return isDisplayed(contactNameField);
    }

    public boolean isContactMessageFieldVisible() {
        return isDisplayed(contactMessageField);
    }

    public void closeContactModal() {
        log.info("Closing contact modal");
        By closeBtn = By.xpath("//div[@id='exampleModal']//button[@class='close']");
        click(closeBtn);
        WaitUtil.shortPause(800);
    }

    public boolean isContactModalClosed() {
        return !isDisplayed(contactModalLocator);
    }

    public boolean isAboutUsModalDisplayed() {
        try {
            WaitUtil.waitForVisibility(driver, aboutUsModal);
            return aboutUsModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVideoPlayerPresent() {
        return isDisplayed(videoPlayer);
    }

    public void closeAboutUsModal() {
        log.info("Closing About Us modal");
        By closeBtn = By.xpath("//div[@id='videoModal']//button[@class='close']");
        click(closeBtn);
        WaitUtil.shortPause(800);
    }

    public boolean isAboutUsModalClosed() {
        return !isDisplayed(aboutUsModalLocator);
    }

    public boolean isOnProductDetailPage() {
        return getCurrentUrl().contains("prod.html");
    }

    public boolean isProductNameVisible() {
        By productName = By.xpath("//h2[@class='name']");
        return isDisplayed(productName);
    }

    public boolean isProductPriceVisible() {
        By productPrice = By.xpath("//h3[@class='price-container']");
        return isDisplayed(productPrice);
    }

    public boolean isAddToCartButtonPresent() {
        By addToCart = By.xpath("//a[text()='Add to cart']");
        return isDisplayed(addToCart);
    }

    public boolean isLoginLinkVisible() {
        By loginLink = By.id("login2");
        return isDisplayed(loginLink);
    }

    public boolean isSignupLinkVisible() {
        By signupLink = By.id("signin2");
        return isDisplayed(signupLink);
    }
}
