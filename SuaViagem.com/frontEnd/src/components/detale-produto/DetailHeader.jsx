import React from 'react'
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import StarIcon from '@mui/icons-material/Star';
import './styleHeader.css'
import { Link } from 'react-router-dom';

export default function DetailHeader({
  type,
  title,
  puntaje,
  comment,
  distancia,
  location,
  country
}) {
  return (
    <div className="detail-header-container" >
      <div className='detail-first-header-wrapper'>
        <div>
          <p className='type-header'>{type}</p>
          <h2 className='title-header'>{title}</h2>
        </div>

        <Link to="/"><ArrowBackIosIcon className='logo-header' /></Link>

      </div>

      <div className='detail-second-header-wrapper'>

        <div className='detail-location'>

          <LocationOnIcon />

          <p className='location-text'>{location}, {country}, {distancia}</p>

        </div>

        <div className='detail-puntaje'>
          <div>
            <span className='nota'>{comment}</span>

            <div className='detail-stars'>
              {/* {
                    stars?.map((star,index) => {
                      <span key = {index} >{star}</span>
                    })
                  } */}
              <StarIcon />
              <StarIcon />
              <StarIcon />
              <StarIcon />
              <StarIcon />
            </div>
          </div>
          <div className='detail'>{puntaje}</div>
        </div>
      </div>
    </div>
  )
}
