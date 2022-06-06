package hr.tvz.pilizota.hardwareapp.hardware;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HardwareDTO {

    private String code;

    private String name;

    private BigDecimal price;

    public HardwareDTO(String code, String name, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
