import React from 'react'
import './styleCalendario.css'
import Calendar from 'react-calendar';
import { Link, useParams} from 'react-router-dom'
import Alert from '@mui/material/Alert';
import 'react-calendar/dist/Calendar.css';
import { useToken } from '../hooks/useToken';
import { useDatas } from '../hooks/useDatas';
import { data } from './data';

export function Calendario({ id }) {

  const tokenLocalStorage = localStorage.getItem('token')

  const { startDate, endDate, changeStartDate, changeEndDate} = useDatas()

  const [tudasDataDisponivel, setTudasDataDisponivel] = React.useState([])

 
  const allDatas = tudasDataDisponivel.map((data, index) => {
    const {dataInicial,dataFinal } = data
  


    // let start = new Date(`${dataInicial}`)
    // let end = new Date(`${dataFinal}`)

   
     
    // let date1 = start.getDate()
    // let date2 = end.getDate()

    // let date1 = dataInicial.slice(0,10)
    // let date2 = dataFinal.slice(0,10)

    var getDaysArray = function(start, end) {
      for(var arr=[],dt=new Date(start); dt<=new Date(end); dt.setDate(dt.getDate()+1)){
          arr.push(new Date(dt));
      }
      return arr;
  };

   const arrayDeDatas = getDaysArray(new Date(`${dataInicial}`),new Date(`${dataFinal}`));



   const resultadoFinalDeDatas = arrayDeDatas.map((v)=>v.toISOString().slice(0,10))
   
    // let fullDates = []
    
    // for (let i = date1; i <= date2; i++) {
    //   fullDates.push(i)
    // }

    return resultadoFinalDeDatas
  
  })

 
  const datasIndisponivel = allDatas.flat(1)

  const datasIndisponivelSemDuplication = [...new Set(datasIndisponivel)]



  React.useEffect(() => {
    async function fetchData() {

      const response = await fetch(`http://3.142.238.11:8081/product/${id}`)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json()
 
      setTudasDataDisponivel(data?.reservasEntity)
      
  
    }
    fetchData()

  },[id])

  function handleDateChange(value){
    changeStartDate(value[0])
    changeEndDate(value[1])
  }

  return (
    <div className='calendario-container'>
      <h1 className='calendario-title'>Datas Disponíveis</h1>
      <div className='calendario-wrapper'>
        <div className='calendario'>
          <div className='double-calender'>
            <Calendar
              locale = "pt-Br"
              onChange={handleDateChange}
        
              showDoubleView
              minDate={new Date()} // Adicione esta linha para desabilitar datas anteriores à data atual
              selectRange
              prev2Label={null}
              next2Label={null}
              tileDisabled={({ date }) => {
                // let currDate = date.getDate()
                // return [].indexOf(currDate) !== -1
                var currDate = date.toISOString().slice(0,10);

                    return datasIndisponivelSemDuplication.indexOf(currDate) !== -1;
              }
            }
            />
          </div>
          <div className='single-calender'>
            <Calendar
            locale = "pt-Br"
              onChange={handleDateChange}
              minDate={new Date()} // Adicione esta linha para desabilitar datas anteriores à data atual
              selectRange
              prev2Label={null}
              next2Label={null}
              tileDisabled={({ date }) => {
                // let currDate = date.getDate()
                // return [].indexOf(currDate) !== -1
                var currDate = date.toISOString().slice(0,10);

                    return datasIndisponivelSemDuplication.indexOf(currDate) !== -1;
              }
            }
            />
          </div>
        </div>
        <div className='calendario-card'>
          <p>Adicione as datas da sua viagem</p>
          {
             typeof tokenLocalStorage === 'string' ?   <Link to={`reserva`} ><button className='calendario-btn' >Iniciar reserva</button></Link> : <Link to={`/iniciar-sessao`} ><button className='calendario-btn' >Iniciar reserva</button></Link> 
            
          }
        </div>
      </div>
      <div>
        { typeof tokenLocalStorage === 'string' ?  null : <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">Para fazer uma reserva você precisa estar logado</Alert> }
      </div>
    </div>
  )
}
