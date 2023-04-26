
import React from 'react'
import { useFilterCategoria } from '../hooks/useFilterCategoria'
import './styles.css'

export function CategoriaCard({ image, title, totals, alt }) {

  const { changeFilter } = useFilterCategoria()

  function handleChange(e) {
    changeFilter(e.currentTarget.name)
  }

  return (
    <form className='card-wrapper' onClick={handleChange} name={title}>
      <div >
        <img className='card-image' src={image} alt={alt} />
      </div>
      <div>
        <div className='card-title' > {title} </div>
        <div className='subtitle' > {totals} </div>
      </div>
    </form>
  )
}
