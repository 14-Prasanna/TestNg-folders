package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "signupData")
    public Object[][] getSignupData() throws Exception {
        return ExcelUtil.getData("src/test/resources/testdata.xlsx", "Sheet1");
    }

    @DataProvider(name = "existingUserData")
    public Object[][] getExistingUser() {
        return new Object[][] {
            {"Mythily", "mythily123@gmail.com"}
        };
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        return ExcelUtil.getData("src/test/resources/testdata.xlsx", "Login");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidData() throws Exception {
        return ExcelUtil.getData("src/test/resources/testdata.xlsx", "Invalid");
    }
}