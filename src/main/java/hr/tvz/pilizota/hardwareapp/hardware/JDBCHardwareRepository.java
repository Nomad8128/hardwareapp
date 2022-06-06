package hr.tvz.pilizota.hardwareapp.hardware;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JDBCHardwareRepository implements HardwareRepository, Serializable {
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JDBCHardwareRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    private static final String SELECT_ALL =
            "SELECT id, product_name, code, price, product_type, amount FROM hardware";

    @Override
    public List<Hardware> findAll() {return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            System.out.println(hardware.toString());
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }
    @Override
    public void deleteByCode(final String code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getString("product_name"),
                rs.getString("code"),
                rs.getBigDecimal("price"),
                Hardware.Type.valueOf(rs.getString("product_type")),
                rs.getInt("amount")
        );
    }
    private long saveHardwareDetails(Hardware hardware){
        Map<String, Object> values = new HashMap<>();

        values.put("code",hardware.getCode());
        values.put("product_name",hardware.getName());
        values.put("product_type",hardware.getType());
        values.put("amount",hardware.getAmount());
        values.put("price",hardware.getPrice());

        return inserter.executeAndReturnKey(values).longValue();
    }
    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        return Optional.empty();
    }
}
