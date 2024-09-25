import axios from 'axios'; // Axios is used to make HTTP requests
import { useEffect, useState } from 'react'; // useEffect and useState are React hooks
import { Container } from 'react-bootstrap'; // Bootstrap component for styling

function Card() {
  // useState hook to store the fetched card data
  const [card, setCard] = useState([]);

  // useEffect hook to fetch data when the component mounts
  useEffect(() => {
    // Function to fetch card data from the API
    const fetchCard = async () => {
      try {
        const response = await axios.get("http://localhost:8080/cart"); // HTTP GET request to fetch card data
        setCard(response.data); // Set the card data in state
      } catch (error) {
        console.error("Error fetching card data:", error); // Log errors if the request fails
      }
    };

    fetchCard(); // Call the fetch function when the component mounts

  }, []); // Empty dependency array to run this effect only once (on mount)

  return (
    <Container>
      <h1>This is the card page</h1>
      <div>
        
        {card.length === 0 ? 
        (<h4 style={{textAlign: "center", marginTop: "50px"}}>No product added in the card</h4>) 
        : (
        card.map((item, index) => (
          <div key={index}>
          <img src={item.image} style={{width: '300px'}} alt={item.name} />
          <div key={index}>{item?.name}</div><br />
          
          </div>
           // Don't forget to return the JSX from the map function
        )))}
      </div>
    </Container>
  );
}

export default Card;
