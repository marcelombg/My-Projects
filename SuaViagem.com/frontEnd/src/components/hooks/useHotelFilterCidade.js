import { useState } from "react"
import { createContext, useContext } from "react"

// Ciração do Contexto
const HotelFilterCidadeContext = createContext()

// Criação do Provedor para o Contexto
export function HotelFilterCidadeProvider(props) {

    // State que irá controlar qual Tema a aplicação está usando
    const [hotelPorCidade, setHotelPorCidade] = useState(null)

    // Função responsável por Trocar o Tema

    function changeHotelPorCidade(startdate) {
        setHotelPorCidade(startdate)
    }


    return(

        // Construção dos Elementos para utilizarmos o Contexto em nossa Aplicação, tudo o que for contido no "value" será exportado e poderá ser utilizado em Componentes que utilizarem o Hook Customizado "useTheme"
        <HotelFilterCidadeContext.Provider value={{hotelPorCidade, changeHotelPorCidade}}>
            { props.children }
        </HotelFilterCidadeContext.Provider>

    )

}

// Hook Personalizado que irá ser utilizado quando quisermos utilizar alguma das Funcionalidades contidas em nosso Contexto
export function useHotelFilterCidade() {

    const context = useContext(HotelFilterCidadeContext)

    return context

}