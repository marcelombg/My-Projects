import React from 'react'
import InstagramIcon from '@mui/icons-material/Instagram';
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import './styles.css'

const date = new Date()

export function Footer() {
  return (
    <footer className='footer'>
      <p className='footer-title'><span>© {date.getFullYear()} </span>SUAVIAGEM.COM</p>
      <div className='footer-social-icon'>
        <FacebookIcon />
        <TwitterIcon />
        <LinkedInIcon />
        <InstagramIcon />
      </div>
    </footer>
  )
}
