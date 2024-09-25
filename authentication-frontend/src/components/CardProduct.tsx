import axios from 'axios';
import { useState } from 'react';
import { Button, Card } from 'react-bootstrap';

function CardProduct({ product }) {
  const [productId, setProductId] = useState(product.id); // Initialize with product.id
  
  const addToCart = async () => {
    try {
      const response = await axios.post(`http://localhost:8080/cart/${productId}`);
      console.log("Product added to cart successfully");
    } catch (error) {
      console.error("Error adding product to cart", error);
    }
  };

  return (
    <Card key={product.id} style={{ width: '18rem', margin: '1rem' }}>
      <Card.Img variant="top" src={product.image} />
      <Card.Body>
        <Card.Title>{product.name}</Card.Title>
        <Card.Text>
          {product.description}
        </Card.Text>
        <Button variant="primary" onClick={addToCart}>Add To Cart</Button>
      </Card.Body>
    </Card>
  );
}

export default CardProduct;
