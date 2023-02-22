package com.shopprod.model;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ShopProdJDBCDAO implements ShopProdDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		// =====================
//		塞圖片
//		File imgFile = new File("C:\\Users\\Tibame_T14\\Pictures\\Starbucks-001.png");
//		FileInputStream fis = new FileInputStream(imgFile);
//		
//		ShopProdJDBCDAO dao = new ShopProdJDBCDAO();
//		ShopProdVO insertTest = new ShopProdVO();
//		insertTest.setProductId(8);
//		insertTest.setProductTypeId(1);
//		insertTest.setProposalId(1);
//		insertTest.setProductDescription("123");
//		insertTest.setProductPicture(fis);
//		insertTest.setProductName("123");
//		insertTest.setProductStatus(1);
//		dao.insert(insertTest);
		// =====================

//		
//		List<ShopProdVO> getAllList = new ShopProdJDBCDAO().getAll();
//		
//		for(ShopProdVO e : getAllList) {
//			System.out.print(e.getProductId() + " ");
//			System.out.print(e.getProductTypeId() + " ");
//			System.out.print(e.getProposalId() + " ");
//			System.out.print(e.getProductDescription() + " ");
//			System.out.print(e.getProductName() + " ");
//			System.out.println(e.getProductStatus() + " ");			
//		}

	}

	// ==============

	private static final String INSERT = "INSERT INTO `shop_product` (`PRODUCT_ID`, `PRODUCT_TYPE_ID`, `PROPOSAL_ID`, `PRODUCT_DESCRIPTION`, `PRODUCT_PICTURE`, `PRODUCT_NAME`, `PRODUCT_STATUS`) VALUES ( ? , ? , ? , ? , ? , ? , ? )";

	@Override
	public void insert(ShopProdVO shopProdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, shopProdVO.getProductId());
			pstmt.setInt(2, shopProdVO.getProductTypeId());
			// 提案案號: 空值=平台方創建的商品, 非空值=提案轉商城之商品
			if (shopProdVO.getProposalId() == null) {
				pstmt.setNull(3, Types.INTEGER);
			} else {
				pstmt.setInt(3, shopProdVO.getProposalId());
			}
			pstmt.setString(4, shopProdVO.getProductDescription());
			pstmt.setBinaryStream(5, shopProdVO.getProductPicture());
			pstmt.setString(6, shopProdVO.getProductName());
			pstmt.setInt(7, shopProdVO.getProductStatus());
			pstmt.executeUpdate();
			System.out.println("Insert完成");

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// ==============
	
