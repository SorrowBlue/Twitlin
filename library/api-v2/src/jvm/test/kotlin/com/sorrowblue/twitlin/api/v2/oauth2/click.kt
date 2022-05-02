package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.BuildKonfig
import java.time.Duration
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

actual fun authenticationFlowScenario(url: String): String? {

    System.setProperty("webdriver.chrome.driver", "chromedriver.exe")
    val driver = ChromeDriver()
    driver.get(url)
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50))
    WebDriverWait(driver, Duration.ofSeconds(30)).until {
        driver.findElement(By.cssSelector("#react-root > div > div > div.css-1dbjc4n.r-13qz1uu.r-417010 > main > div > div > div.css-1dbjc4n.r-1gluylu > div > div > a"))
            .click()
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
        WebDriverWait(driver, Duration.ofSeconds(30)).until {
            driver.findElement(By.ByXPath("//input[@autocomplete='username']")).sendKeys(BuildKonfig.TEST_EMAIL)
            driver.findElement(By.cssSelector("#layers > div:nth-child(1) > div > div > div > div > div > div.css-1dbjc4n.r-1awozwy.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv > div.css-1dbjc4n.r-1867qdf.r-1wbh5a2.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1.r-htvplk.r-1udh08x > div > div > div.css-1dbjc4n.r-kemksi.r-6koalj.r-16y2uox.r-1wbh5a2 > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1jgb5lz.r-1ye8kvj.r-13qz1uu > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1dqxon3 > div > div > div:nth-child(6) > div"))
                .click()
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
            WebDriverWait(driver, Duration.ofSeconds(30)).until {
                driver.findElement(By.ByXPath("//input[@type='password']")).sendKeys(BuildKonfig.TEST_PASS)
                driver.findElement(By.ByXPath("//div[@data-testid='LoginForm_Login_Button']"))
                    .click()
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
                WebDriverWait(driver, Duration.ofSeconds(30)).until {
                    driver.findElement(By.cssSelector("#react-root > div > div > div.css-1dbjc4n.r-13qz1uu.r-417010 > main > div > div > div.css-1dbjc4n.r-1gluylu > div > div > div.css-1dbjc4n.r-1awozwy.r-6koalj.r-q4m81j > div.css-1dbjc4n.r-11rk87y.r-1ur9v65 > div"))
                        .click()
                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
                    WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                    ).until(ExpectedConditions.urlMatches("https://maitter.sorrowblue.com"))
                }
            }
        }
    }
    return driver.currentUrl.also {
        driver.close()
    }
}
