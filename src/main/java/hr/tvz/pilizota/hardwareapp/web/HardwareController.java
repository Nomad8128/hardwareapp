package hr.tvz.pilizota.hardwareapp.web;

import hr.tvz.pilizota.hardwareapp.hardware.HardwareCommand;
import hr.tvz.pilizota.hardwareapp.hardware.HardwareDTO;
import hr.tvz.pilizota.hardwareapp.hardware.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
    public final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    //@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code){
        return hardwareService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        return hardwareService.save(command)
                .map(
                        HardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(HardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand updatedHardwareCommand){
        return hardwareService.update(code, updatedHardwareCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }


}
