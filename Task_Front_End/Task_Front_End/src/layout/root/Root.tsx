import { Outlet } from "react-router-dom";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import {NavBar} from "../../components/NavBar/NavBar";

const Root = () => {
  return (
    <div className="min-h-screen flex flex-col">
     <NavBar />
      <Header />
      <main className="flex-1">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default Root;