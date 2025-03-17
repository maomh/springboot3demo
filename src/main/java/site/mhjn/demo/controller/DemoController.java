package site.mhjn.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.mhjn.demo.assets.Result;
import site.mhjn.demo.controller.dto.CustomerItemDTO;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/hello")
    public Result hello(@Valid @RequestBody CustomerItemDTO customerItemDTO) throws JsonProcessingException {
        log.info("dto.tostring - {}", customerItemDTO);
        log.info("dto.tojson - {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerItemDTO));
        return Result.success(customerItemDTO);
    }
}
