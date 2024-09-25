import { useEffect, useState } from "react";
import CardProduct from "./CardProduct";
import axios from "axios";
import { Container } from "react-bootstrap";

// Define the Product type
interface Product {
  id: number;
  name: string;
  image: string;
  description: string;
  price: number;
}

function Products() {
  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get<Product[]>("http://localhost:8080/product");
        setProducts(response.data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };
    fetchProducts();
  }, []);

  return (
    <Container className="d-flex flex-wrap mt-5">
      {/* Ensure CardProduct is expecting an array of products */}
      {products.map((product) => (
        <CardProduct key={product.id} product={product} />
      ))}
    </Container>
  );
}

export default Products;
