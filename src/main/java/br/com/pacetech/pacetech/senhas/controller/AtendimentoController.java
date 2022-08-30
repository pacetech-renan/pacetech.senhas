/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.pacetech.pacetech.senhas.controller;

import br.com.pacetech.pacetech.senhas.model.AtendimentoModel;
import br.com.pacetech.pacetech.senhas.util.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Renan Miguel
 */
public class AtendimentoController {

    public Integer save(AtendimentoModel atendimentoModel) throws SQLException {

        String sql = "INSERT INTO ATENDIMENTO "
                + "(NOME, DATA, STATUS) "
                + "VALUE (?, ?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = ConnectionFactory.getConnection();

            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 1;

            statement.setString(i++, atendimentoModel.getNome());
            statement.setTimestamp(i++, new java.sql.Timestamp(atendimentoModel.getData().getTime()));
            statement.setInt(i++, atendimentoModel.getStatus());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar inserir o registro no banco de dados" + e.getMessage(), e);

        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

        return 0;
    }

    public void update(AtendimentoModel atendimentoModel) throws SQLException {
        String sql = "UPDATE ATENDIMENTO SET STATUS = ?, ATENDIMENTO = ?"
                + "WHERE ID = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            Integer i = 1;

            statement.setInt(i++, 1);
            statement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
            statement.setInt(i++, atendimentoModel.getId());

            statement.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar atualizar o registro no banco de dados" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public AtendimentoModel getFirst() throws SQLException {

        String sql = "SELECT * FROM ATENDIMENTO WHERE STATUS = 0 order by id asc limit 1";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<AtendimentoModel> atendimentoModels = new ArrayList<>();

        try {

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                AtendimentoModel atendimentoModel = new AtendimentoModel();

                atendimentoModel.setId(resultSet.getInt("ID"));
                atendimentoModel.setNome(resultSet.getString("NOME"));
                atendimentoModel.setData(resultSet.getDate("DATA"));
                atendimentoModel.setStatus(resultSet.getInt("STATUS"));

                atendimentoModels.add(atendimentoModel);

            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar recuperar registro do banco de dados " + e.getMessage(), e);

        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }

        if (atendimentoModels.size() == 0) {
            return null;
        } else {
            return atendimentoModels.get(0);
        }
    }

    public AtendimentoModel getLast() throws SQLException {

        String sql = "SELECT * FROM ATENDIMENTO WHERE STATUS = 0 order by id desc limit 1";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<AtendimentoModel> atendimentoModels = new ArrayList<>();

        try {

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                AtendimentoModel atendimentoModel = new AtendimentoModel();

                atendimentoModel.setId(resultSet.getInt("ID"));
                atendimentoModel.setNome(resultSet.getString("NOME"));
                atendimentoModel.setData(resultSet.getDate("DATA"));
                atendimentoModel.setStatus(resultSet.getInt("STATUS"));

                atendimentoModels.add(atendimentoModel);

            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar recuperar registro do banco de dados " + e.getMessage(), e);

        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }

        if (atendimentoModels.size() == 0) {
            return null;
        } else {
            return atendimentoModels.get(0);
        }

    }

    public Integer getCount() throws SQLException {

        Integer total = 0;

        String sql = "SELECT COUNT(0) AS TOTAL FROM ATENDIMENTO WHERE STATUS = 0";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                total = resultSet.getInt("TOTAL");

            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar recuperar registro do banco de dados " + e.getMessage(), e);

        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }

        return total;

    }

}
