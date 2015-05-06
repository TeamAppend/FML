package prototype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import prototype.PostNummer;

public class PostNummerDataAccess {
  
  private static final String SELECT_ONE = "SELECT bynavn FROM postnummer where postnummer = ?";
  private static final String SELECT_MANY = "SELECT postnummer, bynavn FROM postnummer where Upper(bynavn) LIKE ? or postnummer LIKE ?";
  private static final String INSERT_ONE = "INSERT INTO postnummer (postnummer, bynavn) VALUES(?, ?)";
  private static final String UPDATE_ONE = "UPDATE postnummer SET bynavn = ? where postnummer = ?";
  private static final String DELETE_ONE = "DELETE FROM postnummer where postnummer = ?";

  public PostNummer readPostnummer(DataAccess dataaccess, String postnummer) throws SQLException {
    PreparedStatement statement = null;
    ResultSet resultset = null;
    PostNummer postnr = null;
    try {
      statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
      statement.setString(1, postnummer);
      
      resultset = statement.executeQuery();
      postnr = new PostNummer();
      if (resultset.next()) {
        postnr.setPostnr(postnummer);
        postnr.setBy(resultset.getString("bynavn"));
      } else {
      }
      return postnr;
    } finally {
      if (resultset != null) {
        resultset.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }
  
  public List<PostNummer> listPostnumre(DataAccess dataaccess, String search) throws SQLException {
    PreparedStatement statement = null;
    ResultSet resultset = null;
    try {
      statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);
      statement.setString(1, "%" + search.toUpperCase() + "%");
      statement.setString( 2, "%" + search + "%" );
      resultset = statement.executeQuery();
      List<PostNummer> list = new ArrayList<PostNummer>();
      while (resultset.next()) {
        PostNummer postnr = new PostNummer();
        postnr.setPostnr(resultset.getString("postnummer"));
        postnr.setBy(resultset.getString("bynavn"));
        list.add(postnr);
      }
      return list;
    } finally {
      if (resultset != null) {
        resultset.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }
  
  public void createPostnummer(DataAccess dataaccess, PostNummer postnummer) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
      statement.setString(1, postnummer.getPostnr());
      statement.setString(2, postnummer.getBy());
      statement.executeUpdate();
    } catch (SQLException e) {
      if (e.getSQLState().equalsIgnoreCase("23505")) {
       
      } else {
        throw e;
      }
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }
  
  public void updatePostnummer(DataAccess dataaccess, PostNummer postnummer) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = dataaccess.getConnection().prepareStatement(UPDATE_ONE);
      statement.setString(1, postnummer.getBy());
      statement.setString(2, postnummer.getPostnr());
      int antal = statement.executeUpdate();
      if (antal != 1) {
      }
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

  public void deletePostnummer(DataAccess dataaccess, String postnummer) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);
      statement.setString(1, postnummer);
      int antal = statement.executeUpdate();
      if (antal != 1) {
      }
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

}
