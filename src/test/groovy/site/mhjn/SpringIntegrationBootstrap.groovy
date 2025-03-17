package site.mhjn

import jakarta.annotation.Resource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.context.WebApplicationContext
import site.mhjn.demo.DemoApplication
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest(classes = [DemoApplication])
abstract class SpringIntegrationBootstrap extends Specification {

    WebApplicationContext applicationContext

    @Resource
    def setWebApplicationContext(WebApplicationContext applicationContext) {
        this.applicationContext = applicationContext
    }
}
