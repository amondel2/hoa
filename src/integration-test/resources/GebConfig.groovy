import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

waiting {
    timeout = 2
}

environments {

    // run via “./gradlew -Dgeb.env=chrome iT”
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via “./gradlew -Dgeb.env=chromeHeadless iT”
    chromeHeadless {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            new ChromeDriver(o)
        }
    }

//    // run via “./gradlew -Dgeb.env=firefox iT”
    firefox {
        atCheckWaiting = 1

        driver = {
            FirefoxOptions o = new FirefoxOptions()
            o.addArguments('screenshot')
            new FirefoxDriver()

        }
    }

    firefoxHeadless {
        atCheckWaiting = 1

        driver = {
            FirefoxOptions o = new FirefoxOptions()
            o.addArguments(['screenshot','headless'])
            new FirefoxDriver()

        }
    }
}
