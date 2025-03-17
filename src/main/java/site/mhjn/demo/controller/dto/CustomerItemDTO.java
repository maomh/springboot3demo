package site.mhjn.demo.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import site.mhjn.demo.consts.CustomerType;

@Data
public class CustomerItemDTO {
    @NotEmpty
    private String name;

    @NotNull
//    @JsonDeserialize(using = CustomerType.CustomerTypeJsonDeserializer.class)
//    @JsonSerialize(using = CustomerType.CustomerTypeJsonSerializer.class)
    private CustomerType type;
}
