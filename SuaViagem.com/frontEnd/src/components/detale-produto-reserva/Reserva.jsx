import React, { useEffect, useState } from 'react'
import './styleReserva.css'
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import StarIcon from '@mui/icons-material/Star';
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import { data } from '../detale-produto/data'
import { useParams, Link } from 'react-router-dom';
import { ReservaSucesso } from './ReservaSucesso';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useDatas } from '../hooks/useDatas';
import CelebrationIcon from '@mui/icons-material/Celebration';
import DisabledByDefaultIcon from '@mui/icons-material/DisabledByDefault';
import PointOfSaleIcon from '@mui/icons-material/PointOfSale';
import SmokeFreeIcon from '@mui/icons-material/SmokeFree';
import GavelIcon from '@mui/icons-material/Gavel';
import { useToken } from '../hooks/useToken';
import Alert from '@mui/material/Alert';
import { format } from 'date-fns'
import PetsIcon from '@mui/icons-material/Pets';
import moment from 'moment';

export function Reserva() {

  const { startDate, endDate, changeStartDate, changeEndDate } = useDatas()
  const [produtoReserva, setProdutoReserva] = React.useState([])
  const { id } = useParams()
  const { token } = useToken()
  // const [checkin, setCheckin] = React.useState(new Date())
  // const [checkout, setCheckout] = React.useState(new Date())
  const [selectedValue, setSelectedValue] = useState(null);
  const stars = [<StarIcon fontSize='small' />, <StarIcon fontSize='small' />, <StarIcon fontSize='small' />, <StarIcon fontSize='small' />,]
  const [confirm, setConfirm] = React.useState(false)
  const [secondError, setSecondError] = useState(false)
  const [thirdError, setThirdError] = useState(false)
  const [error, setError] = useState(false)
  const [cidade, setCidade] = React.useState('')
  const [idUser, setIdUser] = useState('')
  const [userData, setUserData] = useState({
    nome: '',
    sobreNome: '',
    email: '',
    cidade: '',
    id_funcoes: ''
  })


  const [tudasDataDisponivel, setTudasDataDisponivel] = useState([])

  const allDatas = tudasDataDisponivel.map((data, index) => {
    const { dataInicial, dataFinal } = data

    // let start = new Date(`${dataInicial}`)
    // let end = new Date(`${dataFinal}`)



    // let date1 = start.getDate()
    // let date2 = end.getDate()

    // let date1 = dataInicial.slice(0,10)
    // let date2 = dataFinal.slice(0,10)

    var getDaysArray = function (start, end) {
      for (var arr = [], dt = new Date(start); dt <= new Date(end); dt.setDate(dt.getDate() + 1)) {
        arr.push(new Date(dt));
      }
      return arr;
    };

    const arrayDeDatas = getDaysArray(new Date(`${dataInicial}`), new Date(`${dataFinal}`));
    const resultadoFinalDeDatas = arrayDeDatas.map((v) => v.toISOString().slice(0, 10))
    // let fullDates = []

    // for (let i = date1; i <= date2; i++) {
    //   fullDates.push(i)
    // }

    return resultadoFinalDeDatas

  })

  const datasIndisponivel = allDatas.flat(1)

  const datasIndisponivelSemDuplication = [...new Set(datasIndisponivel)]

  const tokenLocalStorage = localStorage.getItem('token')

  function handleDateChange(value) {
    changeStartDate(value[0])
    changeEndDate(value[1])

  }

  function handleAutocompleteChange(event, value) {
    setSelectedValue(value.label);
  }

  React.useEffect(() => {
    async function fetchData() {

      const response = await fetch(`http://3.142.238.11:8081/product/${id}`)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json()
      setCidade(data.cidadesEntity.nome)
      setProdutoReserva(data)
      setTudasDataDisponivel(data.reservasEntity)

    }
    fetchData()

    async function fetchUserData() {
      try {
        const response = await fetch(`http://3.142.238.11:8081/user/${token || tokenLocalStorage}`);
        const userData = await response.json();

        // Atualiza os valores dos inputs com os dados da resposta
        setUserData({
          nome: userData.nome,
          sobreNome: userData.sobrenome,
          email: userData.email,
          id_funcoes: userData.funcoesEntity.id_funcoes
        });
        setIdUser(userData.id)

      } catch (error) {
        console.error(error);
      }
    }
    fetchUserData()
  }, [id, userData.id]);


  console.log(userData.id_funcoes)

  function handleReserva() {

    // nova variável criada newStartDate para "corrigir" a divergência no fuso horário do RDS na AWS //
    const newStartDate = new Date(startDate);
    newStartDate.setDate(startDate.getDate() + 1);

    if (userData.id_funcoes === 1) {
      setThirdError(true)
    } else if (startDate === "" && endDate === "" || !selectedValue) {
      setSecondError(true)
    } else {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token || tokenLocalStorage}` },
        body: JSON.stringify({
          horaInicial: selectedValue,
          dataInicial: newStartDate,
          dataFinal: endDate,
          idUser: idUser,
          produtosEntity: {
            id_produtos: id
          }
        })
      };

      fetch('http://3.142.238.11:8081/reservas/register', requestOptions)
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          changeEndDate(null)
          changeStartDate(null)
          setConfirm(true)
        })
        .catch(error => {
          setError(true)
        });
    }

  }

  const { imagensEntityList, nome, cidadesEntity, categoriasEntity, normasCasa, politicasCancelamento, saudeSegurança } = produtoReserva
  const imageUrl = imagensEntityList?.map(img => img.url)

  function handleChange(event) {
    const { name, value } = event.target
    setUserData(prevFormData => {
      return {
        ...prevFormData,
        [name]: value
      }
    });
  }

  return (

    <div >
      <div className='reserva-container-header'>
        <div>
          <span>{categoriasEntity?.descricao}</span>
          <h3>{nome}</h3>
        </div>
        <Link to={`/detaile-produto/${id}`}><ArrowBackIosIcon className='logo-header' /></Link>
      </div>
      <h1 className='title-service'>Complete seus dados</h1>

      <div className='reserva-container'>

        <div className='produto-logado-wrapper'>

          <label htmlFor='name'>Nome:
            <input
              className='input disabled'
              required
              id="name"
              size="small"
              type="text"
              name='nome'
              value={userData.nome}
              onChange={handleChange}
              disabled
            />
          </label>

          <label htmlFor='sobreNome'>Sobrenome:
            <input
              className='input disabled'
              required
              id="sobreNome"
              name='sobreNome'
              type="text"
              value={userData.sobreNome}
              onChange={handleChange}
              size="small"
              disabled
            />
          </label>

          <label htmlFor='sobreNome'>Email:
            <input
              className='input disabled'
              required
              id="sobreNome"
              name='email'
              type="text"
              value={userData.email}
              onChange={handleChange}
              size="small"
              disabled
            />
          </label>
          <label htmlFor='sobreNome'>Cidade:
            <input
              className='input'
              required
              id="sobreNome"
              name='cidade'
              type="text"
              value={cidade}
              onChange={handleChange}
              size="small"
            />
          </label>

        </div>
        <div>
          <div>
            <div className='calendario-reserva'>
              <h1 className='calendario-title'>Selecione sua data de reserva</h1>
              <div className='double-calender'>
                <Calendar
                  locale="pt-Br"
                  onChange={handleDateChange}
                  minDate={new Date()} // Adicione esta linha para desabilitar datas anteriores à data atual
                  showDoubleView
                  selectRange
                  prev2Label={null}
                  next2Label={null}
                  tileDisabled={({ date }) => {
                    // let currDate = date.getDate()
                    // return [].indexOf(currDate) !== -1
                    var currDate = date.toISOString().slice(0, 10);

                    return datasIndisponivelSemDuplication.indexOf(currDate) !== -1;
                  }
                  }
                />
              </div>
              <div className='single-calender'>
                <Calendar
                  locale="pt-Br"
                  onChange={handleDateChange}
                  minDate={new Date()} // Adicione esta linha para desabilitar datas anteriores à data atual
                  selectRange
                  prev2Label={null}
                  next2Label={null}
                  tileDisabled={({ date }) => {
                    // let currDate = date.getDate()
                    // return [].indexOf(currDate) !== -1
                    var currDate = date.toISOString().slice(0, 10);

                    return datasIndisponivelSemDuplication.indexOf(currDate) !== -1;
                  }
                  }
                />
              </div>
            </div>
          </div>
        </div>
        <div className='horas-wrapper' >
          <div className='horas-chegada'>
            <div>
              <CheckCircleOutlineIcon />
              <span>Seu quarto estará pronto para check-in entre 10h00 e 23h00</span>
            </div>
            <div>
              <p >Indique a sua hora prevista de chegada</p>
            </div>
            <Autocomplete

              sx={{}}
              size="small"

              id="combo-box-demo"
              options={data}
              onChange={handleAutocompleteChange}
              renderInput={(params) => <TextField {...params} label="Selecione a sua hora de chegada" />}
            />
          </div>

        </div>
        <div className='reserva-card'>

          <div>
            <h4 className='reserva-header-title'>Detalhes da reserva</h4>
            <img className='reserva-image' src={imageUrl} alt='detale reserva' />
          </div>
          <div className='reserva-body'>
            <p className='reserva-type'>{categoriasEntity?.descricao}</p>
            <p className='reserva-title'>{nome}</p>
            {
              stars.map((star, index) =>
                <span className='reserva-stars' key={index}>{star}</span>
              )
            }
            <div >
              <LocationOnIcon fontSize='small' />
              <span className='reserva-location'>{cidadesEntity?.nome} </span>
            </div>
            <div className='reserva-underline' ></div>
            <div className='reserva-data'>
              <p>check in</p>
              {
                startDate ?
                  <p>{format(new Date(`${startDate}`), 'dd/MM/yyyy')}</p>
                  :
                  <p> </p>
              }

            </div>
            <div className='reserva-underline' ></div>
            <div className='reserva-data'>
              <p>check out</p>

              {
                endDate ?
                  <p>{format(new Date(`${endDate}`), 'dd/MM/yyyy')}</p>
                  :
                  <p> </p>
              }

            </div>
            <div className='reserva-underline' ></div>


            <button className='reserva-btn' onClick={handleReserva}>Confirmar reserva</button>

          </div>

        </div>
        <div className='error-message'>
          {error && <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">Infelizmente a reserva não pôde ser feita. Por favor, tente novamente mais tarde.</Alert>}
          {secondError && <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">Necessário escolher uma data inicial/final e indicar o horário de chegada.</Alert>}
          {thirdError && <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">Desculpe, o usuário ADM não pode realizar reservas. Por gentileza utilize um usuário comum.</Alert>}
        </div>

      </div>


      <h1 className='title-service'>O que voce precisa saber</h1>
      <div className='title-underline'></div>

      <div className='detail-card-container'>

        <div className='detail-card'>

          <h4 className='detail-card-title' > Regras da casa</h4>
          <p>{normasCasa}</p>

        </div>

        <div className='detail-card'>

          <h4 className='detail-card-title' >Saúde e segurança</h4>
          <p>{politicasCancelamento}</p>
        </div>

        <div className='detail-card'>

          <h4 className='detail-card-title' >Políticas de cancelamento</h4>
          <p>{saudeSegurança}</p>
        </div>


      </div>

      {confirm && <ReservaSucesso message={'Sua reserva foi feita com sucesso'} link="/" />}

    </div>
  )
}