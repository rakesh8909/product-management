import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ProductManagementDAO dao = new ProductManagementDAO();

    public static void main(String[] args) throws Exception {
        DBConnection.ensureProductTableExists();
        String input= "";

        do {
            System.out.println("A. View Products");
            System.out.println("B. Add Product");
            System.out.println("C. Update Product");
            System.out.println("D. Delete Product");
            System.out.println("E. Search Product");
            System.out.println("F. Exit");
            System.out.println("===========================================");
            System.out.print("Enter an  :");
            input = sc.nextLine();

            System.out.println("\n");

            switch(input.toUpperCase())
            {
                case "A":
                    viewProducts();
                    break;

                case "B":
                    addProduct();
                    break;

                case "C":
                    updateProduct();
                    break;

                case "D":
                    deleteProduct();
                    break;

                case "E":
                    searchProduct();
                    break;

                case "F":
                    System.out.println("******************************THANK YOU********************");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Option! Please enter again");
                    break;
            }
        }while (!input.equals("F"));
    }
    public static void viewProducts()
    {
        System.out.println("-----------------------------------------------");


        List<Product> productList = dao.getAllProducts();

        if(productList.isEmpty()){
            System.out.println("No Products Found");
        }else {
            for(Product product: productList)
            {
                displayProduct(product);
            }
        }



        System.out.println("-----------------------------------------------");
        System.out.println("\n");

    }

    public static void addProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = sc.nextLine();
        //sc.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product Name:");
        System.out.println("------------------------------------------------");
        String productName = sc.nextLine();
        //sc.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product Price:");
        System.out.println("------------------------------------------------");
        int productPrice = Integer.parseInt(sc.nextLine());
        //sc.nextLine();

        Product product = new Product(productId, productName,productPrice);
        int status = dao.addProduct(product);
        if(status ==1 )
        {
            System.out.println("Product added successfully");
        }
        else
        {
            System.out.println("ERROR while adding product");
        }
        System.out.println("\n");
    }


    public static void updateProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = sc.nextLine();
        //sc.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Product Name:");
        System.out.println("------------------------------------------------");
        String productName = sc.nextLine();
        //sc.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Product Price:");
        System.out.println("------------------------------------------------");
        int productPrice = Integer.parseInt(sc.nextLine());
        //sc.nextLine();

        Product product = new Product(productId, productName,productPrice);
        int status = dao.updateProduct(product);
        if(status ==1 )
        {
            System.out.println("Product updated successfully");
        }
        else
        {
            System.out.println("ERROR while updating product");
        }
        System.out.println("\n");

    }

    public static void deleteProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = sc.nextLine();
        //sc.nextLine();
        int status = dao.deleteProduct(productId);
        if(status == 1 )
        {
            System.out.println("Product deleted successfully");
        }
        else
        {
            System.out.println("ERROR while deleting product");
        }
        System.out.println("\n");

    }


    public static void searchProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = sc.nextLine();
        Product product = dao.getProductByID(productId);
        System.out.println("------------------------------------------------");
        displayProduct(product);
        System.out.println("------------------------------------------------");
        System.out.println("\n");
    }

    public static void displayProduct(Product product)
    {
        System.out.println("Product ID: "+product.getProductId());
        System.out.println("Product Name: "+product.getProductName());
        System.out.println("Product Price: "+product.getProductPrice());
        System.out.println("\n");
    }
}