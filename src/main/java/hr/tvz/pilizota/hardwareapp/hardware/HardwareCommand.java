package hr.tvz.pilizota.hardwareapp.hardware;

import javax.validation.constraints.*;
import java.math.BigDecimal;


public class HardwareCommand {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Code must not be empty")
    @Pattern(regexp = "[\\d]{10}", message = "Code must have 10 digits")
    private String code;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Hardware type must be entered")
    private Hardware.Type type;

    @NotNull(message = "Quantity must be entered")
    @Positive(message = "Quantity must be positive")
    private int stock;

    public HardwareCommand(String name, String code, BigDecimal price, Hardware.Type type, int stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Hardware.Type getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }
}
