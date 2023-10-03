import React, {useState} from 'react'
import LocationOnIcon from '@mui/icons-material/LocationOn';
import FavoriteIcon from '@mui/icons-material/Favorite';
import StarIcon from '@mui/icons-material/Star';
import './styles.css'
import { Link } from 'react-router-dom';
import WifiIcon from '@mui/icons-material/Wifi';
import PoolIcon from '@mui/icons-material/Pool';

export function RecomendacoeCard({  
        id, 
        image, 
        type ,
         title, 
         puntaje,
        distancia,
        mapLink, 
        comment, 
        description, 
        alt, 
        }) {

            const [showMore, setShowMore] = useState(false);

            const stars = [<StarIcon />, <StarIcon />, <StarIcon />, <StarIcon />,]

            const facilities = [<WifiIcon/>, <PoolIcon /> ]
          
    return (
   
      <div className='card-container'>
          <div className='image-wrapper'>
              <FavoriteIcon />
              <img className='recomendacoe-image' src = {image} alt = {alt} />
          </div>
  
          <div className='recomendacao-wrapper'>

            <div className='header-wrapper'>

                <div className='categoria-recomendacoe'>

                        <div className='stars-wrapper'>

                                 <h4 className = 'categoria-title'>{type}</h4>  {stars?.map((star, index) => <span className='stars' key = {index}>{star}</span> )}

                        </div>

                    <h4 className='recomendacoe-title'> {title} </h4>
                    
                </div> 

                <div className='puntaje-comment-wrapper'>
 
                    <div className='card-puntaje'>{puntaje}</div>
                    <p className='card-comment'>{comment}</p>
                    
                </div> 

            </div>

            <div className='location'>

             <LocationOnIcon  />
             <span className='distancia'>{distancia} - <a href={mapLink} target="_blank">MOSTRAR NO MAPA</a></span>

            </div>

            <div className='facilities'>

                {facilities?.map((item, index) => <div key = {index}>{item}</div>)}

            </div>
            
            <div className='description'>
               
                <p>
                {showMore ? description : `${description.substring(0, 40)}`}
                <span className="show-more-btn" onClick={() => setShowMore(!showMore)}>
                {showMore ? "Mostra menos" : "...Mostra mais"}
                </span>
                </p>
               
          
               
                <Link to ={`/detaile-produto/${id}`}><button className='link-btn'>ver mais</button></Link>
            </div>

            
          </div>
      </div>
  
    )
  }

