package hr.tvz.pilizota.hardwareapp.review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);
}
