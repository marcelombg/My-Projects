import React, { useState } from 'react'
import InputAdornment from '@mui/material/InputAdornment';
import IconButton from '@mui/material/IconButton';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import OutlinedInput from '@mui/material/OutlinedInput';
import { Link, useNavigate } from 'react-router-dom';
import { useToken } from '../hooks/useToken';
import axios from 'axios'
import { useLogin } from '../hooks/useLogin'
import './style.css';
import Alert from '@mui/material/Alert';

export function IniciaSessao() {

  const [showPassword, setShowPassword] = useState(false);
  const { changeLogin } = useLogin()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errorMessage, setErrorMessage] = useState('');
  const { token, changeToken } = useToken()


  const navigate = useNavigate();

  

  async function handleSubmit(e) {
    e.preventDefault()

    axios.post('http://3.142.238.11:8081/user/authenticate', {
      email: email,
      senha: password
    }).then(response => {

      changeToken(response.data.jwt)
      localStorage.setItem('token', response.data.jwt);

      changeLogin(true);
      if(localStorage.getItem('rota')==='/criar-conta'){
        navigate(-2)
        localStorage.setItem('rota','')
      }else{
       
        navigate(-1)
      
      }

    }).catch(error => {
      setErrorMessage('Infelizmente, você não pôde efetuar login. Por favor, tente novamente mais tarde.');
    });
  };


  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault()
  };


  return (
    <div className='iniciar-session-container'>
      <h1 className='iniciar-title'>Iniciar sessão</h1>
      {
        !token && <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">Para fazer uma reserva você precisa estar logado</Alert>
      }
      {errorMessage && <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">{errorMessage}</Alert>}
      <form className='iniciar-form' onSubmit={handleSubmit} >
        <label htmlFor='email'>Email: </label>
        <input
          className="input"
          required
          id="email"
          type='email'
          size="small"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          autoComplete="off"
        />
        <label htmlFor='senha'>Confirmar senha: </label>
        <OutlinedInput
          className='input'
          required
          id="senha"
          size="small"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          type={showPassword ? 'text' : 'password'}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                aria-label="toggle password visibility"
                onClick={handleClickShowPassword}
                onMouseDown={handleMouseDownPassword}
                edge="end"
              >
                {showPassword ? <Visibility /> : <VisibilityOff />}
              </IconButton>
            </InputAdornment>
          }
          sx={{
            '&:.MuiInputBase-root-MuiOutlinedInput-root:hover': {
              borderColor: 'none'
            },
            '&:.MuiInputBase-root-MuiOutlinedInput-root': {
              borderColor: 'none',
              backgroundColor: 'white'
            },
          }}
        />
        {password === "" && (<span id="component-error-text" >Este campo é obrigatório</span>)}
        <div>
        </div>
        <div className='btn-wrapper'>
          <button className='iniciar-btn' type="submit">Iniciar sessão</button>
          <span className='iniciar-login'>Ainda não tem uma conta? <Link className='login-link' to="/criar-conta">Registre-se</Link></span>
        </div>
      </form>
    </div>
  )
}