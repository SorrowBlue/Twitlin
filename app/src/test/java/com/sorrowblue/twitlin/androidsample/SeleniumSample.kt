package com.sorrowblue.twitlin.androidsample

import com.sorrowblue.twitlin.core.CoreApiGetter
import com.sorrowblue.twitlin.core.client.ConsumerKeys
import com.sorrowblue.twitlin.core.client.Oauth1aClient
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class SeleniumSample {
    private lateinit var driver: WebDriver
    private val consumerKeys: ConsumerKeys = ConsumerKeys(BuildConfig.TWITTER_API_KEY,BuildConfig.TWITTER_API_SECRET)

    @Before
    fun setUp() {
        //chrome driverの指定
        System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe")

        driver = ChromeDriver(ChromeOptions().apply {
            //chromeのパスを指定
            setBinary(File("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"))
        })
    }

    @After
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun test_sample() {
        runTest {
            val oauthApi = CoreApiGetter.oAuthApi(Oauth1aClient(consumerKeys, null))
            var token = ""
            val url = oauthApi.requestToken("oob").dataOrNull()?.let {
                token = it.oauthToken
                oauthApi.authorize(it.oauthToken)
            }
            driver.get(url)
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
            driver.findElement(By.id("username_or_email")).sendKeys(BuildConfig.TWITTER_EMAIL)
            driver.findElement(By.id("password")).sendKeys(BuildConfig.TWITTER_PASS)
            val file = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
            Files.copy(
                file.toPath(),
                Paths.get("${System.getProperty("user.dir")}/build/reports/${file.name}")
            )
            driver.findElement(By.id("allow")).click()
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
            val pinCode = driver.findElement(By.xpath("/html/body/div[2]/div/p/kbd/code")).text
            oauthApi.accessToken(token, pinCode).dataOrNull().let {
                assertNotNull(it)
            }
        }
    }
}
