
import './App.css';
import { Footer } from './components/footer/Footer';
import Navbar from './components/header/Navbar';
import { Outlet } from "react-router-dom";
import MenuMobile from './components/menu-mobile/MenuMobile';
function App() {
  return (
    <div className="app">
    
    <Navbar />
    <Outlet />
    <Footer />
    </div>
  );
}

export default App;
