package com.sorrowblue.twitlin.androidsample

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.TwitterAPI
import kotlinx.coroutines.runBlocking
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
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

class SeleniumSample {
    private lateinit var driver: WebDriver

    @Before
    fun setUp() {
        //chrome driverの指定
        System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe")

        driver = ChromeDriver(ChromeOptions().apply {
            //chromeのパスを指定
            setBinary(File("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"))
        })
        Twitlin.initialize(
            "ImbKKbcGqVBcgTUlh3eLeQGJc",
            "xFCAagFydlcFgL6eAc84f161X6u7RXkybJkpu2Q36C6KMYoWwb"
        ) {
        }
    }

    @After
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun test_sample() {
        runBlocking {
            var token = ""
            val url = TwitterAPI.oauthApi.requestToken("oob").dataOrNull()?.let {
                token = it.oauthToken
                TwitterAPI.oauthApi.authorize(it.oauthToken)
            }
            driver.get(url)
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS)
            driver.findElement(By.id("username_or_email")).sendKeys("sorrowblue.dev@gmail.com")
            driver.findElement(By.id("password")).sendKeys("twiasadashinon4894")
            val file = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
            Files.copy(
                file.toPath(),
                Paths.get("${System.getProperty("user.dir")}/build/reports/${file.name}")
            )
            driver.findElement(By.id("allow")).click()
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS)
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
            val pinCode = driver.findElement(By.xpath("/html/body/div[2]/div/p/kbd/code")).text
            TwitterAPI.oauthApi.accessToken(token, pinCode).dataOrNull().let {
                assertNotNull(it)
            }
        }
    }
}
