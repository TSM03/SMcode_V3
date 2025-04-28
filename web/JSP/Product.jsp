<%-- 
    Document   : Product.jsp
    Created on : Apr 11, 2025, 12:05:06 AM
    Author     : tsm11
--%>

<%-- 
    Document   : Product
    Created on : 14 Mar 2025, 11:45:45
    Author     : yapji
--%>

<%@ page import="java.util.List, model.Product" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products != null) {
        for (Product p : products) {
%>
            <p>Product Name: <%= p.getName() %></p>
            <p>Price: <%= p.getPrice() %></p>
<%
        }
    } else {
%>
        <p>No products available.</p>
<%
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../CSS/ProductCSS.css">
        <title>Product</title>
    </head>
    <body> 
        
        <div class="outer-container">
            <div class="inner-container">
                
                <section class="products">
  <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyCleanser.jpg" alt="Cleanser">
      <figcaption>Glowy Cleanser</figcaption>
    </figure>
    <h2>Glowy Cleanser</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>

  <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyToner.jpg" alt="Toner">
      <figcaption>Glowy Toner</figcaption>
    </figure>
    <h2>Glowy Toner</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>

  <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowySunscreen.jpg" alt="Sunscreen">
      <figcaption>Glowy Sunscreen</figcaption>
    </figure>
    <h2>Glowy Sunscreen</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>

  <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyVitaminC.jpg" alt="Vitamin C">
      <figcaption>Glowy Vitamin C</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>
            
    <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyMoisturizer.jpg" alt="Moisturizer">
      <figcaption>Glowy Moistuirzer</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article> 
                    
                     <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyAHA.png" alt="Moisturizer">
      <figcaption>Glowy Moistuirzer</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>        
                    
                     <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyEyeCream.png" alt="Moisturizer">
      <figcaption>Glowy Moistuirzer</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>        
                    
                     <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyRetinol.png" alt="Moisturizer">
      <figcaption>Glowy Moistuirzer</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>        
                    
    <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyMoisturizer.jpg" alt="Moisturizer">
      <figcaption>Glowy Moistuirzer</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>        
                    
     <article class="product-item">
    <figure>
      <img class="product-image" src="../ProductImages/GlowyMoisturizer.jpg" alt="Moisturizer">
      <figcaption>Glowy Moistuirzer</figcaption>
    </figure>
    <h2>Glowy Vitamin C</h2>
    <p class="price">RM39.99</p>
    <p>Random description</p>
    <p><button>Add to cart</button></p>
  </article>                        
                    
</section>
                
            </div>         
           
        </div>
       

        
        
        
        
    </body>
</html>