private static final String INSERT_GENERAL_SHOP_PRODUCT = "INSERT INTO `shop_product` (`PRODUCT_TYPE_ID`, `PRODUCT_DESCRIPTION`, `PRODUCT_PICTURE`, `PRODUCT_NAME`, `PRODUCT_STATUS`) VALUES ( ? , ? , ? , ? , ? )";
	
	@Override
	public void insertGeneralShopProduct(ShopProdVO shopProdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_GENERAL_SHOP_PRODUCT);
			pstmt.setInt(1, shopProdVO.getProductTypeId());
			pstmt.setString(2, shopProdVO.getProductDescription());
			// 提案案號: 空值=平台方創建的商品, 非空值=提案轉商城之商品
			pstmt.setBinaryStream(3, shopProdVO.getProductPicture());
			pstmt.setString(4, shopProdVO.getProductName());
			pstmt.setInt(5, 1);
			
			pstmt.executeUpdate();
			System.out.println("Insert完成");
			
		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	

	private static final String UPDATE = "UPDATE `shop_product` SET `PRODUCT_TYPE_ID` = ? , `PROPOSAL_ID` = ? , `PRODUCT_DESCRIPTION` = ? , `PRODUCT_PICTURE` = ? , `PRODUCT_NAME` = ? , `PRODUCT_STATUS` = ? WHERE `PRODUCT_ID` = ? ";

	@Override
	public void update(ShopProdVO shopProdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, shopProdVO.getProductTypeId());
			pstmt.setInt(1, shopProdVO.getProposalId());
			pstmt.setString(1, shopProdVO.getProductDescription());
			pstmt.setBinaryStream(1, shopProdVO.getProductPicture());
			pstmt.setString(1, shopProdVO.getProductName());
			pstmt.setInt(1, shopProdVO.getProductStatus());
			pstmt.setInt(1, shopProdVO.getProductId());
			pstmt.executeUpdate();
			System.out.println("更新完成");

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// ==============

	private static final String DELETE = "DELETE FROM `shop_product` WHERE PRODUCT_ID = ?";

	@Override
	public void delete(Integer productId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();
			System.out.println("刪除完成");

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// ==============

	private static final String GET_ONE = "SELECT `PRODUCT_ID`, `PRODUCT_TYPE_ID`, `PROPOSAL_ID`, `PRODUCT_DESCRIPTION`, `PRODUCT_PICTURE`, `PRODUCT_NAME`, `PRODUCT_STATUS` FROM `shop_product` WHERE `PRODUCT_ID` = ?";

	@Override
	public ShopProdVO findByPrimaryKey(Integer product_id) {

		ShopProdVO shopProdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, product_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopProdVO = new ShopProdVO();
				shopProdVO.setProductId(rs.getInt("product_id"));
				shopProdVO.setProductTypeId(rs.getInt("product_type_id"));
				shopProdVO.setProposalId(rs.getInt("proposal_id"));
				shopProdVO.setProductDescription(rs.getString("product_description"));
				shopProdVO.setProductPicture(rs.getBinaryStream("product_picture"));
				shopProdVO.setProductName(rs.getString("product_name"));
				shopProdVO.setProductStatus(rs.getInt("product_status"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return shopProdVO;
	}

	// ==============

	private static final String GET_ALL = "SELECT `PRODUCT_ID`, `PRODUCT_TYPE_ID`, `PROPOSAL_ID`, `PRODUCT_DESCRIPTION`, `PRODUCT_PICTURE`, `PRODUCT_NAME`, `PRODUCT_STATUS` FROM `SHOP_PRODUCT` ORDER BY `PRODUCT_ID` ";

	@Override
	public List<ShopProdVO> getAll() {

		List<ShopProdVO> list = new ArrayList<ShopProdVO>();
		ShopProdVO shopProdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				shopProdVO = new ShopProdVO();
				shopProdVO.setProductId(rs.getInt("product_id"));
				shopProdVO.setProductTypeId(rs.getInt("product_type_id"));
				shopProdVO.setProposalId(rs.getInt("proposal_id"));
				shopProdVO.setProductDescription(rs.getString("product_description"));
				shopProdVO.setProductPicture(rs.getBinaryStream("product_picture"));
//				shopProdVO.setProductPictureString(rs.getString("product_picture"));
				shopProdVO.setProductName(rs.getString("product_name"));
				shopProdVO.setProductStatus(rs.getInt("product_status"));
				list.add(shopProdVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	// ***************拿特定類別的商品findByType(類似getall寫法)***************//

	private static final String SELECT_PRODUCT_TYPE = "SELECT * FROM mumu_new.shop_product where PRODUCT_TYPE_ID = ?";

	@Override
	public List<ShopProdVO> findByType(Integer productTypeId) {
		List<ShopProdVO> list = new ArrayList<ShopProdVO>();

		ShopProdVO shopProdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_PRODUCT_TYPE);

			pstmt.setInt(1, productTypeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO
				shopProdVO = new ShopProdVO();
				shopProdVO.setProductId(rs.getInt("product_id"));
				shopProdVO.setProductTypeId(rs.getInt("product_type_id"));
				shopProdVO.setProposalId(rs.getInt("proposal_id"));
				shopProdVO.setProductDescription(rs.getString("product_description"));
				shopProdVO.setProductPicture(rs.getBinaryStream("product_picture"));
				shopProdVO.setProductName(rs.getString("product_name"));
				shopProdVO.setProductStatus(rs.getInt("product_status"));

				list.add(shopProdVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

// ****************************模糊查詢商品名****************************//	
	private static final String SELECT_PRODUCT_NAME = "Select * FROM shop_product Where PRODUCT_NAME like ? ";
	
	@Override
	public List<ShopProdVO> selectKeywordProductName(String productName) {
	List<ShopProdVO> list = new ArrayList<ShopProdVO>();
		ShopProdVO shopProdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_PRODUCT_NAME);
			System.out.println(productName);
//			pstmt.setString(1,"%"+productName+"%");
			pstmt.setString(1,"%"+productName+"%");			
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				shopProdVO = new ShopProdVO();
				shopProdVO.setProductId(rs.getInt("product_id"));
				shopProdVO.setProductTypeId(rs.getInt("product_type_id"));
				shopProdVO.setProposalId(rs.getInt("proposal_id"));
				shopProdVO.setProductDescription(rs.getString("product_description"));
				shopProdVO.setProductPicture(rs.getBinaryStream("product_picture"));
				shopProdVO.setProductName(rs.getString("product_name"));
				shopProdVO.setProductStatus(rs.getInt("product_status"));
				list.add(shopProdVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("無法執行SQL指令, 請檢察SQL語法 " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
	
	
	// **********Join方法:myfavorite表格 join shopProd表格資訊*****************//
		private static final String GET_ALLSHOPPROD_STMT = "SELECT * FROM shop_product where product_Id = ?";
		
			@Override
			public ShopProdVO getTheShopProd(Integer productId) {
				ShopProdVO shopProdVO = new ShopProdVO();
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALLSHOPPROD_STMT);
					pstmt.setInt(1, productId);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						shopProdVO.setProductId(rs.getInt("product_id"));
						shopProdVO.setProductTypeId(rs.getInt("product_type_id"));
						shopProdVO.setProposalId(rs.getInt("proposal_id"));
						shopProdVO.setProductDescription(rs.getString("product_description"));
						shopProdVO.setProductPicture(rs.getBinaryStream("product_picture"));
						shopProdVO.setProductName(rs.getString("product_name"));
						shopProdVO.setProductStatus(rs.getInt("product_status"));
						
					}
					// Handle any SQL errors
				} catch (SQLException se) {
//					throw new RuntimeException("A database error occured. "
//							+ se.getMessage());
					se.printStackTrace();
					// Clean up JDBC resources
				} finally {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException se) {
							se.printStackTrace(System.err);
						}
					}
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException se) {
							se.printStackTrace(System.err);
						}
					}
					if (con != null) {
						try {
							con.close();
						} catch (Exception e) {
							e.printStackTrace(System.err);
						}
					}
				}
				return shopProdVO;
				
			}

	@Override
	public List<ShopProdVO> selectKeywordProductName() {
		// TODO Auto-generated method stub
		return null;
	}


}
