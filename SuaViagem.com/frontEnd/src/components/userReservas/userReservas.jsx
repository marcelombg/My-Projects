import React, { useEffect, useState } from 'react'
import { useToken } from '../hooks/useToken';
import { Link } from 'react-router-dom';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useParams } from 'react-router-dom'
import './styles.css'
import { useLogin } from '../hooks/useLogin'
import { CardProdutoReserva } from '../card-produto-reserva/cardProdutoReserva';
import ContentPasteOffIcon from '@mui/icons-material/ContentPasteOff';

export function UserReservas() {

    const { token, changeToken } = useToken()
    const [reservas, changeReservas] = useState()
    const [produtos, changeProdutos] = useState()
    const { login } = useLogin()
    const tokenLocalStorage = localStorage.getItem('token')
    const { idUser } = useParams()
    const [isLoading, setIsLoading] = useState(false);

    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token || tokenLocalStorage}` }
    }

    //Usado para testar o if no lugar de reservas.
    const teste = ''

    useEffect(() => {
        async function fetchReservasData() {
            setIsLoading(true); // Adicione essa linha para indicar que os dados estão sendo carregados
            const response = await fetch(`http://3.142.238.11:8081/reservas/findByIdUser/${idUser}`, requestOptions);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json()
  
            changeReservas(data)
            setIsLoading(false)
        }
        fetchReservasData()

        async function fetchProdutosData() {
            setIsLoading(true)
            const response = await fetch(`http://3.142.238.11:8081/product/findAll`)

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json()
         
            changeProdutos(data)
            setIsLoading(false)
        }
        fetchProdutosData()
    }, [idUser]);

    function dadosReserva(dados) {
        const lista = []
        for (let i = 0; i < dados.reservasEntity.length; i++) {

            const element = dados.reservasEntity[i];

            if (element.idUser == idUser) {

                lista.push({
                    nome: dados.nome,
                    img: dados.imagensEntityList[0].url,
                    dataInicial: element.dataInicial,
                    dataFinal: element.dataFinal,
                    horaEntrada: element.horaInicial
                })
            }
        }
        return lista
    }

    return (
        <main className='app-main'>
            <div className='searchBox-container' >
                <div className='history-reserva-container'>

                    <h2 className='history-reserva-title'>Minhas Reservas</h2>
                    <Link to="/"><ArrowBackIosIcon className='logo-header' /></Link>
                </div>

            </div>
            <div className='history-reserva-fully-container'>
                {isLoading ? (
                    <div>Carregando...</div>
                ) : (
                    reservas ? (
                        produtos?.map((item) => {
                            const lista = dadosReserva(item)
                       
                            return lista.map((item2, index) => (
                                <CardProdutoReserva key={index} dados={item2} />
                            ))
                        })
                    ) : (
                        <div className={"sem-reservas"}>
                            <ContentPasteOffIcon className={"sem-reservas-icon"} />
                            <Link className={'sem-reservas-link'} to={"/"}>Volte para a página inicial</Link>
                            <h5>Você ainda não fez nenhuma reserva</h5>
                        </div>
                    )
                )}
            </div>
        </main>
    )
}