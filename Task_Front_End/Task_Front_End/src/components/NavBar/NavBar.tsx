import { NavLink } from "react-router-dom";
import { BsHouse } from "react-icons/bs";
import DarkModeToggle from "../DarkModeToggle/DarkModeToggle";
import { useContext } from "react";
import { AuthContext } from "../../contexts/AuthContext";

export const NavBar = () => {
  const { isLoggedIn, logout } = useContext(AuthContext);

  return (
    <nav className="flex flex-row justify-between bg-slate-100 text-xl text-slate-900 dark:bg-slate-700 dark:text-white shadow-lg mb-1 p-4">
      <div className="flex flex-row gap-2">
        <NavLink to="/">
          <BsHouse />
        </NavLink>
        {  <NavLink to="/chefs">Chefs</NavLink>}
        {  <NavLink to="/about">About</NavLink>}
      </div>

      <div className="flex flex-row gap-2">
        {!isLoggedIn && <NavLink to="/login">Login</NavLink>}
        {!isLoggedIn && <NavLink to="/register">Register</NavLink>}
       
        { isLoggedIn && <button onClick={()=>{logout()}}>Logout</button>}
        <DarkModeToggle />
      </div>
    </nav>
  );
};
