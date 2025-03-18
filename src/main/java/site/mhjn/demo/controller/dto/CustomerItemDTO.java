package site.mhjn.demo.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import site.mhjn.demo.dict.CustomerLevel;
import site.mhjn.demo.dict.CustomerType;

import java.util.List;

@Data
public class CustomerItemDTO {
    @NotEmpty
    private String name;

    @NotNull
    private CustomerType customerType;

    private List<CustomerLevel> customerLevels;
}
