import React, { useRef, useState } from 'react'
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import { RecomendacoesCards } from '../recomendacoes/RecomendacoesCards'
import DateRangePicker from 'react-bootstrap-daterangepicker';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-daterangepicker/daterangepicker.css';
import { SuggestBox } from '../sugest-box/index';
import { useDatas } from '../hooks/useDatas';
import { useHotelFilterCidade } from '../hooks/useHotelFilterCidade'
import './styles.css'

export default function Main() {

  const [focus, setIsFocused] = useState(false)
  const [isHover, setIsHovered] = useState(false)
  const [isLoading, setIsLoading] = useState(false);
  const [produto, setProduto] = useState(false)
  const [scrollEvent, setScrollEvent] = useState()
  const [eventType, setEventType] = useState()
  const [valor, setValor] = useState('');
  const inputRef = useRef()
  const [cidadePorFiltro, setCidadePorFiltro] = useState(null)
  const [visible, setVisible] = useState(false)

  function handleLimparFiltro(event) {
    event.preventDefault()
    changeCidadeValue('');
    changeHotelPorCidade(null);
  }

  function closeMenu() {
    setVisible(!visible)
  }

  let prevScrollpos = window.pageYOffset;

  // console.log(prevScrollpos)
  window.onscroll = function () {
    let currentScrollPos = window.pageYOffset;
    // console.log(currentScrollPos)
    if (prevScrollpos > currentScrollPos) {
      // console.log('hello')
    }
    prevScrollpos = currentScrollPos;
  }


  window.addEventListener("scroll", closeMenu);

  const handleApply = (event, picker) => {
    picker.element.val(
      picker.startDate.format('DD/MM/YYYY') +
      ' - ' +
      picker.endDate.format('DD/MM/YYYY')
    );
  };

  const handleCancel = (event, picker) => {
    picker.element.val('');
    changeStartDate(null);
    changeEndDate(null);
  };

  /* console.log(eventType) */
  const { hotelPorCidade, changeHotelPorCidade } = useHotelFilterCidade()

  // console.log(hotelPorCidade)

  const { startDate, endDate, cidadeValue, changeStartDate, changeEndDate, changeCidadeValue } = useDatas()

  React.useEffect(() => {
    setIsLoading(true)
    async function fetchData() {

      const response = await fetch(`http://3.142.238.11:8081/cities/findAll`)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json()

      setProduto(data)
      setIsLoading(false)
    }
    fetchData()
  }, []);

  // const formattedStartDate = moment(startDate, 'DD/MM/YYYY').format('YYYY-MM-DD');
  // const formattedEndDate = moment(endDate, 'DD/MM/YYYY').format('YYYY-MM-DD');

  const urlCidadeData = `http://3.142.238.11:8081/product/findByCidadeAndDatas?cidade=${cidadeValue}&dataInicial=${startDate}&dataFinal=${endDate}`
  const urlCidade = `http://3.142.238.11:8081/product/findByCidades/${cidadeValue}`

  async function handleSearch(event) {

    event.preventDefault();


    const response = await fetch(startDate && endDate && cidadeValue ? urlCidadeData : urlCidade);

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();

    changeHotelPorCidade(data)
  };

  // console.log(valor)

  function handleScroll() {
    if ((scrollEvent) && (eventType == 'show')) {
      setValor('date-range-picker')
    }
    else {

      setValor('')
    }
  }

  return (
    <main className='app-main' onScroll={event => {
      setScrollEvent(event)
      handleScroll()
    }}>
      <div className='searchBox-container'>

        <h2 className='searchBox-title'>Buscar ofertas em hotéis, casas e muito mais!</h2>

        <form className='searchBox-form'>
          <div className='searchSuggestBox-div'>

            <TextField className='location-input' type="search" placeholder='Onde Vamos?'
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    {
                      isLoading ?
                        <div className="loading"></div> :
                        <LocationOnIcon />
                    }
                  </InputAdornment>
                ),
                disabled: isLoading // desativa o input enquanto isLoading for true
              }}
              size='small'
              onFocus={() => setIsFocused(true)}
              onBlur={() => isHover ? "" : setIsFocused(false)}
              value={cidadeValue}
              onChange={c => changeCidadeValue(c.target.value)}
              ref={inputRef}
            />
            {focus && (
              <ul className='suggestBox-input'
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
              >
                {produto.slice(0, 10).map((p, i) => {
                  const isMatch = p.nome.toLowerCase().indexOf(cidadeValue.toLowerCase()) > -1
                  return (
                    <li key={i} onClick={() => {
                      changeCidadeValue(p.nome)
                      inputRef.current.focus()
                      setIsFocused(false)
                    }}>
                      {isMatch && (<SuggestBox produto={p} />)}
                    </li>
                  )
                })}
              </ul>
            )}
          </div>

          <DateRangePicker



            onEvent={event => setEventType(event.type)}

            onCancel={handleCancel}

            initialSettings={{
              applyClass: valor,
              autoUpdateInput: false,
              minDate: new Date(),
              //nós precisamos desse código não deleta por favor

              //   isInvalidDate: function(data){
              //     var currDate = moment(data._d).format('YY-MM-DD');
              //     return ["23-03-26" , "23-03-28"].indexOf(currDate) !== -1;
              // },
              locale: {

                "format": "DD/MM/YYYY",
                "separator": " - ",
                "applyLabel": "Aplicar",
                "cancelLabel": "Cancelar",
                "fromLabel": "From",
                "toLabel": "To",
                "customRangeLabel": "Custom",
                "weekLabel": "W",
                "daysOfWeek": [
                  "Dom",
                  "Seg",
                  "Ter",
                  "Qua",
                  "Qui",
                  "Sex",
                  "Sáb"
                ],
                "monthNames": [
                  "Janeiro",
                  "Fevereiro",
                  "Março",
                  "Abril",
                  "Maio",
                  "Junho",
                  "Julho",
                  "Agosto",
                  "Setembro",
                  "Outubro",
                  "Novembro",
                  "Dezembro"
                ],
                "firstDay": 1

              }
            }}
            placeholder="check in check out"
            onApply={(event, picker) => {
              changeStartDate(picker.startDate.format('YYYY-MM-DD'), changeEndDate(picker.endDate.format('YYYY-MM-DD')))
              handleApply(event, picker)
            }

            }
          >
            <input type="text" className="form-control" placeholder="check in check out" defaultValue="" />
          </DateRangePicker>

          <button onClick={handleSearch} className='searchBox-btn'>Buscar</button>
          {hotelPorCidade ?
            <button className='searchBox-btn' onClick={(event) => {handleLimparFiltro(event)}}>Limpar filtro</button> : null
          }
        </form>

      </div>

      <RecomendacoesCards />

    </main>
  )
}