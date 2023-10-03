import React from 'react'
import { useShow } from '../hooks/useShow'
import './carouselImage.css'
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from 'react-responsive-carousel';

export default function CarouselImage({ image }) {

  const { changeShow } = useShow()
  const [toggle, setToggle] = React.useState(false)

  function handleClose() {
    setToggle(true)
    changeShow()
  }

  return (
    <div className={`sliderContainer ${toggle ? 'hide' : null}`}>

      <div className='cardCarousel'>
        <div className='close' onClick={handleClose}>X</div>
        <Carousel>
          {image?.map((img, index) =>
            <div key={index}><img src={img} alt={index} /></div>
          )}
        </Carousel>
      </div>
    </div>
  )
}
