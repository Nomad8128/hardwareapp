package hr.tvz.pilizota.hardwareapp.hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand hardwareCommand);

    void deleteByCode(String code);

    Optional<HardwareDTO> update(String code, HardwareCommand updatedHardwareCommand);
}
