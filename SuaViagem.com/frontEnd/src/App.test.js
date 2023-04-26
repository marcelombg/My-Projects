import {render, screen} from '@testing-library/react'
import React from 'react'
import '@testing-library/jest-dom'
import App from './App'

import {BrowserRouter, MemoryRouter, Routes,Route } from 'react-router-dom'

import { LoginProvider} from '../src/components/hooks/useLogin'
import Main from './components/main/Main'
import { CriarConta } from './components/criar-conta/CriarConta'
import { IniciaSessao } from './components/iniciar-sessao/IniciarSessao'
import ErrorPage from './components/ErrorPage'

export const RenderApp = () => (
  <BrowserRouter>
  <LoginProvider>
  <Routes>
  <Route path='/' element={<App/>}  >
    <Route index element={<Main />}  />
    <Route path="/criar-conta" element={<CriarConta />} />
    <Route path="/iniciar-sessao" element={<IniciaSessao />}  />
    <Route path="*" element={<ErrorPage />} />
  </Route>
  </Routes>
  </LoginProvider>
   </BrowserRouter>
)

describe('Inicial page',  () => {

  describe('navBar component', () => {
    
    it('test navbar Criar conta button', () => {
      render(<RenderApp/>)
      expect(screen.getByText(/Criar conta/i)).toBeInTheDocument()
    })
  
    it('test navbar iniciar sessao button', () => {
      render(<RenderApp/>)
      expect(screen.getByText(/Iniciar sessão/i)).toBeInTheDocument()
    })
    
    it('test navbar logo', () => {
      render(<RenderApp/>)
      expect(screen.getByAltText(/travelLogos/i)).toBeInTheDocument()
    })

  })
  describe('Main page', () => {

    it('categoria title component', () => {
      render(<RenderApp/>)
      expect(screen.getByText(/Buscar por tipo de acomodação/i)).toBeInTheDocument()
    })
    
     describe('test categoria card', () => {

      it('card image', async () => {
        render(<RenderApp/>)
        const list  = await screen.findAllByAltText(/hotelImage/i)

        expect(list.length).toBeTruthy()
      })

      it('card Hotéis title', () => {
        render(<RenderApp/>)
        const list  = screen.getByText(/Hoteis/i)

        expect(list).toBeInTheDocument()
      })

      it('card Hostels title', () => {
        render(<RenderApp/>)
        const list  = screen.getByText(/Hostels/i)

        expect(list).toBeInTheDocument()
      })

      it('card Apartamentos title', () => {
        render(<RenderApp/>)
        const list  = screen.getByText(/Apartamentos/i)

        expect(list).toBeInTheDocument()
      })

      it('card Cama e cafe da manha title', () => {
        render(<RenderApp/>)
        const list  = screen.getByText(/Cama e cafe da manha/i)

        expect(list).toBeInTheDocument()
      })

      describe('Recomendações cards', () => {
        
        it('Recomendações cards title', () => {
          render(<RenderApp/>)
          const list  = screen.getByText(/Recomendacoes/i)
  
          expect(list).toBeInTheDocument()
        })

        it('Recomendações cards image', async () => {
          render(<RenderApp/>)
          const list  = await  screen.findAllByAltText(/hotelImage/i)
  
          expect(list.length).toBeTruthy()
        })
      })

      describe('test footer', () => {

        it('Digital Booking header', () => {
          render(<RenderApp/>)

          expect(screen.getByText(/Digital Booking/i)).toBeInTheDocument()
        })
      })
     })

  })


})

