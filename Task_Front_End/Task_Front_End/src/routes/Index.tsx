import { RouteObject } from "react-router-dom";
import MainPage from "./MainPage";
import Login from "./Login";
import Register from "./Register";
import Chefs from "./Chefs";
import About from "./About";
import Root from "../layout/root/Root";


export const routes: RouteObject[] = [
  {
    path: "/",
    element: <Root />,
    // errorElement: <ErrorPage />,
    children: [
      {
        index: true,
        element: (
          
            <MainPage/>
          
        ),
      },
        {
        path: "/login",
        element: <Login />,
      },
      {
        path: "/register",
        element: <Register />,
      },
      {
        path: "/about",
        element: <About />,
      },
      
      {
        path: "/chefs",
        element:<Chefs/>
        
       
      },
    ],
  },
];