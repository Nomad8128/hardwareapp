package hr.tvz.pilizota.hardwareapp.hardware;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.*;

@Repository
public class MockHardwareRepository implements HardwareRepository{

    private final List<Hardware> MOCKED_HARDWARE = new ArrayList<> (Arrays.asList(
            new Hardware("Hardware1","0123456789", new BigDecimal(9000), Hardware.Type.valueOf("CPU"),2),
            new Hardware("Hardware2","5555555555", new BigDecimal(5000), Hardware.Type.valueOf("GPU"),5),
            new Hardware("Hardware3","3333333333", new BigDecimal(2000), Hardware.Type.valueOf("MBO"),3)
    ));


    @Override
    public List<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        System.out.println(hardware);
        if(!MOCKED_HARDWARE.contains(hardware)){
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) { MOCKED_HARDWARE.removeIf(it -> Objects.equals(it.getCode(), code)); }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {

        if(MOCKED_HARDWARE.stream().anyMatch(e -> e.getCode().equals(code))){
            MOCKED_HARDWARE.removeIf(e -> e.getCode().equals(code));
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        }
        else
            return Optional.empty();
    }
}
