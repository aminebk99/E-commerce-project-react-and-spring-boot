import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Profile from './components/Profile';
import Card from './components/Card';

function App() {
  const authenticated = localStorage.getItem("authenticated");

  return (
    <BrowserRouter>
      <Routes>
        {/* If not authenticated, redirect to login */}
        {!authenticated ? (
          <>
            <Route path="/login" element={<Login />} />
            <Route path="*" element={<Navigate to="/login" />} />
          </>
        ) : (
          <>
            <Route path="/profile" element={<Profile />} />
            <Route path="/card" element={<Card />} />
            {/* Redirect authenticated users from login to profile */}
            <Route path="/login" element={<Navigate to="/profile" />} />
            <Route path="*" element={<Navigate to="/profile" />} />
          </>
        )}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
