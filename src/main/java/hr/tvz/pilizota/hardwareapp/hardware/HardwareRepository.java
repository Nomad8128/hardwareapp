package hr.tvz.pilizota.hardwareapp.hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    void deleteByCode(String code);

    Optional<Hardware> update(String code, Hardware updatedHardware);
}
