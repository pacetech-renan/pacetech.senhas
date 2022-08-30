/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.pacetech.pacetech.senhas.controller;

import br.com.pacetech.pacetech.senhas.model.AtendimentoModel;
import br.com.pacetech.pacetech.senhas.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            //JONATHAN
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
            throw new SQLException("Erro ao tentar inserir o projeto no banco de dados" + e.getMessage(), e);

        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
        
        return 0;
    }

}
