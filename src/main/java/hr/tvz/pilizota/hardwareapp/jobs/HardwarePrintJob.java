package hr.tvz.pilizota.hardwareapp.jobs;

import hr.tvz.pilizota.hardwareapp.hardware.Hardware;
import hr.tvz.pilizota.hardwareapp.hardware.HardwareRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Set;

public class HardwarePrintJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(HardwarePrintJob.class);

    private final HardwareRepository hardwareRepository;

    public HardwarePrintJob(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        final List<Hardware> hardwareList = hardwareRepository.findAll();

        if(!hardwareList.isEmpty()){
            log.info("Ovo su trenutno dostupni hardveri");
            log.info("------------------------------");
            hardwareList.stream().filter(h -> h.getAmount() > 0).forEach(
                    hardware -> log.info(hardware.toString())
            );
            log.info("------------------------------");
        } else {
            log.info("Trenutno nema dostupnih hardvera");
        }
    }
}
