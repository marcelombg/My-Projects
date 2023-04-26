import React from 'react'
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import {Link } from 'react-router-dom'
import './styleReservaSucesso.css'

export  function ReservaSucesso({message, link}) {
  return (
    <div className='reservaSucesso-container'>
        <div className='reservaSucesso-card'>
            <CheckCircleOutlineIcon fontSize='large'/>
            <h3 className = "reservaSucesso-title1" >Muito obrigado!</h3>
            <h4 className = "reservaSucesso-title2">{message}</h4>
            <Link to = {link} className='reservaSucesso-btn' ><button className='reservaSucesso-btn'>ok</button></Link>
        </div>
    </div>
  )
}
