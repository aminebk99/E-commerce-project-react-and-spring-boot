import { Navigate, RouteProps } from 'react-router-dom';
import { useAuth } from './AuthContext';

type PrivateRouteProps = RouteProps & {
  element: JSX.Element; // The component to render is passed as the "element" prop.
};

function PrivateRoute({ element, ...rest }: PrivateRouteProps) {
  const { isAuthenticated } = useAuth();

  // Issue: The component should be passed via the "element" prop, not as children.
  // Fix: Render the "element" prop if authenticated, otherwise redirect to login.
  return isAuthenticated ? element : <Navigate to="/login" />;
}

export default PrivateRoute;
