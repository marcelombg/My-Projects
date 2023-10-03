import { useState } from "react"
import { createContext, useContext } from "react"

// Ciração do Contexto
const DetalesUsuarioContext = createContext()

// Criação do Provedor para o Contexto
export function DetalesUsuarioProvider(props) {

    // State que irá controlar qual Tema a aplicação está usando
    const [userData, setUserData] = useState({})

    // Função responsável por Trocar o Tema

    function changeUserData(startdate) {
        setUserData(startdate)
    }


    return(

        // Construção dos Elementos para utilizarmos o Contexto em nossa Aplicação, tudo o que for contido no "value" será exportado e poderá ser utilizado em Componentes que utilizarem o Hook Customizado "useTheme"
        <DetalesUsuarioContext.Provider value={{userData, changeUserData}}>
            { props.children }
        </DetalesUsuarioContext.Provider>

    )

}

// Hook Personalizado que irá ser utilizado quando quisermos utilizar alguma das Funcionalidades contidas em nosso Contexto
export function useDetalesUsuario() {

    const context = useContext(DetalesUsuarioContext)

    return context

}