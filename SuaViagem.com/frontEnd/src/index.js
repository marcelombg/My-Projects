import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Routes,Route } from "react-router-dom"
import Main from './components/main/Main';
import { IniciaSessao } from './components/iniciar-sessao/IniciarSessao';
import ErrorPage from './components/ErrorPage';
import { LoginProvider} from './components/hooks/useLogin'
import { ShowProvider} from './components/hooks/useShow'
import { CriarConta } from './components/criar-conta/CriarConta';
import DetaleProduto from './components/detale-produto/DetaleProduto';
import { FilterProvider } from './components/hooks/useFilterCategoria'
import { Reserva } from './components/detale-produto-reserva/Reserva';
import { TokenProvider } from './components/hooks/useToken';
import { DatasProvider } from './components/hooks/useDatas';
import { HotelFilterCidadeProvider } from './components/hooks/useHotelFilterCidade';
import Administracao from './paginas/administracao/Administracao';
import { UserReservas} from "./components/userReservas/userReservas"



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
    <LoginProvider>
      <ShowProvider>
        <FilterProvider>
          <TokenProvider>
          <DatasProvider>
            <HotelFilterCidadeProvider>
    <Routes>
    <Route path='/' element={<App/>}  >
      <Route index element={<Main />}  />
      <Route path="/criar-conta" element={<CriarConta />} />
      <Route path="/iniciar-sessao" element={<IniciaSessao />}  />
      <Route path="/detaile-produto/:id" element={<DetaleProduto />}  />
      <Route path="detaile-produto/:id/reserva" element={<Reserva />}  />
      <Route path="/administracao" element={<Administracao />}  />
      <Route path="/:idUser/reservas" element={<UserReservas />} />
      <Route path="*" element={<ErrorPage />} />
    </Route>
</Routes>
</HotelFilterCidadeProvider>
</DatasProvider>
</TokenProvider>
</FilterProvider>
</ShowProvider>
</LoginProvider>
</BrowserRouter>



);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
