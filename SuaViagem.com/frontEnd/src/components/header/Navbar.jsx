import React, { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import MenuIcon from '@mui/icons-material/Menu';
import './styles.css';
import UserAvatar from '../user-avatar/UserAvatar';
import { useLogin } from '../hooks/useLogin';
import MenuMobile from '../menu-mobile/MenuMobile';
import { useToken } from '../hooks/useToken';

export default function Navbar() {

  const tokenLocalStorage = localStorage.getItem('token')

  const location = useLocation();
  const data = localStorage.getItem('userAvata');
  const detalesDoAvatar = JSON.parse(data)

  const navigate = useNavigate();

  const handleGoBack = () => {
    localStorage.setItem('rota','');
    navigate(-1);
  };


  const { nome , sobrenome } = detalesDoAvatar || {}


  const renderCreateAccountButton = () => {
    if (!tokenLocalStorage && location.pathname === '/criar-conta') {
      return <>
        <button className="login-btn" onClick={handleGoBack}>Voltar</button>
      </>;

    } else if (!tokenLocalStorage && location.pathname === '/iniciar-sessao') {
      return <>
        <button className="login-btn" onClick={handleGoBack}>Voltar</button>
      </>;

    } else if (!tokenLocalStorage) {
      return <>
        <Link to="criar-conta"><button className="login-btn">Criar conta</button></Link>
        <Link to="iniciar-sessao"><button className="login-btn">Iniciar sessão</button></Link>
      </>;
    } else if (typeof tokenLocalStorage === 'string'){
      return <> 
      <UserAvatar
      nome={nome}
      sobrenome={sobrenome}
      />
      </>
    } 
   
    
    
  };
  

  return (
    <header className="navbar-header">
      <nav className="navbar">
        <Link to="/">
     
          <span className='navbar-logo-title'>SuaViagem.com</span>
        </Link>
        <MenuIcon className="menu-icon" />

        <div className="navbar-btn">
          {renderCreateAccountButton()}

          {/* {token && 
            // email={localStorage.getItem('email')}
          /> } */}

        </div>
      </nav>
      <div className="menu-icon">
        <MenuMobile />
      </div>
    </header>
  );
}
