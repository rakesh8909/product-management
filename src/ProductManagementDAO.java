import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductManagementDAO {



    // get all products
    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<Product>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("prod_id"), resultSet.getString("prod_name"), resultSet.getInt("prod_price"));
                productsList.add(product);
            }
            DBConnection.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsList;
    }

    //get a product by id
    public Product getProductByID(String productID) {
        Product product = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE prod_id = ?");
            preparedStatement.setString(1, productID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = new Product(resultSet.getString("prod_id"), resultSet.getString(("prod_name")), resultSet.getInt("prod_price"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    // add a product
    public int addProduct(Product product) {
        int status = 0;
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products VALUES(?,?,?)");
            preparedStatement.setString(1,product.getProductId());
            preparedStatement.setString(2,product.getProductName());
            preparedStatement.setInt(3,product.getProductPrice());
            status = preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // update a product
    public int updateProduct(Product product) {
        int status = 0;
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET prod_name=?, prod_price=? WHERE prod_id=?");
            preparedStatement.setString(3,product.getProductId());
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setInt(2,product.getProductPrice());
            status = preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    //deltes product already in the table
    public int deleteProduct(String productId)
    {
        int status = 0;
        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products where prod_id = ?");
            ps.setString(1, productId);
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }


}
