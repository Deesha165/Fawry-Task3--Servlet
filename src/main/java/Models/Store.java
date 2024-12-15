package Models;

import java.util.*;

public class Store {
 private final Set<Product>Products=
         new TreeSet<Product>(Comparator.comparing(Product::getName));

 public String addProduct(Product product)
 {
         String message=validateProductParameters(product);

      if(Products.contains(product))
         return "This product already exists in store";


     if(message.equals("valid"))
         {
             Products.add(product);
             return "Product has been added successfully";
         }
         return message;
 }
 public String updateProduct(Product newProduct)
 {
     String message=validateProductParameters(newProduct);

     if(message.equals("valid"))
     {
         Optional<Product> oldProduct= getProduct(newProduct.getName());

         if(oldProduct.isEmpty()) return "This product doesn't exist in store";

         Products.remove(oldProduct.get());
          Products.add(newProduct);

         return "Product has been updated successfully";
     }
     return  message;
 }
 
public String deleteProduct(String name)
{
   Optional<Product> product= getProduct(name);

   if(product.isEmpty())
       return "This product not exist in store";

   Products.remove(product.get());
   return "Product has been deleted successfully";
}
public  Optional<Product> searchToProduct(String name)
{
    return getProduct(name);
}
private Optional<Product> getProduct(String name)
{
    return Products.stream().filter(product -> product.getName().equals(name)).findFirst();
}

 private String validateProductParameters(Product product)
 {
     String productName=product.getName();
     double productPrice=product.getPrice();

     if(productName.isEmpty())
         return "Product Name mustn't be empty";
     if(productName.length()>100)
         return "Length of product name mustn't exceed 100 character";
     if(productPrice<0)
         return "Product Price must be non negative";
      return "valid";
 }



}
