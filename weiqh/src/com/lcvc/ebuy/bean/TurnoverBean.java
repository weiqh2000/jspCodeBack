

public class TurnoverBean {
  public PageObject getTurnovers(Object page, int pageSize) {
    AdminBean adminBean = new AdminBean();
    ProductTypeBean productTypeBean = new ProductTypeBean();
    List<Turnover> list = new ArrayList<Turnover>();
    int totalRecords = getRecordCount();
    PageObject<Turnover> pageObject = PageUtils.getPageObject(page, pageSize, totalRecords);
    pageObject.setList(list);
    Connection conn = DBHelper.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Turnover turnover = null;
    try {
      String sql = "select * from turnover order by createDate desc limit ?,?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, (pageObject.getCurrentPage() - 1) * pageSize);
      pstmt.setInt(2, pageSize);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        turnover = new Turnover();
        turnover.setCreateDate(rs.getDate("createDate"));
        turnover.setTotalSaleVolume(Float.valueOf(rs.getFloat("totalSaleVolume")));
        list.add(turnover);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBHelper.closeConn(conn, pstmt, rs);
    } 
    return pageObject;
  }
  
  public int getRecordCount() {
    Connection conn = DBHelper.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int count = 0;
    try {
      String sql = "select count(1) from turnover";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next())
        count = rs.getInt(1); 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBHelper.closeConn(conn, pstmt, rs);
    } 
    return count;
  }
}
