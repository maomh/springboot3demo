package site.mhjn.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.mhjn.demo.response.Result;
import site.mhjn.demo.controller.dto.CustomerItemDTO;
import site.mhjn.demo.entity.Activity;
import site.mhjn.demo.entity.ActivityCustomerType;
import site.mhjn.demo.repository.ActivityRepository;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    ActivityRepository activityRepository;

    @Resource
    EntityManager entityManager;

    @GetMapping("/hello")
    public Result hello(@Valid @RequestBody CustomerItemDTO customerItemDTO) throws JsonProcessingException {
        log.info("dto.tostring - \n\n{}\n", customerItemDTO);
        log.info("dto.tojson - \n{}\n", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerItemDTO));

        Activity activity = new Activity();
        activity.setName(customerItemDTO.getName());

        ActivityCustomerType activityCustomerType = new ActivityCustomerType();
        activityCustomerType.setCustomerType(customerItemDTO.getCustomerType());
        activityCustomerType.setCustomerLevels(Set.copyOf(customerItemDTO.getCustomerLevels()));


        activityCustomerType.setActivity(activity);
        activity.getActivityCustomerTypes().add(activityCustomerType);

        activityRepository.save(activity);

        return Result.success(activity);
    }
}
