package site.mhjn

class DemoApplicationIT extends SpringIntegrationBootstrap {

    def "context loads"() {
        expect:
        applicationContext != null
    }
}
