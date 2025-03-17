package site.mhjn.repository

import jakarta.annotation.Resource
import org.springframework.test.annotation.Rollback
import site.mhjn.SpringIntegrationBootstrap
import site.mhjn.demo.consts.CustomerLevel
import site.mhjn.demo.consts.CustomerType
import site.mhjn.demo.entity.Activity
import site.mhjn.demo.entity.CustomerItem
import site.mhjn.demo.repository.ActivityRepository

import java.time.LocalDateTime

class ActivityRepositoryIT extends SpringIntegrationBootstrap {

    @Resource
    ActivityRepository activityRepository

    @Rollback
    def "save activity"() {
        given:
        def activity = new Activity()

        activity.name = "Test"

        activity.customerItems = [
                new CustomerItem(
                        createTime: LocalDateTime.now(),
                        customerType: CustomerType.CPC,
                        customerLevels: [
                                CustomerLevel.STAR1,
                                CustomerLevel.STAR2,
                                CustomerLevel.STAR3
                        ] as Set
                )
        ] as Set


        when:
        def savedActivity = activityRepository.save(activity)

        then:
        savedActivity.activityId > 0
        println(savedActivity)
    }
}
