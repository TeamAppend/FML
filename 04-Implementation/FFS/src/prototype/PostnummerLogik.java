package prototype;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import prototype.PostNummerDataAccess;
import prototype.PostNummer;

public class PostnummerLogik {


  public List<PostNummer> listPostnumre(String search) throws SQLException {
    DataAccess dataaccess = null;
    try {
      dataaccess = new DataAccess();
      PostNummerDataAccess postnummerda = new PostNummerDataAccess();
      List<PostNummer> list =  postnummerda.listPostnumre(dataaccess, search);
      dataaccess.commit();
      return list;
    } catch (Exception e) {
      if (dataaccess != null) {
        dataaccess.rollback();
      }
      throw e;
    } finally {
      if (dataaccess != null) {
        dataaccess.close();
      }
      
    }
  }

  public PostNummer readPostnummer(String postnummer) throws SQLException {
    DataAccess dataacces = null;
    try {
      dataacces = new DataAccess();
      PostNummerDataAccess postnummerda = new PostNummerDataAccess();
      PostNummer postnr = postnummerda.readPostnummer(dataacces, postnummer);
      dataacces.commit();
      return postnr;
    } catch (Exception e) {
      if (dataacces != null) {
        dataacces.rollback();
      }
      throw e;
    } finally {
      if (dataacces != null) {
        dataacces.close();
      }
      
    }
  }

  public void createPostnummer(PostNummer postnummer) throws SQLException {
    DataAccess dataacces = null;
    try {
      dataacces = new DataAccess();
      PostNummerDataAccess postnummerda = new PostNummerDataAccess();
      postnummerda.createPostnummer(dataacces, postnummer);
      dataacces.commit();
    } catch (Exception e) {
      if (dataacces != null) {
        dataacces.rollback();
      }
      throw e;
    } finally {
      if (dataacces != null) {
        dataacces.close();
      }
    }
  }

  public void updatepostnummer(PostNummer postnummer) throws SQLException {
    DataAccess dataacces = null;
    try {
      dataacces = new DataAccess();
      PostNummerDataAccess postnummerda = new PostNummerDataAccess();
      
      postnummerda.updatePostnummer(dataacces, postnummer);
      dataacces.commit();
    } catch (Exception e) {
      if (dataacces != null) {
        dataacces.rollback();
      }
      throw e;
    } finally {
      if (dataacces != null) {
        dataacces.close();
      }
    }
  }

  public void deletePostnummer(String postnummer) throws SQLException {
    DataAccess dataacces = null;
    try {
      dataacces = new DataAccess();
      PostNummerDataAccess postnummerda = new PostNummerDataAccess();
      postnummerda.deletePostnummer(dataacces, postnummer);
      dataacces.commit();
    } catch (Exception e) {
      if (dataacces != null) {
        dataacces.rollback();
      }
      throw e;
    } finally {
      if (dataacces != null) {
        dataacces.close();
      }
    }
  }

}