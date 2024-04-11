package task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;
class Product 
{
    private String name;
    private String description;
    public Product(String name, String description) 
    {
        this.name = name;
        this.description = description;
    }
	public String getName() 
	{
		return name;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description) 
	{
		this.description = description;
	}  
}

class Category 
{
    private String name;
    private Map<String, Product> products;
    public List<Product> getAllProducts() 
    {
        return new ArrayList<>(products.values());
    }
    public String getName() 
    {
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public Map<String, Product> getProducts() 
	{
		return products;
	}
	public void setProducts(Map<String, Product> products) 
	{
		this.products = products;
	}
	public Category(String name) 
	{
        this.name = name;
        this.products = new HashMap<>();
    }
    public void addProduct(Product product) 
    {
        products.put(product.getName(), product);
    }
    public Product getProduct(String productName) 
    {
        return products.get(productName);
    }
}

class ProductDatabase 
{
    private Map<String, Category> categories;
    public ProductDatabase()
    {
        this.categories = new HashMap<>();
    }
    public void addCategory(Category category) 
    {
        categories.put(category.getName(), category);
    }
    public Category getCategory(String categoryName) 
    {
        return categories.get(categoryName);
    }
    public Product searchProduct(String query) 
    {
        for (Category category : categories.values()) 
        {
            for (Product product : category.getAllProducts()) 
            {
                if (product.getName().toLowerCase().contains(query.toLowerCase()) || product.getDescription().toLowerCase().contains(query.toLowerCase())) 
                {
                    return product;
                }
            }
        }
        return null; 
    }
}
public class ProductConfiguration 
{
    public static void main(String[] args) 
    {
       
        ProductDatabase database = new ProductDatabase();
        Category electronics = new Category("Electronics");
        Category books = new Category("Books");
        electronics.addProduct(new Product("Laptop", "Powerful laptop with high performance."));
        electronics.addProduct(new Product("Smartphone", "Latest smartphone with advanced features."));
        books.addProduct(new Product("Java Programming", "Comprehensive guide to Java programming language."));
        books.addProduct(new Product("The Alchemist", "A novel by Paulo Coelho."));
        database.addCategory(electronics);
        database.addCategory(books);
        Product foundProduct = database.searchProduct("laptop");
        if (foundProduct != null) 
        {
            System.out.println("Product found: " + foundProduct.getName());
        } else 
        {
            System.out.println("Product not found.");
        }
    }
}

