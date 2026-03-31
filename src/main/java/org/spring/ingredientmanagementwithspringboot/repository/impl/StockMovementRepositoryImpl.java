package org.spring.ingredientmanagementwithspringboot.repository.impl;

import org.spring.ingredientmanagementwithspringboot.datasource.Datasource;
import org.spring.ingredientmanagementwithspringboot.entity.Enum.UnitType;
import org.spring.ingredientmanagementwithspringboot.entity.MovementTypeEnum;
import org.spring.ingredientmanagementwithspringboot.entity.StockMovement;
import org.spring.ingredientmanagementwithspringboot.entity.StockValue;
import org.spring.ingredientmanagementwithspringboot.repository.StockMovementRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockMovementRepositoryImpl implements StockMovementRepository {
    private final Datasource datasource;
    public StockMovementRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
    }
    @Override
    public List<StockMovement> findOneByIngredientId(int id) {
        String sql = """
                select id, id_ingredient, quantity,type,unit, creation_datetime
                from stockmovement
                where id_ingredient = ?;
                """;
        try(Connection conn = datasource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            List<StockMovement> stockMovements = new ArrayList<>();
            try(ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    StockMovement stockMovement = new StockMovement();
                    stockMovement.setId(rs.getInt("id"));
                    StockValue stockValue = new StockValue();
                    stockValue.setQuantity(rs.getDouble("quantity"));
                    stockValue.setUnit(UnitType.valueOf(rs.getString("unit")));
                    stockMovement.setValue(stockValue);
                    stockMovement.setType(MovementTypeEnum.valueOf(rs.getString("type")));
                    stockMovement.setCreationDatetime(rs.getTimestamp("creation_datetime").toInstant());
                    stockMovements.add(stockMovement);
                }
            }
            return stockMovements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StockMovement> createStockMovement(int ingId,List<StockMovement> stockMovementList) {
        String sql = """
                insert into stockmovement (id_ingredient, quantity, type, unit, creation_datetime)
                values (?, ?, ?::mouvement_type, ?::unit_type, ?) RETURNING id;
                """;
        try(Connection conn = datasource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            for(StockMovement stockMovement : stockMovementList){
                preparedStatement.setInt(1, ingId);
                preparedStatement.setDouble(2, stockMovement.getValue().getQuantity());
                preparedStatement.setString(3, stockMovement.getType().name());
                preparedStatement.setString(4, stockMovement.getValue().getUnit().name());
                if (stockMovement.getCreationDatetime() == null) {
                    Instant now = Instant.now();
                    stockMovement.setCreationDatetime(now);
                    preparedStatement.setTimestamp(5, java.sql.Timestamp.from(now));
                } else {
                    preparedStatement.setTimestamp(5, java.sql.Timestamp.from(stockMovement.getCreationDatetime()));
                }

                try(ResultSet rs = preparedStatement.executeQuery()) {
                    if(rs.next()) {
                        stockMovement.setId(rs.getInt("id"));
                    }
                }
            }
            return stockMovementList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
