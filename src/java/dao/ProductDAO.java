package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;  

public class ProductDAO {
    
    String driverName = "org.apache.derby.jdbc.ClientDriver";
    private static final String host = "jdbc:derby://localhost:1527/Client";
    private static final String user =  "nbuser";
    private static final String password = "nbuser";
     
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT PRODUCT_ID, PRODUCTNAME, CATEGORY, PRICE, STOCK_QUANTITY, IMAGE_URL FROM \"NBUSER\".\"PRODUCTS\"";
        
        try {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
 

            try (Connection conn = DriverManager.getConnection(host, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
            
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("PRODUCT_ID"));
                    product.setName(rs.getString("PRODUCTNAME"));
                    product.setDescription(rs.getString("DESCRIPTION"));
                    product.setCategory(rs.getString("CATEGORY"));
                    product.setPrice(rs.getDouble("PRICE"));
                    product.setStock(rs.getInt("STOCK_QUANTITY"));
                    product.setImageUrl(rs.getString("IMAGE_URL"));  
                  
                    products.add(product);                  }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Derby JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    
    public Product getProductByName(String name) {
    Product product = null;
    
    String sql = "SELECT * FROM NBUSER.PRODUCTS WHERE PRODUCTNAME = ?";
    
    try (Connection conn = DriverManager.getConnection(host, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            product = new Product();
            product.setId(rs.getInt("PRODUCT_ID"));
            product.setName(rs.getString("PRODUCTNAME"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setImageUrl(rs.getString("IMAGE_URL"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return product;
}
    
     
    public Product getProductById(int id) {
    Product product = null;
    try (Connection conn = DriverManager.getConnection(host, user, password)) {
        String query = "SELECT * FROM NBUSER.PRODUCTS WHERE PRODUCT_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
             product = new Product();
            product.setId(rs.getInt("PRODUCT_ID"));
            product.setName(rs.getString("PRODUCTNAME"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setImageUrl(rs.getString("IMAGE_URL"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return product;
}
    public List<Product> getProductsByPromotion(int promoId) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT p.*, promo.DISCOUNT_VALUE " +
                 "FROM NBUSER.PRODUCTS p " +
                 "JOIN NBUSER.PROMOTION_PRODUCT pp ON p.PRODUCT_ID = pp.PRODUCT_ID " +
                 "JOIN NBUSER.PROMOTION promo ON pp.PROMO_ID = promo.PROMOTION_ID " + 
                 "WHERE promo.PROMOTION_ID = ? ";

    /*"SELECT p.* FROM NBUSER.PRODUCTS p " +
             "JOIN NBUSER.PROMOTION_PRODUCT pp ON p.PRODUCT_ID = pp.PRODUCT_ID " +
             "WHERE pp.PROMO_ID = ?";
    */
    try (Connection conn = DriverManager.getConnection(host, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, promoId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("PRODUCT_ID"));
            product.setName(rs.getString("PRODUCTNAME"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setDiscount(rs.getDouble("DISCOUNT_VALUE"));
            product.setImageUrl(rs.getString("IMAGE_URL"));
            products.add(product);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return products;
}
    
    //EXTRACT WHICH PROMOTION IS CURRENTLY ACTIVE BY FILTERING DATE
     public int getActivePromotionId() {
    String sql = "SELECT PROMOTION_ID FROM NBUSER.PROMOTION WHERE CURRENT_DATE BETWEEN START_DATE AND END_DATE AND IS_ACTIVE = TRUE";
    try (Connection conn = DriverManager.getConnection(host, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("PROMOTION_ID");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // No active promotion found
}

     
    public Product searchProductById(int id) {
    Product product = null;
    String sql = "SELECT * FROM NBUSER.PRODUCTS WHERE PRODUCT_ID = ?";
    try (Connection conn = DriverManager.getConnection(host, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            product = extractProductFromResultSet(rs);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return product;
}

public List<Product> searchProductByName(String name) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM NBUSER.PRODUCTS WHERE PRODUCTNAME LIKE ?";
    try (Connection conn = DriverManager.getConnection(host, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + name + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            products.add(extractProductFromResultSet(rs));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return products;
}
       
       
    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
            //THIS IS FOR SEARCH FUNCTION
            Product product = new Product();
           product.setId(rs.getInt("PRODUCT_ID"));
            product.setName(rs.getString("PRODUCTNAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setStock(rs.getInt("STOCK_QUANTITY"));
            product.setImageUrl(rs.getString("IMAGE_URL"));
            
            return product;
}
    
    public List<Product> getProductsPaginated(int offset, int limit) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM NBUSER.PRODUCTS OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try (Connection conn = DriverManager.getConnection(host, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, offset);
        stmt.setInt(2, limit);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("PRODUCT_ID"));
            product.setName(rs.getString("PRODUCTNAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setStock(rs.getInt("STOCK_QUANTITY"));
            product.setImageUrl(rs.getString("IMAGE_URL"));
            products.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return products;
}
    
    public int getTotalProductCount() {
    String sql = "SELECT COUNT(*) FROM NBUSER.PRODUCTS";
    try (Connection conn = DriverManager.getConnection(host, user, password);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    
    
    public static void main(String[] args) {
    ProductDAO dao = new ProductDAO();

    // Test search by ID
    Product productById = dao.searchProductById(1); // Change 101 to a valid ID
    if (productById != null) {
        System.out.println("Found by ID: " + productById.getId() + " - " + productById.getName());
    } else {
        System.out.println("Product not found by ID.");
    }

    // Test search by Name
    List<Product> productsByName = dao.searchProductByName("G"); // Use part of a product name
    for (Product p : productsByName) {
        System.out.println("Found by Name: " + p.getId() + " - " + p.getName());
    }

    if (productsByName.isEmpty()) {
        System.out.println("No products found by name.");
    }
}
        }
