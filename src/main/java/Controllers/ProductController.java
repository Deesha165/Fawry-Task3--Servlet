package Controllers;

import Models.Product;
import Models.Store;
import com.google.gson.Gson;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private static final Gson gson = new Gson();
    private Store store=new Store();
    private String message;
    private String jsonResponse;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();

        String name=request.getParameter("name");

        Optional<Product>product =store.searchToProduct(name);


        if(product.isEmpty())
        {
             message="Product with this name doesn't exist in store";
             jsonResponse=gson.toJson(message);
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else {
            jsonResponse=gson.toJson(product.get());
          response.setStatus(HttpServletResponse.SC_OK);
        }
        out.println(jsonResponse);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();

        String name=request.getParameter("name");
        message=store.deleteProduct(name);
        if(!message.equals("Product has been deleted successfully"))
        {
              response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

          jsonResponse=gson.toJson(message);
        out.println(jsonResponse);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out=response.getWriter();

        BufferedReader reader = request.getReader();
        Product product = gson.fromJson(reader, Product.class);
         message=store.addProduct(product);

        if(!message.equals("Product has been added successfully"))
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
         jsonResponse=gson.toJson(message);
        out.println(jsonResponse);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();

        BufferedReader reader = request.getReader();
        Product product = gson.fromJson(reader, Product.class);
        message=store.updateProduct(product);
        if(!message.equals("Product has been updated successfully"))
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        jsonResponse=gson.toJson(message);
        out.println(jsonResponse);
    }

}
