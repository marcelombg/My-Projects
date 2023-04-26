import React, { useRef } from 'react'
import { RecomendacoeCard } from '../recomendacoe/RecomendacoeCard'
import WifiIcon from '@mui/icons-material/Wifi';
import PoolIcon from '@mui/icons-material/Pool';
import { useHotelFilterCidade } from '../hooks/useHotelFilterCidade';
import './styles.css'

export function RecomendacoesCards() {
  const [produto, setProduto] = React.useState([])
  const [newProduto, setNewPorduto] = React.useState([])
  const [filter, setFilter] = React.useState('')
  const [categoria, setCategoria] = React.useState([])
  const totals = "807.105 hotéis"
  const { hotelPorCidade, changeHotelPorCidade } = useHotelFilterCidade()

  React.useEffect(() => {
    async function fetchData() {

      const response = await fetch(`http://3.142.238.11:8081/category/findAll/`)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json()
      setCategoria(data)
    }
    fetchData()
  }, []);

  React.useEffect(() => {
    async function fetchData() {

      const response = await fetch(`http://3.142.238.11:8081/product/findAll/`)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json()
      setProduto(data)
      setNewPorduto(data)
    }
    fetchData()
  }, []);

  const ref = useRef(null)

  React.useEffect(() => {
    document.addEventListener("click", handleClickOutside, true);
    return () => {
      document.removeEventListener("click", handleClickOutside, true)
    };
  }, [])

  const handleClickOutside = (event) => {

    if (ref.current && !ref.current.contains(event.target)) {
      setFilter("")
    }
  };

  React.useEffect(() => {
    if (filter === "") {
      setNewPorduto(produto)
      return;
    }
    const result = produto.filter((fil) => fil.categoriasEntity.descricao === filter)
    changeHotelPorCidade(null)
    setNewPorduto(result)

  }, [filter, produto]);

  return (
    <div >
      <div className='container-categoria'>
        <h2 className='title'> Buscar por tipo de acomodação</h2>
        <div className='cards-wrapper'>
          {
            categoria.map(item => {
              const { id_categorias, descricao, url_imagem } = item
              return (
                <form ref={ref} key={id_categorias} className='card-wrapper' onClick={(e) => setFilter(e.currentTarget.name)} name={descricao}>
                  <div >
                    <img className='card-image' src={url_imagem} alt={descricao} />
                  </div>
                  <div>
                    <div className='card-title' > {descricao} </div>
                    <div className='subtitle' > {totals} </div>
                  </div>
                </form>
              )
            })
          }

        </div>
      </div>
      <div className='recomendacaoes-container'>
        <h2 className='recomendacaoes-title'> Recomendações </h2>
        <div className='recomendacaoes-cards-wrapper'>
          {

            hotelPorCidade ?
              (
                hotelPorCidade.map(item => {
                  const facilities = [<PoolIcon />, <WifiIcon />]
                  const { id_produtos, nome, descricao, categoriasEntity, pontuacao, comentarios, localMapa, distancia, imagensEntityList } = item
                  return (
                    <RecomendacoeCard key={id_produtos}
                      id={id_produtos}
                      image={imagensEntityList[0].url}
                      alt={nome}
                      type={categoriasEntity.descricao}
                      title={nome}
                      puntaje={pontuacao}
                      distancia={distancia}
                      mapLink={localMapa}
                      comment={comentarios}
                      facilities={facilities}
                      description={descricao}
                    />
                  )
                })
              )

              :

              newProduto.map(item => {
                const facilities = [<PoolIcon />, <WifiIcon />]
                const { id_produtos, nome, descricao, categoriasEntity, pontuacao, comentarios, localMapa, distancia, imagensEntityList } = item
                return (
                  <RecomendacoeCard key={id_produtos}
                    id={id_produtos}
                    image={imagensEntityList[0].url}
                    alt={nome}
                    type={categoriasEntity.descricao}
                    title={nome}
                    puntaje={pontuacao}
                    distancia={distancia}
                    mapLink={localMapa}
                    comment={comentarios}
                    facilities={facilities}
                    description={descricao}
                  />
                )
              }
              )
          }
        </div>
      </div>
    </div>
  )
}