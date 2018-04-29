import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

import com.aoe.gebspockreports.GebReportingListener

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'

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

        driver = {new FirefoxDriver() }
    }

    firefoxHeadless {
        atCheckWaiting = 1
        driver = {
            FirefoxOptions o = new FirefoxOptions()
            o.addArguments('headless')
            new FirefoxDriver(o)
        }
    }
}
